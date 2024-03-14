package TestCases.FunctionalAndRegression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import FunctionalLibrary.functionLibrary;

public class TaxCalc_LineLevelShipToAddress extends functionLibrary {

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

		// Contacts Address Tax calculation
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

	@Test(priority = 1, enabled = true, description = "Ship To Address from Opportunity Line Level to Quote Line Level")
	public void LineLevelShipToAddress_OpportunityToQuote() throws Exception {
		Calculation("TestCaseY", "GenWatt Diesel 1000kW");
	}

	public void Calculation(String testCaseID, String productName) throws Exception {

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
		fLineLevelShipToAddress_Opportunity("Line Level Ship To Address: Opportunity");

		///////////// ""Quotes""/////////////////////
		fMenuSelection("newQuote");
		clickonElement_Xpath("//tbody/tr/td[contains(@class,'oRight')]/div[@class='listHoverLinks']/a[1]/span[1]");
		Thread.sleep(3000);
		clickonElement_Xpath(
				"//body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[5]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[2]/th[1]");

		Thread.sleep(2000);
		String sOnScreenLine1;
		String sOnScreenCity;
		String sOnScreenState;
		String sOnScreenZip;
		String sOnScreenCountry;

		// Copy Address for Quote Line Level Shipping Address
		sOnScreenLine1 = getText("//tbody/tr[7]/td[2]/div[1]");
		sOnScreenCity = getText("//tbody/tr[8]/td[2]/div[1]");
		sOnScreenState = getText("//tbody/tr[9]/td[2]/div[1]");
		sOnScreenZip = getText("//tbody/tr[10]/td[2]/div[1]");
		sOnScreenCountry = getText("//tbody/tr[11]/td[2]/div[1]");

		sGLQuoteLineLevelShippingAddress = sOnScreenLine1 + ", " + sOnScreenCity + ", " + sOnScreenState + ", "
				+ sOnScreenZip + ", " + sOnScreenCountry;
		System.out.println("Validated Address for Quote Line Level: " + sGLQuoteLineLevelShippingAddress);

		softAssert.assertTrue(sGLQuoteLineLevelShippingAddress.equalsIgnoreCase(sGLOpportunityLineLevelShippingAddress),
				"Quote:" + sGLQuoteLineLevelShippingAddress + "And Opportunity:"
						+ sGLOpportunityLineLevelShippingAddress);

		///////////// ""Orders""/////////////////////
	//	fMenuSelection("Orders");

		softAssert.assertAll();

	}
     //	----------------------------------------------------------------------------   //
	
	@Test(priority = 2, enabled = true, description = "Origin Address from Opportunity to Quote")
	public void OriginAddress_OpportunityToQuote() throws Exception {
		Calculation1("TestCaseY1", "GenWatt Diesel 1000kW");
	}

	public void Calculation1(String testCaseID, String productName) throws Exception {

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

		fOriginAddress_Opportunity("Origin Address: Opportunity");

		fLine_Entry("Opportunities");
		Thread.sleep(5000);
		fMenuSelection("newQuote");

		String sOnScreenLine1;
		String sOnScreenCity;
		String sOnScreenState;
		String sOnScreenZip;
		String sOnScreenCountry;

		Thread.sleep(3000);

		// Copy Quotes Origin Address
		sOnScreenLine1 = getText("//tbody/tr[8]/td[2]/div[1]");
		sOnScreenCity = getText("//tbody/tr[9]/td[2]/div[1]");
		sOnScreenState = getText("//tbody/tr[10]/td[2]/div[1]");
		sOnScreenZip = getText("//tbody/tr[11]/td[2]/div[1]");
		sOnScreenCountry = getText("//tbody/tr[12]/td[2]/div[1]");

		sGLQuoteOriginAddress = sOnScreenLine1 + ", " + sOnScreenCity + ", " + sOnScreenState + ", " + sOnScreenZip
				+ ", " + sOnScreenCountry;
		System.out.println("Validated Address for Quote Origin Address: " + sGLQuoteOriginAddress);

		softAssert.assertTrue(sGLQuoteOriginAddress.equalsIgnoreCase(sGLOpportunityOriginAddress),
				"Quote:" + sGLQuoteOriginAddress + "And Opportunity:" + sGLOpportunityOriginAddress);

	//	fLine_Entry("Quotes");
	//	fMenuSelection("Orders");
		// Enter data in Line Tab
	//	fLine_Entry("Orders");

		softAssert.assertAll();

	}

}
