package TestCases.FunctionalAndRegression;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import FunctionalLibrary.functionLibrary;

//USE NIRAV's ACc WHEN RUNNING CROSS BORDER TEST CASES
public class CrossBorder extends functionLibrary {
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void ExcelSheetName() throws Exception {
        sExcelSheetBookName = "CrossBorder";
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

        sGLImportDutyFlag = GetValueFromExcelSheet("EnableCrossBorderFlag");
        sGLEnableAddressValidation = GetValueFromExcelSheet("EnableAddressValidationFlag");
        sGLVerifyValidatedAddress = GetValueFromExcelSheet("VerifyValidatedAddressFlag");
        sGLEnableTaxCode = GetValueFromExcelSheet("EnableTaxCodeMapping");
        sGLEnableIOR = GetValueFromExcelSheet("EnableIOR");
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
        sGLCrossBorderSummaryMessageFlag = GetValueFromExcelSheet("sGLCrossBorderSummaryMessageFlag");

        // TEMPORARY
        sGLAmount = GetValueFromExcelSheet("Amount");
        sGLTotalTax = GetValueFromExcelSheet("TotalTax");
        sExpectedImportFees = GetValueFromExcelSheet("ImportFees");
       // sExpectedSalesTax = GetValueFromExcelSheet("TotalTax");


        sGLEnableVAT = GetValueFromExcelSheet("EnableVAT");
        sGLInvoiceMessaging = GetValueFromExcelSheet("InvoiceMessaging");
        sGLEnableMyTestProjecteXAMPLEExemptions = GetValueFromExcelSheet("EnableMyTestProjecteXAMPLEExemptions");

    }

    @Test(priority = 1, enabled = true, description = "TestCase180_CustomDutyCalculated_DeminimusThresholdMet_IORYes_HSCode")
    public void TestCase180_CustomDutyCalculated_DeminimusThresholdMet_IORYes_HSCode() throws Exception {
   // The Item code is the product or item having HS CODE configured in the MyTestProjecteXAMPLE company items for The shipto country
        calculateCrossBorderImportDuty("TestCase180", "UPC:sanyotv"); //This itemcode or active product should have the details matching to Nirav's ACc itemcode("whatyousell") details hs code: 7011208510  tax code: P0000000
    }

    @Test(priority = 2, enabled = true, description = "TestCase181_CustomDutyCalculated_DeminimusThresholdMet_IORYes_ProductCategory")
    public void TestCase181_CustomDutyCalculated_DeminimusThresholdMet_IORYes_ProductCategory() throws Exception {
        // The Item code is the product or item having  Product category configured and no HS Code in the MyTestProjecteXAMPLE company items
        calculateCrossBorderImportDuty("TestCase181","SKU 1000.6"); //This itemcode or active product should have the details matching to Nirav's ACc itemcode("whatyousell") details hs code: but product category: Man - Jewelry, Gold - Bracelet  tax code: P0000000
    }


    @Test(priority = 3, enabled = true, description = "TestCase182_CustomDutyCalculated_DeminimusThresholdNotMet_IORYes_HSCode")
    public void TestCase182_CustomDutyCalculated_DeminimusThresholdNotMet_IORYes_HSCode() throws Exception {
        // The Item code is the product or item having  Product category configured and no HS Code in the MyTestProjecteXAMPLE company items
        calculateCrossBorderImportDuty("TestCase182", "Product by Nirav"); //This itemcode or active product should have the details matching to Nirav's ACc itemcode("whatyousell") details hs code: 7011208510  tax code: P0000000
    }
    @Test(priority = 4, enabled = true, description = "TestCase183_CustomDutyCalculated_DeminimusThresholdMet_IORNo_HSCode")
    public void TestCase183_CustomDutyCalculated_DeminimusThresholdMet_IORNo_HSCode() throws Exception {
        // The Item code is the product or item having  Product category configured and no HS Code in the MyTestProjecteXAMPLE company items
        calculateCrossBorderImportDuty("TestCase183", "UPC:sanyotv"); //This itemcode or active product should have the details matching to Nirav's ACc itemcode("whatyousell") details hs code: 7011208510  tax code: P0000000
    }
        @Test(priority = 5, enabled = true, description = "TestCase184_CustomDutyCalculated_DeminimusThresholdNotMet_IORNo_HSCode")
        public void TestCase184_CustomDutyCalculated_DeminimusThresholdNotMet_IORNo_HSCode() throws Exception {
            // The Item code is the product or item having  Product category configured and no HS Code in the MyTestProjecteXAMPLE company items
            calculateCrossBorderImportDuty("TestCase184", "Product by Nirav"); //This itemcode or active product should have the details matching to Nirav's ACc itemcode("whatyousell") details hs code: 7011208510  tax code: P0000000
        }

    @Test(priority = 6, enabled = true, description = "TestCase185_CustomDutyNotCalculated_DeminimusThresholdMet_IORYes_NoHSCode")
    public void TestCase185_CustomDutyNotCalculated_DeminimusThresholdMet_IORYes_NoHSCode() throws Exception {
        // The Item code is the product or item having  Product category configured and no HS Code in the MyTestProjecteXAMPLE company items
        calculateCrossBorderImportDuty("TestCase185", "GenWatt Diesel 10kW"); //This itemcode or active product should have the details matching to Nirav's ACc itemcode("whatyousell") details hs code:   tax code:
    }
    @Test(priority = 7, enabled = true, description = "TestCase186_EUtoEUCustomNotDutyCalculated_DeminimusThresholdMet_IORYes_HSCode")
    public void TestCase186_EUtoEUCustomNotDutyCalculated_DeminimusThresholdMet_IORYes_HSCode() throws Exception {
        // The Item code is the product or item having  Product category configured and no HS Code in the MyTestProjecteXAMPLE company items
        calculateCrossBorderImportDuty("TestCase186", "UPC:sanyotv"); //This itemcode or active product should have the details matching to Nirav's ACc itemcode("whatyousell") details hs code: 7011208510  tax code: P0000000
    }
    @Test(priority = 8, enabled = true, description = "TestCase187_UStoUSCustomNotDutyCalculated_DeminimusThresholdMet_IORYes_HSCode")
    public void TestCase187_UStoUSCustomNotDutyCalculated_DeminimusThresholdMet_IORYes_HSCode() throws Exception {
        // The Item code is the product or item having  Product category configured and no HS Code in the MyTestProjecteXAMPLE company items
        calculateCrossBorderImportDuty("TestCase187", "UPC:sanyotv");  //This itemcode or active product should have the details matching to Nirav's ACc itemcode("whatyousell") details hs code: 7011208510  tax code: P0000000
    }
    @Test(priority =9, enabled = true, description = "TestCase188_UStoUKCustomDutyCalculated_DeminimusThresholdMet_IORYes_HSCode")
    public void TestCase188_UStoUKCustomDutyCalculated_DeminimusThresholdMet_IORYes_HSCode() throws Exception {
        // The Item code is the product or item having  Product category configured and no HS Code in the MyTestProjecteXAMPLE company items
        calculateCrossBorderImportDuty("TestCase188", "UPC:sanyotv"); //This itemcode or active product should have the details matching to Nirav's ACc itemcode("whatyousell") details hs code: 7011208510  tax code: P0000000
    }
    @Test(priority =10, enabled = true, description = "TestCase189_UStoFRCustomDutyCalculated_DeminimusThresholdMet_IORYes_HSCode")
    public void TestCase189_UStoFRCustomDutyCalculated_DeminimusThresholdMet_IORYes_HSCode() throws Exception {
        // The Item code is the product or item having  Product category configured and no HS Code in the MyTestProjecteXAMPLE company items
        calculateCrossBorderImportDuty("TestCase189", "UPC:sanyotv"); //This itemcode or active product should have the details matching to Nirav's ACc itemcode("whatyousell") details hs code: 7011208510  tax code: P0000000
    }
    @Test(priority =11, enabled = true, description = "TestCase190_UStoCANCustomDutyCalculated_DeminimusThresholdMet_IORYes_HSCode")
    public void TestCase190_UStoCANCustomDutyCalculated_DeminimusThresholdMet_IORYes_HSCode() throws Exception {
        // The Item code is the product or item having  Product category configured and no HS Code in the MyTestProjecteXAMPLE company items
        calculateCrossBorderImportDuty("TestCase190", "UPC:sanyotv"); //This itemcode or active product should have the details matching to Nirav's ACc itemcode("whatyousell") details hs code: 7011208510  tax code: P0000000
    }
    @Test(priority =12, enabled = true, description = "TestCase191_CustomDutyCalculated_DeminimusThresholdMet_IORYes_HSCode_TaxcodePB500000")
    public void TestCase191_CustomDutyCalculated_DeminimusThresholdMet_IORYes_HSCode_TaxcodePB500000() throws Exception {
        // The Item code is the product or item having  Product category configured and no HS Code in the MyTestProjecteXAMPLE company items
        calculateCrossBorderImportDuty("TestCase191", "24MB01");  //This itemcode or active product should have the details matching to Nirav's ACc itemcode("whatyousell") details hs code: 7011208510  tax code: PB500000
    }
    public void calculateCrossBorderImportDuty(String testCaseId, String itemCode1) throws Exception {
        SoftAssert softAssert = new SoftAssert();
        sGLTestCaseName = "fTaxCalculation_CrossBorder";
        // Tax code NT
        sGLItem1Code = itemCode1;
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
         fVerifyImportFeesValues("Opportunities");

        Assert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
                "Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);

        Assert.assertTrue(sExpectedImportFees.equalsIgnoreCase(sGLImportFees),
                "Expected:" + sExpectedImportFees + "And Actual:" + sGLImportFees);

        // End fCalculateSalesTax
        fMenuSelection("newQuote");
        // Enter data in Line Tab
        fLine_Entry("Quotes");
        fTriggergetTax("Quotes");
        fVerifyTaxValues("Quotes");
        fVerifyImportFeesValues("Quotes");
        Assert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
                "Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);
        Assert.assertTrue(sExpectedImportFees.equalsIgnoreCase(sGLImportFees),
                "Expected:" + sExpectedImportFees + "And Actual:" +sGLImportFees );
       // Assert.assertTrue(sExpectedImportFees.equalsIgnoreCase(sGLTotalTax),
         //       "Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);


        ///////////// ""Orders""/////////////////////
        sGLTotalTax = GetValueFromExcelSheet("TotalTax");
        fMenuSelection("Orders");
        // Enter data in Line Tab
        fLine_Entry("Orders");
        fTriggergetTax("Orders");
        fVerifyTaxValues("Orders");
        fVerifyImportFeesValues("Orders");
        String ActualCrossBorderMessage = fVerifyCrossBorderSummaryMessage("Orders");
        System.out.println("ActualCrossBorderMessage");
        Assert.assertTrue(sExpectedSalesTax.equalsIgnoreCase(sGLTotalTax),
                "Expected:" + sGLTotalTax + "And Actual:" + sExpectedSalesTax);
        Assert.assertTrue(sExpectedImportFees.equalsIgnoreCase(sGLImportFees),
                "Expected:" + sExpectedImportFees  + "And Actual:" + sGLImportFees);
        if(sGLCrossBorderSummaryMessageFlag != "0")
        Assert.assertTrue((!ActualCrossBorderMessage.isEmpty() && ActualCrossBorderMessage != "" && ActualCrossBorderMessage!=null),
                "Actual:" + ActualCrossBorderMessage);
        softAssert.assertAll();
    }
}
