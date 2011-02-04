package com.shealevy.android.model.injection;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

public class ContentResolverDelegate {
	private ContentResolver delegate;

	public ContentResolverDelegate(ContentResolver delegate) {
		setDelegate(delegate);
	}

	public ContentResolver getDelegate() {
		return delegate;
	}

	public void setDelegate(ContentResolver delegate) {
		this.delegate = delegate;
	}

	public Cursor query (Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		return delegate.query(uri, projection, selection, selectionArgs, sortOrder);
	}
}
