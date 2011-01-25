package com.shealevy.android.model.test.app.test.feature;

import com.shealevy.android.model.test.app.TestTableDelegate;

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
		// When I create a TestTableDelegate AndroidModel called "model"
		stepDefs.whenICreateATestTableDelegateAndroidModelCalled_("model");
		
		// Then all TestTableDelegate fields of TestTableDelegate AndroidModel "model" should be set to defaults
		stepDefs.thenAllTestTableDelegateFieldsOfTestTableDelegateAndroidModel_ShouldBeSetToDefaults("model");
	}
	
	public void testSettingFields() {
		// Given a TestTableDelegate AndroidModel called "model"
		stepDefs.givenATestTableDelegateAndroidModelCalled_("model");
		
		// When I set the ID of TestTableDelegate AndroidModel "model" to 2
		stepDefs.whenISetThe_OfTestTableDelegateAndroidModel_To_(TestTableDelegate.Field.ID, "model", 2);
		// And I set the NAME of TestTableDelegate AndroidModel "model" to "SecondTest"
		stepDefs.whenISetThe_OfTestTableDelegateAndroidModel_To_(TestTableDelegate.Field.NAME, "model", "SecondTest");
		
		// Then the NAME of TestTableDelegate AndroidModel "model" should be set to "SecondTest"
		stepDefs.thenThe_OfTestTableDelegateAndroidModel_ShouldBeSetTo_(TestTableDelegate.Field.NAME, "model", "SecondTest");
		// And the ID of TestTableDelegate AndroidModel "model" should be set to 2
		stepDefs.thenThe_OfTestTableDelegateAndroidModel_ShouldBeSetTo_(TestTableDelegate.Field.ID, "model", 2);
	}
}
