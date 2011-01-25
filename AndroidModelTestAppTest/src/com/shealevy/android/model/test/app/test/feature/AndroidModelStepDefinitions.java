package com.shealevy.android.model.test.app.test.feature;

import java.util.HashMap;

import junit.framework.Assert;

import com.shealevy.android.model.AndroidModel;
import com.shealevy.android.model.test.app.test.TestTableDelegate;

import android.content.Context;

public class AndroidModelStepDefinitions {
	@SuppressWarnings("unused")
	private Context context;
	private HashMap<String, Object> globals;
	public AndroidModelStepDefinitions(Context context) {
		this.context = context;
		globals = new HashMap<String, Object>();
	}
	public void whenICreateATestTableDelegateAndroidModelCalled_(String modelName) {
		globals.put(modelName, new AndroidModel<TestTableDelegate>());
	}
	public void thenAllTestTableDelegateFieldsOfTestTableDelegateAndroidModel_ShouldBeSetToDefaults(
			String modelName) {
		@SuppressWarnings("unchecked")
		AndroidModel<TestTableDelegate> model = (AndroidModel<TestTableDelegate>) globals.get(modelName);
		Assert.assertEquals("Test",(String) model.get(TestTableDelegate.Field.NAME));
		Assert.assertEquals(1, ((Integer) model.get(TestTableDelegate.Field.ID)).intValue());
	}

}
