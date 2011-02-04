package com.shealevy.android.model;

import com.shealevy.android.model.injection.ContentResolverDelegate;

import android.database.Cursor;
import android.net.Uri;

public abstract class TableDelegate {
	public Cursor query(ContentResolverDelegate cr, String[] projection,
			String selection, String[] selectionArgs, String sortOrder) {
		return cr.query(getUri(), projection, selection, selectionArgs,
				sortOrder);
	}

	public abstract Uri getUri();
}
