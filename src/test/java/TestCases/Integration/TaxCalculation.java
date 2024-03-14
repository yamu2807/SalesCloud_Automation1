package TestCases.Integration;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import FunctionalLibrary.functionLibrary;

public class TaxCalculation extends functionLibrary {
	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void ExcelSheetName() throws Exception {
		sExcelSheetBookName = "TaxCalculation";
		fLogin_SalesForce();
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
		sGLEnableUPCcode = GetValueFromExcelSheet("UPCCodeEnable");
		sGLTaxOverRide = GetValueFromExcelSheet("EnableTaxOverride");

		// Ship to address
		sGLShiptoAddress_Line1 = GetValueFromExcelSheet("ShiptoAddress_Line1");
		sGLShiptoAddress_City = GetValueFromExcelSheet("ShiptoAddress_City");
		sGLShiptoAddress_State = GetValueFromExcelSheet("ShiptoAddress_State");
		sGLShiptoAddress_Zip = GetValueFromExcelSheet("ShiptoAddress_Zip");
		sGLShipToCountryCode = GetValueFromExcelSheet("ShiptoAddress_Country");

		// Ship From address
		sGLShipFromAddress_Line1 = GetValueFromExcelSheet("ShipFromAddress_Line1");
		sGLShipFromAddress_City = GetValueFromExcelSheet("ShipFromAddress_City");
		sGLShipFromAddress_State = GetValueFromExcelSheet("ShipFromAddress_State");
		sGLShipFromAddress_Zip = GetValueFromExcelSheet("ShipFromAddress_Zip");
		sGLShipFromCountryCode = GetValueFromExcelSheet("ShipFromAddress_Country");

		// Secondary Ship to address
		sGLSecondaryShiptoAddress_Line1 = GetValueFromExcelSheet("SecondaryShipToAddress_Line1");
		sGLSecondaryShiptoAddress_City = GetValueFromExcelSheet("SecondaryShipToAddress_City");
		sGLSecondaryShiptoAddress_State = GetValueFromExcelSheet("SecondaryShipToAddress_State");
		sGLSecondaryShiptoAddress_Zip = GetValueFromExcelSheet("SecondaryShipToAddress_Zip");
		sGLSecondaryShipToAddressCountryCode = GetValueFromExcelSheet("SecondaryShipToAddress_Country");

		// Flags
		sGLACcShipTo = GetValueFromExcelSheet("ACcShipTo");
		sGLACcBillTo = GetValueFromExcelSheet("ACcBillTo");
		sGLQuotesShipTo = GetValueFromExcelSheet("QuotesShipTo");
		sGLQuotesBillTo = GetValueFromExcelSheet("QuotesBillTo");
		sGLOrderShipTo = GetValueFromExcelSheet("OrderShipTo");
		sGLOrderBillTo = GetValueFromExcelSheet("OrderBillTo");

		// Contacts Address Tax calc
		sGLOpportunitiesContactsShipToFlag = GetValueFromExcelSheet("OpportunitiesContactsShipToFlag");
		sGLOpportunitiesContactsBillToFlag = GetValueFromExcelSheet("OpportunitiesContactsBillToFlag");

		sGLErrorMessageDisplayed = GetValueFromExcelSheet("EnableErrorMessage");
		// TEMPORARY
		sGLAmount = GetValueFromExcelSheet("Amount");
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");

		sGLEnableACcNumber = GetValueFromExcelSheet("ACcNumber");
		sGLEnableUseACcNameforCustIdentification = GetValueFromExcelSheet(
				"EnableUseACcNameforCustIdentification");
		sGLEnableMyTestProjecteXAMPLEExemptions = GetValueFromExcelSheet("EnableMyTestProjecteXAMPLEExemptions");
	}
	// Tax Cal on Primary Shipping Address ACc

	@Test(priority = 1, description = "EnableTaxCalc ACc Primary ShipTo Address CAN Uncommit")
	public void TestCase1_EnableTaxCalc_ACc_PrimaryShipToAddressUncommit() throws Exception {
		EnableTaxCalc_ACc_Primary("TestCase3", "GenWatt Diesel 1000kW");
	}

	@Test(priority = 2, description = "EnableTaxCalc ACc Primary BillTo Address DenverCity SalesOrde")
	public void TestCase2_EnableTaxCalc_Quotes_PrimaryShipToAddressUncommit() throws Exception {
		EnableTaxCalc_Quotes_Primary("TestCase7");
	}



	/******************************************************************************************************
	 * //Common function for ACc Primary Address
	 */
	public void EnableTaxCalc_ACc_Primary(String testCaseID, String productName) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		sGLTestCaseName = "taxCalculation";
		sGLItem1Code = productName;
		sGLTestcaseId = testCaseID;
		// BELOW IS THE EXCEL SHEET CONNECTION
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");
		Thread.sleep(5000);
		// Change Tax now settings depending on Flags retrieved from Excel file
		fMyTestProjecteXAMPLEConfigurationSettings();
		// Enter Ship To address
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		Thread.sleep(5000);
		fTriggergetTax("Opportunities");
		fVerifyTaxValues("Opportunities");
		Thread.sleep(5000);
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Opportunities Expected:" + sGLTotalTax + " And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OpportuntiesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OpportuntiesPostGetTax Expected:" + sGLMyTestProjectDocStatus + " And Actual:" + sGLActualMyTestProjectDocStatus);

		// End fCalculateSalesTax
		/*******************************************************************************************************************
		 * // Start- Transaction for Quotes
		 ********************************************************************************************************************/
		fMenuSelection("newQuote");

		// Enter data in Line Tab
		fLine_Entry("Quotes");
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		sGLTotalTax = "240.00";
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Quotes Expected:" + sGLTotalTax + " And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("QuotesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"QuotesPostGetTax Expected:" + sGLMyTestProjectDocStatus + " And Actual:" + sGLActualMyTestProjectDocStatus);

		///////////// ""Orders""/////////////////////
		fMenuSelection("Orders");

		// Enter data in Line Tab
		fLine_Entry("Orders");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		sGLTotalTax = "72.50";
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"OrdersTotoalTax Expected:" + sGLTotalTax + " And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OrdersPostGetTax Expected:" + sGLMyTestProjectDocStatus + " And Actual:" + sGLActualMyTestProjectDocStatus);

		softAssert.assertAll();
	}

	/*******************************************************************************************************
	 * //Common function for Quotes Primary Address
	 */

	public void EnableTaxCalc_Quotes_Primary(String testCaseID) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		sGLTestCaseName = "taxCalculation";
		sGLItem1Code = "InvalidUPCCode";
		sGLTestcaseId = testCaseID;
		// BELOW IS THE EXCEL SHEET CONNECTION
		ValuesFromExcelSheet();
		Thread.sleep(2000);
//		fMenuSelection("TaxNowSettings");

		// Change Tax now settings depending on Flags retrieved from Excel file
//		fMyTestProjecteXAMPLEConfigurationSettings();

		// Enter Ship To address
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		Thread.sleep(5000);
		fTriggergetTax("Opportunities");
		fVerifyTaxValues("Opportunities");
		//sGLTotalTax = "72.50";
		Thread.sleep(5000);
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Opportunities Expected:" + sExpectedSalesTax + " And Actual:" + sGLTotalTax);
		fVerifyStatusAndTaxOnWindow("OpportuntiesPostGetTax");
		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OpportuntiesPostGetTax Expected:" + sGLMyTestProjectDocStatus + " And Actual:" + sGLActualMyTestProjectDocStatus);

		// End fCalculateSalesTax
		/*******************************************************************************************************************
		 * // Start- Transaction for Quotes
		 ********************************************************************************************************************/
		// sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		fMenuSelection("newQuote");

		// Enter data in Line Tab
		fLine_Entry("Quotes");
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		sGLTotalTax = "240.00";
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Quotes Expected:" + sGLTotalTax + " And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("QuotesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"QuotesPostGetTax Expected:" + sGLMyTestProjectDocStatus + " And Actual:" + sGLActualMyTestProjectDocStatus);
		///////////// ""Orders""/////////////////////
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		fMenuSelection("Orders");
		// Enter data in Line Tab
		fLine_Entry("Orders");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		sGLTotalTax = "72.50";
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"OrdersSalesTax Expected:" + sGLTotalTax + " And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OrdersPostGetTax Expected:" + sGLMyTestProjectDocStatus + " And Actual:" + sGLActualMyTestProjectDocStatus);

		softAssert.assertAll();
	}
}
