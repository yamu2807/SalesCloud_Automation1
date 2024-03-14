package TestCases.FunctionalAndRegression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import FunctionalLibrary.functionLibrary;

public class EntityUseCode extends functionLibrary {
	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void ExcelSheetName() throws Exception {
		sExcelSheetBookName = "EntityUse_Code";
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
		sGLEntityUseCode = GetValueFromExcelSheet("Entity/UseCOde");

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
		sGLEnableEntityUseCode = GetValueFromExcelSheet("EnableEntity/USeCode");
		sGLEnableUPCcode = GetValueFromExcelSheet("UPCCodeEnable");
		sGLTaxOverRide = GetValueFromExcelSheet("EnableTaxOverride");
		// TEMPORARY
		sGLAmount = GetValueFromExcelSheet("Amount");
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		sGLEnableMyTestProjecteXAMPLEExemptions = GetValueFromExcelSheet("EnableMyTestProjecteXAMPLEExemptions");
	}

	@Test(priority = 1, enabled = true, description = "EnableTaxCalc_ShipToAddress")
	public void TestCase110_EntityUseCode_A_TaxCalculation() throws Exception {
		fTaxCalculation_ValidEntityUseCode("TestCase110");
	}

	@Test(priority = 2, enabled = true, description = "EnableTaxCalc_ShipToAddress")
	public void TestCase111_EntityUseCode_B_TaxCalculation() throws Exception {
		fTaxCalculation_ValidEntityUseCode("TestCase111");
	}

	public void fTaxCalculation_ValidEntityUseCode(String testCaseID) throws Exception {

		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLItem1Code = "GenWatt Diesel 1000kW";
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
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OpportuntiesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
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
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("QuotesPostGetTax");
		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		fMenuSelection("Orders");

		// Enter data in Line Tab
		fLine_Entry("Orders");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

		softAssert.assertAll();
	}

	// Test cases for Updating/Removing Entity/Use code or ACc on
	// Opportunity Transactions
	@Test(priority = 3, enabled = true, description = "Test rail test case-C1592569, Update Entity/Use code on Transaction")
	public void TestCaseC1592569_EntityUseCode_UpdatedOnOpportunityTransaction() throws Exception {
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTestcaseId = "TestCase110";
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");
		// Change Tax now settings depending on Flags retrieved from Excel file
		fMyTestProjecteXAMPLEConfigurationSettings();

		// Enter Ship To address
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		fUpdateOrRemoveEntityUseCodeOnTransaction("Update");
		fTriggergetTax("Opportunities");
		fVerifyTaxValues("Opportunities");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OpportuntiesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		softAssert.assertAll();
	}

	@Test(priority = 4, enabled = true, description = "Test rail test case-C1592570, Remove Entity/Use code on Transaction")
	public void testCaseC1592570_EntityUseCode_RemoveFromOpportunityTransaction() throws Exception {
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTestcaseId = "TestCase112";
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");
		// Change Tax now settings depending on Flags retrieved from Excel file
		fMyTestProjecteXAMPLEConfigurationSettings();

		// Enter Ship To address
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		fUpdateOrRemoveEntityUseCodeOnTransaction("Remove");
		fTriggergetTax("Opportunities");
		fVerifyTaxValues("Opportunities");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OpportuntiesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		softAssert.assertAll();
	}

	@Test(priority = 5, enabled = true, description = "Test rail test case-C1592571, Update ACc Name With Diffrent Entity Use Code On Transaction")
	public void testCaseC1592571_UpdateACcNameWithDiffrentEntityUseCodeOnOpportunityTransaction() throws Exception {
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTestcaseId = "TestCase110";
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");
		// Change Tax now settings depending on Flags retrieved from Excel file
		fMyTestProjecteXAMPLEConfigurationSettings();

		// Enter Ship To address
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		fUpdateOrRemoveACcNameOnTransaction("UpdateAccWithDiffEntityUseCode");
		fTriggergetTax("Opportunities");
		fVerifyTaxValues("Opportunities");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OpportuntiesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		softAssert.assertAll();
	}

	@Test(priority = 6, enabled = true, description = "Test rail test case-C1592572, Update ACc Name With No Entity Use Code, On Transaction")
	public void testCaseC1592572_UpdateACcNameWithNoEntityUseCodeOnOpportunityTransaction() throws Exception {
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTestcaseId = "TestCase113";
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");
		// Change Tax now settings depending on Flags retrieved from Excel file
		fMyTestProjecteXAMPLEConfigurationSettings();

		// Enter Ship To address
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		fUpdateOrRemoveACcNameOnTransaction("UpdateAccWithNoEntityUseCode");
		fTriggergetTax("Opportunities");
		fVerifyTaxValues("Opportunities");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OpportuntiesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		softAssert.assertAll();
	}

	// Test cases for Updating/Removing Entity/Use code on Quotes Transactions
	@Test(priority = 7, enabled = true, description = "Test rail test case-C1593290, Update Entity/Use code on Transaction")
	public void testCaseC1593290_EntityUseCode_UpdatedOnQuotesTransaction() throws Exception {
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTestcaseId = "TestCase110";
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");
		// Change Tax now settings depending on Flags retrieved from Excel file
		fMyTestProjecteXAMPLEConfigurationSettings();
		// Enter Ship To address
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		fMenuSelection("newQuote");
		// Enter Quote line item
		fLine_Entry("Quotes");
		fUpdateOrRemoveEntityUseCodeOnTransaction("Update");
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("QuotesPostGetTax");
		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		softAssert.assertAll();
	}

	@Test(priority = 8, enabled = true, description = "Test rail test case-C1593293, Remove Entity/Use code on Transaction")
	public void testCaseC1593293_EntityUseCode_RemoveFromQuotesTransaction() throws Exception {
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTestcaseId = "TestCase112";
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");
		// Change Tax now settings depending on Flags retrieved from Excel file
		fMyTestProjecteXAMPLEConfigurationSettings();
		// Enter Ship To address
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		fMenuSelection("newQuote");
		// Enter Quote line item
		fLine_Entry("Quotes");
		Thread.sleep(3000);
		fUpdateOrRemoveEntityUseCodeOnTransaction("Remove");
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("QuotesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		softAssert.assertAll();
	}

	// Test cases for Updating/Removing Entity/Use code or ACc on Order
	// Transactions
	@Test(priority = 9, enabled = true, description = "Test rail test case-C1593297, Update Entity/Use code on Transaction")
	public void testCaseC1593297_EntityUseCode_UpdatedOnOrderTransaction() throws Exception {
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTestcaseId = "TestCase110";
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");
		// Change Tax now settings depending on Flags retrieved from Excel file
		fMyTestProjecteXAMPLEConfigurationSettings();
		fMenuSelection("Orders");
		// Enter data in Line Tab
		fLine_Entry("Orders");
		fUpdateOrRemoveEntityUseCodeOnTransaction("Update");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");

		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"OrdersBeforeGetTax Expected:" + sGLTotalTax + " And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OrdersPostGetTax Expected:" + sGLMyTestProjectDocStatus + " And Actual:" + sGLActualMyTestProjectDocStatus);
		softAssert.assertAll();
	}

	@Test(priority = 10, enabled = true, description = "Test rail test case-C1593298, Remove Entity/Use code on Transaction")
	public void testCaseC1593298_EntityUseCode_RemoveFromOrderTransaction() throws Exception {
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTestcaseId = "TestCase112";
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("Orders");
		// Enter data in Line Tab
		fLine_Entry("Orders");
		fUpdateOrRemoveEntityUseCodeOnTransaction("Remove");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"OrdersBeforeGetTax Expected:" + sGLTotalTax + " And Actual:" + sExpectedSalesTax);
		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");
		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OrdersPostGetTax Expected:" + sGLMyTestProjectDocStatus + " And Actual:" + sGLActualMyTestProjectDocStatus);
		softAssert.assertAll();
	}

	@Test(priority = 11, enabled = true, description = "Test rail test case-C1593299, Update ACc Name With Diffrent Entity Use Code On Transaction")
	public void testCaseC1593299_UpdateACcNameWithDiffrentEntityUseCodeOnOrderTransaction() throws Exception {
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTestcaseId = "TestCase110";
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("Orders");
		
		// Enter data in Line Tab
		fLine_Entry("Orders");
		fUpdateOrRemoveACcNameOnTransaction("UpdateAccWithDiffEntityUseCode_Order");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"OrdersBeforeGetTax Expected:" + sGLTotalTax + " And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");
		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OrdersPostGetTax Expected:" + sGLMyTestProjectDocStatus + " And Actual:" + sGLActualMyTestProjectDocStatus);
		softAssert.assertAll();
	}

	@Test(priority = 12, enabled = true, description = "Test rail test case-C1593300, Update ACc Name With No Entity Use Code, On Transaction")
	public void testCaseC1593300_UpdateACcNameWithNoEntityUseCodeOnOrderTransaction() throws Exception {
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTestcaseId = "TestCase112";
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("Orders");
		// Enter data in Line Tab
		fLine_Entry("Orders");
		fUpdateOrRemoveACcNameOnTransaction("UpdateAccWithNoEntityUseCode_Order");	
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"OrdersBeforeGetTax Expected:" + sGLTotalTax + " And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OrdersPostGetTax Expected:" + sGLMyTestProjectDocStatus + " And Actual:" + sGLActualMyTestProjectDocStatus);
		softAssert.assertAll();
	}

	// * ********************** Line level Entity use code *********************

	@Test(priority = 13, enabled = true, description = "Test rail test case-C1852479, Opportunities-Verify the Line Level Entity/Use Code mapping for multiple line items")
	public void TestCaseC1852479_LineLevelEntityUseCode_OpportunityTransaction() throws Exception {
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTestcaseId = "TestCase114";
		String testCaseID = sGLTestcaseId;
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		// fMenuSelection("TaxNowSettings");
		// Change Tax now settings depending on Flags retrieved from Excel file
		// fMyTestProjecteXAMPLEConfigurationSettings();
		// Enter Ship To address
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		// Line 2
		sGLItem1Code = "GenWatt Diesel 200kW";
		sGLTestcaseId = testCaseID + ".1";
		ValuesFromExcelSheet();
		Thread.sleep(5000);
		fLine_Entry("Opportunities");
		// Line 3
		sGLItem1Code = "GenWatt Diesel 10kW";
		sGLTestcaseId = testCaseID + ".1";
		ValuesFromExcelSheet();
		Thread.sleep(5000);
		fLine_Entry("Opportunities");
		sGLTestcaseId = testCaseID;
		ValuesFromExcelSheet();
		enterLineLevelEntityUseCode("1");
		navigateBackToObjectPage("opportunity");
		enterLineLevelEntityUseCode("2");
		navigateBackToObjectPage("opportunity");
		fTriggergetTax("Opportunities");
		fVerifyTaxValues("Opportunities");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OpportuntiesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		softAssert.assertAll();
	}

	@Test(priority = 14, enabled = true, description = "Test rail test case-C1853073,Quotes -Verify Entity use code at line level passed on to Quote transaction from Opportunity transaction")
	public void TestCaseC1853073_LineLevelEntityUseCode_QuoteTransaction() throws Exception {
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTestcaseId = "TestCase114";
		String testCaseID = sGLTestcaseId;
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		// fMenuSelection("TaxNowSettings");
		// Change Tax now settings depending on Flags retrieved from Excel file
		// fMyTestProjecteXAMPLEConfigurationSettings();
		// Enter Ship To address
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		// Line 2
		sGLItem1Code = "GenWatt Diesel 200kW";
		sGLTestcaseId = testCaseID + ".1";
		ValuesFromExcelSheet();
		Thread.sleep(5000);
		fLine_Entry("Opportunities");
		// Line 3
		sGLItem1Code = "GenWatt Diesel 10kW";
		sGLTestcaseId = testCaseID + ".1";
		ValuesFromExcelSheet();
		Thread.sleep(5000);
		fLine_Entry("Opportunities");
		sGLTestcaseId = testCaseID;
		ValuesFromExcelSheet();
		enterLineLevelEntityUseCode("1");
		navigateBackToObjectPage("opportunity");
		enterLineLevelEntityUseCode("2");
		navigateBackToObjectPage("opportunity");
		fMenuSelection("newQuote");
		String actualLine1EntityUseCodeValue = verifyLineLevelEntityUseCode("1");
		System.out.println("actualLine1EntityUseCodeValue" + actualLine1EntityUseCodeValue);
		navigateBackToObjectPage("quote");
		String actualLine2EntityUseCodeValue = verifyLineLevelEntityUseCode("2");
		System.out.println("actualLine2EntityUseCodeValue" + actualLine2EntityUseCodeValue);
		String expecedLine1EntityUseCodeValue = "B";
		String expecedLine2EntityUseCodeValue = "C";
		softAssert.assertTrue(actualLine1EntityUseCodeValue.equalsIgnoreCase(expecedLine1EntityUseCodeValue),
				"Expected Line1 EntityUseCode: " + expecedLine2EntityUseCodeValue + "And Actual Line1 EntityUseCode::"
						+ actualLine2EntityUseCodeValue);

		softAssert.assertTrue(actualLine2EntityUseCodeValue.equalsIgnoreCase(expecedLine2EntityUseCodeValue),
				"Expected Line2 EntityUseCode: " + expecedLine2EntityUseCodeValue + "And Actual Line2 EntityUseCode::"
						+ actualLine2EntityUseCodeValue);
		softAssert.assertAll();
	}

	@Test(priority = 15, enabled = true, description = "Test rail test case-C1853081, Quotes - Verify the Line Level Entity/Use Code mapping for multiple line items")
	public void TestCaseC1853081_LineLevelEntityUseCode_OpportunityTransaction() throws Exception {
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTestcaseId = "TestCase114";
		String testCaseID = sGLTestcaseId;
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		// fMenuSelection("TaxNowSettings");
		// Change Tax now settings depending on Flags retrieved from Excel file
		// fMyTestProjecteXAMPLEConfigurationSettings();
		// Enter Ship To address
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		// Line 2
		sGLItem1Code = "GenWatt Diesel 200kW";
		sGLTestcaseId = testCaseID + ".1";
		ValuesFromExcelSheet();
		Thread.sleep(5000);
		fLine_Entry("Opportunities");
		// Line 3
		sGLItem1Code = "GenWatt Diesel 10kW";
		sGLTestcaseId = testCaseID + ".1";
		ValuesFromExcelSheet();
		Thread.sleep(5000);
		fLine_Entry("Opportunities");
		sGLTestcaseId = testCaseID;
		ValuesFromExcelSheet();
		enterLineLevelEntityUseCode("1");
		navigateBackToObjectPage("opportunity");
		enterLineLevelEntityUseCode("2");
		navigateBackToObjectPage("opportunity");
		fMenuSelection("newQuote");
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("QuotesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		softAssert.assertAll();
	}

	@Test(priority = 16, enabled = true, description = "Test rail test case-C1853087, Order - Verify the Line Level Entity/Use Code mapping for multiple line items")
	public void TestCaseC1853087_LineLevelEntityUseCode_OrderTransaction() throws Exception {
		sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTestcaseId = "TestCase114";
		String testCaseID = sGLTestcaseId;
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("Orders");
		fLine_Entry("Orders");
		// Line 2
		sGLItem1Code = "GenWatt Diesel 200kW";
		sGLTestcaseId = testCaseID + ".1";
		ValuesFromExcelSheet();
		Thread.sleep(5000);
		fLine_Entry("Orders");
		// Line 3
		sGLItem1Code = "GenWatt Diesel 10kW";
		sGLTestcaseId = testCaseID + ".1";
		ValuesFromExcelSheet();
		Thread.sleep(5000);
		fLine_Entry("Orders");
		sGLTestcaseId = testCaseID;
		ValuesFromExcelSheet();
		enterLineLevelEntityUseCode("1");
		navigateBackToObjectPage("order");
		enterLineLevelEntityUseCode("2");
		navigateBackToObjectPage("order");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"OrdersTotoalTax Expected:" + sGLTotalTax + " And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OrdersPostGetTax Expected:" + sGLMyTestProjectDocStatus + " And Actual:" + sGLActualMyTestProjectDocStatus);

		softAssert.assertAll();
	}
}
