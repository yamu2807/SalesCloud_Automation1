package TestCases.FunctionalAndRegression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import FunctionalLibrary.functionLibrary;

public class ThirdPartyAddressValidation extends functionLibrary {
	@BeforeClass
	public void ExcelSheetName() throws Exception {
		sExcelSheetBookName = "ThirdPartyAddressValidation";
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
		sGLEnableSalesOrder = GetValueFromExcelSheet("EnableUnCommittedInvoicesFlag");
		sGLEnableTaxCalculation = GetValueFromExcelSheet("EnableTaxCalculationFlag");
		sGLEnableUPCcode = GetValueFromExcelSheet("UPCCodeEnable");
		sGLTaxOverRide = GetValueFromExcelSheet("EnableTaxOverride");
		sGLEnableUPCcode = GetValueFromExcelSheet("UPCCodeEnable");
		sGLTaxOverRide = GetValueFromExcelSheet("EnableTaxOverride");

		sGLShiptoAddress_Line1 = GetValueFromExcelSheet("ShiptoAddress_Line1");
		sGLShiptoAddress_City = GetValueFromExcelSheet("ShiptoAddress_City");
		sGLShiptoAddress_State = GetValueFromExcelSheet("ShiptoAddress_State");
		sGLShiptoAddress_Zip = GetValueFromExcelSheet("ShiptoAddress_Zip");
		sGLShipToCountryCode = GetValueFromExcelSheet("ShiptoAddress_Country");
		sGLExpectedAddress = GetValueFromExcelSheet("VerifyFinalAddress");
		sGLAddressValidationFlag = GetValueFromExcelSheet("AddressValidationStatus");
		sGLEnableAddressValidation = GetValueFromExcelSheet("EnableAddressValidationFlag");
		sGLVerifyValidatedAddress = GetValueFromExcelSheet("VerifyValidatedAddressFlag");

	}

	@Test(priority = 1, enabled = true, description = "US address validated")
	public void TestCase162_USAddressValidated() throws Exception {
		addressValidation("TestCase162");
	}

	@Test(priority = 2, enabled = true, description = "CAN address validated")
	public void TestCase163_CANAddressValidated() throws Exception {
		addressValidation("TestCase163");
	}

	@Test(priority = 3, enabled = true, description = "Military address validated")
	public void TestCase164_MilitaryAddressValidated() throws Exception {
		addressValidation("TestCase164");
	}

	@Test(priority = 4, enabled = true, description = "Non-US, Non-CAN address")
	public void TestCase165_NonUS_NonCANAddress() throws Exception {
		addressValidationforInvalidAddress("TestCase165");
	}

//	@Test(priority = 5, enabled = true, description = "Address out of range")
	public void TestCase166_AddressOutOfRange() throws Exception {
		addressValidationforInvalidAddress("TestCase166");
	}

//	@Test(priority = 6, enabled = true, description = "Unknown Street")
	public void TestCase167_UnknownStreet() throws Exception {
		addressValidationforInvalidAddress("TestCase167");
	}

//	@Test(priority = 7, enabled = true, description = "Non deliverable address")
	public void TestCase168_NonDeliverableAddress() throws Exception {
		addressValidationforInvalidAddress("TestCase168");
	}

//	@Test(priority = 8, enabled = true, description = "City Error")
	public void TestCase169_CityError() throws Exception {
		addressValidationforInvalidAddress("TestCase169");
	}

	/* Consolidated Function */
	public void addressValidation(String testCaseID) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		sGLTestCaseName = "fAddressValidation_ValidAddress";
		sGLItem1Code = "GenWatt Diesel 1000kW";
		sGLTestcaseId = testCaseID;
		ValuesFromExcelSheet();
		Thread.sleep(3000);
		// Address validation for ACcS
		fMenuSelection("ACcs");

		fTriggerShipToAddress("ACcs");

		sGLActualValidatedAddress = fVerifyValidatedShipToAndBillToAddress("ACcs");
		softAssert.assertTrue(sGLActualValidatedAddress.equalsIgnoreCase(sGLExpectedAddress),
				"ACcs Expected:" + sGLExpectedAddress + "\tAnd Actual:" + sGLActualValidatedAddress);

		fTriggerBillToAddress("ACcs");
		sGLActualValidatedAddress = fVerifyValidatedShipToAndBillToAddress("ACcs");
		softAssert.assertTrue(sGLActualValidatedAddress.equalsIgnoreCase(sGLExpectedAddress),
				"ACcs Expected:" + sGLExpectedAddress + "\tAnd Actual:" + sGLActualValidatedAddress);

		// Create Opportunity for Quotes
		Thread.sleep(1000);
		TempOpportunity();

		// Address validation for QUOTES
		fMenuSelection("newQuote");

		fTriggerShipToAddress("newQuote");
		sGLActualValidatedAddress = fVerifyValidatedShipToAndBillToAddress("newQuote");
		softAssert.assertTrue(sGLActualValidatedAddress.equalsIgnoreCase(sGLExpectedAddress),
				"newQuote Expected:" + sGLExpectedAddress + "\tAnd Actual:" + sGLActualValidatedAddress);

		fTriggerBillToAddress("newQuote");
		sGLActualValidatedAddress = fVerifyValidatedShipToAndBillToAddress("newQuote");
		softAssert.assertTrue(sGLActualValidatedAddress.equalsIgnoreCase(sGLExpectedAddress),
				"newQuote Expected:" + sGLExpectedAddress + "\tAnd Actual:" + sGLActualValidatedAddress);

		// Orders
		fMenuSelection("Orders");
		fTriggerShipToAddress("Orders");
		sGLActualValidatedAddress = fVerifyValidatedShipToAndBillToAddress("Orders");
		softAssert.assertTrue(sGLActualValidatedAddress.equalsIgnoreCase(sGLExpectedAddress),
				"Orders Expected:" + sGLExpectedAddress + "\tAnd Actual:" + sGLActualValidatedAddress);

		fTriggerBillToAddress("Orders");
		sGLActualValidatedAddress = fVerifyValidatedShipToAndBillToAddress("Orders");
		softAssert.assertTrue(sGLActualValidatedAddress.equalsIgnoreCase(sGLExpectedAddress),
				"Orders Expected:" + sGLExpectedAddress + "\tAnd Actual:" + sGLActualValidatedAddress);

		softAssert.assertAll();
	}

	public void addressValidationforInvalidAddress(String testCaseID) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		sGLTestCaseName = "fAddressValidation_ValidAddress";
		sGLItem1Code = "GenWatt Diesel 1000kW";

		sGLTestcaseId = testCaseID;
		ValuesFromExcelSheet();
		Thread.sleep(2000);
		// Orders
		fMenuSelection("Orders");
		fTriggerShipToAddress("Orders");
	//	sGLActualValidatedAddress = fVerifyValidatedShipToAndBillToAddress("Orders");
		clickonElement_Xpath("//td[@id='topButtonRow']//input[@title='Validate Billing Address']");
		fVerifyValidatedShipToAndBillToAddress("Orders");
		System.out.println(sGLActualValidatedAddress);
		softAssert.assertTrue(sGLActualValidatedAddress.equalsIgnoreCase(sGLExpectedAddress),
				"Expected:" + sGLExpectedAddress + "\tAnd Actual:" + sGLActualValidatedAddress);

		fTriggerBillToAddress("Orders");
		fVerifyValidatedShipToAndBillToAddress("Orders");
		System.out.println(sGLActualValidatedAddress);
		softAssert.assertTrue(sGLActualValidatedAddress.equalsIgnoreCase(sGLExpectedAddress),
				"Expected:" + sGLExpectedAddress + "\tAnd Actual:" + sGLActualValidatedAddress);

		// Address validation for ACcS
		fMenuSelection("ACcs");

		fTriggerShipToAddress("ACcs");

		fVerifyValidatedShipToAndBillToAddress("ACcs");
		System.out.println(sGLActualValidatedAddress);
		softAssert.assertTrue(sGLActualValidatedAddress.equalsIgnoreCase(sGLExpectedAddress),
				"Expected:" + sGLExpectedAddress + "\tAnd Actual:" + sGLActualValidatedAddress);

		fTriggerBillToAddress("ACcs");
		sGLActualValidatedAddress = fVerifyValidatedShipToAndBillToAddress("ACcs");
		softAssert.assertTrue(sGLActualValidatedAddress.equalsIgnoreCase(sGLExpectedAddress),
				"Expected:" + sGLExpectedAddress + "\tAnd Actual:" + sGLActualValidatedAddress);
		// Create Opportunity for Quotes
		TempOpportunity();

		// Address validation for QUOTES
		fMenuSelection("newQuote");

		fTriggerShipToAddress("newQuote");
		sGLActualValidatedAddress = fVerifyValidatedShipToAndBillToAddress("newQuote");
		softAssert.assertTrue(sGLActualValidatedAddress.equalsIgnoreCase(sGLExpectedAddress),
				"Expected:" + sGLExpectedAddress + "\tAnd Actual:" + sGLActualValidatedAddress);

		fTriggerBillToAddress("newQuote");
		sGLActualValidatedAddress = fVerifyValidatedShipToAndBillToAddress("newQuote");
		softAssert.assertTrue(sGLActualValidatedAddress.equalsIgnoreCase(sGLExpectedAddress),
				"Expected:" + sGLExpectedAddress + "\tAnd Actual:" + sGLActualValidatedAddress);

		softAssert.assertAll();
	}
}