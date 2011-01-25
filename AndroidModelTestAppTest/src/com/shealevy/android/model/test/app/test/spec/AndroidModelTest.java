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

	// Describe get(TableDelegateField<T, U> field)
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
	
	// Describe set(TableDelegateField<T, U> field, U value)
	public void testItSetsStringValues() {
		StringField s = new StringField();
		AndroidModel<TestTableDelegate> model = new AndroidModel<TestTableDelegate>();
		model.set(s, "SecondTest");
		assertEquals("SecondTest", model.get(s));
	}
	
	public void testItSetsIntValues() {
		IntField i = new IntField();
		AndroidModel<TestTableDelegate> model = new AndroidModel<TestTableDelegate>();
		model.set(i, 2);
		assertEquals(2, model.get(i).intValue());
	}
	
	private class IntField extends TableDelegateField<TestTableDelegate, Integer> {
		public IntField() {
			super("int", 1);
		}
		
		public Integer defaultValue() {
			return 1;
		}
		
		public String name() {
			return "int";
		}
	}
	
	private class StringField extends TableDelegateField<TestTableDelegate, String> {
		public StringField() {
			super("string", "Test");
		}
		
		public String defaultValue() {
			return "Test";
		}
		
		public String name() {
			return "string";
		}
	}
}
