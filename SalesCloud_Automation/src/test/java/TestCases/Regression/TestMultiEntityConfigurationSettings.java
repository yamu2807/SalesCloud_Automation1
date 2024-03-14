package TestCases.Regression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import FunctionalLibrary.functionLibrary;

public class TestMultiEntityConfigurationSettings extends functionLibrary {
	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void logInFunction() throws Exception {
		fLogin_SalesForce();
	}

	@AfterClass
	public void tearDown() {
		quit();
	}

	@Test(priority = 1, description = "Testrail Test Case : C1675210, C1675221")
	public void TestCaseC1675210_C1675221_MappMyTestProjecteXAMPLECompanyToSub() throws Exception {
		// navigate to MyTestProjecteXAMPLE configuration page
		navigateToMyTestProjecteXAMPLEConfigurationPage();
		// updated multi-entity settings
		ExecutionDelay(5);
		clickOnMultiCompanySettingsTab();
		enterObjectNameforSubsidiaryValue("Valid");
		enterSubsidiaryFieldNameOnACcValue("Valid");
		clickOnDisplayMappingButton();
		Thread.sleep(3000);
		clickOnMyTestProjecteXAMPLECompanyCodeDropdown();
		clickOnMyTestProjecteXAMPLECompanyCodeOptionPSSelection();
		ExecutionDelay(5);
		clickOnMyTestProjecteXAMPLECompanyCodeLabel();
//		clickOnSaveButton();
		Thread.sleep(2000);
		sGLExpectedAddress = " 13307 Birch Cir,Denver,CO,80241,United States";
		String companyCode_PS_ActualAddress = getAddressForPSCompanyCode();
		softAssert.assertTrue(companyCode_PS_ActualAddress.equalsIgnoreCase(sGLExpectedAddress),
				"Expected:" + sGLExpectedAddress + "And Actual:" + companyCode_PS_ActualAddress);
		Thread.sleep(2000);
		softAssert.assertAll();
	}

	@Test(priority = 2, description = "Testrail Test Case : C1675211")
	public void TestCaseC1675211_ValidObjectSub_InvalidFieldname() throws Exception {
		// navigate to MyTestProjecteXAMPLE configuration page
		navigateToMyTestProjecteXAMPLEConfigurationPage();
		// updated multi-entity settings
		ExecutionDelay(10);
		clickOnMultiCompanySettingsTab();
		enterObjectNameforSubsidiaryValue("Valid");
		enterSubsidiaryFieldNameOnACcValue("Invalid");
		clickOnDisplayMappingButton();
		Thread.sleep(3000);
		fVerifyErrorMassageOnMyTestProjecteXAMPLEConfiguration();
		sGLExpectedAddress = "Object Name for Subsidiary and Subsidiary Field Name on ACc are not related or incorrect. Kindly ensure to enter valid values for these fields.";
		softAssert.assertTrue(sGLActualValidatedErrorMessage.contains(sGLExpectedAddress),
				"Expected:" + sGLExpectedAddress + "And Actual:" + sGLActualValidatedErrorMessage);
		Thread.sleep(2000);
		softAssert.assertAll();
	}

	@Test(priority = 3, description = "Testrail Test Case : C1675212")
	public void TestCaseC1675212_InvalidObjectSub_ValidFieldname() throws Exception {
		// navigate to MyTestProjecteXAMPLE configuration page
		navigateToMyTestProjecteXAMPLEConfigurationPage();
		// updated multi-entity settings
		ExecutionDelay(5);
		clickOnMultiCompanySettingsTab();
		enterObjectNameforSubsidiaryValue("Invalid");
		enterSubsidiaryFieldNameOnACcValue("Valid");
		clickOnDisplayMappingButton();
		Thread.sleep(3000);
		fVerifyErrorMassageOnMyTestProjecteXAMPLEConfiguration();
		sGLExpectedAddress = "Object Name for Subsidiary and Subsidiary Field Name on ACc are not related or incorrect. Kindly ensure to enter valid values for these fields.";
		softAssert.assertTrue(sGLActualValidatedErrorMessage.contains(sGLExpectedAddress),
				"Expected:" + sGLExpectedAddress + "And Actual:" + sGLActualValidatedErrorMessage);
		Thread.sleep(2000);
		softAssert.assertAll();
	}

	@Test(priority = 4, description = "Testrail Test Case : C1675213")
	public void TestCaseC1675213_InvalidObjectSub_InvalidFieldname() throws Exception {
		// navigate to MyTestProjecteXAMPLE configuration page
		navigateToMyTestProjecteXAMPLEConfigurationPage();
		// updated multi-entity settings
		ExecutionDelay(5);
		clickOnMultiCompanySettingsTab();
		enterObjectNameforSubsidiaryValue("Invalid");
		enterSubsidiaryFieldNameOnACcValue("Invalid");
		clickOnDisplayMappingButton();
		Thread.sleep(3000);
		fVerifyErrorMassageOnMyTestProjecteXAMPLEConfiguration();
		sGLExpectedAddress = "Object Name for Subsidiary and Subsidiary Field Name on ACc are not related or incorrect. Kindly ensure to enter valid values for these fields.";
		softAssert.assertTrue(sGLActualValidatedErrorMessage.contains(sGLExpectedAddress),
				"Expected:" + sGLExpectedAddress + "And Actual:" + sGLActualValidatedErrorMessage);
		Thread.sleep(2000);
		softAssert.assertAll();
	}

	@Test(priority = 5, description = "Testrail Test Case : C1675214")
	public void TestCaseC1675214_ValidObjectSub_BlankFieldname() throws Exception {
		// navigate to MyTestProjecteXAMPLE configuration page
		navigateToMyTestProjecteXAMPLEConfigurationPage();
		// updated multi-entity settings
		ExecutionDelay(5);
		clickOnMultiCompanySettingsTab();
		enterObjectNameforSubsidiaryValue("Valid");
		enterSubsidiaryFieldNameOnACcValue("Blank");
		clickOnDisplayMappingButton();
		Thread.sleep(3000);
		fVerifyErrorMassageOnMyTestProjecteXAMPLEConfiguration();
		sGLExpectedAddress = "Object Name for Subsidiary and Subsidiary Field Name on ACc are not related or incorrect. Kindly ensure to enter valid values for these fields.";
		softAssert.assertTrue(sGLActualValidatedErrorMessage.contains(sGLExpectedAddress),
				"Expected:" + sGLExpectedAddress + "And Actual:" + sGLActualValidatedErrorMessage);
		Thread.sleep(2000);
		softAssert.assertAll();
	}

	@Test(priority = 6, description = "Testrail Test Case : C1675215")
	public void TestCaseC1675215_BlankObjectSub_ValidFieldname() throws Exception {
		// navigate to MyTestProjecteXAMPLE configuration page
		navigateToMyTestProjecteXAMPLEConfigurationPage();
		// updated multi-entity settings
		ExecutionDelay(5);
		clickOnMultiCompanySettingsTab();
		enterObjectNameforSubsidiaryValue("Blank");
		enterSubsidiaryFieldNameOnACcValue("Valid");
		clickOnDisplayMappingButton();
		Thread.sleep(3000);
		fVerifyErrorMassageOnMyTestProjecteXAMPLEConfiguration();
		sGLExpectedAddress = "Object Name for Subsidiary and Subsidiary Field Name on ACc are not related or incorrect. Kindly ensure to enter valid values for these fields.";
		softAssert.assertTrue(sGLActualValidatedErrorMessage.contains(sGLExpectedAddress),
				"Expected:" + sGLExpectedAddress + "And Actual:" + sGLActualValidatedErrorMessage);
		Thread.sleep(2000);
		softAssert.assertAll();
	}

	@Test(priority = 7, description = "Testrail Test Case : C1675216")
	public void TestCaseC1675216_BlankObjectSub_BlankFieldname() throws Exception {
		// navigate to MyTestProjecteXAMPLE configuration page
		navigateToMyTestProjecteXAMPLEConfigurationPage();
		// updated multi-entity settings
		ExecutionDelay(5);
		clickOnMultiCompanySettingsTab();
		enterObjectNameforSubsidiaryValue("Blank");
		enterSubsidiaryFieldNameOnACcValue("Blank");
		clickOnDisplayMappingButton();
		Thread.sleep(3000);
		fVerifyErrorMassageOnMyTestProjecteXAMPLEConfiguration();
		sGLExpectedAddress = "Object Name for Subsidiary and Subsidiary Field Name on ACc are not related or incorrect. Kindly ensure to enter valid values for these fields.";
		softAssert.assertTrue(sGLActualValidatedErrorMessage.contains(sGLExpectedAddress),
				"Expected:" + sGLExpectedAddress + "And Actual:" + sGLActualValidatedErrorMessage);
		Thread.sleep(2000);
		softAssert.assertAll();
	}

	@Test(priority = 8, description = "Testrail Test Case : C1675222")
	public void TestCaseC1675222_VerifyCorporateAddressWaringSign() throws Exception {
		// navigate to MyTestProjecteXAMPLE configuration page
		navigateToMyTestProjecteXAMPLEConfigurationPage();
		// updated multi-entity settings
		ExecutionDelay(10);
		clickOnMultiCompanySettingsTab();
		enterObjectNameforSubsidiaryValue("Valid");
		enterSubsidiaryFieldNameOnACcValue("Valid");
		clickOnDisplayMappingButton();
		Thread.sleep(3000);
		clickOnMyTestProjecteXAMPLECompanyCodeDropdown();
		clickOnMyTestProjecteXAMPLECompanyCodeOptionAVASelection();
		ExecutionDelay(5);
		clickOnMyTestProjecteXAMPLECompanyCodeLabel();
		clickOnSaveButton();
		ExecutionDelay(5);
		verifyIsWarningDisplayed();
		Thread.sleep(2000);
		softAssert.assertAll();
	}
	// ********************************************************************************************************************

	public void enterObjectNameforSubsidiaryValue(String input) throws InterruptedException {
		String ObjectNameforSubsidiaryTextBox = "//div[4]/div/table/tr/td[3]/lightning-input/div/input";
		if (input.equalsIgnoreCase("Valid")) {
			enterValue_LocatorXpath(ObjectNameforSubsidiaryTextBox, sGLSubsidiaryObjectConfig);
		} else if (input.equalsIgnoreCase("Invalid")) {
			enterValue_LocatorXpath(ObjectNameforSubsidiaryTextBox, "invalidSub");
		} else if (input.equalsIgnoreCase("Blank")) {
			enterValue_LocatorXpath(ObjectNameforSubsidiaryTextBox, " ");
		}
	}

	public void enterSubsidiaryFieldNameOnACcValue(String input) throws InterruptedException {
		String subsidiaryFieldNameOnACcTextBox = "//label[text()='Subsidiary Field Name on ACc ']/ancestor::td[1]/following-sibling::td[1]//div//input";
		String subsidiaryFieldNameOnACcLabel = "//label[text()='Subsidiary Field Name on ACc ']";
		if (input.equalsIgnoreCase("Valid")) {
			enterValue_LocatorXpath(subsidiaryFieldNameOnACcTextBox, sGLSubsidiaryFieldConfig);
			clickonElement_Xpath(subsidiaryFieldNameOnACcLabel);
		} else if (input.equalsIgnoreCase("Invalid")) {
			enterValue_LocatorXpath(subsidiaryFieldNameOnACcTextBox, "invalidSub");
			clickonElement_Xpath(subsidiaryFieldNameOnACcLabel);
		} else if (input.equalsIgnoreCase("Blank")) {
			enterValue_LocatorXpath(subsidiaryFieldNameOnACcTextBox, " ");
			clickonElement_Xpath(subsidiaryFieldNameOnACcLabel);
		}
	}
}
