package com.shealevy.android.model.injection;

public class ClassDelegate<T> {
	private Class<T> delegate;

	public ClassDelegate(Class<T> delegate) {
		setDelegate(delegate);
	}

	public Class<T> getDelegate() {
		return delegate;
	}

	public T newInstance() throws IllegalAccessException,
			InstantiationException {
		return getDelegate().newInstance();
	}

	public void setDelegate(Class<T> delegate) {
		this.delegate = delegate;
	}
}
