package TestCases.FunctionalAndRegression;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import FunctionalLibrary.functionLibrary;

public class TaxCode extends functionLibrary {
	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void ExcelSheetName() throws Exception {
		sExcelSheetBookName = "TaxCode";
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
		sGLEnableTaxCode = GetValueFromExcelSheet("EnableTaxCodeMapping");
		sGLEnableUPCcode = GetValueFromExcelSheet("UPCCodeEnable");
		sGLTaxOverRide = GetValueFromExcelSheet("EnableTaxOverride");

		// TEMPORARY
		sGLAmount = GetValueFromExcelSheet("Amount");
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		System.out.println("Fetched values from Excel.");
		sGLEnableMyTestProjecteXAMPLEExemptions = GetValueFromExcelSheet("EnableMyTestProjecteXAMPLEExemptions");
	}

	@Test(priority = 1, description = "Tax code NT")
	public void TestCase40_TaxcodeNT() throws Exception {
		// Tax code NT
		taxCodeValidation("TestCase40", "TaxCodeNT");
	}

//	@Test(priority = 2, description = "Mapped-TaxCode")
	public void TestCase41_Mapped_TaxCode_1() throws Exception {
		taxCodeValidation("TestCase41", "TaxCode1");
	}

//	@Test(priority = 3, description = "Mapped-TaxCode_withSingleItem-MultipleLines")
	public void TestCase42_MappedTaxCode_1_withSingleItemMultipleLines() throws Exception {
		taxCodeValidationWithMultiLine("TestCase42", "GenWatt Diesel 1000kW");
	}

	@Test(priority = 4, description = "Mapped Diff TaxCode_withTwoItem-MultipleLines")
	public void TestCase43_Line1_TC1_Line2_TCFR_MultipleLines() throws Exception {
		taxCodeValidationWithMultiLine("TestCase43", "GenWatt Diesel 1000kW");
	}

	/*
	 * Consolidated Function
	 ***********************************************************************************/
	public void taxCodeValidation(String testCaseID, String item1Code) throws Exception {
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "fTaxCalculation_Invoice";
		sGLItem1Code = item1Code;
		sGLTestcaseId = testCaseID;
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");

		// Change Tax now settings depending on Flags retrieved from Excel file
		fMyTestProjecteXAMPLEConfigurationSettings();

		// Enter Ship To address
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		fTriggergetTax("Opportunities");
		fVerifyTaxValues("Opportunities");
		Assert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OpportuntiesPostGetTax");

		Assert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

		// End fCalculateSalesTax
		/*******************************************************************************************************************
		 * // Start- Transaction for Quotes
		 *******************************************************************************************************************/
		fMenuSelection("newQuote");

		// Enter data in Line Tab
		fLine_Entry("Quotes");
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		Assert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("QuotesPostGetTax");

		Assert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

		///////////// ""Orders""/////////////////////
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		fMenuSelection("Orders");

		// Enter data in Line Tab
		fLine_Entry("Orders");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		Assert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");

		Assert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		softAssert.assertAll();
	}

	// BELOW IS THE EXCEL SHEET CONNECTION
	public void taxCodeValidationWithMultiLine(String testCaseID, String item1Code) throws Exception {
		sGLTestCaseName = "fTaxCalculation_Invoice";
		sGLItem1Code = item1Code;
		sGLTestcaseId = testCaseID;
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");

		// Change Tax now settings depending on Flags retrieved from Excel file
		fMyTestProjecteXAMPLEConfigurationSettings();

		// Enter Ship To address
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		// Multi-lines
		sGLTestcaseId = testCaseID + ".1";
		ValuesFromExcelSheet();
		Thread.sleep(5000);
		fLine_Entry("Opportunities");
		sGLTestcaseId = testCaseID;
		ValuesFromExcelSheet();
		System.out.println("sGLTestcaseId after 42.1 OpportuntiesBeforeGetTax: " + sGLTestcaseId);
		fTriggergetTax("Opportunities");
		fVerifyTaxValues("Opportunities");
		Assert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OpportuntiesPostGetTax");
		System.out.println("sGLTestcaseId after 42.1 OpportuntiesPostGetTax: " + sGLTestcaseId);
		Assert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		/*******************************************************************************************************************
		 * // Start- Transaction for Quotes
		 *******************************************************************************************************************/
		fMenuSelection("newQuote");
		// Enter data in Line Tab
		fLine_Entry("Quotes");
		System.out.println("sGLTestcaseId after 42 Quotes: " + sGLTestcaseId);
		sGLTestcaseId = testCaseID + ".1";
		Thread.sleep(5000);
		fLine_Entry("Quotes"); //
		System.out.println("sGLTestcaseId after 42.1 Quotes: " + sGLTestcaseId);
		sGLTestcaseId = testCaseID;
		ValuesFromExcelSheet();
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		Assert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("QuotesPostGetTax");

		Assert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

		///////////// ""Orders""/////////////////////
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		fMenuSelection("Orders");
		// Enter data in Line Tab
		fLine_Entry("Orders");
		System.out.println("sGLTestcaseId after 42.1 Total Tax: " + sGLTestcaseId);
		sGLTestcaseId = testCaseID + ".1";
		ValuesFromExcelSheet();
		Thread.sleep(5000);
		fLine_Entry("Orders");
		System.out.println("sGLTestcaseId after 42.1 Total Tax: " + sGLTestcaseId);
		sGLTestcaseId = testCaseID;
		ValuesFromExcelSheet();
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		Assert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");

		Assert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		softAssert.assertAll();

	}

}
