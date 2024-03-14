package TestCases.Regression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import FunctionalLibrary.functionLibrary;

public class MISC_Charges extends functionLibrary {
	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void ExcelSheetName() throws Exception {
		sExcelSheetBookName = "MISC_Charges";
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
		sGLShippingHandlingTax = GetValueFromExcelSheet("ShippingHandlingTax");
		sGLShippingHandling = GetValueFromExcelSheet("ShippingHandling");
		sGLShippingTaxCode = GetValueFromExcelSheet("ShippigHandlingCode");
		sGLEnableUPCcode = GetValueFromExcelSheet("UPCCodeEnable");
		sGLTaxOverRide = GetValueFromExcelSheet("EnableTaxOverride");

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
		sGLQuotesShipTo = GetValueFromExcelSheet("QuotesShipTo");
		sGLQuotesBillTo = GetValueFromExcelSheet("QuotesBillTo");
		// Secondary Ship to address
		sGLSecondaryShiptoAddress_Line1 = GetValueFromExcelSheet("SecondaryShipToAddress_Line1");
		sGLSecondaryShiptoAddress_City = GetValueFromExcelSheet("SecondaryShipToAddress_City");
		sGLSecondaryShiptoAddress_State = GetValueFromExcelSheet("SecondaryShipToAddress_State");
		sGLSecondaryShiptoAddress_Zip = GetValueFromExcelSheet("SecondaryShipToAddress_Zip");
		sGLSecondaryShipToAddressCountryCode = GetValueFromExcelSheet("SecondaryShipToAddress_Country");

		// TEMPORARY
		sGLAmount = GetValueFromExcelSheet("Amount");
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");

	}

	@Test(priority = 1, enabled = true, description = "Shipping+Handling Amount")
	public void TestCase100_Shipping_Charges() throws Exception {
		fTaxCalculation_Invoice("TestCase100");
	}

	@Test(priority = 2, enabled = true, description = "Shipping+Handling with Freight code")
	public void TestCase101_Shipping_Handling_with_Freight_code() throws Exception {
		fTaxCalculation_Invoice("TestCase101");
	}

	public void fTaxCalculation_Invoice(String testCaseID) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		// BELOW IS THE EXCEL SHEET CONNECTION
		sGLTestCaseName = "fTaxCalculation_Invoice";
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
		fMenuSelection("newQuote");
		addShippingAndHandlingCharges_Quotes();
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		sGLTotalTax = "93.50";
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Quotes Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);
		fVerifyMISCTaxValues("Quotes");
		softAssert.assertTrue(sGLActualShippingHandlingTax.equalsIgnoreCase(sGLShippingHandlingTax),
				"Quotes Expected:" + sGLShippingHandlingTax + "And Actual:" + sGLActualShippingHandlingTax);
		fVerifyStatusAndTaxOnWindow("QuotesPostGetTax");
		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"QuotesPostGetTax Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		softAssert.assertAll();
	}
}
