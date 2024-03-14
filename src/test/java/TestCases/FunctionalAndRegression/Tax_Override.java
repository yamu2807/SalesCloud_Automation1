package TestCases.FunctionalAndRegression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import FunctionalLibrary.functionLibrary;

public class Tax_Override extends functionLibrary {
	@BeforeClass
	public void ExcelSheetName() throws Exception {
		sExcelSheetBookName = "TaxOverride1";
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
		// TEMPORARY
		sGLAmount = GetValueFromExcelSheet("Amount");
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		sGLShippingHandling = GetValueFromExcelSheet("ShippingHandling");
		sGLTOQuantity = GetValueFromExcelSheet("TOQuantity");
		sGLTOSalesTaxAmount = GetValueFromExcelSheet("TOSalesTaxAmount");
		sGLTOSalesPrice = GetValueFromExcelSheet("TOSalesPrice");
		sGLTOUnitPrice = GetValueFromExcelSheet("TOUnitPrice");
		sGLTaxOverRide = GetValueFromExcelSheet("EnableTaxOverride");
		sGLErrorMessageDisplayed = GetValueFromExcelSheet("EnableErrorMessage");
		sGLEnableEntityUseCode = GetValueFromExcelSheet("EnableEntity/USeCode");
		sGLEntityUseCode = GetValueFromExcelSheet("Entity/UseCOde");
		sGLEnableTaxCode = GetValueFromExcelSheet("EnableTaxCodeMapping");
		sGLEnableUPCcode = GetValueFromExcelSheet("UPCCodeEnable");
		sGLTaxOverRide = GetValueFromExcelSheet("EnableTaxOverride");
		sGLEnableMyTestProjecteXAMPLEExemptions = GetValueFromExcelSheet("EnableMyTestProjecteXAMPLEExemptions");
	}

	/**********************
	 * Tax amount Override
	 ***********************************/
	@Test(priority = 1, enabled = true, description = "Configuration-Yes, Line-Yes - Single Line Item, covering test case- C1598160")
	public void testCaseC1598160_SalesTaxAmountOverride_Opportunity() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "fTaxCalculation_Invoice";
		sGLTestcaseId = "TestCase131";
		sGLItem1Code = "GenWatt Diesel 1000kW";
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
		fSalesTaxAmountOverride("Yes");
		fTriggergetTax("Opportunities");
		fVerifyTaxValues("Opportunities");
	/*	softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Opportunities Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);*/
		softAssert.assertAll();
	}

	@Test(priority = 2, enabled = true, description = "Configuration-Yes, Line-Yes - Single Line Item, covering test case- C1599617")
	public void testCaseC1599617_SalesTaxAmountOverride_Quotes() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "fTaxCalculation_Invoice";
		sGLTestcaseId = "TestCase131";
		sGLItem1Code = "GenWatt Diesel 1000kW";
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

		// Enter data in Line Tab
		fLine_Entry("Quotes");
		fTriggergetTax("Quotes");
		// This is to update tax override values
		fSalesTaxAmountOverride("Yes");
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		sGLTotalTax = "190.00";
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Quotes Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);
		softAssert.assertAll();
	}

	@Test(priority = 3, enabled = true, description = "Configuration-Yes, Line-Yes - Single Line Item, covering test case- C1600120")
	public void testCaseC1600120_SalesTaxAmountOverride_Orders() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "fTaxCalculation_Invoice";
		sGLTestcaseId = "TestCase131";
		sGLItem1Code = "GenWatt Diesel 1000kW";
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");
		// Change Tax now settings depending on Flags retrieved from Excel file
		fMyTestProjecteXAMPLEConfigurationSettings();
		fMenuSelection("Orders");

		// Enter data in Line Tab
		fLine_Entry("Orders");
		fTriggergetTax("Orders");
		fSalesTaxAmountOverride("Yes");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Orders Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);
		softAssert.assertAll();
	}

	/************************************************************************************************/
	@Test(priority = 4, enabled = true, description = "Configuration-Yes, Line-No - Single Line Item, covering test case- C1598163")
	public void testCaseC1598163_TaxOverrideDisabledAtLine_Opportunity() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "fTaxCalculation_Invoice";
		sGLTestcaseId = "TestCase132";
		sGLItem1Code = "GenWatt Diesel 1000kW";
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
		fSalesTaxAmountOverride("No");
		fTriggergetTax("Opportunities");
		fVerifyTaxValues("Opportunities");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Opportunities Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);
		softAssert.assertAll();
	}

	@Test(priority = 5, enabled = true, description = "Configuration-Yes, Line-No - Single Line Item, covering test case- C1599620")
	public void testCaseC1599620_TaxOverrideDisabledAtLine_Quotes() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "fTaxCalculation_Invoice";
		sGLTestcaseId = "TestCase132";
		sGLItem1Code = "GenWatt Diesel 1000kW";
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

		// Enter data in Line Tab
		fLine_Entry("Quotes");
		fTriggergetTax("Quotes");
		// This is to update tax override values
		fSalesTaxAmountOverride("No");
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Quotes Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);
		softAssert.assertAll();
	}

	@Test(priority = 6, enabled = true, description = "Configuration-Yes, Line-No - Single Line Item, covering test case- C1600152")
	public void testCaseC1600152_TaxOverrideDisabledAtLine_Orders() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "fTaxCalculation_Invoice";
		sGLTestcaseId = "TestCase132";
		sGLItem1Code = "GenWatt Diesel 1000kW";
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");

		// Change Tax now settings depending on Flags retrieved from Excel file
		fMyTestProjecteXAMPLEConfigurationSettings();
		fMenuSelection("Orders");

		// Enter data in Line Tab
		fLine_Entry("Orders");
		fTriggergetTax("Orders");
		fSalesTaxAmountOverride("No");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Orders Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);
		softAssert.assertAll();
	}

	/************************************************************************************************/
	@Test(priority = 7, enabled = true, description = "Configuration-No, Line-Yes - Single Line Item, covering test case- C1598164")
	public void testCaseC1598164_TaxOverrideDisabledAtConfig_Opportunity() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "fTaxCalculation_Invoice";
		sGLTestcaseId = "TestCase133";
		sGLItem1Code = "GenWatt Diesel 1000kW";
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
		fSalesTaxAmountOverride("Yes");
		fTriggergetTax("Opportunities");
		fVerifyTaxValues("Opportunities");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Opportunities Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);
		softAssert.assertAll();
	}

	@Test(priority = 8, enabled = true, description = "Configuration-No, Line-Yes - Single Line Item, covering test case- C1599621")
	public void testCaseC1599621_TaxOverrideDisabledAtConfig_Quotes() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "fTaxCalculation_Invoice";
		sGLTestcaseId = "TestCase133";
		sGLItem1Code = "GenWatt Diesel 1000kW";
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

		// Enter data in Line Tab
		fLine_Entry("Quotes");
		fTriggergetTax("Quotes");
		// This is to update tax override values
		fSalesTaxAmountOverride("Yes");
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Quotes Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);
		softAssert.assertAll();
	}

	@Test(priority = 9, enabled = true, description = "Configuration-No, Line-Yes - Single Line Item, covering test case- C1600153")
	public void testCaseC1600153_TaxOverrideDisabledAtConfig_Orders() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "fTaxCalculation_Invoice";
		sGLTestcaseId = "TestCase133";
		sGLItem1Code = "GenWatt Diesel 1000kW";
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");

		// Change Tax now settings depending on Flags retrieved from Excel file
		fMyTestProjecteXAMPLEConfigurationSettings();
		fMenuSelection("Orders");

		// Enter data in Line Tab
		fLine_Entry("Orders");
		fTriggergetTax("Orders");
		fSalesTaxAmountOverride("Yes");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Orders Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);
		softAssert.assertAll();
	}

}
