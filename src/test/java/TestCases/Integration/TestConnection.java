package TestCases.Integration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import FunctionalLibrary.functionLibrary;

public class TestConnection extends functionLibrary {

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
}
