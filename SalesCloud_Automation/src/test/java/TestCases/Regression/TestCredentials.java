package TestCases.Regression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import FunctionalLibrary.functionLibrary;

public class TestMyTestProjecteXAMPLEcred extends functionLibrary {

	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void logInFunction() throws Exception {
		fLogin_SalesForce();
	}

//	@AfterTest
	public void setvalidCredential() {
		try {
			enterValidcred();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void tearDown() {
		quit();
	}

//	@Test(priority = 1, description = "Test Connection: Invalid credential details,covers Test rail test case -C1581129")
	public void TestCaseC1581129_TestConnectionWithInValidcredSandbox() throws Exception {
		navigateToMyTestProjecteXAMPLEConfigurationPage();
		Thread.sleep(2000);
		fillACcNumberTextBox("123456");
		filllickeyTextBox("3435435435431212");
		ExecutionDelay(5);
		Thread.sleep(5000);
		clickOnlickeyLabel();
		clickOnTestConnectionButton();
	//	ExecutionDelay(5);
		// verify failed error message
		String MyTestProjecteXAMPLEMessage = fVerifyAlertPopupForAut();  //getTestConnectionFailureMessage
		//clickOnOkButton();
		String expectedMessage = "Aut failed.";
		softAssert.assertTrue(MyTestProjecteXAMPLEMessage.equalsIgnoreCase(expectedMessage),
				"Expected Error Message:" + expectedMessage + " And Actual Error Message:" + MyTestProjecteXAMPLEMessage);
		softAssert.assertAll();
	}

//	@Test(priority = 2, description = "Test Connection: Valid credential details,covers Test rail test case -C1581130,C1581150")
	public void TestCaseC1581130_C1581150_TestConnectionWithValidACcNumAndLicenceKeySandbox() throws Exception {
		navigateToMyTestProjecteXAMPLEConfigurationPage();
		Thread.sleep(2000);
		fillACcNumberTextBox(prop.getProperty("sbx.ACc.number"));
		filllickeyTextBox(prop.getProperty("sbx.licence.key"));
		ExecutionDelay(5);
		Thread.sleep(5000);
		clickOnlickeyLabel();
		clickOnTestConnectionButton();
		ExecutionDelay(5);
		// verify failed error message
		String MyTestProjecteXAMPLEMessage = getTestConnectionSuccessMessage();
		clickOnOkButton();
		String expectedMessage = "You are connected to MyTestProjecteXAMPLE";
		softAssert.assertTrue(MyTestProjecteXAMPLEMessage.equalsIgnoreCase(expectedMessage),
				"Expected Error Message:" + expectedMessage + " And Actual Error Message:" + MyTestProjecteXAMPLEMessage);
		// verify company code selection
		clickOnCompanyCodeOptionSelection();
		ExecutionDelay(5);
	//	clickOnCompanyCodeLabel();
		softAssert.assertAll();
	}

//	@Test(priority = 3, description = "Test Connection: Valid credential Production ACc,covers Test rail test case -C1581131")
	public void TestCaseC1581131TestConnectionWithValidcredProduction() throws Exception {
		navigateToMyTestProjecteXAMPLEConfigurationPage();
		Thread.sleep(2000);
		fillACcNumberTextBox(prop.getProperty("prd.ACc.number"));
		filllickeyTextBox(prop.getProperty("prd.licence.key"));
		ExecutionDelay(5);
		Thread.sleep(5000);
		clickOnlickeyLabel();
		ExecutionDelay(5);
		// Switch on Production toggle
		clickOnEnableProductionToggle();
		clickOnTestConnectionButton();
		ExecutionDelay(5);
		// verify failed error message
		String MyTestProjecteXAMPLEMessage = getTestConnectionSuccessMessage();
		ExecutionDelay(5);
		clickOnOkButton();
		String expectedMessage = "You are connected to MyTestProjecteXAMPLE";
		softAssert.assertTrue(MyTestProjecteXAMPLEMessage.equalsIgnoreCase(expectedMessage),
				"Expected Error Message:" + expectedMessage + " And Actual Error Message:" + MyTestProjecteXAMPLEMessage);
		softAssert.assertAll();
	}

//	@Test(priority = 4, description = "Test Connection: Invalid credential Production details,covers Test rail test case -C1581132")
	public void TestCaseC1581132_TestConnectionWithInvalidcredProduction() throws Exception {
		navigateToMyTestProjecteXAMPLEConfigurationPage();
		Thread.sleep(2000);
		fillACcNumberTextBox("1234556");
		filllickeyTextBox("3435435435431212");
		ExecutionDelay(5);
		Thread.sleep(5000);
		clickOnlickeyLabel();
		ExecutionDelay(5);
		// Switch on Production toggle
		clickOnEnableProductionToggle();
		clickOnTestConnectionButton();
		ExecutionDelay(5);
		// verify failed error message
		String MyTestProjecteXAMPLEMessage = getTestConnectionFailureMessage();
		ExecutionDelay(5);
		clickOnOkButton();
		String expectedMessage = "Aut failed.";
		softAssert.assertTrue(MyTestProjecteXAMPLEMessage.equalsIgnoreCase(expectedMessage),
				"Expected Error Message:" + expectedMessage + " And Actual Error Message:" + MyTestProjecteXAMPLEMessage);
		softAssert.assertAll();
	}
}
