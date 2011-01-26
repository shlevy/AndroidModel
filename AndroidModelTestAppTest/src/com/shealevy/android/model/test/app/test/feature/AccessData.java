package com.shealevy.android.model.test.app.test.feature;

import com.shealevy.android.model.test.app.TestTableDelegate;

import android.test.AndroidTestCase;

public class AccessData extends AndroidTestCase {
	/*
	 * As an Android developer
	 * I want to be able to access the data given by providers
	 * So that my apps can do use the data easily
	 */
	private AndroidModelStepDefinitions stepDefs;

	public AccessData() {
		super();
	}

	protected void setUp() throws Exception {
		super.setUp();
		stepDefs = new AndroidModelStepDefinitions(getContext());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testLoadingData() {
		// Given a TestTableDelegate AndroidModel called "model"
		stepDefs.givenATestTableDelegateAndroidModelCalled_("model");
		
		// When I set the ID of TestTableDelegate AndroidModel "model" to 2
		stepDefs.whenISetThe_OfTestTableDelegateAndroidModel_To_(TestTableDelegate.Field.ID, "model", 2);
		// And I load TestTableDelegate AndroidModel "model" with a fake ContentResolver
		stepDefs.whenILoadTestTableDelegateAndroidModel_WithAFakeContentResolver("model");
		
		// Then the NAME of TestTableDelegate AndroidModel "model" should be set to "SecondTest"
		stepDefs.thenThe_OfTestTableDelegateAndroidModel_ShouldBeSetTo_(TestTableDelegate.Field.NAME, "model", "SecondTest");
	}

}
