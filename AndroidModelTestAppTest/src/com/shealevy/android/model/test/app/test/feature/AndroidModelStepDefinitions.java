package com.shealevy.android.model.test.app.test.feature;

import java.util.HashMap;

import junit.framework.Assert;

import com.shealevy.android.model.AndroidModel;
import com.shealevy.android.model.TableDelegate;
import com.shealevy.android.model.test.app.TestTableDelegate;
import com.shealevy.android.model.test.app.TestTableDelegate.Field;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.test.mock.MockContentResolver;

public class AndroidModelStepDefinitions {
	@SuppressWarnings("unused")
	private Context context;
	private HashMap<String, Object> globals;
	public AndroidModelStepDefinitions(Context context) {
		this.context = context;
		globals = new HashMap<String, Object>();
	}
	
	public void givenATestTableDelegateAndroidModelCalled_(String modelName) {
		globals.put(modelName, new AndroidModel<TestTableDelegate>(TestTableDelegate.class));
	}
	
	public void givenAFakeTestTableDelegateCalled_(String delegateName) {
		globals.put(delegateName, new FakeTestTableDelegate());
	}
	
	public void whenICreateATestTableDelegateAndroidModelCalled_(String modelName) {
		globals.put(modelName, new AndroidModel<TestTableDelegate>(TestTableDelegate.class));
	}
	
	public <T> void whenISetThe_OfTestTableDelegateAndroidModel_To_(Field<T> field,
			String modelName, T value) {
		@SuppressWarnings("unchecked")
		AndroidModel<TestTableDelegate> model = (AndroidModel<TestTableDelegate>) globals.get(modelName);
		model.set(field, value);
	}
	
	public void whenILoadTestTableDelegateAndroidModel_WithAFakeContentResolver(
			String modelName) {
		@SuppressWarnings("unchecked")
		AndroidModel<TestTableDelegate> model = (AndroidModel<TestTableDelegate>) globals.get(modelName);
		ContentResolver cr = new AndroidModelContentResolver();
		model.load(cr);
	}

	public void whenISetTheTableDelegateOfTestTableDelegateAndroidModel_ToTableDelegate_(
			String modelName, String delegateName) {
		@SuppressWarnings("unchecked")
		AndroidModel<TestTableDelegate> model = (AndroidModel<TestTableDelegate>) globals.get(modelName);
		model.setTableDelegate((TableDelegate) globals.get(delegateName));
		
	}
	
	public void thenAllTestTableDelegateFieldsOfTestTableDelegateAndroidModel_ShouldBeSetToDefaults(
			String modelName) {
		@SuppressWarnings("unchecked")
		AndroidModel<TestTableDelegate> model = (AndroidModel<TestTableDelegate>) globals.get(modelName);
		Assert.assertEquals("Test",(String) model.get(TestTableDelegate.Field.NAME));
		Assert.assertEquals(1, ((Integer) model.get(TestTableDelegate.Field.ID)).intValue());
	}

	public <T> void thenThe_OfTestTableDelegateAndroidModel_ShouldBeSetTo_(
			Field<T> field, String modelName, T value) {
		@SuppressWarnings("unchecked")
		AndroidModel<TestTableDelegate> model = (AndroidModel<TestTableDelegate>) globals.get(modelName);
		Assert.assertEquals(value, model.get(field));
	}
	
	public void thenTheTableDelegateOfTestTableDelegateAndroidModel_ShouldBeATestTableDelegate(
			String modelName) {
		@SuppressWarnings("unchecked")
		AndroidModel<TestTableDelegate> model = (AndroidModel<TestTableDelegate>) globals.get(modelName);
		assertEquals(TestTableDelegate.class, model.getTableDelegate().getClass());
	}

	public void thenTheTableDelegateOfTestTableDelegateAndroidModel_ShouldEqualTableDelegate_(
			String modelName, String delegateName) {
		@SuppressWarnings("unchecked")
		AndroidModel<TestTableDelegate> model = (AndroidModel<TestTableDelegate>) globals.get(modelName);
		assertEquals((TableDelegate) globals.get(delegateName), model.getTableDelegate());
	}
	
	private class AndroidModelContentResolver extends MockContentResolver {
		public AndroidModelContentResolver() {
			super();
			this.addProvider("test", new MockContentProvider());
		}
		
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
				if(uri.equals(Uri.parse("content://test/table"))) {
					MatrixCursor cursor;
					if(projection == null) {
						cursor = new MatrixCursor(new String[] { "_id", "name" });
						Object[] columnValues = null;
						if(selection.contains("_id = 2"))
							columnValues = new Object[] { 2, "SecondTest" };
						else if(selection.contains("name = ThirdTest"))
							columnValues = new Object[] { 3, "ThirdTest" };
						cursor.addRow(columnValues);
						return cursor;
					} 
				}
				return null;
			}

			@Override
			public int update(Uri uri, ContentValues values, String selection,
					String[] selectionArgs) {
				return 0;
			}
			
		}
	}




	private class FakeTestTableDelegate extends TestTableDelegate {
		
	}





}
