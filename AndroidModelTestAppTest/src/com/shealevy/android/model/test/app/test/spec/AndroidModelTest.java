package com.shealevy.android.model.test.app.test.spec;

import com.shealevy.android.model.AndroidModel;
import com.shealevy.android.model.TableDelegate;
import com.shealevy.android.model.TableDelegateField;
import com.shealevy.android.model.test.app.TestTableDelegate;
import com.shealevy.android.model.test.app.TestTableDelegate.Field;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.test.mock.MockContentResolver;

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
	
	// Describe AndroidModel(Class<T> tClass)
	public void testItSetsTheTableDelegate() {
		AndroidModel<TestTableDelegate> model = new AndroidModel<TestTableDelegate>(TestTableDelegate.class);
		assertNotNull(model.getTableDelegate());
	}

	// Describe get(TableDelegateField<T, U> field)
	public void testItReturnsDefaultValueWhenAnUninitializedIntIsRequested() {
		IntField i = new IntField();
		AndroidModel<TestTableDelegate> model = new AndroidModel<TestTableDelegate>(TestTableDelegate.class);
		assertEquals(1, ((Integer) model.get(i)).intValue());
	}
	
	public void testItReturnsDefaultValueWhenAnUninitializedStringIsRequested() {
		StringField s = new StringField();
		AndroidModel<TestTableDelegate> model = new AndroidModel<TestTableDelegate>(TestTableDelegate.class);
		assertEquals("Test", (String) model.get(s));
	}
	
	// Describe set(TableDelegateField<T, U> field, U value)
	public void testItSetsStringValues() {
		StringField s = new StringField();
		AndroidModel<TestTableDelegate> model = new AndroidModel<TestTableDelegate>(TestTableDelegate.class);
		model.set(s, "SecondTest");
		assertEquals("SecondTest", model.get(s));
	}
	
	public void testItSetsIntValues() {
		IntField i = new IntField();
		AndroidModel<TestTableDelegate> model = new AndroidModel<TestTableDelegate>(TestTableDelegate.class);
		model.set(i, 2);
		assertEquals(2, model.get(i).intValue());
	}
	
	// Describe load(ContentResolver cr)
	public void testItLoadsWhenIdIsSetToTwo() {
		AndroidModel<TwoTableDelegate> model = new AndroidModel<TwoTableDelegate>(TwoTableDelegate.class);
		model.set(TwoTableDelegate.Field.ID, 2);
		model.load(getContext().getContentResolver());
		assertEquals("SecondTest", model.get(TwoTableDelegate.Field.NAME));
	}
	
	private static class TwoTableDelegate extends TableDelegate {
		public static class Field<T> extends TableDelegateField<TwoTableDelegate, T> {
			public static final Field<Integer> ID = new Field<Integer>("_id", 1);
			public static final Field<String> NAME = new Field<String>("name", "Test");
			
			private Field(String value, T defaultValue) {
				super(value, defaultValue);
			}
			
			public Field<?>[] getFields() {
				return new Field<?>[] {ID, NAME};
			}
		}
		
		public Cursor query(ContentResolver cr, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
			if(selection.contains("_id = 2")){
				MatrixCursor cursor = new MatrixCursor(new String[] {"_id", "name"});
				cursor.addRow(new Object[] {2, "SecondTest"});
				return cursor;
			}
			return null;
		}
	}
	
	private static class IntField extends TableDelegateField<TestTableDelegate, Integer> {
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
	
	private static class StringField extends TableDelegateField<TestTableDelegate, String> {
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
