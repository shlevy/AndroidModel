package com.shealevy.android.model.test.feature;

import java.util.HashMap;

import junit.framework.Assert;

import com.shealevy.android.model.AndroidModel;
import com.shealevy.android.model.TableDelegate;
import com.shealevy.android.model.injection.ClassDelegate;
import com.shealevy.android.model.test.TestTableDelegate;
import com.shealevy.android.model.test.TestTableDelegate.Field;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.test.mock.MockContentResolver;

public class AndroidModelStepDefinitions {
	private class AndroidModelContentResolver extends MockContentResolver {
		private class MockContentProvider extends ContentProvider {

			@Override
			public int delete(Uri uri, String selection, String[] selectionArgs) {
				return 0;
			}

			@Override
			public String getType(Uri uri) {
				return null;
			}

			@Override
			public Uri insert(Uri uri, ContentValues values) {
				return null;
			}

			@Override
			public boolean onCreate() {
				return false;
			}

			@Override
			public Cursor query(Uri uri, String[] projection, String selection,
					String[] selectionArgs, String sortOrder) {
				if (uri.equals(Uri.parse("content://test/table"))) {
					MatrixCursor cursor;
					cursor = new MatrixCursor(projection);
					Object[] columnValues = null;
					if (selection.contains("_id = 2"))
						columnValues = new Object[] { 2, "SecondTest" };
					else if (selection.contains("name = ThirdTest"))
						columnValues = new Object[] { 3, "ThirdTest" };
					cursor.addRow(columnValues);
					return cursor;
				}
				return null;
			}

			@Override
			public int update(Uri uri, ContentValues values, String selection,
					String[] selectionArgs) {
				return 0;
			}

		}

		public AndroidModelContentResolver() {
			super();
			this.addProvider("test", new MockContentProvider());
		}
	}

	public static class FakeHashMap<K, V> extends HashMap<K, V> {
		private static final long serialVersionUID = -651988855125124148L;
		public static Object putKey;
		public static Object putValue;

		@Override
		public V put(K key, V value){
			putKey = key;
			putValue = value;
			return value;
		}
	}

	private class FakeTestTableDelegate extends TestTableDelegate {

	}

	@SuppressWarnings("unused")
	private Context context;

	private HashMap<String, Object> globals;

	public AndroidModelStepDefinitions(Context context) {
		this.context = context;
		FakeHashMap.putKey = null;
		FakeHashMap.putValue = null;
		globals = new HashMap<String, Object>();
	}

	public void givenAFakeTestTableDelegateCalled_(String delegateName) {
		globals.put(delegateName, new FakeTestTableDelegate());
	}

	public void givenATestTableDelegateAndroidModelCalled_(String modelName) {
		globals.put(modelName, new AndroidModel<TestTableDelegate>(
				TestTableDelegate.class));
	}

	public void givenATestTableDelegateAndroidModelCalled_WithAFakeHashMapClass(
			String modelName) {
		globals.put(modelName, new AndroidModel<TestTableDelegate>(
				TestTableDelegate.class, FakeHashMap.class));
	}

	public void givenATestTableDelegateAndroidModelCalled_WithAFakeHashMapClassCreatedUsingClassDelegates(
			String modelName) {
		globals.put(modelName, new AndroidModel<TestTableDelegate>(
				new ClassDelegate<TestTableDelegate>(TestTableDelegate.class), new ClassDelegate<FakeHashMap>(FakeHashMap.class)));
	}
	
	public void whenICreateATestTableDelegateAndroidModelCalled_(
			String modelName) {
		globals.put(modelName, new AndroidModel<TestTableDelegate>(
				TestTableDelegate.class));
	}

	public void whenICreateATestTableDelegateAndroidModelCalled_UsingAClassDelegate(
			String modelName) {
		globals.put(modelName, new AndroidModel<TestTableDelegate>(
				new ClassDelegate<TestTableDelegate>(TestTableDelegate.class)));
	}

	public void whenICreateATestTableDelegateAndroidModelCalled_WithTestTableDelegate_(
			String modelName, String delegateName) {
		globals.put(modelName, new AndroidModel<TestTableDelegate>(
				(TestTableDelegate) globals.get(delegateName)));
	}

	public void whenILoadTestTableDelegateAndroidModel_WithAFakeContentResolver(
			String modelName) {
		@SuppressWarnings("unchecked")
		AndroidModel<TestTableDelegate> model = (AndroidModel<TestTableDelegate>) globals
				.get(modelName);
		ContentResolver cr = new AndroidModelContentResolver();
		model.load(cr);
	}

	public <T> void whenISetThe_OfTestTableDelegateAndroidModel_To_(
			Field<T> field, String modelName, T value) {
		@SuppressWarnings("unchecked")
		AndroidModel<TestTableDelegate> model = (AndroidModel<TestTableDelegate>) globals
				.get(modelName);
		model.set(field, value);
	}

	public void whenISetTheTableDelegateOfTestTableDelegateAndroidModel_ToTestTableDelegate_(
			String modelName, String delegateName) {
		@SuppressWarnings("unchecked")
		AndroidModel<TestTableDelegate> model = (AndroidModel<TestTableDelegate>) globals
				.get(modelName);
		model.setTableDelegate((TestTableDelegate) globals.get(delegateName));

	}

	public void thenAllTestTableDelegateFieldsOfTestTableDelegateAndroidModel_ShouldBeSetToDefaults(
			String modelName) {
		@SuppressWarnings("unchecked")
		AndroidModel<TestTableDelegate> model = (AndroidModel<TestTableDelegate>) globals
				.get(modelName);
		Assert.assertEquals("Test",
				(String) model.get(TestTableDelegate.Field.NAME));
		Assert.assertEquals(1,
				((Integer) model.get(TestTableDelegate.Field.ID)).intValue());
	}

	public <T> void thenThe_OfTestTableDelegateAndroidModel_ShouldBeSetTo_(
			Field<T> field, String modelName, T value) {
		@SuppressWarnings("unchecked")
		AndroidModel<TestTableDelegate> model = (AndroidModel<TestTableDelegate>) globals
				.get(modelName);
		Assert.assertEquals(value, model.get(field));
	}

	public void thenTheTableDelegateOfTestTableDelegateAndroidModel_ShouldBeATestTableDelegate(
			String modelName) {
		@SuppressWarnings("unchecked")
		AndroidModel<TestTableDelegate> model = (AndroidModel<TestTableDelegate>) globals
				.get(modelName);
		Assert.assertEquals(TestTableDelegate.class, model.getTableDelegate()
				.getClass());
	}

	public void thenTheTableDelegateOfTestTableDelegateAndroidModel_ShouldEqualTableDelegate_(
			String modelName, String delegateName) {
		@SuppressWarnings("unchecked")
		AndroidModel<TestTableDelegate> model = (AndroidModel<TestTableDelegate>) globals
				.get(modelName);
		Assert.assertEquals((TableDelegate) globals.get(delegateName),
				model.getTableDelegate());
	}

	public void thenTheFakeHashMapPutMethodShouldHaveBeenCalledWith_And_(
			Field<?> key, Object value) {
		Assert.assertEquals("Key not sent!", key, FakeHashMap.putKey);
		Assert.assertEquals("Value not sent!", value, FakeHashMap.putValue);
	}
}
