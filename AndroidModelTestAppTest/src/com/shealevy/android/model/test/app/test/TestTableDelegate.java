package com.shealevy.android.model.test.app.test;

import com.shealevy.android.model.TableDelegate;
import com.shealevy.android.model.TableDelegateField;

public class TestTableDelegate extends TableDelegate {
	public enum Field implements TableDelegateField<TestTableDelegate> {
		ID("_id", int.class),
		NAME("name", String.class),
		TEST_BOOLEAN("boolean", boolean.class),
		TEST_BYTE("byte", byte.class),
		TEST_SHORT("short", short.class),
		TEST_LONG("long", long.class),
		TEST_FLOAT("float", float.class),
		TEST_DOUBLE("double", double.class),
		TEST_CHAR("char", char.class);
		
		private String value;
		private Class<?> type;
		private Field(String value, Class<?> type) {
			this.value=value;
			this.type=type;
		}
		
		public Class<?> type() {
			return type;
		}
		
		public String value() {
			return value;
		}
	}
}
