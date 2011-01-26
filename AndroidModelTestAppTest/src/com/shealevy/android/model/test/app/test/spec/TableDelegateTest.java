package com.shealevy.android.model.test.app.test.spec;

import com.shealevy.android.model.TableDelegate;
import com.shealevy.android.model.test.app.TestTableDelegate;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.test.mock.MockContentResolver;

public class TableDelegateTest extends AndroidTestCase {

	public TableDelegateTest() {
		super();
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	// Describe query(ContentResolver cr, String[] projection, String selection, String[] selectionArgs, String sortOrder)
	public void testItReturnsACursorWithTheDataGivenByTheUri() {
		TableDelegate td = new TestTableDelegate();
		ContentResolver cr = new AndroidModelContentResolver();
		String[] projection = new String[] {"_id", "name"};
		String selection = "_id = 2";
		Cursor tdCursor = td.query(cr, projection, selection, null, null);
		Cursor crCursor = cr.query(Uri.parse("content://test/table"), projection, selection, null, null);
		while(tdCursor.moveToNext()) {
			assertTrue(crCursor.moveToNext());
			assertEquals(crCursor.getInt(0), tdCursor.getInt(0));
			assertEquals(crCursor.getString(1), tdCursor.getString(1));
		}
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
					cursor = new MatrixCursor(projection);
					Object[] columnValues = null;
					if(selection.contains("_id = 2"))
						columnValues = new Object[] { 2, "SecondTest" };
					else if(selection.contains("name = ThirdTest"))
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
	}
}
