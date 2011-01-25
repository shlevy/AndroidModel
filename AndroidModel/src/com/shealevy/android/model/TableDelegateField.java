package com.shealevy.android.model;

public class TableDelegateField<T, U> {
	private U defaultValue;
	protected TableDelegateField(String value, U defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public U defaultValue() {
		return defaultValue;
	}
}
