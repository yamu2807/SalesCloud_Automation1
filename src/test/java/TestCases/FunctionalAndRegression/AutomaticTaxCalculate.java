package TestCases.FunctionalAndRegression;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import FunctionalLibrary.functionLibrary;

public class AutomaticTaxCalculate extends functionLibrary {

	@BeforeClass
	public void ExcelSheetName() throws Exception {
		sExcelSheetBookName = "AutomaticTaxCalculation";
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
		sGLErrorMessageDisplayed = GetValueFromExcelSheet("EnableErrorMessage");

		// TEMPORARY
		sGLAmount = GetValueFromExcelSheet("Amount");
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		sGLEnableTriggerForGetTax = GetValueFromExcelSheet("TotalTax");
		sGLExpectedErrorMessage = GetValueFromExcelSheet("ErrorMessage");
		sGLEnableUPCcode = GetValueFromExcelSheet("UPCCodeEnable");
		sGLTaxOverRide = GetValueFromExcelSheet("EnableTaxOverride");
		sGLEnableMyTestProjecteXAMPLEExemptions = GetValueFromExcelSheet("EnableMyTestProjecteXAMPLEExemptions");
	}

	// Automatic Tax Calculation test cases covers both Workflow rule and
	// Triggers test rail test cases
	@Test(priority = 1, enabled = true)
	public void AutomaticTaxCalculation_SalesOrder_Opportunities() throws Exception {
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "AutomaticTaxCalculation_SalesOrder_Opportunities";
		sGLTestcaseId = "TestCase165";
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");

		// Change Tax now settings depending on Flags retrieved from Excel file
		fMyTestProjecteXAMPLEConfigurationSettings();

		// Enter Ship To address
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		fCheckCalculateTaxAndCommitCheckBox("CheckCalculateTaxCheckBox");
		fRefreshPage();
		fRefreshPage();
		fVerifyTaxValues("Opportunities");
		Assert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		// sGLActualMyTestProjectDocStatus = "Committed";
		fVerifyStatusAndTaxOnWindow("OpportuntiesPostGetTax");

		Assert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
	}

	@Test(priority = 2, enabled = true)
	public void AutomaticTaxCalculation_SalesOrder_Quotes() throws Exception {
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "AutomaticTaxCalculation_SalesOrder_Quotes";
		sGLTestcaseId = "TestCase166";
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		fMenuSelection("newQuote");
		Thread.sleep(2000);
		// Enter data in Line Tab
		fLine_Entry("Quotes");
		fCheckCalculateTaxAndCommitCheckBox("CheckCalculateTaxCheckBox");
		fRefreshPage();
		fRefreshPage();
		fVerifyTaxValues("Quotes");
		Assert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("QuotesPostGetTax");

		Assert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

	}

	@Test(priority = 3, enabled = true)
	public void AutomaticTaxCalculation_SalesOrder_Orders() throws Exception {
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "AutomaticTaxCalculation_SalesOrder_Orders";
		sGLTestcaseId = "TestCase167";
		ValuesFromExcelSheet();
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		Thread.sleep(2000);
		fMenuSelection("Orders");

		// Enter data in Line Tab
		fLine_Entry("Orders");
		fCheckCalculateTaxAndCommitCheckBox("CheckCalculateTaxCheckBox");
		fRefreshPage();
		fRefreshPage();
		fVerifyTaxValues("Orders");
		Assert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);
		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");
		Assert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

	}

	@Test(priority = 4, enabled = true)
	public void AutomaticTaxCalculation_Uncommit_Orders() throws Exception {
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "AutomaticTaxCalculation_SalesOrder";
		sGLTestcaseId = "TestCase170";
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		fMenuSelection("Orders");
		// Enter data in Line Tab
		fLine_Entry("Orders");
		fCheckCalculateTaxAndCommitCheckBox("CheckCalculateTaxCheckBox");
		fRefreshPage();
		fRefreshPage();
		fVerifyTaxValues("Orders");
		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");
		Assert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

	}

	@Test(priority = 5, enabled = true)
	public void AutomaticTaxCalculation_Commit_Orders() throws Exception {
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "AutomaticTaxCalculation_Commit_Orders";
		sGLTestcaseId = "TestCase173";
		ValuesFromExcelSheet();
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		Thread.sleep(2000);
		fMenuSelection("Orders");
		// Enter data in Line Tab
		fLine_Entry("Orders");
		fCheckCalculateTaxAndCommitCheckBox("CheckCommitCheckBox");
		fRefreshPage();
		fRefreshPage();
		fVerifyTaxValues("Orders");
		fVerifyStatusAndTaxOnWindow("OrdersPostCommitTax");
		Assert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

	}

	/**
	 * *********************************************************************************************
	 * Disable setting scenarios
	 * *********************************************************************************************
	 **/

	@Test(priority = 6, enabled = true)
	public void AutomaticTaxCalculation_DisableTaxCalcSettings_Opportunities() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "AutomaticTaxCalculation_DisableTaxCalcSettings_Opportunities";
		sGLTestcaseId = "TestCase174";
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		// Call the function here
		fMenuSelection("TaxNowSettings");
		// Change Tax now settings depending on Flags retrieved from Excel file
		fMyTestProjecteXAMPLEConfigurationSettings();
		// Enter Ship To address
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		fCheckCalculateTaxAndCommitCheckBox("CheckCalculateTaxCheckBox");
		fRefreshPage();
		fRefreshPage();
		Thread.sleep(2000);
		fRefreshPage();
		fErrorMessageDisplayedResult();
		Thread.sleep(5000);
		String opportunitiesQuotesError = "Tax calculation is disabled in MyTestProjecteXAMPLE configuration,(AVA_SFCLOUD)";
		softAssert.assertTrue(sGLActualErrorMessage.contentEquals(opportunitiesQuotesError),
				"Opportunties Actual Error Message : " + sGLActualErrorMessage);
		softAssert.assertAll();
	}

	@Test(priority = 7, enabled = true)
	public void AutomaticTaxCalculation_DisableTaxCalcSettings_Quotes() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "AutomaticTaxCalculation_DisableTaxCalcSettings_SalesOrder_Quotes";
		sGLTestcaseId = "TestCase175";
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		fMenuSelection("newQuote");
		Thread.sleep(2000);
		// Enter data in Line Tab
		fLine_Entry("Quotes");
		fCheckCalculateTaxAndCommitCheckBox("CheckCalculateTaxCheckBox");
		fRefreshPage();
		fRefreshPage();
		fRefreshPage();
		fErrorMessageDisplayedResult();
		Thread.sleep(5000);
		String opportunitiesQuotesError = "Tax calculation is disabled in MyTestProjecteXAMPLE configuration,(AVA_SFCLOUD)";
		softAssert.assertTrue(sGLActualErrorMessage.contentEquals(opportunitiesQuotesError),
				"Opportunties Actual Error Message : " + sGLActualErrorMessage);
		softAssert.assertAll();
	}

	@Test(priority = 8, enabled = true)
	public void AutomaticTaxCalculation_DisableTaxCalcSettings_Orders() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "AutomaticTaxCalculation_SalesOrder_Orders";
		sGLTestcaseId = "TestCase176";
		ValuesFromExcelSheet();
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		Thread.sleep(2000);
		fMenuSelection("Orders");
		// Enter data in Line Tab
		fLine_Entry("Orders");
		fCheckCalculateTaxAndCommitCheckBox("CheckCalculateTaxCheckBox");
		fRefreshPage();
		fRefreshPage();
		fRefreshPage();
		fErrorMessageDisplayedResult();
		Thread.sleep(5000);
		String opportunitiesQuotesError = "Tax calculation is disabled in MyTestProjecteXAMPLE configuration,(AVA_SFCLOUD)";
		softAssert.assertTrue(sGLActualErrorMessage.contentEquals(opportunitiesQuotesError),
				"Opportunties Actual Error Message : " + sGLActualErrorMessage);
		softAssert.assertAll();
	}

	/**
	 * *********************************************************************************************
	 * Invalid Address scenarios
	 * *********************************************************************************************
	 **/

	@Test(priority = 9, enabled = true, description = "EnableTaxCalc_WithInvalidShipToAddressFor Opportunities")
	public void AutomaticTaxCalculation_OnInvalidAddress_Opportunities() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "AutomaticTaxCalculation_OnInvalidAddres_Opportunities";
		sGLTestcaseId = "TestCase177";
		// BELOW IS THE EXCEL SHEET CONNECTION
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");
		fMyTestProjecteXAMPLEConfigurationSettings();
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		Thread.sleep(5000);
		fCheckCalculateTaxAndCommitCheckBox("CheckCalculateTaxCheckBox");
		fRefreshPage();
		fRefreshPage();
		fErrorMessageDisplayedResult();
		softAssert.assertTrue(sGLActualErrorMessage.equalsIgnoreCase(sGLExpectedErrorMessage),
				"Quotes Actual Error Message : " + sGLActualErrorMessage);
		softAssert.assertAll();
	}

	@Test(priority = 10, enabled = true, description = "EnableTaxCalc_WithInvalidShipToAddress for Quotes")
	public void AutomaticTaxCalculation_OnInvalidAddress_Quotes() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "AutomaticTaxCalculation_OnInvalidAddress_Quotes";
		sGLTestcaseId = "TestCase178";
		// BELOW IS THE EXCEL SHEET CONNECTION
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		Thread.sleep(5000);
		fMenuSelection("newQuote");
		// Enter data in Line Tab
		fLine_Entry("Quotes");
		fCheckCalculateTaxAndCommitCheckBox("CheckCalculateTaxCheckBox");
		fRefreshPage();
		fRefreshPage();
		fErrorMessageDisplayedResult();
		softAssert.assertTrue(sGLActualErrorMessage.equalsIgnoreCase(sGLExpectedErrorMessage),
				"Quotes Actual Error Message : " + sGLActualErrorMessage);
		softAssert.assertAll();
	}

	@Test(priority = 11, enabled = true, description = "EnableTaxCalc_WithInvalidShipToAddress for Orders")
	public void AutomaticTaxCalculation_OnInvalidAddress_Orders() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "AutomaticTaxCalculation_OnInvalidAddress_Orders";
		sGLTestcaseId = "TestCase179";
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
		fCheckCalculateTaxAndCommitCheckBox("CheckCalculateTaxCheckBox");
		fRefreshPage();
		fRefreshPage();
		fErrorMessageDisplayedResult();
		softAssert.assertTrue(sGLActualErrorMessage.equalsIgnoreCase(sGLExpectedErrorMessage),
				"Quotes Actual Error Message : " + sGLActualErrorMessage);
		softAssert.assertAll();
	}

	@Test(priority = 12, enabled = true)
	public void AutomaticTaxCalculation_Commit_Orders_SaveTrasaction_Off() throws Exception {
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "AutomaticTaxCalculation_Commit_Orders";
		sGLTestcaseId = "TestCase170";
		ValuesFromExcelSheet();
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");
		fMyTestProjecteXAMPLEConfigurationSettings();
		fMenuSelection("ACcs");
		Thread.sleep(5000);
		fMenuSelection("Orders");
		// Enter data in Line Tab
		fLine_Entry("Orders");
		fCheckCalculateTaxAndCommitCheckBox("CheckCommitCheckBox");
		fRefreshPage();
		fRefreshPage();
		fVerifyTaxValues("Orders");
		Assert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);
		fVerifyStatusAndTaxOnWindow("OrdersPostCommitTax");
		Assert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

	}
}
