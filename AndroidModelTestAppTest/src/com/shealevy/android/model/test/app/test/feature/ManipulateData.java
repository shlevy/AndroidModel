package com.shealevy.android.model.test.app.test.feature;

import android.test.AndroidTestCase;

public class ManipulateData extends AndroidTestCase {
	/*
	 * As an Android developer
	 * I want to be able to manipulate the data in my Models
	 * So that my apps can do something useful once the data is accessed and before it is written
	 */
	AndroidModelStepDefinitions stepDefs;

	public ManipulateData() {
		super();
	}

	protected void setUp() throws Exception {
		super.setUp();
		stepDefs =  new AndroidModelStepDefinitions(getContext());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGettingUnsetFields() {
		//When I create a TestTableDelegate AndroidModel called "model"
		stepDefs.whenICreateATestTableDelegateAndroidModelCalled_("model");
		
		//Then all TestTableDelegate fields of TestTableDelegate AndroidModel "model" should be set to defaults
		stepDefs.thenAllTestTableDelegateFieldsOfTestTableDelegateAndroidModel_ShouldBeSetToDefaults("model");
	}
}
