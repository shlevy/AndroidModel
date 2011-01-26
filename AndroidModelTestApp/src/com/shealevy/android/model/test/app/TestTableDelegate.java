package com.shealevy.android.model.test.app;

import android.net.Uri;

import com.shealevy.android.model.TableDelegate;
import com.shealevy.android.model.TableDelegateField;

public class TestTableDelegate extends TableDelegate {
	public static class Field<T> extends TableDelegateField<TestTableDelegate, T> {
		public static final Field<Integer> ID = new Field<Integer>("_id", int.class, 1);
		public static final Field<String> NAME = new Field<String>("name", String.class, "Test");
		
		private Field(String value, Class<T> type, T defaultValue) {
			super(value, type, defaultValue);
		}
		
		public Field<?>[] getFields() {
			return new Field<?>[] {ID, NAME};
		}
	}
	
	public Uri getUri() {
		return Uri.parse("content://test/table");
	}
}
