package com.shealevy.android.model.test.app.test.feature;

import android.test.AndroidTestCase;

public class InjectDependencies extends AndroidTestCase {
	/*
	 * As an Android developer
	 * I want to be able to inject dependencies into my AndroidModels
	 * So that I can control how they interact with the system
	 */
	AndroidModelStepDefinitions stepDefs;

	public InjectDependencies() {
		super();
	}

	protected void setUp() throws Exception {
		super.setUp();
		stepDefs =  new AndroidModelStepDefinitions(getContext());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testNoInjection() {
		// When I create a TestTableDelegate AndroidModel called "model"
		stepDefs.whenICreateATestTableDelegateAndroidModelCalled_("model");
		
		// Then the TableDelegate of TestTableDelegate AndroidModel "model" should be a TestTableDelegate
		stepDefs.thenTheTableDelegateOfTestTableDelegateAndroidModel_ShouldBeATestTableDelegate("model");
	}
	
	public void testSettingTheTableDelegate() {
		// Given a TestTableDelegate AndroidModel called "model"
		stepDefs.givenATestTableDelegateAndroidModelCalled_("model");
		// And a FakeTestTableDelegate called "delegate"
		stepDefs.givenAFakeTestTableDelegateCalled_("delegate");
		
		// When I set the TableDelegate of TestTableDelegate AndroidModel "model" to TableDelegate "delegate"
		stepDefs.whenISetTheTableDelegateOfTestTableDelegateAndroidModel_ToTableDelegate_("model", "delegate");
		
		// Then the TableDelegate of TestTableDelegate AndroidModel "model" should equal TableDelegate "delegate"
		stepDefs.thenTheTableDelegateOfTestTableDelegateAndroidModel_ShouldEqualTableDelegate_("model", "delegate");
	}
}