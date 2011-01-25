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
		Assert.assertNull((String) model.get(TestTableDelegate.Field.NAME));
		Assert.assertEquals(0, ((Byte) model.get(TestTableDelegate.Field.TEST_BYTE)).byteValue());
		Assert.assertEquals(0, ((Short) model.get(TestTableDelegate.Field.TEST_SHORT)).shortValue());
		Assert.assertEquals(0, ((Integer) model.get(TestTableDelegate.Field.ID)).intValue());
		Assert.assertEquals(0L, ((Long) model.get(TestTableDelegate.Field.TEST_LONG)).longValue());
		Assert.assertEquals(0.0f, ((Float) model.get(TestTableDelegate.Field.TEST_FLOAT)).floatValue(), 0.0f);
		Assert.assertEquals(0.0d, ((Double) model.get(TestTableDelegate.Field.TEST_DOUBLE)).doubleValue(), 0.0d);
		Assert.assertEquals('\u0000', ((Character) model.get(TestTableDelegate.Field.TEST_CHAR)).charValue());
		Assert.assertEquals(false, ((Boolean) model.get(TestTableDelegate.Field.TEST_BOOLEAN)).booleanValue());
	}

}
