package com.shealevy.android.model.test.feature;

import com.shealevy.android.model.test.TestTableDelegate;

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
	
	public void testProvidingHashMapSubClassAtConstruction() {
		// Given a TestTableDelegate AndroidModel called "model" with a FakeHashMapClass
		stepDefs.givenATestTableDelegateAndroidModelCalled_WithAFakeHashMapClass("model");
		
		// When I set the ID of TestTableDelegate AndroidModel "model" to 2
		stepDefs.whenISetThe_OfTestTableDelegateAndroidModel_To_(TestTableDelegate.Field.ID, "model", 2);
		
		// Then the FakeHashMap put method should have been called with TestTableDelegate.Field.ID and 2
		stepDefs.thenTheFakeHashMapPutMethodShouldHaveBeenCalledWith_And_(TestTableDelegate.Field.ID, 2);
		// And the TableDelegate of TestTableDelegate AndroidModel "model" should be a TestTableDelegate
		stepDefs.thenTheTableDelegateOfTestTableDelegateAndroidModel_ShouldBeATestTableDelegate("model");
	}
	
	public void testUsingAClassDelegateToSetTheTableDelegateAtConstruction() {
		// When I create a TestTableDelegate AndroidModel called "model" using a ClassDelegate
		stepDefs.whenICreateATestTableDelegateAndroidModelCalled_UsingAClassDelegate("model");
		
		// Then the TableDelegate of TestTableDelegate AndroidModel "model" should be a TestTableDelegate
		stepDefs.thenTheTableDelegateOfTestTableDelegateAndroidModel_ShouldBeATestTableDelegate("model");
	}
	
	public void testUsingAClassDelegateToSetTheTableDelegateAndProvidingHashMapSubClassClassDelegateAtConstruction() {
		// Given a TestTableDelegate AndroidModel called "model" with a FakeHashMapClass created using a ClassDelegate
		stepDefs.givenATestTableDelegateAndroidModelCalled_WithAFakeHashMapClassCreatedUsingClassDelegates("model");
		
		// When I set the ID of TestTableDelegate AndroidModel "model" to 2
		stepDefs.whenISetThe_OfTestTableDelegateAndroidModel_To_(TestTableDelegate.Field.ID, "model", 2);
		
		// Then the FakeHashMap put method should have been called with TestTableDelegate.Field.ID and 2
		stepDefs.thenTheFakeHashMapPutMethodShouldHaveBeenCalledWith_And_(TestTableDelegate.Field.ID, 2);
		// And the TableDelegate of TestTableDelegate AndroidModel "model" should be a TestTableDelegate
		stepDefs.thenTheTableDelegateOfTestTableDelegateAndroidModel_ShouldBeATestTableDelegate("model");
	}
	
	public void testSettingTheTableDelegateAtConstruction() {
		// Given a FakeTestTableDelegate called "delegate"
		stepDefs.givenAFakeTestTableDelegateCalled_("delegate");
		
		// When I create a TestTableDelegate AndroidModel called "model" with TableDelegate "delegate"
		stepDefs.whenICreateATestTableDelegateAndroidModelCalled_WithTestTableDelegate_("model", "delegate");
		
		// Then the TableDelegate of TestTableDelegate AndroidModel "model" should equal TableDelegate "delegate"
		stepDefs.thenTheTableDelegateOfTestTableDelegateAndroidModel_ShouldEqualTableDelegate_("model", "delegate");
	}
	
	public void testSettingTheTableDelegateAfterConstruction() {
		// Given a TestTableDelegate AndroidModel called "model"
		stepDefs.givenATestTableDelegateAndroidModelCalled_("model");
		// And a FakeTestTableDelegate called "delegate"
		stepDefs.givenAFakeTestTableDelegateCalled_("delegate");
		
		// When I set the TableDelegate of TestTableDelegate AndroidModel "model" to TableDelegate "delegate"
		stepDefs.whenISetTheTableDelegateOfTestTableDelegateAndroidModel_ToTestTableDelegate_("model", "delegate");
		
		// Then the TableDelegate of TestTableDelegate AndroidModel "model" should equal TableDelegate "delegate"
		stepDefs.thenTheTableDelegateOfTestTableDelegateAndroidModel_ShouldEqualTableDelegate_("model", "delegate");
	}
}
