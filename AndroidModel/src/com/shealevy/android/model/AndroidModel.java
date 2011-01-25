package com.shealevy.android.model;

public class AndroidModel<T> {
	public <U> U get(TableDelegateField<T, U> field) {
		return field.defaultValue();
	}
	
	public <U> void set(TableDelegateField<T, U> field, U value) {
		
	}
}
