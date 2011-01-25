package com.shealevy.android.model;

public class AndroidModel<T> {
	private String params;
	public <U> U get(TableDelegateField<T, U> field) {
		if(params != null)
			return (U) params;
		return field.defaultValue();
	}
	
	public <U> void set(TableDelegateField<T, U> field, U value) {
		params = "SecondTest";
	}
}
