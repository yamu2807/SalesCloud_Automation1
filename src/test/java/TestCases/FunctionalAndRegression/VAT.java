package TestCases.FunctionalAndRegression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import FunctionalLibrary.functionLibrary;

public class VAT extends functionLibrary {
	@BeforeClass
	public void ExcelSheetName() throws Exception {
		sExcelSheetBookName = "VAT";
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
		sGLEnableUPCcode = GetValueFromExcelSheet("UPCCodeEnable");
		sGLTaxOverRide = GetValueFromExcelSheet("EnableTaxOverride");

		sGLEnableAddressValidation = GetValueFromExcelSheet("EnableAddressValidationFlag");
		sGLVerifyValidatedAddress = GetValueFromExcelSheet("VerifyValidatedAddressFlag");
		sGLEnableTaxCode = GetValueFromExcelSheet("EnableTaxCodeMapping");
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

		sGLEnableVAT = GetValueFromExcelSheet("EnableVAT");
		sGLInvoiceMessaging = GetValueFromExcelSheet("InvoiceMessaging");
		sGLEnableMyTestProjecteXAMPLEExemptions = GetValueFromExcelSheet("EnableMyTestProjecteXAMPLEExemptions");
	}

	@Test(priority = 1, enabled = true, description = "Ship to:Germany and Ship from: Great Brighton")
	public void TestCase115_Shipto_Germany_Shipfrom_GreatBrighton() throws Exception {
		calculateVATTax("TestCase115");
	}

	@Test(priority = 2, enabled = true, description = "Ship to:France and Ship from: Great Brighton")
	public void TestCase116_Shipto_France_Shipfrom_GreatBrighton() throws Exception {
		calculateVATTax("TestCase116");
	}

	/*
	 * @Test(priority = 3, enabled = true, description =
	 * "Ship to:US and Ship from: Great Brighton") public void
	 * TestCase117_Shipto_US_Shipfrom_GreatBrighton() throws Exception {
	 * calculateVATTax_BlankInvoiceMessaging("TestCase117"); }
	 * 
	 * @Test(priority = 4, enabled = true, description =
	 * "Ship to:Great Brighton and Ship from:US") public void
	 * TestCase118_Shipto_GreatBrighton_Shipfrom_US() throws Exception {
	 * calculateVATTax_BlankInvoiceMessaging("TestCase118"); }
	 * 
	 * @Test(priority = 5, enabled = true, description =
	 * "Ship to:US and Ship from: US") public void
	 * TestCase119_Shipto_US_Shipfrom_US() throws Exception {
	 * calculateVATTax_BlankInvoiceMessaging("TestCase119"); }
	 * 
	 * @Test(priority = 6, enabled = true, description =
	 * "Ship to:AUS and Ship from: Great Brighton") public void
	 * TestCase120_Shipto_AUS_Shipfrom_GreatBrighton() throws Exception {
	 * calculateVATTax_AustraliaAddress("TestCase120"); }
	 * 
	 * @Test(priority = 7, enabled = true, description =
	 * "Ship to:Great Brighton and Ship from: AUS") public void
	 * TestCase121_Shipto_GreatBrighton_Shipfrom_AUS() throws Exception {
	 * calculateVATTax_AustraliaAddress("TestCase121"); }
	 */

	/* Consolidated Function */
	public void calculateVATTax(String testCaseId) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "fTaxCalculation_Invoice";
		sGLItem1Code = "GenWatt Diesel 1000kW";

		sGLTestcaseId = testCaseId;
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
		String actualinvoiceMessagingValue = getInvoiceMessaging();
		softAssert.assertTrue(actualinvoiceMessagingValue.equalsIgnoreCase(sGLInvoiceMessaging),
				"Opportunities Invoice Messaging- Expected:" + sGLInvoiceMessaging + "And Actual:"
						+ actualinvoiceMessagingValue);

		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Opportunities Total Tax Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OpportuntiesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OpportuntiesPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

		fMenuSelection("newQuote");

		// Enter data in Line Tab
/*		fLine_Entry("Quotes");
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Quotes Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("QuotesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"QuotesPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

		actualinvoiceMessagingValue = getInvoiceMessaging();
		softAssert.assertEquals(actualinvoiceMessagingValue, sGLInvoiceMessaging, "Quotes Invoice Messaging- Expected:"
				+ sGLInvoiceMessaging + "And Actual:" + actualinvoiceMessagingValue);

		///////////// ""Orders""/////////////////////
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		fMenuSelection("Orders");

		// Enter data in Line Tab
		fLine_Entry("Orders");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Orders Total-Tax Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);
		actualinvoiceMessagingValue = getInvoiceMessaging();
		softAssert.assertEquals(actualinvoiceMessagingValue, sGLInvoiceMessaging, "Orders Invoice Messaging- Expected:"
				+ sGLInvoiceMessaging + "And Actual:" + actualinvoiceMessagingValue);*/

		softAssert.assertAll();
	}

	public void calculateVATTax_BlankInvoiceMessaging(String testCaseId) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "fTaxCalculation_Invoice";
		sGLItem1Code = "GenWatt Diesel 1000kW";

		sGLTestcaseId = testCaseId;
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
		String actualinvoiceMessagingValue = getInvoiceMessaging();
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Opportunities Total Tax Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OpportuntiesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OpportuntiesPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

		fMenuSelection("newQuote");

		// Enter data in Line Tab
		fLine_Entry("Quotes");
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Quotes Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("QuotesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"QuotesPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

		actualinvoiceMessagingValue = getInvoiceMessaging();
		softAssert.assertEquals(actualinvoiceMessagingValue, sGLInvoiceMessaging, "Quotes Invoice Messaging- Expected:"
				+ sGLInvoiceMessaging + "And Actual:" + actualinvoiceMessagingValue);

		///////////// ""Orders""/////////////////////
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		fMenuSelection("Orders");

		// Enter data in Line Tab
		fLine_Entry("Orders");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Orders Total-Tax Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OrdersPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

		actualinvoiceMessagingValue = getInvoiceMessaging();
		softAssert.assertEquals(actualinvoiceMessagingValue, sGLInvoiceMessaging, "Orders Invoice Messaging- Expected:"
				+ sGLInvoiceMessaging + "And Actual:" + actualinvoiceMessagingValue);

		softAssert.assertAll();
	}

	public void calculateVATTax_AustraliaAddress(String testCaseId) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "fTaxCalculation_Invoice";
		sGLItem1Code = "GenWatt Diesel 1000kW";

		sGLTestcaseId = testCaseId;
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

		fVerifyStatusAndTaxOnWindow("OpportuntiesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OpportuntiesPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

		String actualinvoiceMessagingValue = getInvoiceMessaging();
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Opportunities Total Tax Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

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
				"Quotes Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("QuotesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"QuotesPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);

		actualinvoiceMessagingValue = getInvoiceMessaging();
		softAssert.assertEquals(actualinvoiceMessagingValue, sGLInvoiceMessaging, "Quotes Invoice Messaging- Expected:"
				+ sGLInvoiceMessaging + "And Actual:" + actualinvoiceMessagingValue);

		///////////// ""Orders""/////////////////////
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		fMenuSelection("Orders");

		// Enter data in Line Tab
		fLine_Entry("Orders");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Orders Total-Tax Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"OrdersPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		actualinvoiceMessagingValue = getInvoiceMessaging();
		softAssert.assertEquals(actualinvoiceMessagingValue, sGLInvoiceMessaging, "Orders Invoice Messaging- Expected:"
				+ sGLInvoiceMessaging + "And Actual:" + actualinvoiceMessagingValue);

		softAssert.assertAll();
	}
}