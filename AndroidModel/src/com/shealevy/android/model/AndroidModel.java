package com.shealevy.android.model;

import java.util.HashMap;

import android.content.ContentResolver;

public class AndroidModel<T> {
	private HashMap<TableDelegateField<T,?>, Object> params = new HashMap<TableDelegateField<T,?>, Object>();
	
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
