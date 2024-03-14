package TestCases.Regression;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import FunctionalLibrary.functionLibrary;

public class UPC_Code extends functionLibrary {
	@BeforeClass
	public void ExcelSheetName() throws Exception {
		sExcelSheetBookName = "UPCCODE";
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

	}

	@Test(priority = 1, enabled = true, description = "Mapped-UPCCode_Valid UPC code -2036302")
	public void TestCase45_ValidUPCcode_2036302_TaxcodeSettingDisabled() throws Exception {
		fTaxCalculation_ValidInvalidUPC_Code("TestCase45", "ValidUPCCode");
	}

	@Test(priority = 2, enabled = true, description = "Mapped-UPCCode_Invalid UPC Code -12345")
	public void TestCase46_InvalidUPCcode_TaxcodeSettingDisabled() throws Exception {
		fTaxCalculation_ValidInvalidUPC_Code("TestCase46", "InvalidUPCCode");
	}

	@Test(priority = 3, enabled = true, description = "Mapped UPCCode Greater Than 50Char")
	public void TestCase49_MappedUPCCode_GreaterThan_50Char() throws Exception {
		fTaxCalculation_ValidInvalidUPC_Code("TestCase49", "UPC_GreaterThan_50Char");
	}

	public void fTaxCalculation_ValidInvalidUPC_Code(String testCaseID, String ItemCode) throws Exception {
		// BELOW IS THE EXCEL SHEET CONNECTION

		SoftAssert softAssert = new SoftAssert();
		sGLTestCaseName = "fTaxCalculation_Invoice";
		// Tax code NT
		sGLItem1Code = ItemCode;
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
}
