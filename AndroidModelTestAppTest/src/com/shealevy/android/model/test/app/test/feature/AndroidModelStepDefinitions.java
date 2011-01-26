package com.shealevy.android.model.test.app.test.feature;

import java.util.HashMap;

import junit.framework.Assert;

import com.shealevy.android.model.AndroidModel;
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
		globals.put(modelName, new AndroidModel<TestTableDelegate>());
	}
	
	public void whenICreateATestTableDelegateAndroidModelCalled_(String modelName) {
		globals.put(modelName, new AndroidModel<TestTableDelegate>());
	}
	
	public <T> void whenISetThe_OfTestTableDelegateAndroidModel_To_(Field<T> field,
			String modelName, T value) {
		@SuppressWarnings("unchecked")
		AndroidModel<TestTableDelegate> model = (AndroidModel<TestTableDelegate>) globals.get(modelName);
		model.set(field, value);
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

	public void whenILoadTestTableDelegateAndroidModel_WithAFakeContentResolver(
			String modelName) {
		@SuppressWarnings("unchecked")
		AndroidModel<TestTableDelegate> model = (AndroidModel<TestTableDelegate>) globals.get(modelName);
		ContentResolver cr = new AndroidModelContentResolver();
		model.load(cr);
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
				int id;
				try {
					id =Integer.decode(uri.getLastPathSegment());
				} catch(NumberFormatException e) {
					id = 0;
				}
				String test = uri.getPathSegments().get(1);
				
				if(test.equals("table")) {
					MatrixCursor cursor;
					if(projection == null) {
						cursor = new MatrixCursor(new String[] { "_id", "name" });
						Object[] columnValues = new Object[] { id, id == 2 ? "TestCalendar":null };
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
}
