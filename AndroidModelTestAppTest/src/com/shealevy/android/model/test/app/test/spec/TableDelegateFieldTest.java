package com.shealevy.android.model.test.app.test.spec;

import com.shealevy.android.model.TableDelegateField;
import com.shealevy.android.model.test.app.test.TestTableDelegate;

import android.test.AndroidTestCase;

public class TableDelegateFieldTest extends AndroidTestCase {
	public TableDelegateFieldTest() {
		super();
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	// Describe defaultValue()
	public void testItReturnsStringSetAtConstruction() {
		StringField s = new StringField();
		assertEquals("Test", s.defaultValue());
	}
	
	private class StringField extends TableDelegateField<TestTableDelegate, String> {
		public StringField() {
			super("string", "Test");
		}
	}
}
