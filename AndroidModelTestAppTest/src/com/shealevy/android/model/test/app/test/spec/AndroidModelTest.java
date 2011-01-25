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
	
	public void testItReturnsDefaultValueWhenAnUninitializedStringIsRequested() {
		StringField s = new StringField();
		AndroidModel<TestTableDelegate> model = new AndroidModel<TestTableDelegate>();
		assertEquals("Test", (String) model.get(s));
	}
	
	private class IntField extends TableDelegateField<TestTableDelegate, Integer> {
		public IntField() {
			super("int", 1);
		}
		
		public Integer defaultValue() {
			return 1;
		}
	}
	
	private class StringField extends TableDelegateField<TestTableDelegate, String> {
		public StringField() {
			super("string", "Test");
		}
		
		public String defaultValue() {
			return "Test";
		}
	}
}
