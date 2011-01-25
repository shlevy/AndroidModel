package com.shealevy.android.model;

public class AndroidModel<T> {
	public Object get(TableDelegateField<T> field) {
		return field.defaultValue();
	}
}
