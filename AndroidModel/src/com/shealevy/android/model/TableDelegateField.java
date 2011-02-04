package com.shealevy.android.model;

public abstract class TableDelegateField<T, U> {
	private U defaultValue;
	private Class<U> type;

	protected TableDelegateField(String name, Class<U> type, U defaultValue) {
		this.defaultValue = defaultValue;
		this.type = type;
	}

	public U defaultValue() {
		return defaultValue;
	}

	public abstract TableDelegateField<T, ?>[] getFields();

	public String name() {
		return null;
	}

	public Class<U> type() {
		return type;
	}
}
