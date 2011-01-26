package com.shealevy.android.model.test.app;

import android.net.Uri;

import com.shealevy.android.model.TableDelegate;
import com.shealevy.android.model.TableDelegateField;

public class TestTableDelegate extends TableDelegate {
	public static class Field<T> extends TableDelegateField<TestTableDelegate, T> {
		public static final Field<Integer> ID = new Field<Integer>("_id", 1);
		public static final Field<String> NAME = new Field<String>("name", "Test");
		
		private Field(String value, T defaultValue) {
			super(value, defaultValue);
		}
		
		public Uri getUri() {
			return Uri.parse("content://test/table");
		}
	}
}
