package TestCases.FunctionalAndRegression;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import FunctionalLibrary.functionLibrary;

public class ExemptionUtility extends functionLibrary {

	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void ExcelSheetName() throws Exception {
		sExcelSheetBookName = "Exemption";
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
		sGLCustExemptNo = GetValueFromExcelSheet("ExemptionNumber");
		if (sGLTestCaseName.equalsIgnoreCase("TestCase113")) {
			sGLCustExemptNo = "@#$%";
		}

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
		sGLEnableMyTestProjecteXAMPLEExemptions = GetValueFromExcelSheet("EnableMyTestProjecteXAMPLEExemptions");
		sGLExpectedSuccessMessage = GetValueFromExcelSheet("ExpectedSuccessMessage");
		sGLAmount = GetValueFromExcelSheet("Amount");
		sGLTotalTax = GetValueFromExcelSheet("TotalTax");
	}
	
	@Test(priority = 1, enabled = true, description = "Exmeption Certificate with Expired Date before Issue Date")
	public void ExmeptionCertificate_ExpiredDateBeforeIssueDate() throws Exception {
		Test1("TestCase01", "GenWatt Diesel 1000kW");
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
		
		fSaveToMyTestProjecteXAMPLE("TaxExemption_ACcs");
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);
		
		fTaxExemptionCertificate("TaxExemption_ACcs_ExpiredDateBeforeIssueDate");
		sGLExpectedSuccessMessage = "Error - Date issued must be on or before the provided expiration date.";
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);
		softAssert.assertAll();
		
	}
	
	@Test(priority = 2, enabled = true, description = "Exmeption Certificate with MyTestProjecteXAMPLE Exemptions Disabled ")
	public void ExmeptionCertificate_MyTestProjecteXAMPLEExemptionsDisabled() throws Exception {
		Test2("TestCase02", "GenWatt Diesel 1000kW");
	}
	public void Test2(String testCaseID, String productName) throws Exception {
		sGLTestCaseName = "taxCalculation";
		sGLItem1Code = productName;
		sGLTestcaseId = testCaseID;
		// BELOW IS THE EXCEL SHEET CONNECTION
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		fMenuSelection("TaxNowSettings");
     //	fMenuSelection("ExemptionUtility");
		Thread.sleep(5000);
		// Change Tax now settings depending on Flags retrieved from Excel file
		fMyTestProjecteXAMPLEConfigurationSettings();
        fMenuSelection("ACcs");
		
		fSaveToMyTestProjecteXAMPLE("TaxExemption_ACcs_Disabled");
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
	}
	
	
	@Test(priority = 3, enabled = true, description = "Exmeption Certificate with Upload Files with MyTestProjecteXAMPLE Exemptions Disabled ")
	public void ExmeptionCertificate_UploadFiles_MyTestProjecteXAMPLEExemptionsDisabled() throws Exception {
		Test3("TestCase03", "GenWatt Diesel 1000kW");
	}
	public void Test3(String testCaseID, String productName) throws Exception {
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
		fSaveToMyTestProjecteXAMPLE("TaxExemption_ACcs_Disabled");
		
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
		fTaxExemptionCertificate("TaxExemption_ACcs_FutureExpiredDate");
		fUploadFiles("TaxExemptionCertificate_DisabledMyTestProjecteXAMPLEExemption");
		 
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
	}
	
	
	
	@Test(priority = 4, enabled = true, description = "Tax Calculation_Exmeption Certificate_Expired Date")
	public void ExmeptionCertificate_ExpiredDate() throws Exception {
		Test4("TestCase04", "GenWatt Diesel 1000kW");
	}
	public void Test4(String testCaseID, String productName) throws Exception {
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
		fSaveToMyTestProjecteXAMPLE("TaxExemption_ACcs");
		
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
		fTaxExemptionCertificate("TaxExemption_ACcs_PastExpiredDate");
		ExecutionDelay(2);
		
		sGLExpectedSuccessMessage = "Success - Certificate record(s) is saved in MyTestProjecteXAMPLE successfully.";
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
				
		fUploadFiles("TaxExemptionCertificate_EnabledMyTestProjecteXAMPLEExemption");
		sGLExpectedSuccessMessage = "Certificate saved to MyTestProjecteXAMPLE";
		 
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
		clickonElement_Xpath("//a[contains(text(),'Ajay')]");
		
		Thread.sleep(2000);
		TempOpportunity();
		fMenuSelection("newQuote");

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
	
	
	@Test(priority = 5, enabled = true, description = "Tax Calculation_Exmeption Certificate_Update Future Date to Past Date")
	public void ExmeptionCertificate_FutureToPastDate() throws Exception {
		Test5("TestCase05", "GenWatt Diesel 1000kW");
	}
	public void Test5(String testCaseID, String productName) throws Exception {
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
		fSaveToMyTestProjecteXAMPLE("TaxExemption_ACcs");
		
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
		fTaxExemptionCertificate("TaxExemption_ACcs_FutureExpiredDate");
		ExecutionDelay(2);
		
		sGLExpectedSuccessMessage = "Success - Certificate record(s) is saved in MyTestProjecteXAMPLE successfully.";
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
		fUploadFiles("TaxExemptionCertificate_EnabledMyTestProjecteXAMPLEExemption");
		sGLExpectedSuccessMessage = "Certificate saved to MyTestProjecteXAMPLE";
		 
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
		clickonElement_Xpath("//a[contains(text(),'Ajay')]");
		
		Thread.sleep(2000);
		TempOpportunity();
		fMenuSelection("newQuote");

		fMenuSelection("Orders");
		// Enter data in Line Tab
		fLine_Entry("Orders");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		
		//sExpectedSalesTax = "0.00";
		Assert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");

		Assert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		
		//Go back to the recently created Tax Exemption Certificate and Update the 'Expiration Date' field as past date and save the record.
		Thread.sleep(2000);
		fMenuSelection("ACcs");
		fTaxExemptionCertificate("TaxExemption_ACcs_PastExpiredDate");
		
		Thread.sleep(3000);
		driver.findElement(By.linkText("Orders")).click();
		clickonElement_Xpath("//tbody/tr[2]/th[1]/a[1]");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		Assert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");

		Assert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		
		softAssert.assertAll();
	}
	
	
	@Test(priority = 6, enabled = true, description = "Tax Calculation_Exmeption Certificate_Update Past Date to Future Date")
	public void ExmeptionCertificate_PastToFutureDate() throws Exception {
		Test6("TestCase06", "GenWatt Diesel 1000kW");
	}
	public void Test6(String testCaseID, String productName) throws Exception {
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
		fSaveToMyTestProjecteXAMPLE("TaxExemption_ACcs");
		
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
		fTaxExemptionCertificate("TaxExemption_ACcs_PastExpiredDate");
		ExecutionDelay(2);
		
		sGLExpectedSuccessMessage = "Success - Certificate record(s) is saved in MyTestProjecteXAMPLE successfully.";
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
		fUploadFiles("TaxExemptionCertificate_EnabledMyTestProjecteXAMPLEExemption");
		sGLExpectedSuccessMessage = "Certificate saved to MyTestProjecteXAMPLE";
		 
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
		clickonElement_Xpath("//a[contains(text(),'Ajay')]");
		
		Thread.sleep(2000);
		TempOpportunity();
		fMenuSelection("newQuote");

		fMenuSelection("Orders");
		// Enter data in Line Tab
		fLine_Entry("Orders");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		
		//sExpectedSalesTax = "0.00";
		Assert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");

		Assert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		
		//Go back to the recently created Tax Exemption Certificate and Update the 'Expiration Date' field as future date and save the record.
		Thread.sleep(2000);
		fMenuSelection("ACcs");
		fTaxExemptionCertificate("TaxExemption_ACcs_FutureExpiredDate");
		
		Thread.sleep(3000);
		driver.findElement(By.linkText("Orders")).click();
		clickonElement_Xpath("//tbody/tr[2]/th[1]/a[1]");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		Assert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");

		Assert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);
		
		softAssert.assertAll();
	}
	
	
	@Test(priority = 7, enabled = true, description = "Invalidate Exemption Certificate with MyTestProjecteXAMPLE Exemptions disabled")
	public void InvalidateCertificate_AvtaxExemptionDisabled() throws Exception {
		Test7("TestCase07", "GenWatt Diesel 1000kW");
	}
	public void Test7(String testCaseID, String productName) throws Exception {
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
        
        // Create New Tax Exemption Certificate for Invalidate functionality
        fTaxExemptionCertificate("TaxExemption_ACcs_NewTaxExemptionCertificate");
		
	    //Upload Files
        fUploadFiles("TaxExemptionCertificate_UploadFiles");
		
		//Click on Invalidate Certificate
        fInvalidateCertificate("TaxExemption_ACcs_InvalidateCertificate");
		 
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
		ExecutionDelay(2);
		
		//Delete Newly created data for Invalidate Tax Exemption Certificate
		clickonElement_Xpath("//td[@id='topButtonRow']//input[@title='Delete']");
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		
		softAssert.assertAll();
	}
	
	@Test(priority = 8, enabled = true, description = "Invalidate Exemption Certificate with MyTestProjecteXAMPLE Exemptions enabled")
	public void InvalidateCertificate_AvtaxExemptionEnabled() throws Exception {
		Test8("TestCase08", "GenWatt Diesel 1000kW");
	}
	public void Test8(String testCaseID, String productName) throws Exception {
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
        fSaveToMyTestProjecteXAMPLE("TaxExemption_ACcs");
        
        // Create New Tax Exemption Certificate for Invalidate functionality
        fTaxExemptionCertificate("TaxExemption_ACcs_NewTaxExemptionCertificate");
        //Save Certificate to MyTestProjecteXAMPLE
        clickonElement_Xpath("//td[@class='pbButton']//input[@title='Save Certificate']");
        Thread.sleep(10000);
		clickonElement_Xpath("//button[normalize-space()='Back']");
		
	    //Upload Files
        fUploadFiles("TaxExemptionCertificate_EnabledMyTestProjecteXAMPLEExemption");
        
		//Click on Invalidate Certificate
        fInvalidateCertificate("TaxExemption_ACcs_InvalidateCertificate");
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		ExecutionDelay(2);
		
		//Delete Newly created data for Invalidate Tax Exemption Certificate
		clickonElement_Xpath("//td[@id='topButtonRow']//input[@title='Delete']");
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		
		softAssert.assertAll();
	}
	
	@Test(priority = 9, enabled = true, description = "Update Certificate after Exemption Certificate is Invalid")
	public void InvalideCertificate_Update() throws Exception {
		Test9("TestCase09", "GenWatt Diesel 1000kW");
	}
	public void Test9(String testCaseID, String productName) throws Exception {
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
        fSaveToMyTestProjecteXAMPLE("TaxExemption_ACcs");
        
        // Create New Tax Exemption Certificate for Invalidate functionality
        fTaxExemptionCertificate("TaxExemption_ACcs_NewTaxExemptionCertificate");
        //Save Certificate to MyTestProjecteXAMPLE
        clickonElement_Xpath("//td[@class='pbButton']//input[@title='Save Certificate']");
        Thread.sleep(10000);
		clickonElement_Xpath("//button[normalize-space()='Back']");
		
	    //Upload Files
        fUploadFiles("TaxExemptionCertificate_EnabledMyTestProjecteXAMPLEExemption");
        		
		//Click on Invalidate Certificate
        fInvalidateCertificate("TaxExemption_ACcs_InvalidateCertificate");
        
        //Update any field on invalid certificate
        clickonElement_Xpath("//td[@id='topButtonRow']//input[@title='Edit']");
		ExecutionDelay(3);
        enterValue_LocatorXpath("//input[@name='CF00N4x00000LrykS']", "RESALE");
        clickonElement_Xpath("//td[@class='pbButtonb']//input[@title='Save']");
        sGLActualSuccessMessage= getText("//div[@id='errorDiv_ep']");
        
        softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
        ExecutionDelay(2);
        clickonElement_Xpath("//td[@id='topButtonRow']//input[@title='Cancel']");
        ExecutionDelay(2);
        
		//Delete Newly created data for Invalidate Tax Exemption Certificate
		clickonElement_Xpath("//td[@id='topButtonRow']//input[@title='Delete']");
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		
		softAssert.assertAll();
	}
	
	@Test(priority = 10, enabled = true, description = "Use Invalidate Certificate button again on Invalid Certificate")
	public void InvalidateCertificate_AgainOnInvalidCertificate() throws Exception {
		Test10("TestCase10", "GenWatt Diesel 1000kW");
	}
	public void Test10(String testCaseID, String productName) throws Exception {
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
        fSaveToMyTestProjecteXAMPLE("TaxExemption_ACcs");
        
        // Create New Tax Exemption Certificate for Invalidate functionality
        fTaxExemptionCertificate("TaxExemption_ACcs_NewTaxExemptionCertificate");
        //Save Certificate to MyTestProjecteXAMPLE
        clickonElement_Xpath("//td[@class='pbButton']//input[@title='Save Certificate']");
        Thread.sleep(10000);
		clickonElement_Xpath("//button[normalize-space()='Back']");
		
	    //Upload Files
        fUploadFiles("TaxExemptionCertificate_EnabledMyTestProjecteXAMPLEExemption");
       		
		//Click on Invalidate Certificate
        fInvalidateCertificate("TaxExemption_ACcs_InvalidateCertificate");
        ExecutionDelay(2);
        fInvalidateCertificate("TaxExemption_ACcs_InvalidateCertificate");
        
        softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
		//Delete Newly created data for Invalidate Tax Exemption Certificate
		clickonElement_Xpath("//td[@id='topButtonRow']//input[@title='Delete']");
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		
		softAssert.assertAll();
	}
	
	
	@Test(priority = 11, enabled = true, description = "Invalidate Exemption Certificate with MyTestProjecteXAMPLE Exemptions enabled_PastDate")
	public void InvalidateCertificate_AvtaxExemptionEnabled_PastDate() throws Exception {
		Test11("TestCase11", "GenWatt Diesel 1000kW");
	}
	public void Test11(String testCaseID, String productName) throws Exception {
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
        fSaveToMyTestProjecteXAMPLE("TaxExemption_ACcs");
        
		// Create New Tax Exemption Certificate for Invalidate functionality
		clickonElement_Xpath("//input[@title='New Tax Exemption Certificate']");
		ExecutionDelay(2);
		enterValue_LocatorXpath("//input[@id='Name']", "TaxExemption_Invalidate");
		enterValue_LocatorXpath("//input[@name='00N4x00000LrykR']", "3/10/2021");
		enterValue_LocatorXpath("//input[@name='00N4x00000LrykT']", "3/10/2021");
		enterValue_LocatorXpath("//input[@name='CF00N4x00000LrykS']", "EXPOSURE: NON-DELIVERABLE");
		enterValue_LocatorXpath("//input[@name='CF00N4x00000LrykU']", "Alabama");
		clickonElement_Xpath("//td[@class='pbButtonb']//input[@title='Save']");
		ExecutionDelay(2);
		
        //Save Certificate to MyTestProjecteXAMPLE
        clickonElement_Xpath("//td[@class='pbButton']//input[@title='Save Certificate']");
        Thread.sleep(10000);
		clickonElement_Xpath("//button[normalize-space()='Back']");
		
	    //Upload Files
        fUploadFiles("TaxExemptionCertificate_EnabledMyTestProjecteXAMPLEExemption");
       
		//Click on Invalidate Certificate
        fInvalidateCertificate("TaxExemption_ACcs_InvalidateCertificate");
    
        softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
		//Delete Newly created data for Invalidate Tax Exemption Certificate
		clickonElement_Xpath("//td[@id='topButtonRow']//input[@title='Delete']");
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		
		softAssert.assertAll();
	}
	
	@Test(priority = 12, enabled = true, description = "Invalidate Exemption Certificate without saving it to MyTestProjecteXAMPLE")
	public void InvalidateCertificate_withoutSaveCertificate() throws Exception {
		Test12("TestCase12", "GenWatt Diesel 1000kW");
	}
	public void Test12(String testCaseID, String productName) throws Exception {
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
        fSaveToMyTestProjecteXAMPLE("TaxExemption_ACcs");
        
		// Create New Tax Exemption Certificate for Invalidate functionality
		fTaxExemptionCertificate("TaxExemption_ACcs_NewTaxExemptionCertificate");

		//Click on Invalidate Certificate
        fInvalidateCertificate("TaxExemption_ACcs_InvalidateCertificate");
        softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
		//Delete Newly created data for Invalidate Tax Exemption Certificate
		clickonElement_Xpath("//td[@id='topButtonRow']//input[@title='Delete']");
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		
		softAssert.assertAll();
	}
	
	@Test(priority = 13, enabled = true, description = "Tax Calculation_Invalid Exmeption Certificate_Opportunity")
	public void TaxCalculation_InvalidExmeptionCertificate_Opportunity() throws Exception {
		Test13("TestCase13", "GenWatt Diesel 1000kW");
	}
	public void Test13(String testCaseID, String productName) throws Exception {
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
		fSaveToMyTestProjecteXAMPLE("TaxExemption_ACcs");
		
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
		// Create New Tax Exemption Certificate for Invalidate functionality
        fTaxExemptionCertificate("TaxExemption_ACcs_NewTaxExemptionCertificate");
        //Save Certificate to MyTestProjecteXAMPLE
        clickonElement_Xpath("//td[@class='pbButton']//input[@title='Save Certificate']");
        Thread.sleep(10000);
		clickonElement_Xpath("//button[normalize-space()='Back']");
		
		//sGLExpectedSuccessMessage = "Success - Certificate record(s) is saved in MyTestProjecteXAMPLE successfully.";
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
		//Upload Files
		fUploadFiles("TaxExemptionCertificate_EnabledMyTestProjecteXAMPLEExemption");
		sGLExpectedSuccessMessage = "Certificate saved to MyTestProjecteXAMPLE";
		 
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
		//Click on Invalidate Certificate
        fInvalidateCertificate("TaxExemption_ACcs_InvalidateCertificate");
		
		clickonElement_Xpath("//a[contains(text(),'Ajay')]");
		
		Thread.sleep(2000);
		TempOpportunity();
		
		// Enter data in Line Tab
		fLine_Entry("Opportunities");
		fTriggergetTax("Opportunities");
		fVerifyTaxValues("Opportunities");
		Assert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");

		/*Assert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);*/
		
		ExecutionDelay(2);
		clickonElement_Xpath("//a[contains(text(),'Ajay')]");
		clickonElement_Xpath("//a[contains(text(),'TaxExemption_Invalidate')]");
		
		// Delete Newly created data for Invalidate Tax Exemption Certificate
		clickonElement_Xpath("//td[@id='topButtonRow']//input[@title='Delete']");
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		
		softAssert.assertAll();
	}
	
	
	@Test(priority = 14, enabled = true, description = "Tax Calculation_Invalid Exmeption Certificate_Quote")
	public void TaxCalculation_InvalidExmeptionCertificate_Quote() throws Exception {
		Test14("TestCase14", "GenWatt Diesel 1000kW");
	}
	public void Test14(String testCaseID, String productName) throws Exception {
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
		fSaveToMyTestProjecteXAMPLE("TaxExemption_ACcs");
		System.out.println(sGLExpectedSuccessMessage);
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
		// Create New Tax Exemption Certificate for Invalidate functionality
        fTaxExemptionCertificate("TaxExemption_ACcs_NewTaxExemptionCertificate");
        //Save Certificate to MyTestProjecteXAMPLE
        clickonElement_Xpath("//td[@class='pbButton']//input[@title='Save Certificate']");
        Thread.sleep(10000);
		clickonElement_Xpath("//button[normalize-space()='Back']");
		
		//sGLExpectedSuccessMessage = "Success - Certificate record(s) is saved in MyTestProjecteXAMPLE successfully.";
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
		//Upload Files
		fUploadFiles("TaxExemptionCertificate_EnabledMyTestProjecteXAMPLEExemption");
		sGLExpectedSuccessMessage = "Certificate saved to MyTestProjecteXAMPLE";
		 
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
		//Click on Invalidate Certificate
        fInvalidateCertificate("TaxExemption_ACcs_InvalidateCertificate");
		
		clickonElement_Xpath("//a[contains(text(),'Ajay')]");
		
		Thread.sleep(2000);
		
		TempOpportunity();	
		//Enter data in Line Tab
		fLine_Entry("Opportunities");
		
		fMenuSelection("newQuote");
		//Enter data in Line Tab
		fLine_Entry("Quotes");
		
		fTriggergetTax("Quotes");
		fVerifyTaxValues("Quotes");
		Assert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");

		/*Assert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);*/
		
		ExecutionDelay(2);
		clickonElement_Xpath("//a[contains(text(),'Ajay')]");
		clickonElement_Xpath("//a[contains(text(),'TaxExemption_Invalidate')]");
		
		// Delete Newly created data for Invalidate Tax Exemption Certificate
		clickonElement_Xpath("//td[@id='topButtonRow']//input[@title='Delete']");
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		
		softAssert.assertAll();
	}
	
	@Test(priority = 15, enabled = true, description = "Tax Calculation_Invalid Exmeption Certificate_Order")
	public void TaxCalculation_InvalidExmeptionCertificate_Order() throws Exception {
		Test15("TestCase15", "GenWatt Diesel 1000kW");
	}
	public void Test15(String testCaseID, String productName) throws Exception {
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
		fSaveToMyTestProjecteXAMPLE("TaxExemption_ACcs");
		System.out.println(sGLExpectedSuccessMessage);
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
		// Create New Tax Exemption Certificate for Invalidate functionality
        fTaxExemptionCertificate("TaxExemption_ACcs_NewTaxExemptionCertificate");
        //Save Certificate to MyTestProjecteXAMPLE
        clickonElement_Xpath("//td[@class='pbButton']//input[@title='Save Certificate']");
        Thread.sleep(10000);
		clickonElement_Xpath("//button[normalize-space()='Back']");
		
		//sGLExpectedSuccessMessage = "Success - Certificate record(s) is saved in MyTestProjecteXAMPLE successfully.";
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
		//Upload Files
		fUploadFiles("TaxExemptionCertificate_EnabledMyTestProjecteXAMPLEExemption");
		sGLExpectedSuccessMessage = "Certificate saved to MyTestProjecteXAMPLE";
		 
		softAssert.assertTrue(sGLActualSuccessMessage.equalsIgnoreCase(sGLExpectedSuccessMessage),
				"SuccessMessage Expected:" + sGLExpectedSuccessMessage + " And Actual:" + sGLActualSuccessMessage);	
		
		//Click on Invalidate Certificate
        fInvalidateCertificate("TaxExemption_ACcs_InvalidateCertificate");
		
		clickonElement_Xpath("//a[contains(text(),'Ajay')]");
		
		Thread.sleep(2000);
		
		TempOpportunity();	
		
		fMenuSelection("newQuote");
		
		fMenuSelection("Orders");
		// Enter data in Line Tab
		fLine_Entry("Orders");
		fTriggergetTax("Orders");
		fVerifyTaxValues("Orders");
		Assert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
				"Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

		fVerifyStatusAndTaxOnWindow("OrdersPostGetTax");

		/*Assert.assertTrue(sGLActualMyTestProjectDocStatus.equalsIgnoreCase(sGLMyTestProjectDocStatus),
				"Expected:" + sGLMyTestProjectDocStatus + "And Actual:" + sGLActualMyTestProjectDocStatus);*/
		
		ExecutionDelay(2);
		clickonElement_Xpath("//a[contains(text(),'Ajay')]");
		clickonElement_Xpath("//a[contains(text(),'TaxExemption_Invalidate')]");
		
		// Delete Newly created data for Invalidate Tax Exemption Certificate
		clickonElement_Xpath("//td[@id='topButtonRow']//input[@title='Delete']");
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		
		softAssert.assertAll();
	}
	
}