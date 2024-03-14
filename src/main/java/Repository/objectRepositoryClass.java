package Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class objectRepositoryClass {

	// SalesForce//
	protected static String sValueFromTextFile = "0";
	protected static String sExcelSheetBookName = "0";
	protected static Properties prop = null;

	protected static String sGLActualErrorMessage = "0";
	protected static String sGLMyTestProjectDocStatus = null;
	protected static String sGLActualMyTestProjectDocStatus = null;

	protected static String Value;
	protected static String sGLTestcaseId = "0";

	protected static String sGLExpectedAddress = null;
	protected static String sGLActualValidatedAddress = null;
	protected static String sGLActualValidatedErrorMessage = null;
	protected static String sGLTOQuantity;
	protected static String sGLTOSalesTaxAmount;
	protected static String sGLTOSalesPrice;
	protected static String sGLTOUnitPrice;
	protected static String sGLActualAutMessage = null;
	protected static String sGLOpportunityLineLevelShippingAddress = null;
	protected static String sGLQuoteLineLevelShippingAddress = null;
	protected static String sGLOpportunityOriginAddress = null;
	protected static String sGLQuoteOriginAddress = null;
	
	protected static String sGLExpectedMessageForAddressUpdate = null;
	protected static String sGLActualMessageForAddressUpdate = null;

	// Code Decode method
	public static StandardPBEStringEncryptor encryptor = null;

	// Workflow rules
	protected static String sGLWorkflowRulesForGetTax = null;
	protected static String sGLEnableTriggerForGetTax = null;
	protected static String sGLTriggerStatus = null;

	// Tax Calc scenarios
	protected static String sGLInvoiceType = null;
	protected static String sGLShipFromAddress_Line1 = null;
	protected static String sGLShipFromAddress_Line2 = null;
	protected static String sGLShipFromAddress_City = null;
	protected static String sGLShipFromAddress_State = null;
	protected static String sGLShipFromAddress_Zip = null;

	protected static String sGLShiptoAddress_Line1 = null;
	protected static String sGLShiptoAddress_Line2 = null;
	protected static String sGLShiptoAddress_City = null;
	protected static String sGLShiptoAddress_State = null;
	protected static String sGLShiptoAddress_Zip = null;

	protected static String sGLSecondaryShiptoAddress_Line1 = null;
	protected static String sGLSecondaryShiptoAddress_Line2 = null;
	protected static String sGLSecondaryShiptoAddress_City = null;
	protected static String sGLSecondaryShiptoAddress_State = null;
	protected static String sGLSecondaryShiptoAddress_Zip = null;
	protected static String sGLSecondaryShipToAddressCountryCode = null;
	// For Multi-Entity
	protected static String sGLEnableMultiEntity = "0";
	protected static String sGLSubsidiaryObjectConfig = "MultientitySub__c";
	protected static String sGLSubsidiaryFieldConfig = "MultientitySub__c";
	protected static String sGLACcSubsidiaryValue = "Sub1";
	protected static String sGLCompanyCode = "Default";

	protected static String sGLACcShipTo = "0";
	protected static String sGLACcBillTo = "0";
	protected static String sGLQuotesShipTo = "0";
	protected static String sGLQuotesBillTo = "0";
	protected static String sGLOrderShipTo = "0";
	protected static String sGLOrderBillTo = "0";
	
	protected static String sGLOriginAddress_Line1 = null;
	protected static String sGLOriginAddress_City = null;
	protected static String sGLOriginAddress_State = null;
	protected static String sGLOriginAddress_Zip = null;
	protected static String sGLOriginAddress_Country = null;

	// Contacts address tax calc and Fall back mechanism
	protected static String sGLOpportunitiesContactsShipToFlag = "0";
	protected static String sGLOpportunitiesContactsBillToFlag = "0";

	protected static String sGLExpectedErrorMessage = null;

	//////// CHROME /////////
	protected static String sGLBrowser = System.setProperty("webdriver.chrome.driver", "122.exe");
	protected static WebDriver driver = null;
	protected WebDriverWait wait = null;
	protected WebElement element = null;

	protected static final String sGL_MAX_WAIT_TIME_IN_MS = "60000";
	protected static final long lGL_MAX_WAIT_TIME_IN_Seconds = 60;

	protected static String sGLProjectName = "SALESFORCE";
	public static int iGLRoleName = 10;
	// Generic Parameters
	// Test Case Name
	protected static String sGLTestCaseName = "";

	// Database Connection
	protected static Connection oConnection = null;
	// Result Set
	protected static ResultSet oResultSet = null;
	// Statment
	protected static Statement oStatement = null;
	protected static String sGlMyTestProjectStatus = null;
	protected static String sGLTaxCodeForMultipleLines = null;
	protected static String sGLAmountForMultipleLines = null;
	protected static String sGLTestConnectionFlag = null;
	protected static String sGLTaxOverRide = null;
	protected static String sGLEnableUPCcode = null;
	protected static String sGLEnableVAT = "0";

	protected static String sGLEnableIOR = "0";
	protected static String sGLInvoiceMessaging = null;
	protected static String sGLReturnAddressInUpperCase = null;
	protected static String sGLEnableTaxCalculationForUnCommit = null;
	protected static String sGLEnableUseACcNameforCustIdentification = "0";
	protected static String sGLEnableACcNumber = "0";
	protected static String sGL = null;
	protected static String sGLEnableSalesOrder = null;

	// Date Format
	protected static String sGLDate = "3/26/2021";
	protected static String OverrideTaxDate = "3/26/2021";

	protected static int iGLCalculateTax = 0;
	protected static int iGLEnterShiptoAddressontheFly = 1;

	// ERP details
	protected static String sGLShipToAddressCode = null;
	protected static String sGLCustExemptNo = null;
	protected static String sGLItem1Code = null;
	protected static String sGLAmount = null;
	protected static String sGLTotalTax = null;
	protected static String sExpectedSalesTax = null;
	protected static String sExpectedImportFees = null;
	protected static String sGLImportFees = null;
	protected static String sGLEnableBillingAddress = "0";
	protected static String sGLShippingHandlingTax = null;
	protected static String sGLActualShippingHandlingTax = null;
	protected static String sGLShippingHandling = null;
	protected static String sGLCountryPickList = "0";
	protected static String sGLEnableAddressValidation = null;
	protected static String sGLVerifyValidatedAddress = null; // sGLMyTestProjectDocStatus

	protected static String sGLEntityUseCode = null;
	protected static String sGLErrorMessageDisplayed = null;
	protected static String sGLAddressValidationFlag = "0";
	protected static String sGLImportDutyFlag = "0";
	protected static String sMyTestProjecteXAMPLECredentialFlag = "0";
	protected static String sReplaceWithValidAddressFlag = "0";
	protected static String sGLEnableTaxCalculation = null;
	protected static String sGLEnableMyTestProjecteXAMPLEExemptions = null;
	protected static String sGLEnableUnCommitts = null;
	protected static String sGLEnableCommittedInvoices = null;
	protected static String sGLAutomationTaxCalculation = null;
	protected static String sGLShipToCountryCode = null;
	protected static String sGLShipFromCountryCode = null;
	protected static String sGLEnableEntityUseCode = "0";
	protected static String sGLEnableTaxCode = "0";
	protected static String sGLShippingTaxCode = null;
	protected static String sGLUPCCode = "0";
	
	//Success Messages
	protected static String sGLExpectedSuccessMessage = null;
	protected static String sGLActualSuccessMessage = null;
	protected static String sGLCrossBorderSummaryMessageFlag = "0";
}
