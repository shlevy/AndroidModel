package com.shealevy.android.model;

public abstract class TableDelegateField<T, U> {
	private U defaultValue;
	protected TableDelegateField(String name, U defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public U defaultValue() {
		return defaultValue;
	}
}
