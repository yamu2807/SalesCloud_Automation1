package TestCases.FunctionalAndRegression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import FunctionalLibrary.functionLibrary;

public class ShippingAndHandling extends functionLibrary {

	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void ExcelSheetName() throws Exception {
		sExcelSheetBookName = "ShippingAndHandling";
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

		// Contacts Address Tax calculation
		sGLOpportunitiesContactsShipToFlag = GetValueFromExcelSheet("OpportunitiesContactsShipToFlag");
		sGLOpportunitiesContactsBillToFlag = GetValueFromExcelSheet("OpportunitiesContactsBillToFlag");

		sGLErrorMessageDisplayed = GetValueFromExcelSheet("EnableErrorMessage");
		// TEMPORARY
		sGLAmount = GetValueFromExcelSheet("Amount");
	    sGLTotalTax = GetValueFromExcelSheet("TotalTax");
		
		sGLShippingHandling = GetValueFromExcelSheet("TotalTax");
		
		sGLEnableACcNumber = GetValueFromExcelSheet("ACcNumber");
		sGLEnableUseACcNameforCustIdentification = GetValueFromExcelSheet(
				"EnableUseACcNameforCustIdentification");
		sGLEnableMyTestProjecteXAMPLEExemptions = GetValueFromExcelSheet("EnableMyTestProjecteXAMPLEExemptions");
	}
	
	//Shipping and Handling Code
	String shippingCode = "FR010000";
	
	@Test(priority = 1, enabled = true, description = "TaxCalculation_withShippingAndHandling_Quote")
	public void ShippingAndHandling_Quote() throws Exception {
		Calculation("TestCaseS", "GenWatt Diesel 1000kW");
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
		
		selectByValue("//div[@aria-labelledby='advanceSettings__item']//div//div//div//div//div//div//div//select", shippingCode);
		clickonElement_Xpath("//button[normalize-space()='Save']");
		// Enter Ship To address
		fMenuSelection("ACcs");
		TempOpportunity();
		fLine_Entry("Opportunities");
		Thread.sleep(5000);
		fLineLevelShipToAddress_Opportunity("Line Level Ship To Address: Opportunity");

		///////////// ""Quotes""/////////////////////
		fMenuSelection("newQuote");
		
		fVerifyTaxValues_ShippingAndHandling("Quotes");
		
		sGLShippingHandlingTax = sGLShippingHandling.trim();
	    sGLShippingHandlingTax = "$" + sGLShippingHandlingTax;
		
		System.out.println("Expected Shipping and Handling Tax is: " + sGLShippingHandlingTax);
		System.out.println("Actual Shipping and Handling Tax is: " + sGLActualShippingHandlingTax);
		
		softAssert.assertTrue(sGLShippingHandlingTax.equalsIgnoreCase(sGLActualShippingHandlingTax),
				"Quotes Expected:" + sGLShippingHandlingTax + " And Actual:" + sGLActualShippingHandlingTax);
		
		fVerifyTaxValues("Quotes");
		
		sGLTotalTax = "102.25";
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Quotes Expected:" + sGLTotalTax + " And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("QuotesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"QuotesPostGetTax Expected:" + sGLMyTestProjectDocStatus + " And Actual:" + sGLActualMyTestProjectDocStatus);
		

		// -----------Deleting Shipping and Handling field value--------------
		
		clickonElement_Xpath("//td[@id='topButtonRow']//input[@title='Edit']");
		clearValue_LocatorXpath("//input[@id='ShippingHandling']");
		clickonElement_Xpath("//td[@id='topButtonRow']//input[@title='Save']");
		
		fTriggergetTax("Quotes");
	    System.out.println("Updated shipping and Handling tax is: " + getText("//tbody/tr[9]/td[4]/div[1]") );	
	    fVerifyTaxValues("Quotes");
		
		sGLTotalTax = "92.00";
		softAssert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Quotes Expected:" + sGLTotalTax + " And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("QuotesPostGetTax");

		softAssert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"QuotesPostGetTax Expected:" + sGLMyTestProjectDocStatus + " And Actual:" + sGLActualMyTestProjectDocStatus);
		
		softAssert.assertAll();

	}
	
	
	//@Test(priority = 2, enabled = true, description = "TaxCalculation_withShippingAndHandling_Order")
	public void ShippingAndHandling_Order() throws Exception {
		Calculation1("TestCaseS", "GenWatt Diesel 1000kW");
	}
	public void Calculation1(String testCaseID, String productName) throws Exception {
	System.out.println();

	}
}