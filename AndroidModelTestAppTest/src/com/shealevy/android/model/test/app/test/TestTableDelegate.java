package com.shealevy.android.model.test.app.test;

import com.shealevy.android.model.TableDelegate;
import com.shealevy.android.model.TableDelegateField;

public class TestTableDelegate extends TableDelegate {
	public enum Field implements TableDelegateField<TestTableDelegate> {
		ID("_id", int.class, 1),
		NAME("name", String.class, "Test");
		
		private String value;
		private Class<?> type;
		private Object defaultValue;
		
		private Field(String value, Class<?> type, Object defaultValue) {
			this.value=value;
			this.type=type;
			this.defaultValue=defaultValue;
		}
		
		public Class<?> type() {
			return type;
		}
		
		public String value() {
			return value;
		}
		
		public Object defaultValue() {
			return defaultValue;
		}
	}
}
