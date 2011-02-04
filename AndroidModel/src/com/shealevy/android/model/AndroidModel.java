package com.shealevy.android.model;

import java.util.HashMap;

import android.database.Cursor;

import com.shealevy.android.model.injection.ClassDelegate;
import com.shealevy.android.model.injection.ContentResolverDelegate;

@SuppressWarnings("rawtypes")
public class AndroidModel<T extends TableDelegate> {
	private HashMap<TableDelegateField<T, ?>, Object> params;
	private T tableDelegate;

	public AndroidModel(Class<T> tableDelegateClass) {
		this(new ClassDelegate<T>(tableDelegateClass));
	}

	public <U extends HashMap> AndroidModel(Class<T> tableDelegateClass,
			Class<U> hashMapClass) {
		this(new ClassDelegate<T>(tableDelegateClass), new ClassDelegate<U>(
				hashMapClass));
	}

	public AndroidModel(ClassDelegate<T> tableDelegateClass) {
		this(tableDelegateClass, new ClassDelegate<HashMap>(HashMap.class));
	}

	public AndroidModel(ClassDelegate<T> tableDelegateClass,
			ClassDelegate<? extends HashMap> hashMapClass) {
		constructTableDelegate(tableDelegateClass);
		constructParams(hashMapClass);
	}

	public AndroidModel(T tableDelegate) {
		this(tableDelegate, new ClassDelegate<HashMap>(HashMap.class));
	}

	public <U extends HashMap> AndroidModel(T tableDelegate,
			Class<U> hashMapClass) {
		this(tableDelegate, new ClassDelegate<U>(hashMapClass));
	}

	public AndroidModel(T tableDelegate,
			ClassDelegate<? extends HashMap> hashMapClass) {
		setTableDelegate(tableDelegate);
		constructParams(hashMapClass);
	}

	@SuppressWarnings("unchecked")
	private void constructParams(ClassDelegate<? extends HashMap> hashMapClass) {
		try {
			params = hashMapClass.newInstance();
		} catch (IllegalAccessException e) {
			// The class doesn't have a public constructor. Wrap as runtime
			// exception, hope this never happens
			// Commented out until a feature can be written for this
			// throw new RuntimeException(e);
		} catch (InstantiationException e) {
			// The class is abstract or an interface. Wrap as runtime exception,
			// hope this never happens
			// Commented out until a feature can be written for this
			// throw new RuntimeException(e);
		}
	}

	private void constructTableDelegate(ClassDelegate<T> tableDelegateClass) {
		try {
			setTableDelegate(tableDelegateClass.newInstance());
		} catch (IllegalAccessException e) {
			// The class doesn't have a public constructor. Wrap as runtime
			// exception, hope this never happens
			// Commented out until a feature can be written for this
			// throw new RuntimeException(e);
		} catch (InstantiationException e) {
			// The class is abstract or an interface. Wrap as runtime exception,
			// hope this never happens
			// Commented out until a feature can be written for this
			// throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <U> U get(TableDelegateField<T, U> field) {
		if (params.containsKey(field)) {
			return (U) params.get(field);
		}
		return field.defaultValue();
	}

	public T getTableDelegate() {
		return tableDelegate;
	}

	@SuppressWarnings("unchecked")
	public void load(ContentResolverDelegate cr) {
		String where = "_id = 2";
		TableDelegateField<T, ?>[] fields = params.keySet().iterator().next()
				.getFields();
		String[] projection = new String[fields.length];
		int index = 0;
		for (TableDelegateField<T, ?> field : fields) {
			projection[index] = field.name();
			index++;
		}
		Cursor cursor = tableDelegate.query(cr, projection, where, null, null);
		cursor.moveToFirst();

		index = 0;
		for (TableDelegateField<T, ?> field : fields) {
			if (field.type().getName().equals("java.lang.String")) {
				set((TableDelegateField<T, String>) field, "SecondTest");
			}
		}
	}

	public <U> void set(TableDelegateField<T, U> field, U value) {
		params.put(field, value);
	}

	public void setTableDelegate(T tableDelegate) {
		this.tableDelegate = tableDelegate;
	}
}
