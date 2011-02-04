package com.shealevy.android.model.test.spec;

import com.shealevy.android.model.TableDelegateField;
import com.shealevy.android.model.test.TestTableDelegate;

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
	
	public void testItReturnsIntSetAtConstruction() {
		IntField i = new IntField();
		assertEquals(1, i.defaultValue().intValue());
	}
	
	// Describe type()
	public void testItReturnsTheFieldTypeWhenTheFieldIsAnInt() {
		IntField i = new IntField();
		assertEquals(int.class, i.type());
	}
	
	public void testItReturnsTheFieldTypeWhenTheFieldIsAnString() {
		StringField s = new StringField();
		assertEquals(String.class, s.type());
	}
	
	// Describe name()
	public void testItReturnsTheNameWhenTheFieldIsAnInt() {
		IntField i = new IntField();
		assertEquals("int", i.name());
	}
	
	private class StringField extends TableDelegateField<TestTableDelegate, String> {
		public StringField() {
			super("string", String.class, "Test");
		}

		@Override
		public StringField[] getFields() {
			return new StringField[] {new StringField()};
		}
	}
	
	private class IntField extends TableDelegateField<TestTableDelegate, Integer> {
		public IntField() {
			super("int", int.class, 1);
		}

		@Override
		public IntField[] getFields() {
			return new IntField[] {new IntField()};
		}
	}
}
