package TestCases.FunctionalAndRegression;

import java.util.List;

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

	@Test(priority = 3, description = "EnableTaxCalc Quotes Primary BillTo Address DenverCity Uncommit")
	public void TestCase3_EnableTaxCalc_Quotes_PrimaryBillToAddressUncommit() throws Exception {
		EnableTaxCalc_Quotes_Primary("TestCase8");
	}

	// Tax Cal on Primary Shipping Address Order
	@Test(priority = 4, description = "EnableTaxCalc Order Primary ShipTo Address Uncommit")
	public void TestCase4_EnableTaxCalc_Order_PrimaryShipToAddressUncommit() throws Exception {
		EnableTaxCalc_Order_Primary("TestCase11");
	}

	@Test(priority = 5, description = "EnableTaxCalc Order Primary BillTo Address Uncommit")
	public void TestCase5_EnableTaxCalc_Order_PrimaryBillToAddressUncommit() throws Exception {
		EnableTaxCalc_Order_Primary("TestCase12");
	}

	// Multiline Tax Calc
	@Test(priority = 6, description = "EnableTaxCalc ACc OnlyShipToAddress DenverCity Uncommit")
	public void TestCase6_EnableTaxCalc_ACc_WithMultiLine() throws Exception {
		taxCalcValidationWithMultiLine("TestCase13");
	}

/*	// Disable Tax Calc
	@Test(priority = 7, description = "EnableTaxCalc Disable Address Settings ACc")
	public void TestCase7_EnableTaxCalc_DisableTaxSettings_ACc() throws Exception {
		disabledtaxValidation("TestCase14");
	}

	// Tax Cal on Only Shipping/Billing Address ACc
	@Test(priority = 8, description = "TestCase24(EnableTaxCalc ACc OnlyShipTo Address Uncommit)")
	public void TestCase8_EnableTaxCalc_ACc_OnlyShipToAddressUncommit() throws Exception {
		EnableTaxCalc_ACc_Primary("TestCase24", "GenWatt Diesel 1000kW");
	}

	@Test(priority = 9, description = "EnableTaxCalc ACc Only BillTo Address CAN Uncommit")
	public void TestCase9_EnableTaxCalc_ACc_OnlyBillToAddressUncommit() throws Exception {
		EnableTaxCalc_ACc_Primary("TestCase25", "GenWatt Diesel 1000kW");
	}

	@Test(priority = 10, description = "EnableTaxCalc Product With DescGreater Than 2048Char")
	public void TestCase10_EnableTaxCalc_ProductWithDescGreaterThan2048Char() throws Exception {
		EnableTaxCalc_ACc_Primary("TestCase26", "ProductCodeGreaterThan2048Char");
	}

	// Product with desc greater than 2048 char and Product code length greater
	// than 60 Char
	@Test(priority = 11, description = "ProductCodeGreaterThan60Char")
	public void TestCase11_EnableTaxCalc_ProductCodeGreaterThan60Char() throws Exception {
		EnableTaxCalc_ACc_Primary("TestCase27", "ProductCodeGreaterThan60 Char");
	}

	@Test(priority = 12, description = "ACcNon_NorthAmericaAddress")
	public void TestCase12_EnableTaxCalc_ACcNon_NorthAmericaAddressShipTo() throws Exception {
		EnableTaxCalc_NonNorthAmerricaAddress("TestCase30", "GenWatt Diesel 1000kW");
	}

	// Use ACc Name for Cust Identification
	@Test(priority = 13, description = "Use ACc Name for Cust Identification")
	public void TestCase13_EnableTaxCalc_ACcNumber_Yes_UseACcNumberForCustIdentification() throws Exception {
		EnableTaxCalc_ACcNumber_UseACcNameForCustIdentification("TestCase31", "GenWatt Diesel 1000kW");
	}

	@Test(priority = 14, description = "Use ACc Name for Cust Identification")
	public void TestCase14_EnableTaxCalc_ACcNumber_No_UseACcNameForCustIdentification() throws Exception {
		EnableTaxCalc_ACcNumber_UseACcNameForCustIdentification("TestCase32", "GenWatt Diesel 1000kW");
	}

	// Salestax Details field validation
	@Test(priority = 15, description = "SalesTaxDetailsfieldValidation")
	public void TestCase15_EnableTaxCalc_SalesTaxDetailsfieldValidation() throws Exception {
		EnableTaxCalc_SalestaxDetailsfieldValidation("TestCase33", "GenWatt Diesel 1000kW");
	}

	@Test(priority = 16, description = "EnableTaxCalc ACc Primary ShipTo Address CAN SalesOrder")
	public void TestCase16_EnableTaxCalc_ACc_PrimaryShipToAddressSalesOrder() throws Exception {
		EnableTaxCalc_ACc_Primary("TestCase3", "GenWatt Diesel 1000kW");
	}
	
	*/


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
		sGLTotalTax = "0.00";

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
		sGLTotalTax = "77.50";
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
		//sGLTotalTax = "77.50";
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
		// sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		fMenuSelection("newQuote");

		// Enter data in Line Tab
		fLine_Entry("Quotes");
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		sGLTotalTax = "0.00";
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
		//sGLTotalTax = "77.50";
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"OrdersSalesTax Expected:" + sGLTotalTax + " And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OrdersPostGetTax Expected:" + sGLMyTestProjectDocStatus + " And Actual:" + sGLActualMyTestProjectDocStatus);

		softAssert.assertAll();
	}

	/*******************************************************************************************************
	 * //Common function for Order Primary Address
	 */
	public void EnableTaxCalc_Order_Primary(String testCaseID) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		sGLTestCaseName = "taxCalculation";
		sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTestcaseId = testCaseID;
		// BELOW IS THE EXCEL SHEET CONNECTION
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");
		// Change Tax now settings depending on Flags retrieved from Excel file
		fMyTestProjecteXAMPLEConfigurationSettings();
		// Enter Ship To address
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		Thread.sleep(5000);
		fTriggergetTax("Opportunities");
		fVerifyTaxValues("Opportunities");
		sGLTotalTax = "77.50";
		Thread.sleep(5000);
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Opportunities Expected:" + sGLTotalTax + " And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OpportuntiesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OpportuntiesPostGetTax Expected:" + sGLMyTestProjectDocStatus + " And Actual:" + sGLActualMyTestProjectDocStatus);

		/*******************************************************************************************************************
		 * // Start- Transaction for Quotes
		 ********************************************************************************************************************/
		fMenuSelection("newQuote");
		// Enter data in Line Tab
		fLine_Entry("Quotes");
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		sGLTotalTax = "0.00";
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
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"OrdersSalesTax Expected:" + sGLTotalTax + " And Actual:" + sExpectedSalesTax);
		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");
		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OrdersPostGetTax Expected:" + sGLMyTestProjectDocStatus + " And Actual:" + sGLActualMyTestProjectDocStatus);
		softAssert.assertAll();
	}

	/*******************************************************************************************************
	 * //Common function for Disable Tax Validation fields
	 */

	public void disabledtaxValidation(String testCaseID) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		sGLTestCaseName = "taxCalculation";
		sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTestcaseId = testCaseID;
		// BELOW IS THE EXCEL SHEET CONNECTION
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");
		// Change Tax now settings depending on Flags retrieved from Excel file
		fMyTestProjecteXAMPLEConfigurationSettings();
		// Enter Ship To address
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		Thread.sleep(5000);
		fTriggergetTax("Opportunities");
		fErrorMessageDisplayedResult();
		Thread.sleep(5000);
		String opportunitiesQuotesError = "Tax calculation is disabled in MyTestProjecteXAMPLE configuration,(AVA_SFCLOUD)";
		softAssert.assertTrue(sGLActualErrorMessage.contentEquals(opportunitiesQuotesError),
				"Opportunties Actual Error Message : " + sGLActualErrorMessage);

		// *******************************************************************************************************************
		// Start- Transaction for Quotes
		// *******************************************************************************************************************
		// */
		fMenuSelection("newQuote");
		// Enter data in Line Tab
		fLine_Entry("Quotes");
		fTriggergetTax("Quotes");
		fErrorMessageDisplayedResult();
		softAssert.assertTrue(sGLActualErrorMessage.contentEquals(opportunitiesQuotesError),
				"Quotes Quotes Actual Error Message : " + sGLActualErrorMessage);
		// Start- Transaction for Orders
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		fMenuSelection("Orders");
		// Enter data in Line Tab
		fLine_Entry("Orders");
		fTriggergetTax("Orders");
		fErrorMessageDisplayedResult();
		Thread.sleep(5000);
		String ordersError = "Tax calculation is disabled in MyTestProjecteXAMPLE configuration,(AVA_SFCLOUD)";
		softAssert.assertTrue(sGLActualErrorMessage.contentEquals(ordersError),
				"Orders Orders Actual Error Message : " + sGLActualErrorMessage);
		softAssert.assertAll();

	}

	/******************************************************************************************************
	 * //Common function for ACc Primary Address with Multiple lines
	 */
	public void taxCalcValidationWithMultiLine(String testCaseID) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "TaxCalcThirdParty";
		sGLItem1Code = "GenWatt Diesel 1000kW";

		sGLTestcaseId = testCaseID;
		ValuesFromExcelSheet();
		Thread.sleep(2000);
//		fMenuSelection("TaxNowSettings");

		// Change Tax now settings depending on Flags retrieved from Excel file
//		fMyTestProjecteXAMPLEConfigurationSettings();

		// Enter Ship To address
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		// Multi-lines
		// sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTestcaseId = testCaseID + ".1";
		ValuesFromExcelSheet();
		Thread.sleep(5000);
		fLine_Entry("Opportunities");
		sGLTestcaseId = testCaseID;
		ValuesFromExcelSheet();
		fTriggergetTax("Opportunities");
		fVerifyTaxValues("Opportunities");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Opportunities Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OpportuntiesPostGetTax");
		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OpportuntiesPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

		// End fCalculateSalesTax
		/*******************************************************************************************************************
		 * // Start- Transaction for Quotes
		 */
		fMenuSelection("newQuote");
		// Enter data in Line Tab
		sGLItem1Code = "GenWatt Diesel 1000kW";
		fLine_Entry("Quotes");
		sGLTestcaseId = testCaseID + ".1";
		ValuesFromExcelSheet();
		Thread.sleep(5000);
		fLine_Entry("Quotes");
		sGLTestcaseId = testCaseID;
		ValuesFromExcelSheet();
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		sGLTotalTax = "280.00";
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Quotes Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("QuotesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"QuotesPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

		///////////// ""Orders""/////////////////////
		sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		fMenuSelection("Orders");
		// Enter data in Line Tab
		fLine_Entry("Orders");
		sGLTestcaseId = testCaseID + ".1";
		ValuesFromExcelSheet();
		Thread.sleep(5000);
		fLine_Entry("Orders");
		sGLTestcaseId = testCaseID;
		ValuesFromExcelSheet();
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		sGLTotalTax = "155.00";
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Orders Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);
		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");
		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OrdersPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		softAssert.assertAll();
	}

	public void EnableTaxCalc_Connect_5940(String testCaseID) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		sGLTestCaseName = "taxCalculation";
		sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTestcaseId = testCaseID;
		// BELOW IS THE EXCEL SHEET CONNECTION
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");
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
		 */
		fMenuSelection("newQuote");
		// Enter data in Line Tab
		fLine_Entry("Quotes");
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Quotes Expected:" + sGLTotalTax + " And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("QuotesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"QuotesPostGetTax Expected:" + sGLMyTestProjectDocStatus + " And Actual:" + sGLActualMyTestProjectDocStatus);

		///////////// ""Orders""/////////////////////
		// sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		fMenuSelection("Orders");
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		// Enter data in Line Tab
		fLine_Entry("Orders");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"OrdersSalesTax Expected:" + sGLTotalTax + " And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OrdersPostGetTax Expected:" + sGLMyTestProjectDocStatus + " And Actual:" + sGLActualMyTestProjectDocStatus);

		softAssert.assertAll();
	}

	public void EnableTaxCalc_ACcNumber_UseACcNameForCustIdentification(String testCaseID, String productName)
			throws Exception {
		SoftAssert softAssert = new SoftAssert();
		sGLTestCaseName = "taxCalculation";
		sGLTestcaseId = testCaseID;
		sGLItem1Code = productName;
		// BELOW IS THE EXCEL SHEET CONNECTION
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");

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
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
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

	public void EnableTaxCalc_SalestaxDetailsfieldValidation(String testCaseID, String productName) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		sGLTestCaseName = "taxCalculation";
		sGLItem1Code = productName;
		sGLTestcaseId = testCaseID;
		// BELOW IS THE EXCEL SHEET CONNECTION
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");

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

		Thread.sleep(3000);
		List<String> salesTaxDetailsfieldValues = fSalesTaxDetailsfieldValidation();
		System.out.println("salesTaxDetailsfieldValues=" + salesTaxDetailsfieldValues);
		softAssert.assertEquals(salesTaxDetailsfieldValues.get(0).length() > 0, true);
		softAssert.assertEquals(salesTaxDetailsfieldValues.get(1).length() > 0, true);
		softAssert.assertEquals(salesTaxDetailsfieldValues.get(2).length() > 0, true);

		// End fCalculateSalesTax
		/*******************************************************************************************************************
		 * // Start- Transaction for Quotes
		 */
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

		Thread.sleep(3000);
		salesTaxDetailsfieldValues = fSalesTaxDetailsfieldValidation();
		System.out.println("salesTaxDetailsfieldValues=" + salesTaxDetailsfieldValues);
		softAssert.assertEquals(salesTaxDetailsfieldValues.get(0).length() > 0, true);
		softAssert.assertEquals(salesTaxDetailsfieldValues.get(1).length() > 0, true);
		softAssert.assertEquals(salesTaxDetailsfieldValues.get(2).length() > 0, true);
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

		Thread.sleep(3000);

		salesTaxDetailsfieldValues = fSalesTaxDetailsfieldValidation();
		System.out.println("salesTaxDetailsfieldValues=" + salesTaxDetailsfieldValues);
		softAssert.assertEquals(salesTaxDetailsfieldValues.get(0).length() > 0, true);
		softAssert.assertEquals(salesTaxDetailsfieldValues.get(1).length() > 0, true);
		softAssert.assertEquals(salesTaxDetailsfieldValues.get(2).length() > 0, true);

		softAssert.assertAll();
	}

	public void EnableTaxCalc_ErroValidation(String testCaseID, String productName) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		sGLTestCaseName = "taxCalculation";
		sGLItem1Code = productName;
		sGLTestcaseId = testCaseID;
		// BELOW IS THE EXCEL SHEET CONNECTION
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");

		// Change Tax now settings depending on Flags retrieved from Excel file
		fMyTestProjecteXAMPLEConfigurationSettings();

		// Enter Ship To address
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		Thread.sleep(5000);
		fTriggergetTax("Opportunities");
		fErrorMessageDisplayedResult();
		Thread.sleep(5000);
		softAssert.assertTrue(sGLActualErrorMessage.contains("Error"),
				"Opportunties Actual Error Message : " + sGLActualErrorMessage);

		fVerifyStatusAndTaxOnWindow("OpportuntiesPostGetTax");
		sGLMyTestProjectDocStatus = "Error: TaxAddressError";
		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.contains(sGLMyTestProjectDocStatus),
				"OpportuntiesPostGetTax Expected:" + sGLMyTestProjectDocStatus + " And Actual:" + sGLActualMyTestProjectDocStatus);

		softAssert.assertAll();
	}

	public void EnableTaxCalc_NonNorthAmerricaAddress(String testCaseID, String productName) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		sGLTestCaseName = "taxCalculation";
		sGLItem1Code = productName;
		sGLTestcaseId = testCaseID;
		// BELOW IS THE EXCEL SHEET CONNECTION
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");

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
		//sGLTotalTax = "0.00";
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Opportunities Expected:" + sGLTotalTax + " And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OpportuntiesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OpportuntiesPostGetTax Expected:" + sGLMyTestProjectDocStatus + " And Actual:" + sGLActualMyTestProjectDocStatus);

		// End fCalculateSalesTax
		/*******************************************************************************************************************
		 * // Start- Transaction for Quotes
		 */
		fMenuSelection("newQuote");

		// Enter data in Line Tab
		fLine_Entry("Quotes");
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		//sGLTotalTax = "0.00";
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Quotes Expected:" + sGLTotalTax + " And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("QuotesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"QuotesPostGetTax Expected:" + sGLMyTestProjectDocStatus + " And Actual:" + sGLActualMyTestProjectDocStatus);

		fMenuSelection("Orders");
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		// Enter data in Line Tab
		fLine_Entry("Orders");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		//sGLTotalTax = "0.00";
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"OrdersSalesTax Expected:" + sGLTotalTax + " And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OrdersPostGetTax Expected:" + sGLMyTestProjectDocStatus + " And Actual:" + sGLActualMyTestProjectDocStatus);

		softAssert.assertAll();
	}
}
