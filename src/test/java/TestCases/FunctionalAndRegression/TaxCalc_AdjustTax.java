package TestCases.FunctionalAndRegression;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import FunctionalLibrary.functionLibrary;

public class TaxCalc_AdjustTax extends functionLibrary {

	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void ExcelSheetName() throws Exception {
		sExcelSheetBookName = "TaxCalculation";
		fLogin_SalesForce();
	}

	@AfterClass
	public void tearDown() {
		//quit();
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
		sGLEnableTriggerForGetTax = GetValueFromExcelSheet("TotalTax");

		sGLEnableACcNumber = GetValueFromExcelSheet("ACcNumber");
		sGLEnableUseACcNameforCustIdentification = GetValueFromExcelSheet(
				"EnableUseACcNameforCustIdentification");
		sGLEnableMyTestProjecteXAMPLEExemptions = GetValueFromExcelSheet("EnableMyTestProjecteXAMPLEExemptions");
	}
	
	@Test(priority = 1, enabled = true, description = "TaxCalculation_AdjustTax_Update & Use Calculate Tax button")
	public void TaxCalculation_AdjustTax() throws Exception {
		Test1("TestCaseY", "GenWatt Diesel 1000kW");
	}
	public void Test1(String testCaseID, String productName) throws Exception {
		sGLTestCaseName = "taxCalculation";
		sGLItem1Code = productName;
		sGLTestcaseId = testCaseID;
		// BELOW IS THE EXCEL SHEET CONNECTION
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");
       // fMenuSelection("ExemptionUtility");
		Thread.sleep(5000);
		// Change Tax now settings depending on Flags retrieved from Excel file
		fMyTestProjecteXAMPLEConfigurationSettings();
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		Thread.sleep(5000);
		fMenuSelection("Orders");
		// Enter data in Line Tab
		fLine_Entry("Orders");
		Thread.sleep(5000);
		clickonElement_Xpath("//td[@id='topButtonRow']//input[@title='Edit']");
		selectByValue("//select[@id='Status']", "Approved");
		clickonElement_Xpath("//td[@id='topButtonRow']//input[@title='Save']");
		Thread.sleep(5000);
		
		driver.navigate().refresh();
		fVerifyTaxValues("Orders");
		sGLTotalTax = "77.50";
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"OrdersSalesTax Expected:" + sGLTotalTax + " And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostCommitTax");

		Assert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		Thread.sleep(5000);
		
		sGLTestcaseId = testCaseID + ".1";
		// Enter data in Line Tab
		fLine_Entry("Orders");
		
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		sGLTotalTax = "155.00";
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"OrdersSalesTax Expected:" + sGLTotalTax + " And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostCommitTax");
		
		Assert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		softAssert.assertAll();
		
	}
}