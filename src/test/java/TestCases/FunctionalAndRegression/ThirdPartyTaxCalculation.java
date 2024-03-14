package TestCases.FunctionalAndRegression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import FunctionalLibrary.functionLibrary;

public class ThirdPartyTaxCalculation extends functionLibrary {
	@BeforeClass
	public void ExcelSheetName() throws Exception {
		sExcelSheetBookName = "ThirdPartyTaxCalculation";
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
		sGLEnableTaxCode = GetValueFromExcelSheet("EnableTaxCodeMapping");
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
		sGLEnableUPCcode = GetValueFromExcelSheet("UPCCodeEnable");
		sGLTaxOverRide = GetValueFromExcelSheet("EnableTaxOverride");
		// TEMPORARY
		sGLAmount = GetValueFromExcelSheet("Amount");
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		sGLCustExemptNo = GetValueFromExcelSheet("ExemptionNumber");
		sGLEnableMyTestProjecteXAMPLEExemptions = GetValueFromExcelSheet("EnableMyTestProjecteXAMPLEExemptions");
	}

	@Test(priority = 1, enabled = true, description = "TestRatesCO-1")
	public void TestCase150_TestRatesCO1() throws Exception {
		taxCalc_ThirdPartyCases("TestCase150", "GenWatt Diesel 1000kW");
	}

	@Test(priority = 2, enabled = true, description = "TestRatesWA-2")
	public void TestCase151_TestRatesWA2() throws Exception {
		taxCalc_ThirdPartyCases("TestCase151", "GenWatt Diesel 1000kW");
	}

	@Test(priority = 3, enabled = true, description = "TypeRule 4")
	public void TestCase152_TypeRule4() throws Exception {
		taxCalc_ThirdPartyCases("TestCase152", "GenWatt Diesel 1000kW");
	}

	@Test(priority = 4, enabled = true, description = "TestRatesCA-3")
	public void TestCase153_TestRatesCA3_TaxCalculationWithMulti_Line() throws Exception {
		taxCalcValidationWithMultiLine("TestCase153");
	}

	@Test(priority = 5, enabled = true, description = "Sourcing 10")
	public void TestCase154_Sourcing10() throws Exception {
		taxCalc_ThirdPartyCases("TestCase154", "GenWatt Diesel 1000kW");
	}

	@Test(priority = 6, enabled = true, description = "Sourcing 10")
	public void TestCase155_Sourcing10() throws Exception {
		taxCalc_ThirdPartyCases("TestCase155", "GenWatt Diesel 1000kW");
	}

	@Test(priority = 7, enabled = true, description = "TestRatesCO 1")
	public void TestCase156_TestRatesCO1() throws Exception {
		taxCalc_ThirdPartyCases("TestCase156", "GenWatt Diesel 1000kW");
	}

	@Test(priority = 8, enabled = true, description = "TestRatesCO 1")
	public void TestCase157_TestRatesCO1() throws Exception {
		taxCalc_ThirdPartyCases("TestCase157", "GenWatt Diesel 1000kW");
	}

	@Test(priority = 9, enabled = true, description = "TestRatesCO-1")
	public void TestCase158_TestRatesCO1() throws Exception {
		taxCalc_ThirdPartyCases("TestCase158", "GenWatt Diesel 1000kW");
	}

	@Test(priority = 10, enabled = true, description = "Error generated case")
	public void TestCase159_Error_generated_case() throws Exception {
		errorGenerationOnTaxValidation("TestCase159");
	}

	@Test(priority = 11, enabled = true, description = "Tiered Case")
	public void TestCase160_Tiered_Case() throws Exception {
		taxCalc_ThirdPartyCases("TestCase160", "GenWatt Diesel 1000kW");
	}

	@Test(priority = 12, enabled = true, description = "Caps Case")
	public void TestCase161_CapsCase() throws Exception {
		taxCalc_ThirdPartyCapsCase("TestCase161", "CapsCase24");
	}

	@Test(priority = 13, enabled = true, description = "TaxCalc on CAN address with Exemption number")
	public void TestCase162_TaxCalcwithExemption() throws Exception {
		taxCalc_CANAddressWithExemptionNumber("TestCase162", "GenWatt Diesel 1000kW");
	}

	/* Consolidated Function */

	public void taxCalc_ThirdPartyCases(String testCaseID, String productName) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "TaxCalcThirdParty";
		sGLItem1Code = productName;

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
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Opportunities Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OpportuntiesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OpportuntiesPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

		// End fCalculateSalesTax
		// *******************************************************************************************************************
		// Start- Transaction for Quotes
		// *****************************************************************************************************************
		fMenuSelection("newQuote");

		// Enter data in Line Tab
		fLine_Entry("Quotes");
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		/*
		 * sGLTotalTax = "140.00";
		 * softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
		 * "Quotes Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);
		 * 
		 * fVerifyStatusAndTaxOnWindow("QuotesPostGetTax");
		 * 
		 * softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(
		 * sGLMyTestProjectDocStatus), "QuotesPostGetTax Expected:" + sGLMyTestProjectDocStatus +
		 * "And Actual:" + sGLActualMyTestProjectDocStatus);
		 */
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		fMenuSelection("Orders");
		// Enter data in Line Tab
		fLine_Entry("Orders");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Orders Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OrdersPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

		softAssert.assertAll();
	}

	public void taxCalcValidationWithMultiLine(String testCaseID) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "TaxCalcThirdParty";
		sGLItem1Code = "TaxCodeNT";
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
		sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTestcaseId = testCaseID + ".1";
		ValuesFromExcelSheet();
		Thread.sleep(5000);
		fLine_Entry("Opportunities");
		fTriggergetTax("Opportunities");
		fVerifyTaxValues("Opportunities");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Opportunities Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OpportuntiesPostGetTax");
		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OpportuntiesPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

		// End fCalculateSalesTax
		// *******************************************************************************************************************
		// Start- Transaction for Quotes
		// *******************************************************************************************************************//*
		fMenuSelection("newQuote");
		sGLTestcaseId = testCaseID;
		ValuesFromExcelSheet();
		// Enter data in Line Tab
		sGLItem1Code = "TaxCodeNT";
		fLine_Entry("Quotes");
		sGLTestcaseId = testCaseID + ".1";
		ValuesFromExcelSheet();
		sGLItem1Code = "GenWatt Diesel 1000kW";
		Thread.sleep(5000);
		fLine_Entry("Quotes");
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Quotes Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("QuotesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"QuotesPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		sGLItem1Code = "TaxCodeNT";
		fMenuSelection("Orders");
		sGLTestcaseId = testCaseID;
		ValuesFromExcelSheet();
		// Enter data in Line Tab
		fLine_Entry("Orders");
		sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTestcaseId = testCaseID + ".1";
		ValuesFromExcelSheet();
		Thread.sleep(5000);
		fLine_Entry("Orders");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Orders Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);
		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");
		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OrdersPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		softAssert.assertAll();
	}

	public void errorGenerationOnTaxValidation(String testCaseID) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		sGLTestCaseName = "taxCalculation";
		sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTestcaseId = testCaseID;
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
		sGLExpectedErrorMessage = "Tax calculation cannot be determined. Zip is not valid for the state.";
		Thread.sleep(5000);
		softAssert.assertTrue(sGLActualErrorMessage.equalsIgnoreCase(sGLExpectedErrorMessage),
				"Opportunties Actual Error Message : " + sGLActualErrorMessage);
		// End fCalculateSalesTax
		// *******************************************************************************************************************
		// Start- Transaction for Quotes
		// *******************************************************************************************************************
		fMenuSelection("newQuote");
		// Enter data in Line Tab
		fLine_Entry("Quotes");
		fTriggergetTax("Quotes");
		fErrorMessageDisplayedResult();
		Thread.sleep(5000);
		softAssert.assertTrue(sGLActualErrorMessage.equalsIgnoreCase(sGLExpectedErrorMessage),
				"Opportunties Actual Error Message : " + sGLActualErrorMessage);

		// Orders
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		fMenuSelection("Orders");
		// Enter data in Line Tab
		fLine_Entry("Orders");
		fTriggergetTax("Orders");
		fErrorMessageDisplayedResult();
		Thread.sleep(5000);
		softAssert.assertTrue(sGLActualErrorMessage.equalsIgnoreCase(sGLExpectedErrorMessage),
				"Opportunties Actual Error Message : " + sGLActualErrorMessage);
		softAssert.assertAll();
	}

	public void taxCalc_ThirdPartyCapsCase(String testCaseID, String productName) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "TaxCalcThirdParty";
		sGLItem1Code = productName;

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
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Quotes Expected:" + sExpectedSalesTax + "And Actual:" + sGLTotalTax);

		fVerifyStatusAndTaxOnWindow("OpportuntiesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OpportuntiesPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

		// End fCalculateSalesTax
		// *******************************************************************************************************************
		// Start- Transaction for Quotes
		// *******************************************************************************************************************
		fMenuSelection("newQuote");

		// Enter data in Line Tab
		fLine_Entry("Quotes");
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		// sExpectedSalesTax ="3.120";
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Quotes Expected:" + sExpectedSalesTax + "And Actual:" + sGLTotalTax);

		fVerifyStatusAndTaxOnWindow("QuotesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"QuotesPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		fMenuSelection("Orders");

		// Enter data in Line Tab
		fLine_Entry("Orders");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Quotes Expected:" + sExpectedSalesTax + "And Actual:" + sGLTotalTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OrdersPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		softAssert.assertAll();
	}

	public void taxCalc_CANAddressWithExemptionNumber(String testCaseID, String productName) throws Exception {
		SoftAssert softAssert = new SoftAssert();

		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "TaxCalcThirdParty";
		sGLItem1Code = productName;

		sGLTestcaseId = testCaseID;
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");
		// Thread.sleep(2000);
		// Change Tax now settings depending on Flags retrieved from Excel file
		fMyTestProjecteXAMPLEConfigurationSettings();
		// Enter Ship To address
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		fTriggergetTax("Opportunities");
		fVerifyTaxValues("Opportunities");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Opportunities Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OpportuntiesPostGetTax");
		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OpportuntiesPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

		// End fCalculateSalesTax
		// *******************************************************************************************************************
		// Start- Transaction for Quotes
		// *******************************************************************************************************************
		fMenuSelection("newQuote");

		// Enter data in Line Tab
		fLine_Entry("Quotes");
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Quotes Expected:" + sExpectedSalesTax + "And Actual:" + sGLTotalTax);

		fVerifyStatusAndTaxOnWindow("QuotesPostGetTax");
		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"QuotesPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		fMenuSelection("Orders");

		// Enter data in Line Tab
		fLine_Entry("Orders");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Orders Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");
		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OrdersPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

		softAssert.assertAll();
	}
}
