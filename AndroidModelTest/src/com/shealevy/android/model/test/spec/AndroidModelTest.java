package com.shealevy.android.model.test.spec;

import java.util.HashMap;
import java.lang.reflect.Field;

import com.shealevy.android.model.AndroidModel;
import com.shealevy.android.model.TableDelegate;
import com.shealevy.android.model.TableDelegateField;
import com.shealevy.android.model.injection.ClassDelegate;
import com.shealevy.android.model.injection.ContentResolverDelegate;
import com.shealevy.android.model.test.TestTableDelegate;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
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
	
	// Describe AndroidModel(Class<T> tableDelegateClass)
	public void testItSetsTheTableDelegate() {
		AndroidModel<TestTableDelegate> model = new AndroidModel<TestTableDelegate>(TestTableDelegate.class);
		assertNotNull(model.getTableDelegate());
	}
	
	// Describe AndroidModel(Class<T> tableDelegateClass, Class<? extends HashMap> hashMapClass)
	public void testItCreatesANewInstanceOfHashMapClassAndSavesItInParams() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		AndroidModel<TestTableDelegate> model = new AndroidModel<TestTableDelegate>(TestTableDelegate.class, FakeHashMap.class);
		Field[] fields = AndroidModel.class.getDeclaredFields();
		for(Field f:fields) {
			if(f.getName().equals("params")) {
				f.setAccessible(true);
				assertEquals(FakeHashMap.class, f.get(model).getClass());	
			}	
		}
	}
	
	public void testItSetsTheTableDelegateWhenAHashMapClassIsProvided() {
		AndroidModel<TestTableDelegate> model = new AndroidModel<TestTableDelegate>(TestTableDelegate.class, FakeHashMap.class);
		assertNotNull(model.getTableDelegate());
	}
	
	// Describe AndroidModel(ClassDelegate<T> tableDelegateClass)
	public void testItSetsTheTableDelegateWithAClassDelegate() {
		AndroidModel<TestTableDelegate> model = new AndroidModel<TestTableDelegate>(new ClassDelegate<TestTableDelegate>(TestTableDelegate.class));
		assertNotNull(model.getTableDelegate());
	}
	
	// Describe AndroidModel(ClassDelegate<T> tableDelegateClass, ClassDelegate<? extends HashMap> hashMapClass)
	public void testItCreatesANewInstanceOfHashMapClassAndSavesItInParamsWhenAClassDelegateIsUsed() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		AndroidModel<TestTableDelegate> model = new AndroidModel<TestTableDelegate>(new ClassDelegate(TestTableDelegate.class), new ClassDelegate(FakeHashMap.class));
		Field[] fields = AndroidModel.class.getDeclaredFields();
		for(Field f:fields) {
			if(f.getName().equals("params")) {
				f.setAccessible(true);
				assertEquals(FakeHashMap.class, f.get(model).getClass());	
			}
		}
	}
	
	public void testItSetsTheTableDelegateWithAClassDelegateWhenAHashMapClassClassDelegateIsProvided() {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		AndroidModel<TestTableDelegate> model = new AndroidModel<TestTableDelegate>(new ClassDelegate(TestTableDelegate.class), new ClassDelegate(FakeHashMap.class));
		assertNotNull(model.getTableDelegate());
	}
	
	// Describe AndroidModel(T tableDelegate)
	public void testConstructorSetsTheTableDelegateToTheProvidedTableDelegate() {
		TestTableDelegate td = new TestTableDelegate();
		AndroidModel<TestTableDelegate> model = new AndroidModel<TestTableDelegate>(td);
		assertEquals(td,model.getTableDelegate());
	}
	
	// Describe AndroidModel(T tableDelegate, Class<? extends HashMap> hashMapClass)
	public void testItCreatesANewInstanceOfHashMapClassAndSavesItInParamsWhenATableDelegateIsSent() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		TestTableDelegate td = new TestTableDelegate();
		AndroidModel<TestTableDelegate> model = new AndroidModel<TestTableDelegate>(td, FakeHashMap.class);
		Field[] fields = AndroidModel.class.getDeclaredFields();
		for(Field f:fields) {
			if(f.getName().equals("params")) {
				f.setAccessible(true);
				assertEquals(FakeHashMap.class, f.get(model).getClass());	
			}
		}
	}
	
	public void testConstructorSetsTheTableDelegateToTheProvidedTableDelegateWhenAHashMapClassIsProvided() {
		TestTableDelegate td = new TestTableDelegate();
		AndroidModel<TestTableDelegate> model = new AndroidModel<TestTableDelegate>(td, FakeHashMap.class);
		assertEquals(td,model.getTableDelegate());
	}
	
	// Describe AndroidModel(T tableDelegate, ClassDelegate<? extends HashMap> hashMapClass)
	public void testItCreatesANewInstanceOfHashMapClassAndSavesItInParamsWhenATableDelegateIsSentAndAClassDelegateIsUsed() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		TestTableDelegate td = new TestTableDelegate();
		@SuppressWarnings("rawtypes")
		AndroidModel<TestTableDelegate> model = new AndroidModel<TestTableDelegate>(td, new ClassDelegate<FakeHashMap>(FakeHashMap.class));
		Field[] fields = AndroidModel.class.getDeclaredFields();
		for(Field f:fields) {
			if(f.getName().equals("params")) {
				f.setAccessible(true);
				assertEquals(FakeHashMap.class, f.get(model).getClass());	
			}
		}
	}
	
	public void testConstructorSetsTheTableDelegateToTheProvidedTableDelegateWhenAHashMapClassIsProvidedAsAClassDelegate() {
		TestTableDelegate td = new TestTableDelegate();
		@SuppressWarnings("rawtypes")
		AndroidModel<TestTableDelegate> model = new AndroidModel<TestTableDelegate>(td, new ClassDelegate<FakeHashMap>(FakeHashMap.class));
		assertEquals(td,model.getTableDelegate());
	}
	
	// Describe setTableDelegate(T tableDelegate)
	public void testSetTableDelegateSetsTheTableDelegateToTheProvidedTableDelegate() {
		AndroidModel<TestTableDelegate> model = new AndroidModel<TestTableDelegate>(TestTableDelegate.class);
		TestTableDelegate td = new TestTableDelegate();
		model.setTableDelegate(td);
		assertEquals(td,model.getTableDelegate());
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
	
	// Describe load(ContentResolverDelegate cr)
	public void testItLoadsWhenIdIsSetToTwo() {
		AndroidModel<TwoTableDelegate> model = new AndroidModel<TwoTableDelegate>(TwoTableDelegate.class);
		model.set(TwoTableDelegate.Field.ID, 2);
		model.load(new ContentResolverDelegate(getContext().getContentResolver()));
		assertEquals("SecondTest", model.get(TwoTableDelegate.Field.NAME));
		assertEquals(2, ((Integer) model.get(TwoTableDelegate.Field.ID)).intValue());
	}
	
	public void testItLoadsWhenNameIsSetToThirdTest() {
		AndroidModel<TwoTableDelegate> model = new AndroidModel<TwoTableDelegate>(TwoTableDelegate.class);
		model.set(TwoTableDelegate.Field.NAME, "ThirdTest");
		model.load(new ContentResolverDelegate(getContext().getContentResolver()));
		assertEquals("ThirdTest", model.get(TwoTableDelegate.Field.NAME));
		assertEquals(3, ((Integer) model.get(TwoTableDelegate.Field.ID)).intValue());
	}
	
	public static class FakeHashMap<K, V> extends HashMap<K, V> {
		private static final long serialVersionUID = -3427816561016230757L;
		
	}
	
	public static class TwoTableDelegate extends TableDelegate {
		public static class Field<T> extends TableDelegateField<TwoTableDelegate, T> {
			public static final Field<Integer> ID = new Field<Integer>("_id", int.class, 1);
			public static final Field<String> NAME = new Field<String>("name", String.class, "Test");
			
			private Field(String value, Class<T> type, T defaultValue) {
				super(value, type, defaultValue);
			}
			
			public String name() {
				if(this == ID)
					return "_id";
				return "name";
			}
			
			@SuppressWarnings("unchecked")
			public Class<T> type(){
				if(this == ID)
					return (Class<T>) int.class;
				return (Class<T>) String.class;
						
			}
			
			@Override
			public Field<?>[] getFields() {
				return new Field<?>[] {ID, NAME};
			}
		}
		
		public Cursor query(ContentResolverDelegate cr, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
			if(selection.contains("_id = 2")){
				MatrixCursor cursor = new MatrixCursor(projection);
				cursor.addRow(new Object[] {2, "SecondTest"});
				return cursor;
			}
			if(selection.contains("name = 'ThirdTest'")) {
				MatrixCursor cursor = new MatrixCursor(projection);
				cursor.addRow(new Object[] {3, "ThirdTest"});
				return cursor;
			}
			return null;
		}
		@Override
		public Uri getUri() {
			return null;
		}
	}
	
	public static class IntField extends TableDelegateField<TestTableDelegate, Integer> {
		public IntField() {
			super("int", int.class, 1);
		}
		
		public Integer defaultValue() {
			return 1;
		}
		
		public String name() {
			return "int";
		}
		
		public IntField[] getFields() {
			return new IntField[] {new IntField()};
		}
	}
	
	public static class StringField extends TableDelegateField<TestTableDelegate, String> {
		public StringField() {
			super("string", String.class, "Test");
		}
		
		public String defaultValue() {
			return "Test";
		}
		
		public String name() {
			return "string";
		}
		
		public StringField[] getFields() {
			return new StringField[] {new StringField()};
		}
	}
}
