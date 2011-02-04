package com.shealevy.android.model;

public abstract class TableDelegateField<T, U> {
	private U defaultValue;
	private Class<U> type;
	private String name;

	protected TableDelegateField(String name, Class<U> type, U defaultValue) {
		this.defaultValue = defaultValue;
		this.type = type;
		this.name = name;
	}

	public U defaultValue() {
		return defaultValue;
	}

	public abstract TableDelegateField<T, ?>[] getFields();

	public String name() {
		return name;
	}

	public Class<U> type() {
		return type;
	}
}
