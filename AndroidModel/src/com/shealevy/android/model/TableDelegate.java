package com.shealevy.android.model;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

public abstract class TableDelegate {
	public Cursor query(ContentResolver cr, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		return cr.query(getUri(), projection, selection, selectionArgs, sortOrder);
	}

	public abstract Uri getUri();
}
