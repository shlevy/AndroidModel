package com.shealevy.android.model.test.app.test;

import com.shealevy.android.model.TableDelegate;
import com.shealevy.android.model.TableDelegateField;

public class TestTableDelegate extends TableDelegate {
	public class Field<T> implements TableDelegateField<TestTableDelegate, T> {
		public static Field<Integer> ID = new Field<Integer>("_id", 1);
		public static Field<String> NAME = new Field<String>("name", "Test");
		
		private String value;
		private T defaultValue;
		
		private Field(String value, T defaultValue) {
			this.value=value;
			this.defaultValue=defaultValue;
		}
		
		public Class<?> type() {
			return defaultValue.getClass();
		}
		
		public String value() {
			return value;
		}
		
		public Object defaultValue() {
			return defaultValue;
		}
	}
}
