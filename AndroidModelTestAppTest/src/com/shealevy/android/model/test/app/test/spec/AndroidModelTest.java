package com.shealevy.android.model.test.app.test.spec;

import com.shealevy.android.model.AndroidModel;
import com.shealevy.android.model.TableDelegateField;
import com.shealevy.android.model.test.app.test.TestTableDelegate;

import android.test.AndroidTestCase;

public class AndroidModelTest extends AndroidTestCase {

	public AndroidModelTest() {
		super();
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	// Describe get(TableDelegateField<T> field)
	public void testItReturnsDefaultValueWhenAnUninitializedIntIsRequested() {
		IntField i = new IntField();
		AndroidModel<TestTableDelegate> model = new AndroidModel<TestTableDelegate>();
		assertEquals(1, ((Integer) model.get(i)).intValue());
	}
	
	public void testItReturnsNullWhenAnUninitializedStringIsRequested() {
		StringField s = new StringField();
		AndroidModel<TestTableDelegate> model = new AndroidModel<TestTableDelegate>();
		assertEquals("name", (String) model.get(s));
	}
	
	private class IntField implements TableDelegateField<TestTableDelegate> {
		public String value() {
			return "byte";
		}
		
		public Class<?> type() {
			return byte.class;
		}
		
		public Object defaultValue() {
			return 1;
		}
	}
	
	private class StringField implements TableDelegateField<TestTableDelegate> {
		public String value() {
			return "string";
		}
		
		public Class<?> type() {
			return String.class;
		}
		
		public Object defaultValue() {
			return "name";
		}
	}
}
