package TestCases.FunctionalAndRegression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import FunctionalLibrary.functionLibrary;

public class AddressValidation_MyTestProjecteXAMPLECompanyCodes extends functionLibrary {
	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void ExcelSheetName() throws Exception {
		sExcelSheetBookName = "AddressValidation";
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

		sGLShiptoAddress_Line1 = GetValueFromExcelSheet("ShiptoAddress_Line1");
		sGLShiptoAddress_City = GetValueFromExcelSheet("ShiptoAddress_City");
		sGLShiptoAddress_State = GetValueFromExcelSheet("ShiptoAddress_State");
		sGLShiptoAddress_Zip = GetValueFromExcelSheet("ShiptoAddress_Zip");
		sGLShipToCountryCode = GetValueFromExcelSheet("ShiptoAddress_Country");
		sGLExpectedAddress = GetValueFromExcelSheet("VerifyFinalAddress");
		sGLAddressValidationFlag = GetValueFromExcelSheet("AddressValidationStatus");
		sGLEnableAddressValidation = GetValueFromExcelSheet("EnableAddressValidationFlag");
		sGLVerifyValidatedAddress = GetValueFromExcelSheet("VerifyValidatedAddressFlag");
		// Expected Result for verification
		sGLExpectedErrorMessage = GetValueFromExcelSheet("ExpectedErrorMessage");
		sReplaceWithValidAddressFlag = GetValueFromExcelSheet("ReplaceWithValidatedAddress");
		sGLEnableSalesOrder = GetValueFromExcelSheet("EnableUnCommittedInvoicesFlag");
		sGLEnableTaxCalculation = GetValueFromExcelSheet("EnableTaxCalculationFlag");
		sGLEnableVAT = GetValueFromExcelSheet("EnableVAT");
		sGLErrorMessageDisplayed = GetValueFromExcelSheet("EnableErrorMessage");
		sGLEnableUPCcode = GetValueFromExcelSheet("UPCCodeEnable");
		sGLTaxOverRide = GetValueFromExcelSheet("EnableTaxOverride");
		sMyTestProjecteXAMPLECredentialFlag = GetValueFromExcelSheet("MyTestProjecteXAMPLEcredFlag");
		// Origin Address
		sGLOriginAddress_Line1 = GetValueFromExcelSheet("OriginAddress_Street");
		sGLOriginAddress_City = GetValueFromExcelSheet("OriginAddress_City");
		sGLOriginAddress_State = GetValueFromExcelSheet("OriginAddress_State");
		sGLOriginAddress_Zip = GetValueFromExcelSheet("OriginAddress_ZipCode");
		sGLOriginAddress_Country = GetValueFromExcelSheet("OriginAddress_Country");

		// LineLevelAddress
		sGLAmount = GetValueFromExcelSheet("Amount");
		//Temporary
		sGLEnableMyTestProjecteXAMPLEExemptions = GetValueFromExcelSheet("EnableMyTestProjecteXAMPLEExemptions");
	}
	
	// Tax Calculation on Primary Shipping Address ACc							 
	

		@Test(priority = 1, enabled= true, description = "EnableTaxCalc ACc Primary ShipTo Address CAN Uncommit")
		public void TestCase1() throws Exception {
			Validation("TestCaseX", "GenWatt Diesel 1000kW");
		}
		
		public void Validation(String testCaseID, String productName) throws Exception {
		//	SoftAssert softAssert = new SoftAssert();
			sGLTestCaseName = "taxCodes";
			sGLItem1Code = productName;
			sGLTestcaseId = testCaseID;
			// BELOW IS THE EXCEL SHEET CONNECTION
			ValuesFromExcelSheet();
			Thread.sleep(2000);
			fMenuSelection("TaxNowSettings");
			Thread.sleep(5000);
			// Change Tax now settings depending on Flags retrieved from Excel file
			fMyTestProjecteXAMPLEConfigurationSettings();
			fMyTestProjecteXAMPLECompanyCodes("MyTestProjecteXAMPLE Company Codes");
			
			sGLExpectedAddress = "Line 1: 900 WINSLOW WAY E, City: BAINBRIDGE ISLAND, State/Region: WA, Postal Code: 98110-2450, Country: US";
			sGLExpectedMessageForAddressUpdate = "Company Address is updated successfully";
			
			fTriggerValidateCompanyAddress("MyTestProjecteXAMPLE Company Codes");
			fVerifyValidatedCompanyAddress("MyTestProjecteXAMPLE Company Codes");
			
			System.out.println("Verification Starts");
			softAssert.assertTrue(sGLActualValidatedAddress.equalsIgnoreCase(sGLExpectedAddress),
					"Expected:" + sGLExpectedAddress + "And Actual:" + sGLActualValidatedAddress);
			
			softAssert.assertTrue(sGLActualMessageForAddressUpdate.equalsIgnoreCase(sGLExpectedMessageForAddressUpdate),
					"Expected:" + sGLExpectedMessageForAddressUpdate + "And Actual:" + sGLActualMessageForAddressUpdate);
			
			Thread.sleep(2000);
			softAssert.assertAll();
			
			clickonElement_Xpath("//td[@id='topButtonRow']//input[@title='Delete']");
			driver.switchTo().alert().accept();
			Thread.sleep(3000);
			
		}
		
		
		
		@Test(priority = 2, enabled=true, description = "EnableTaxCalc ACc Primary ShipTo Address CAN Uncommit")
		public void TestCase2() throws Exception {
			Validation1("TestCaseX1", "GenWatt Diesel 1000kW");
		}
		
		public void Validation1(String testCaseID, String productName) throws Exception {
		//	SoftAssert softAssert = new SoftAssert();
			sGLTestCaseName = "taxCodes";
			sGLItem1Code = productName;
			sGLTestcaseId = testCaseID;
			// BELOW IS THE EXCEL SHEET CONNECTION
			ValuesFromExcelSheet();
			Thread.sleep(2000);
			fMenuSelection("TaxNowSettings");
			Thread.sleep(5000);
			// Change Tax now settings depending on Flags retrieved from Excel file
			fMyTestProjecteXAMPLEConfigurationSettings();
			fMyTestProjecteXAMPLECompanyCodes("MyTestProjecteXAMPLE Company Codes");
			
			fTriggerValidateCompanyAddress("MyTestProjecteXAMPLE Company Codes");
			fVerifyValidatedCompanyAddress("MyTestProjecteXAMPLE Company Codes");
			
			System.out.println(sGLExpectedErrorMessage);
			softAssert.assertTrue(sGLActualValidatedAddress.equalsIgnoreCase(sGLExpectedErrorMessage),
					"Expected:" + sGLExpectedErrorMessage + "And Actual:" + sGLActualValidatedAddress);
			
			Thread.sleep(2000);
			softAssert.assertAll();
			
			clickonElement_Xpath("//td[@id='topButtonRow']//input[@title='Delete']");
			driver.switchTo().alert().accept();
			Thread.sleep(3000);
			
		}
		
				
		@Test(priority = 3, enabled=true, description = "EnableTaxCalc ACc Primary ShipTo Address CAN Uncommit")
		public void TestCase3() throws Exception {
			Validation2("TestCaseX2", "GenWatt Diesel 1000kW");
		}
		
		public void Validation2(String testCaseID, String productName) throws Exception {
		//	SoftAssert softAssert = new SoftAssert();
			sGLTestCaseName = "taxCodes";
			sGLItem1Code = productName;
			sGLTestcaseId = testCaseID;
			// BELOW IS THE EXCEL SHEET CONNECTION
			ValuesFromExcelSheet();
			Thread.sleep(2000);
			fMenuSelection("TaxNowSettings");
			Thread.sleep(5000);
			// Change Tax now settings depending on Flags retrieved from Excel file
			fMyTestProjecteXAMPLEConfigurationSettings();
			fMyTestProjecteXAMPLECompanyCodes("MyTestProjecteXAMPLE Company Codes");
			
			fTriggerValidateCompanyAddress("MyTestProjecteXAMPLE Company Codes");
			fVerifyValidatedCompanyAddress("MyTestProjecteXAMPLE Company Codes");
			
			System.out.println(sGLExpectedErrorMessage);
			softAssert.assertTrue(sGLActualValidatedAddress.equalsIgnoreCase(sGLExpectedErrorMessage),
					"Expected:" + sGLExpectedErrorMessage + "And Actual:" + sGLActualValidatedAddress);
			
			Thread.sleep(2000);
			softAssert.assertAll();
			
			clickonElement_Xpath("//td[@id='topButtonRow']//input[@title='Delete']");
			driver.switchTo().alert().accept();
			Thread.sleep(3000);
		}
		
		
		
		@Test(priority = 4, enabled=true, description = "EnableTaxCalc ACc Primary ShipTo Address CAN Uncommit")
		public void TestCase4() throws Exception {
			Validation3("TestCaseX3", "GenWatt Diesel 1000kW");
		}
		
		public void Validation3(String testCaseID, String productName) throws Exception {
		//	SoftAssert softAssert = new SoftAssert();
			sGLTestCaseName = "taxCodes";
			sGLItem1Code = productName;
			sGLTestcaseId = testCaseID;
			// BELOW IS THE EXCEL SHEET CONNECTION
			ValuesFromExcelSheet();
			Thread.sleep(2000);
			fMenuSelection("TaxNowSettings");
			Thread.sleep(5000);
			// Change Tax now settings depending on Flags retrieved from Excel file
			fMyTestProjecteXAMPLEConfigurationSettings();
			fMyTestProjecteXAMPLECompanyCodes("MyTestProjecteXAMPLE Company Codes");
			
			fTriggerValidateCompanyAddress("MyTestProjecteXAMPLE Company Codes");
			fVerifyValidatedCompanyAddress("MyTestProjecteXAMPLE Company Codes");
			
			System.out.println(sGLExpectedErrorMessage);
			softAssert.assertTrue(sGLActualValidatedAddress.equalsIgnoreCase(sGLExpectedErrorMessage),
					"Expected:" + sGLExpectedErrorMessage + "And Actual:" + sGLActualValidatedAddress);
			
			Thread.sleep(2000);
			softAssert.assertAll();
			
			clickonElement_Xpath("//td[@id='topButtonRow']//input[@title='Delete']");
			driver.switchTo().alert().accept();
			Thread.sleep(3000);
			
			
		}
		
		
		@Test(priority = 5, enabled=true, description = "EnableTaxCalc ACc Primary ShipTo Address CAN Uncommit")
		public void TestCase5() throws Exception {
			Validation4("TestCaseX4", "GenWatt Diesel 1000kW");
		}
		
		public void Validation4(String testCaseID, String productName) throws Exception {
		//	SoftAssert softAssert = new SoftAssert();
			sGLTestCaseName = "taxCodes";
			sGLItem1Code = productName;
			sGLTestcaseId = testCaseID;
			// BELOW IS THE EXCEL SHEET CONNECTION
			ValuesFromExcelSheet();
			Thread.sleep(2000);
			fMenuSelection("TaxNowSettings");
			Thread.sleep(5000);
			// Change Tax now settings depending on Flags retrieved from Excel file
			fMyTestProjecteXAMPLEConfigurationSettings();
			fMyTestProjecteXAMPLECompanyCodes("MyTestProjecteXAMPLE Company Codes");
			
			fTriggerValidateCompanyAddress("MyTestProjecteXAMPLE Company Codes");
			fVerifyValidatedCompanyAddress("MyTestProjecteXAMPLE Company Codes");
			
			System.out.println(sGLExpectedErrorMessage);
			softAssert.assertTrue(sGLActualValidatedAddress.equalsIgnoreCase(sGLExpectedErrorMessage),
					"Expected:" + sGLExpectedErrorMessage + "And Actual:" + sGLActualValidatedAddress);
			
			Thread.sleep(2000);
			softAssert.assertAll();
			
			clickonElement_Xpath("//td[@id='topButtonRow']//input[@title='Delete']");
			driver.switchTo().alert().accept();
			
			Thread.sleep(3000);
			
			
		}
}