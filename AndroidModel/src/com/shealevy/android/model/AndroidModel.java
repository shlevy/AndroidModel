package com.shealevy.android.model;

import java.util.HashMap;

import com.shealevy.android.model.injection.ClassDelegate;

import android.content.ContentResolver;
import android.database.Cursor;

public class AndroidModel<T extends TableDelegate> {
	private HashMap<TableDelegateField<T, ?>, Object> params = new HashMap<TableDelegateField<T, ?>, Object>();
	private T tableDelegate;

	public AndroidModel(Class<T> tableDelegateClass) {
		this(new ClassDelegate<T>(tableDelegateClass));
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AndroidModel(Class<T> tableDelegateClass, Class<? extends HashMap> hashMapClass) {
		this(tableDelegateClass);
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

	public AndroidModel(ClassDelegate<T> tableDelegateClass) {
		constructTableDelegate(tableDelegateClass);
	}
	
	@SuppressWarnings("rawtypes")
	public AndroidModel(ClassDelegate<T> tableDelegateClass, ClassDelegate<? extends HashMap> hashMapClass) {
		this(tableDelegateClass);
		constructParams(hashMapClass);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
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

	public AndroidModel(T tableDelegate) {
		setTableDelegate(tableDelegate);
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
	public void load(ContentResolver cr) {
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
