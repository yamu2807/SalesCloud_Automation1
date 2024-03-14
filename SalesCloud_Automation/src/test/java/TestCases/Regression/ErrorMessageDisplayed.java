package TestCases.Regression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import FunctionalLibrary.functionLibrary;

public class ErrorMessageDisplayed extends functionLibrary {
	@BeforeClass
	public void ExcelSheetName() throws Exception {
		sExcelSheetBookName = "Error_Messages";
		fLogin_SalesForce();
		sGLItem1Code = "GenWatt Diesel 1000kW";
	}

	@AfterClass
	public void tearDown() {
		quit();
	}

	public void ValuesFromExcelSheet() throws Exception {
		/****************************************************************************
		 * These variables are storing the values from excel data sheet
		 *****************************************************************************/

		sGLEnableBillingAddress = GetValueFromExcelSheet("EnableBillingAddressFlag");

		sGLEnableSalesOrder = GetValueFromExcelSheet("EnableUnCommittedInvoicesFlag");
		sGLEnableTaxCalculation = GetValueFromExcelSheet("EnableTaxCalculationFlag");

		sGLEnableAddressValidation = GetValueFromExcelSheet("EnableAddressValidationFlag");
		sGLVerifyValidatedAddress = GetValueFromExcelSheet("VerifyValidatedAddressFlag");

		sGLErrorMessageDisplayed = GetValueFromExcelSheet("EnableErrorMessage");
		sGLShiptoAddress_Line1 = GetValueFromExcelSheet("ShiptoAddress_Line1");
		sGLShiptoAddress_City = GetValueFromExcelSheet("ShiptoAddress_City");
		sGLShiptoAddress_State = GetValueFromExcelSheet("ShiptoAddress_State");
		sGLShiptoAddress_Zip = GetValueFromExcelSheet("ShiptoAddress_Zip");
		sGLShipToCountryCode = GetValueFromExcelSheet("ShiptoAddress_Country");

		sGLShipFromAddress_Line1 = GetValueFromExcelSheet("ShipFromAddress_Line1");
		sGLShipFromAddress_City = GetValueFromExcelSheet("ShipFromAddress_City");
		sGLShipFromAddress_State = GetValueFromExcelSheet("ShipFromAddress_State");
		sGLShipFromAddress_Zip = GetValueFromExcelSheet("ShipFromAddress_Zip");
		sGLShipFromCountryCode = GetValueFromExcelSheet("ShipFromAddress_Country");
		sGLEnableUPCcode = GetValueFromExcelSheet("UPCCodeEnable");
		sGLTaxOverRide = GetValueFromExcelSheet("EnableTaxOverride");

		// TEMPORARY
		sGLAmount = GetValueFromExcelSheet("Amount");
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		sGLExpectedErrorMessage = GetValueFromExcelSheet("ErrorMessage");
	}

	@Test(priority = 1, enabled = true, description = "EnableTaxCalc_WithInvalidShipToAddressFor Opportunities")
	public void TestCase80_EnableTaxCalc_WithInvalidShipToAddressOpportunities() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		sGLTestCaseName = "SalesForce_ErrorMessageDisplayed";
		sGLTestcaseId = "TestCase80";
		// BELOW IS THE EXCEL SHEET CONNECTION
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");
		fMyTestProjecteXAMPLEConfigurationSettings();
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		Thread.sleep(5000);
		fTriggergetTax("Opportunities");
		fErrorMessageDisplayedResult();
		Thread.sleep(5000);
		softAssert.assertTrue(sGLActualErrorMessage.equalsIgnoreCase(sGLExpectedErrorMessage),
				"Opportunties Actual Error Message : " + sGLActualErrorMessage);
		softAssert.assertAll();
	}

	@Test(priority = 2, enabled = true, description = "EnableTaxCalc_WithInvalidShipToAddress for Quotes")
	public void TestCase81_EnableTaxCalc_WithInvalidShipToAddressQuotes() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		sGLTestCaseName = "SalesForce_ErrorMessageDisplayed";
		sGLTestcaseId = "TestCase81";
		// BELOW IS THE EXCEL SHEET CONNECTION
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");
		fMyTestProjecteXAMPLEConfigurationSettings();
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		Thread.sleep(5000);

		fMenuSelection("newQuote");

		// Enter data in Line Tab
		fLine_Entry("Quotes");
		fTriggergetTax("Quotes");
		fErrorMessageDisplayedResult();
		softAssert.assertTrue(sGLActualErrorMessage.equalsIgnoreCase(sGLExpectedErrorMessage),
				"Quotes Actual Error Message : " + sGLActualErrorMessage);
		softAssert.assertAll();
	}

	@Test(priority = 3, enabled = true, description = "EnableTaxCalc_WithInvalidShipToAddress for Orders")
	public void TestCase82_EnableTaxCalc_WithInvalidShipToAddressOrders() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		sGLTestCaseName = "SalesForce_ErrorMessageDisplayed";
		sGLTestcaseId = "TestCase82";
		// BELOW IS THE EXCEL SHEET CONNECTION
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");
		fMyTestProjecteXAMPLEConfigurationSettings();
		fMenuSelection("ACcs");
		Thread.sleep(5000);
		fMenuSelection("Orders");

		// Enter data in Line Tab
		fLine_Entry("Orders");
		fTriggergetTax("Orders");
		fErrorMessageDisplayedResult();
		Thread.sleep(5000);
		softAssert.assertTrue(sGLActualErrorMessage.equalsIgnoreCase(sGLExpectedErrorMessage),
				"Orders Orders Actual Error Message : " + sGLActualErrorMessage);
		softAssert.assertAll();
	}
}
