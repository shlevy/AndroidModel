package com.shealevy.android.model;

import java.util.HashMap;

import android.content.ContentResolver;

public class AndroidModel<T> {
	private HashMap<TableDelegateField<T,?>, Object> params = new HashMap<TableDelegateField<T,?>, Object>();
	private T tableDelegate;
	
	public AndroidModel(Class<T> tClass) {
		try {
			tableDelegate = tClass.newInstance();
		} catch (IllegalAccessException e) {
			// The class doesn't have a public constructor. Wrap as runtime exception, hope this never happens
			// Commented out until a feature can be written for this
			// throw new RuntimeException(e); 
		} catch (InstantiationException e) {
			// The class is abstract or an interface. Wrap as runtime exception, hope this never happens
			// Commented out until a feature can be written for this
			//throw new RuntimeException(e);
		}
	}
	
	public void setTableDelegate(T tableDelegate) {
		
	}
	
	public T getTableDelegate() {
		return tableDelegate;
	}
	
	@SuppressWarnings("unchecked")
	public <U> U get(TableDelegateField<T, U> field) {
		if(params.containsKey(field)) {
			return (U) params.get(field);
		}
		return field.defaultValue();
	}
	
	public <U> void set(TableDelegateField<T, U> field, U value) {
		params.put(field, value);
	}
	
	public void load(ContentResolver cr) {
		
	}
}
