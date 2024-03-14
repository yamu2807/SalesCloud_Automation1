package FunctionalLibrary;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import GenericFunctionLibrary.genericFunctions;

public class functionLibrary extends genericFunctions {

	/****************** Login *******************************************/
	String usnm_TextBox = "usnm";
	String pd_TextBox = "pd";
	String login_Button = "Login";

	/******************
	 * MyTestProjecteXAMPLE Credential Verification
	 *******************************************/
	String ACcNumberTextBox = "//label[text()='ACc Number']/ancestor::div[1]//div[1]//input";
	String lickeyTextBox = "//label[text()='lic Key']/ancestor::div[1]//div[1]/input";
	String lickeyLabel = "//label[text()='lic Key']";
	String ACcNumber = "";
	String licenceKey = "";
	String sandboxToggle = "//span[text()='Sandbox']/ancestor::div[1]/following-sibling::div[1]//span[@class='slds-checkbox_faux']";
	String enableSandboxToggle = "//span[text()='Sandbox']/ancestor::div[1]/following-sibling::div[1]//span[text()='Sandbox']";
	String enabledProductionToggle = "//span[text()='Sandbox']/ancestor::div[1]/following-sibling::div[1]//span[text()='Production']";
	String testConnectionButton = "//button[text()='Test Connection']";

	String MyTestProjecteXAMPLEMessagePopUpLabel = "//div[@class='slds-notify__content']//h2";
	String testConnectionSuccessMessage = "//p[text()='You are connected to MyTestProjecteXAMPLE']";
	String okButton = "//button[@title='OK']";
	String testConnectionFailureMessage = "//p[text()='Aut failed.']";
	String companyCodeDropdown = "//select[@class='slds-select']";
	String companyCodeOptionSelection = "//select[@class='slds-select']//option[@value='SF']";
	String companyCodeLabel = "//span[text()='Company Code']";

	/******************
	 * MultiEntity Configuration Settings
	 *******************************************/
	String multiCompanySettingsTab = "//a[text()='MultiCompany Settings']";
	String ObjectNameforSubsidiaryTextBox = "//label[text()='Object Name for Subsidiary']/ancestor::div[1]//div[1]//input";
	String subsidiaryFieldNameOnACcTextBox = "//label[text()='Subsidiary Field Name on ACc']/ancestor::div[1]//div[1]//input";
	String displayMappingButton = "//button[text()='Display Mapping']";
	String MyTestProjecteXAMPLECompanyCodeDropdown = "//div[text()='MyTestProjecteXAMPLE Company Code']/ancestor::thead[1]/following-sibling::tbody//select";
	String MyTestProjecteXAMPLECompanyCodeOptionPSSelection = "//div[text()='MyTestProjecteXAMPLE Company Code']/ancestor::thead[1]/following-sibling::tbody//option[@value='PS']";
	String MyTestProjecteXAMPLECompanyCodeOptionAVASelection = "//div[text()='MyTestProjecteXAMPLE Company Code']/ancestor::thead[1]/following-sibling::tbody//option[@value='ASD']";
	String MyTestProjecteXAMPLECompanyCodeLabel = "//div[text()='MyTestProjecteXAMPLE Company Code']";
	String verifyAddressForPSCompanyCode = "//div[text()='13307 Birch Cir,Denver,CO,80241,United States']";
	String warnningSignImage = "//img[@title='Please update the company address in MyTestProjecteXAMPLE Companies Tab to use it as a “Ship From” address for tax calculation']";
	String saveButton = "//button[text()='Save']";

	/******************
	 * Origin Address text Fields on Opp, Quote, Order obj
	 *******************************************/
	String saveHeaderButton = "//div[@class='pbHeader']//input[@title='Save']";
	String editButton = "//div[@class='pbHeader']//input[@name='edit']";
	String originStreetTextBox = "//label[text()='Origin Street']/ancestor::td[1]/following-sibling::td[1]/input";
	String originCityTextBox = "//label[text()='Origin City']/ancestor::td[1]/following-sibling::td[1]/input";
	String originStateTextBox = "//label[text()='Origin State/Province']/ancestor::td[1]/following-sibling::td[1]/input";
	String originZipTextBox = "//label[text()='Origin Zip/Postal Code']/ancestor::td[1]/following-sibling::td[1]/input";
	String originCountryTextBox = "//label[text()='Origin Country']/ancestor::td[1]/following-sibling::td[1]/input";
	String validateOriginAddressButton = "//div[@class='pbHeader']//input[@name='ava_sfcloud__validate_origin_address']";

	/******************
	 * Line Level ShipTo address fields on Opp, Quote, Order obj
	 *******************************************/
	String lineItemLinkText = "//a[contains(text(),'GenWatt Diesel 1000kW')]";
	// Opp and Order
	String lineLevelStreetTextBox = "//label[text()='Shipping Street']/ancestor::td[1]/following-sibling::td[1]/input";
	String lineLevelCityTextBox = "//label[text()='Shipping City']/ancestor::td[1]/following-sibling::td[1]/input";
	String lineLevelStateTextBox = "//label[text()='Shipping State/Province']/ancestor::td[1]/following-sibling::td[1]/input";
	String lineLevelZipTextBox = "//label[text()='Shipping Zip/Postal Code']/ancestor::td[1]/following-sibling::td[1]/input";
	String lineLevelCountryTextBox = "//label[text()='Shipping Country']/ancestor::td[1]/following-sibling::td[1]/input";
	String validateLineLevelAddressButton = "//td[@id='topButtonRow']//input[contains(@name,'ava_sfcloud__validate_shipping_address')]";
	// Quotes
	String quoteLineLevelStreetTextBox = "//label[text()='Ship To Street']/ancestor::td[1]/following-sibling::td[1]/input";
	String quoteLineLevelCityTextBox = "//label[text()='Ship To City']/ancestor::td[1]/following-sibling::td[1]/input";
	String quoteLineLevelStateTextBox = "//label[text()='Ship To State/Province']/ancestor::td[1]/following-sibling::td[1]/input";
	String quoteLineLevelZipTextBox = "//label[text()='Ship To Zip/Postal Code']/ancestor::td[1]/following-sibling::td[1]/input";
	String quoteLineLevelCountryTextBox = "//label[text()='Ship To Country']/ancestor::td[1]/following-sibling::td[1]/input";
	String validateQuoteLineLevelAddressButton = "//td[@id='topButtonRow']//input[@name='ava_sfcloud__validate_ship_to_address']";

	// Line level- Entity Use code
	String entityUseCodeLookupField = "//label[text()='Entity/Use Code']/ancestor::td[1]/following-sibling::td[1]//span//input";
	String opportunityLinkText = "//td[text()='Opportunity']//following-sibling::td[1]//div//a";
	String quoteLinkText = "//td[text()='Quote Name']//following-sibling::td[1]//div//a";
	String orderLinkText = "//td[text()='Order']//following-sibling::td[1]//div//a";
	String lineItem2LinkText = "//a[contains(text(),'GenWatt Diesel 200kW')]";
	String lineItem3LinkText = "//a[contains(text(),'GenWatt Diesel 10kW')]";
	String entityUseCodeGetText = "//div[@id='CF00N0H00000KoMXG_ileinner']//a";

	public void fLogin_SalesForce() throws Exception {

		String sEmail = "";
		String spd = "";
		try {
			initializeDriver();
			sEmail = prop.getProperty("user.name");
			spd = prop.getProperty("user.pd");
			driver.get("https://login.salesforce.com");
			driver.manage().window().maximize();

			Thread.sleep(1000);
			// Enter cred
			enterValue(usnm_TextBox, sEmail);
			enterValue(pd_TextBox, spd);
			clickonElement_ID(login_Button);
			ExecutionDelay(5);
		} catch (Exception oException) {
			System.out.println(oException.getMessage());
		}
	}// End fLogin_Sales Force

	/**************************************************************************************************
	 * /** Description : Function to go to menu
	 * 
	 * @param sMenu:
	 *            String for menu
	 * @return boolean : true if Menu selection is successful, false otherwise
	 */
	public void fMenuSelection(String sMenu) throws Exception {
		// For MyTestProject MyTestProjecteXAMPLE tab
		String sOpportunityName, sQuotesName;
		try {
			ExecutionDelay(3);
			// Open TaxNowSettings
			if (sMenu.equalsIgnoreCase("TaxNowSettings")) {

				clickonElement_Xpath("//span[@id='userNavLabel']");
				clickonElement_Xpath("//a[text()='Setup']");
				enterValue_LocatorXpath("//input[@id='setupSearch']", "Installed Packages");

				String installedPackagesLink = "//a[text()='Installed Packages']";
				String configureLink = "//a[text()='Configure'][contains(@title,'MyTestProjecteXAMPLE')]";
				Thread.sleep(3000);
				expectpresenceOfElementLocated(installedPackagesLink);
				expectelementToBeClickable(installedPackagesLink);
				clickonElement_Xpath(installedPackagesLink);
				expectvisibilityOfElementLocated(configureLink);
				clickonElement_Xpath(configureLink);
				ExecutionDelay(5);
				Thread.sleep(5000);

				if (sMyTestProjecteXAMPLECredentialFlag.equalsIgnoreCase("1")) {
					expectelementToBeClickable(ACcNumberTextBox);
					clickonElement_Xpath(ACcNumberTextBox);
					enterValue_LocatorXpath(ACcNumberTextBox, "1234556");
					expectelementToBeClickable(lickeyTextBox);
					clickonElement_Xpath(lickeyTextBox);
					enterValue_LocatorXpath(lickeyTextBox, "3435435435431212");
					ExecutionDelay(5);
					Thread.sleep(5000);
					clickonElement_Xpath(lickeyLabel);
					clickOnTestConnectionButton();
					Thread.sleep(5000);
				}
				// Click on Corporate Address
				expectelementToBeClickable("//a[text()='Corporate Address']");
				clickonElement_Xpath("//a[text()='Corporate Address']");
				Thread.sleep(3000);
				// Check Address validation flag

				String line1AddressTextbox = "//label[text()='Line 1']/ancestor::div[1]//div[1]/input";
				String cityTextbox = "//label[text()='City']/ancestor::div[1]//div[1]/input";
				String stateTextbox = "//label[text()='State']/ancestor::div[1]//div[1]/input";
				String postalZipTextBox = "//label[text()='Postal Code']/ancestor::div[1]//div[1]/input";
				String countryTextbox = "//label[text()='Country']/ancestor::div[1]//div[1]/input";

				if (sGLAddressValidationFlag != null) {
					enterValue_LocatorXpath(line1AddressTextbox, sGLShiptoAddress_Line1);
					enterValue_LocatorXpath(cityTextbox, sGLShiptoAddress_City);
					enterValue_LocatorXpath(stateTextbox, sGLShiptoAddress_State);
					enterValue_LocatorXpath(postalZipTextBox, sGLShiptoAddress_Zip);
					enterValue_LocatorXpath(countryTextbox, sGLShipToCountryCode);
				} else {

					enterValue_LocatorXpath(line1AddressTextbox, sGLShipFromAddress_Line1);
					enterValue_LocatorXpath(cityTextbox, sGLShipFromAddress_City);
					enterValue_LocatorXpath(stateTextbox, sGLShipFromAddress_State);
					enterValue_LocatorXpath(postalZipTextBox, sGLShipFromAddress_Zip);
					enterValue_LocatorXpath(countryTextbox, sGLShipFromCountryCode);
				}
			}

			// Open Order tab and create new Order
			if (sMenu.equalsIgnoreCase("Orders")) {
				if (IsElementPresent(By.linkText("Orders"))) {
					driver.findElement(By.linkText("Orders")).click();
					ExecutionDelay(6);
					if (IsElementPresent(By.id("tryLexDialogX"))) {
						driver.findElement(By.id("tryLexDialogX")).click();
						ExecutionDelay(3);
					}
					clickonElement_Xpath("//input[@name='new']");
					ExecutionDelay(4);
					enterValue_LocatorXpath("//div/span/input", "Ajay");
					enterValue_LocatorID("EffectiveDate", sGLDate);
					if (sGLOrderShipTo.equalsIgnoreCase("1")) {
						// Entering secondary shipping address
						enterValue_LocatorXpath("//textarea[@id='ShippingAddressstreet']",
								sGLSecondaryShiptoAddress_Line1);
						enterValue_LocatorXpath("//input[@id='ShippingAddresscity']", sGLSecondaryShiptoAddress_City);
						enterValue_LocatorXpath("//input[@id='ShippingAddresszip']", sGLSecondaryShiptoAddress_Zip);

						if (sGLCountryPickList.equalsIgnoreCase("0")) {
							enterValue_LocatorXpath("//input[@id='ShippingAddressstate']",
									sGLSecondaryShiptoAddress_State);
							enterValue_LocatorXpath("//input[@id='ShippingAddresscountry']",
									sGLSecondaryShipToAddressCountryCode);
						}
					} else if (sGLOrderShipTo.equalsIgnoreCase("Blank")) {
						// Entering Blank as shipping address
						enterValue_LocatorXpath("//textarea[@id='ShippingAddressstreet']", "     ");
						enterValue_LocatorXpath("//input[@id='ShippingAddresscity']", "     ");
						enterValue_LocatorXpath("//input[@id='ShippingAddresszip']", "       ");
						if (sGLCountryPickList.equalsIgnoreCase("0")) {
							enterValue_LocatorXpath("//input[@id='ShippingAddressstate']", "        ");
							enterValue_LocatorXpath("//input[@id='ShippingAddresscountry']", "      ");
						}
					} else {
						// Entering Primary shipping address
						enterValue_LocatorXpath("//textarea[@id='ShippingAddressstreet']", sGLShiptoAddress_Line1);
						enterValue_LocatorXpath("//input[@id='ShippingAddresscity']", sGLShiptoAddress_City);
						enterValue_LocatorXpath("//input[@id='ShippingAddresszip']", sGLShiptoAddress_Zip);

						if (sGLCountryPickList.equalsIgnoreCase("0")) {
							enterValue_LocatorXpath("//input[@id='ShippingAddressstate']", sGLShiptoAddress_State);
							enterValue_LocatorXpath("//input[@id='ShippingAddresscountry']", sGLShipToCountryCode);
						}

					}

					if (sGLOrderBillTo.equalsIgnoreCase("1")) {
						// Entering Secondary Billing address
						enterValue_LocatorXpath("//textarea[@id='BillingAddressstreet']",
								sGLSecondaryShiptoAddress_Line1);
						enterValue_LocatorXpath("//input[@id='BillingAddresscity']", sGLSecondaryShiptoAddress_City);
						enterValue_LocatorXpath("//input[@id='BillingAddresszip']", sGLSecondaryShiptoAddress_Zip);
						if (sGLCountryPickList.equalsIgnoreCase("0")) {
							enterValue_LocatorXpath("//input[@id='BillingAddressstate']",
									sGLSecondaryShiptoAddress_State);
							enterValue_LocatorXpath("//input[@id='BillingAddresscountry']",
									sGLSecondaryShipToAddressCountryCode);
						}

					} else if (sGLOrderBillTo.equalsIgnoreCase("Blank")) {
						// Entering Billing address
						enterValue_LocatorXpath("//textarea[@id='BillingAddressstreet']", "         ");
						enterValue_LocatorXpath("//input[@id='BillingAddresscity']", "        ");
						enterValue_LocatorXpath("//input[@id='BillingAddresszip']", "      ");

						if (sGLCountryPickList.equalsIgnoreCase("0")) {
							enterValue_LocatorXpath("//input[@id='BillingAddressstate']", "           ");
							enterValue_LocatorXpath("//input[@id='BillingAddresscountry']", "        ");
						}
					} else {
						// Entering Billing address
						enterValue_LocatorXpath("//textarea[@id='BillingAddressstreet']", sGLShiptoAddress_Line1);
						enterValue_LocatorXpath("//input[@id='BillingAddresscity']", sGLShiptoAddress_City);
						enterValue_LocatorXpath("//input[@id='BillingAddresszip']", sGLShiptoAddress_Zip);
						if (sGLCountryPickList.equalsIgnoreCase("0")) {
							enterValue_LocatorXpath("//input[@id='BillingAddressstate']", sGLShiptoAddress_State);
							enterValue_LocatorXpath("//input[@id='BillingAddresscountry']", sGLShipToCountryCode);
						}
					}
					selectByVisibleText("//select[@id='Status']", "Draft");

					clickonElement_Xpath("//div[@class='pbHeader']//input[@name='save']");
					System.out.println("Order created successfully: " + sGLTestCaseName);
				} else
					System.out.println("Order link not visible:" + sGLTestCaseName);
			}

			// Open Opportunities tab and create new opportunities
			if (sMenu.equalsIgnoreCase("Opportunities")) {
				if (IsElementPresent(By.id("Opportunity_Tab"))) {
					// To create opportunity from ACcs page
					ExecutionDelay(4);
					driver.findElement(By.linkText("ACcs")).click();
					ExecutionDelay(5);
					clickonElement_Xpath("//a[text()='Ajay']");
					clickonElement_Xpath("//input[@title='New Opportunity']");
					ExecutionDelay(6);
					sOpportunityName = fRamdomString(5);
					enterValue_LocatorID("opp3", sOpportunityName);
					enterValue_LocatorID("opp9", sGLDate);
					selectByVisibleText("//select[@id='opp11']", "Closed Won");
					clickonElement_Xpath("//div[@class='pbHeader']//input[@name='save']");
					ExecutionDelay(5);
					System.out.println("Opportunities created successfully: " + sGLTestCaseName);
				} else
					System.out.println("Opportunities link not visible: " + sGLTestCaseName);
			}
			// Create newQuote
			if (sMenu.equalsIgnoreCase("newQuote")) {
				driver.findElement(By.name("newQuote")).click();
				ExecutionDelay(5);
				sQuotesName = fRamdomString(4);
				enterValue_LocatorXpath("//input[@id='Name']", sQuotesName);
				clickonElement_Xpath("//input[@id='ExpirationDate']/following-sibling::span[1]/a");

				// For Shipping address
				if (sGLQuotesShipTo.equalsIgnoreCase("1")) {
					// Entering Secondary Shipping address
					enterValue_LocatorXpath("//textarea[@id='ShippingAddressstreet']", sGLSecondaryShiptoAddress_Line1);
					enterValue_LocatorXpath("//input[@id='ShippingAddresscity']", sGLSecondaryShiptoAddress_City);
					enterValue_LocatorXpath("//input[@id='ShippingAddresszip']", sGLSecondaryShiptoAddress_Zip);

					if (sGLCountryPickList.equalsIgnoreCase("0")) {
						enterValue_LocatorXpath("//input[@id='ShippingAddressstate']", sGLSecondaryShiptoAddress_State);
						enterValue_LocatorXpath("//input[@id='ShippingAddresscountry']",
								sGLSecondaryShipToAddressCountryCode);
					}

				} else if (sGLQuotesShipTo.equalsIgnoreCase("Blank")) {
					// Entering Blank as Shipping address
					enterValue_LocatorXpath("//textarea[@id='ShippingAddressstreet']", "         ");
					enterValue_LocatorXpath("//input[@id='ShippingAddresscity']", "        ");
					enterValue_LocatorXpath("//input[@id='ShippingAddresszip']", "      ");
					if (sGLCountryPickList.equalsIgnoreCase("0")) {
						enterValue_LocatorXpath("//input[@id='ShippingAddressstate']", "           ");
						enterValue_LocatorXpath("//input[@id='ShippingAddresscountry']", "        ");
					}
				}

				else {
					// Entering Primary shipping address
					enterValue_LocatorXpath("//textarea[@id='ShippingAddressstreet']", sGLShiptoAddress_Line1);
					enterValue_LocatorXpath("//input[@id='ShippingAddresscity']", sGLShiptoAddress_City);
					enterValue_LocatorXpath("//input[@id='ShippingAddresszip']", sGLShiptoAddress_Zip);

					if (sGLCountryPickList.equalsIgnoreCase("0")) {
						enterValue_LocatorXpath("//input[@id='ShippingAddressstate']", sGLShiptoAddress_State);
						enterValue_LocatorXpath("//input[@id='ShippingAddresscountry']", sGLShipToCountryCode);
					}
				}

				// Entering Billing Address on Quotes
				if (sGLQuotesBillTo.equalsIgnoreCase("1")) {

					// Entering Secondary Billing address
					enterValue_LocatorXpath("//textarea[@id='BillingAddressstreet']", sGLSecondaryShiptoAddress_Line1);
					enterValue_LocatorXpath("//input[@id='BillingAddresscity']", sGLSecondaryShiptoAddress_City);
					enterValue_LocatorXpath("//input[@id='BillingAddresszip']", sGLSecondaryShiptoAddress_Zip);
					if (sGLCountryPickList.equalsIgnoreCase("0")) {
						enterValue_LocatorXpath("//input[@id='BillingAddressstate']", sGLSecondaryShiptoAddress_State);
						enterValue_LocatorXpath("//input[@id='BillingAddresscountry']",
								sGLSecondaryShipToAddressCountryCode);
					}

				} else if (sGLQuotesBillTo.equalsIgnoreCase("Blank")) {
					// Entering Blank as Billing address
					enterValue_LocatorXpath("//textarea[@id='BillingAddressstreet']", "         ");
					enterValue_LocatorXpath("//input[@id='BillingAddresscity']", "        ");
					enterValue_LocatorXpath("//input[@id='BillingAddresszip']", "      ");
					if (sGLCountryPickList.equalsIgnoreCase("0")) {
						enterValue_LocatorXpath("//input[@id='BillingAddressstate']", "           ");
						enterValue_LocatorXpath("//input[@id='BillingAddresscountry']", "        ");
					}
				} else {
					// Entering Primary Billing address
					enterValue_LocatorXpath("//textarea[@id='BillingAddressstreet']", sGLShiptoAddress_Line1);
					enterValue_LocatorXpath("//input[@id='BillingAddresscity']", sGLShiptoAddress_City);
					enterValue_LocatorXpath("//input[@id='BillingAddresszip']", sGLShiptoAddress_Zip);
					if (sGLCountryPickList.equalsIgnoreCase("0")) {
						enterValue_LocatorXpath("//input[@id='BillingAddressstate']", sGLShiptoAddress_State);
						enterValue_LocatorXpath("//input[@id='BillingAddresscountry']", sGLShipToCountryCode);
					}
				}

				// In Case of Address Validation
				if (sGLAddressValidationFlag != null) {
					// Enter Billing address on Quotes window
					enterValue_LocatorXpath("//textarea[@id='BillingAddressstreet']", sGLShiptoAddress_Line1);
					enterValue_LocatorXpath("//input[@id='BillingAddresscity']", sGLShiptoAddress_City);
					enterValue_LocatorXpath("//input[@id='BillingAddresszip']", sGLShiptoAddress_Zip);
					if (sGLCountryPickList.equalsIgnoreCase("0")) {
						enterValue_LocatorXpath("//input[@id='BillingAddressstate']", sGLShiptoAddress_State);
						enterValue_LocatorXpath("//input[@id='BillingAddresscountry']", sGLShipToCountryCode);
					}
					// Enter Shipping address on Quotes window
					enterValue_LocatorXpath("//textarea[@id='ShippingAddressstreet']", sGLShiptoAddress_Line1);
					enterValue_LocatorXpath("//input[@id='ShippingAddresscity']", sGLShiptoAddress_City);
					enterValue_LocatorXpath("//input[@id='ShippingAddresszip']", sGLShiptoAddress_Zip);
					if (sGLCountryPickList.equalsIgnoreCase("0")) {
						enterValue_LocatorXpath("//input[@id='ShippingAddressstate']", sGLShiptoAddress_State);
						enterValue_LocatorXpath("//input[@id='ShippingAddresscountry']", sGLShipToCountryCode);
					}
				}
				clickonElement_Xpath("//div[@class='pbHeader']//input[@name='save']");
			}

			// Open ACcs window
			if (sMenu.equalsIgnoreCase("ACcs")) {
				clickonElement_Xpath("//a[text()='ACcs']");
				ExecutionDelay(6);

				if (IsElementPresent(By.id("tryLexDialogX"))) {
					driver.findElement(By.id("tryLexDialogX")).click();
					ExecutionDelay(3);
				}
				driver.findElement(By.xpath("//a[text()='Ajay']")).click();

				ExecutionDelay(5);
				clickonElement_Xpath("//div[@class='pbHeader']//input[@name='edit']");
				ExecutionDelay(4);

				if (sGLEntityUseCode != null) {
					enterValue_LocatorXpath("//span/input", sGLEntityUseCode);
				} else {
					clearValue_LocatorXpath("//span/input");
				}

				if (sGLCustExemptNo != null) {
					enterValue_LocatorXpath("//tr[2]/td[4]/input", sGLCustExemptNo);
				} else {
					clearValue_LocatorXpath("//tr[2]/td[4]/input");
				}

				if (!sGLEnableACcNumber.equalsIgnoreCase("0")) {
					enterValue_LocatorXpath(
							"//label[text()='ACc Number']/ancestor::td[1]/following-sibling::td[1]/input",
							"CD656092");
				} else {
					clearValue_LocatorXpath(
							"//label[text()='ACc Number']/ancestor::td[1]/following-sibling::td[1]/input");
				}
				if (!sGLEnableVAT.equalsIgnoreCase("0")) {
					enterValue_LocatorXpath(
							"//label[text()='Business Identification Number']/ancestor::td[1]/following-sibling::td[1]/input",
							sGLEnableVAT);
				} else {
					clearValue_LocatorXpath(
							"//label[text()='Business Identification Number']/ancestor::td[1]/following-sibling::td[1]/input");
				}

				// Error message displayed test case for ship to null value
				if (sGLShiptoAddress_Line1 == null) {
					sGLShiptoAddress_Line1 = "";
					sGLShiptoAddress_City = "";
					sGLShiptoAddress_State = "";
					sGLShiptoAddress_Zip = "";
					if (sGLEnableVAT != null) {
						System.out.println("VAT Country");
					} else {
						sGLShipToCountryCode = "";
					}
				}
				// Entering Billing and Shipping Address

				if (sGLACcShipTo.equalsIgnoreCase("1")) {
					// Entering Primary Shipping address
					enterValue_LocatorXpath("//textarea[@id='acc18street']", sGLSecondaryShiptoAddress_Line1);
					enterValue_LocatorXpath("//input[@id='acc18city']", sGLSecondaryShiptoAddress_City);
					enterValue_LocatorXpath("//input[@id='acc18zip']", sGLSecondaryShiptoAddress_Zip);

					// if (sGLCountryPickList.equalsIgnoreCase("0")) {
					enterValue_LocatorXpath("//input[@id='acc18state']", sGLSecondaryShiptoAddress_State);
					enterValue_LocatorXpath("//input[@id='acc18country']", sGLSecondaryShipToAddressCountryCode);
					// }
				} else if (sGLACcShipTo.equalsIgnoreCase("Blank")) {
					// Entering Blank in Shipping address
					enterValue_LocatorXpath("//textarea[@id='acc18street']", "       ");
					enterValue_LocatorXpath("//input[@id='acc18city']", "      ");
					enterValue_LocatorXpath("//input[@id='acc18zip']", "    ");

					// if (sGLCountryPickList.equalsIgnoreCase("0")) {
					enterValue_LocatorXpath("//input[@id='acc18state']", "     ");
					enterValue_LocatorXpath("//input[@id='acc18country']", "    ");
					// }
				} else {
					// Entering Secondary Shipping address
					enterValue_LocatorXpath("//textarea[@id='acc18street']", sGLShiptoAddress_Line1);
					enterValue_LocatorXpath("//input[@id='acc18city']", sGLShiptoAddress_City);
					enterValue_LocatorXpath("//input[@id='acc18zip']", sGLShiptoAddress_Zip);
					enterValue_LocatorXpath("//input[@id='acc18state']", sGLShiptoAddress_State);
					enterValue_LocatorXpath("//input[@id='acc18country']", sGLShipToCountryCode);
					// }
				}
				if (sGLACcBillTo.equalsIgnoreCase("1")) {
					// Entering Primary Billing address
					enterValue_LocatorXpath("//textarea[@id='acc17street']", sGLSecondaryShiptoAddress_Line1);
					enterValue_LocatorXpath("//input[@id='acc17city']", sGLSecondaryShiptoAddress_City);
					enterValue_LocatorXpath("//input[@id='acc17zip']", sGLSecondaryShiptoAddress_Zip);
					if (sGLCountryPickList.equalsIgnoreCase("0")) {
						enterValue_LocatorXpath("//input[@id='acc17state']", sGLSecondaryShiptoAddress_State);
						enterValue_LocatorXpath("//input[@id='acc17country']", sGLSecondaryShipToAddressCountryCode);
					}
				} else if (sGLACcBillTo.equalsIgnoreCase("Blank")) {

					// Entering Blank in Billing address
					enterValue_LocatorXpath("//textarea[@id='acc17street']", "       ");
					enterValue_LocatorXpath("//input[@id='acc17city']", "      ");
					enterValue_LocatorXpath("//input[@id='acc17zip']", "    ");

					if (sGLCountryPickList.equalsIgnoreCase("0")) {
						enterValue_LocatorXpath("//input[@id='acc17state']", "     ");
						enterValue_LocatorXpath("//input[@id='acc17country']", "    ");
					}
				}

				else {
					// Entering Secondary Billing address
					enterValue_LocatorXpath("//textarea[@id='acc17street']", sGLShiptoAddress_Line1);
					enterValue_LocatorXpath("//input[@id='acc17city']", sGLShiptoAddress_City);
					enterValue_LocatorXpath("//input[@id='acc17zip']", sGLShiptoAddress_Zip);

					if (sGLCountryPickList.equalsIgnoreCase("0")) {
						enterValue_LocatorXpath("//input[@id='acc17state']", sGLShiptoAddress_State);
						enterValue_LocatorXpath("//input[@id='acc17country']", sGLShipToCountryCode);
					}
				}
				ExecutionDelay(2);
				clickonElement_Xpath("//div[@class='pbHeader']//input[@name='save']");
				Thread.sleep(5000);
			}
			ExecutionDelay(10);
			System.out.println(
					"After clicking on menu " + sGLTestCaseName + "Respective page opened successfully: " + sMenu);

		} catch (Exception oException) {
			System.out.println("fMenuSelection: Exception: " + sGLTestCaseName + oException.toString());

		}

	}

	// End fMenuSelection
	/*******************************************************************************************************************
	 * TaxNow Setting Function Description : Function for select the checkbox
	 * according data sheet test cases in TaxNow setting
	 ******************************************************************************************************************/
	public void fMyTestProjecteXAMPLEConfigurationSettings() {
		String advancedSettingsLink = "//a[text()='Advanced Settings']";
		// Save transaction to MyTestProjecteXAMPLE locators
		String saveTransactionsToMyTestProjecteXAMPLEToggle = "//span[text()='Save Transactions To MyTestProjecteXAMPLE']/ancestor::div[1]/following-sibling::div[1]//span[@class='slds-checkbox_faux']";
		String disabledSaveTransactionsToMyTestProjecteXAMPLEToggle = "//span[text()='Save Transactions To MyTestProjecteXAMPLE']/ancestor::div[1]/following-sibling::div[1]//span[text()='Disabled']";
		String enabledSaveTransactionsToMyTestProjecteXAMPLEToggle = "//span[text()='Save Transactions To MyTestProjecteXAMPLE']/ancestor::div[1]/following-sibling::div[1]//span[text()='Enabled']";
		// Tax calculation locators
		String enableTaxCalculationToggle = "//span[text()='Enable Tax Calculation']/ancestor::div[1]/following-sibling::div[1]//span[@class='slds-checkbox_faux']";
		String disabledEnableTaxCalculationToggle = "//span[text()='Enable Tax Calculation']/ancestor::div[1]/following-sibling::div[1]//span[text()='Disabled']";
		String enabledEnableTaxCalculationToggle = "//span[text()='Enable Tax Calculation']/ancestor::div[1]/following-sibling::div[1]//span[text()='Enabled']";
		// Address validation locators
		String enableAddressValidationToggle = "//span[text()='Enable Address Validation']/ancestor::div[1]/following-sibling::div[1]//span[@class='slds-checkbox_faux']";
		String disabledEnableAddressValidationToggle = "//span[text()='Enable Address Validation']/ancestor::div[1]/following-sibling::div[1]//span[text()='Disabled']";
		String enabledEnableAddressValidationToggle = "//span[text()='Enable Address Validation']/ancestor::div[1]/following-sibling::div[1]//span[text()='Enabled']";
		// Address validation locators
		String enableUPCAsItemCodeToggle = "//span[text()='Enable UPC Code As Item Code']/ancestor::div[1]/following-sibling::div[1]//span[@class='slds-checkbox_faux']";
		String disablEnableUPCAsItemCodeToggle = "//span[text()='Enable UPC Code As Item Code']/ancestor::div[1]/following-sibling::div[1]//span[text()='Disabled']";
		String enabledEnableUPCAsItemCodeToggle = "//span[text()='Enable UPC Code As Item Code']/ancestor::div[1]/following-sibling::div[1]//span[text()='Enabled']";
		// Address validation locators
		String allowTaxOverrideToggle = "//span[text()='Allow Tax Override']/ancestor::div[1]/following-sibling::div[1]//span[@class='slds-checkbox_faux']";
		String disabledAllowTaxOverrideToggle = "//span[text()='Allow Tax Override']/ancestor::div[1]/following-sibling::div[1]//span[text()='Disabled']";
		String enabledAllowTaxOverrideToggle = "//span[text()='Allow Tax Override']/ancestor::div[1]/following-sibling::div[1]//span[text()='Enabled']";

		expectpresenceOfElementLocated(advancedSettingsLink);
		expectvisibilityOfElementLocated(advancedSettingsLink);
		expectelementToBeClickable(advancedSettingsLink);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		clickonElement_Xpath(advancedSettingsLink);
		expectvisibilityOfElementLocated(saveTransactionsToMyTestProjecteXAMPLEToggle);

		if (sGLEnableAddressValidation.equalsIgnoreCase("1")
				&& getTextValue(disabledEnableAddressValidationToggle).equalsIgnoreCase("Disabled")) {
			clickonElement_Xpath(enableAddressValidationToggle);
		} else if (sGLEnableAddressValidation.equalsIgnoreCase("0")
				&& getTextValue(enabledEnableAddressValidationToggle).equalsIgnoreCase("Enabled")) {
			clickonElement_Xpath(enableAddressValidationToggle);
		}

		if (sGLEnableTaxCalculation.equalsIgnoreCase("1")
				&& getTextValue(disabledEnableTaxCalculationToggle).equalsIgnoreCase("Disabled")) {
			clickonElement_Xpath(enableTaxCalculationToggle);
		} else if (sGLEnableTaxCalculation.equalsIgnoreCase("0")
				&& getTextValue(enabledEnableTaxCalculationToggle).equalsIgnoreCase("Enabled")) {
			clickonElement_Xpath(enableTaxCalculationToggle);
		}

		if (sGLEnableSalesOrder.equalsIgnoreCase("1")
				&& getTextValue(disabledSaveTransactionsToMyTestProjecteXAMPLEToggle).equalsIgnoreCase("Disabled")) {
			clickonElement_Xpath(saveTransactionsToMyTestProjecteXAMPLEToggle);
		} else if (sGLEnableSalesOrder.equalsIgnoreCase("0")
				&& getTextValue(enabledSaveTransactionsToMyTestProjecteXAMPLEToggle).equalsIgnoreCase("Enabled")) {
			clickonElement_Xpath(saveTransactionsToMyTestProjecteXAMPLEToggle);
		}

		if (sGLEnableUPCcode.equalsIgnoreCase("1")
				&& getTextValue(disablEnableUPCAsItemCodeToggle).equalsIgnoreCase("Disabled")) {
			clickonElement_Xpath(enableUPCAsItemCodeToggle);
		} else if (sGLEnableUPCcode.equalsIgnoreCase("0")
				&& getTextValue(enabledEnableUPCAsItemCodeToggle).equalsIgnoreCase("Enabled")) {
			clickonElement_Xpath(enableUPCAsItemCodeToggle);
		}
		if (sGLTaxOverRide.equalsIgnoreCase("1")
				&& getTextValue(disabledAllowTaxOverrideToggle).equalsIgnoreCase("Disabled")) {
			clickonElement_Xpath(allowTaxOverrideToggle);
		} else if (sGLTaxOverRide.equalsIgnoreCase("0")
				&& getTextValue(enabledAllowTaxOverrideToggle).equalsIgnoreCase("Enabled")) {
			clickonElement_Xpath(allowTaxOverrideToggle);
		}
		String advanceSettingsSaveButton = "//button[text()='Save']";
		// Click on Save Button
		try {
			ExecutionDelay(20);
			Thread.sleep(6000);
			expectvisibilityOfElementLocated(advanceSettingsSaveButton);
			clickonElement_Xpath(advanceSettingsSaveButton);
			ExecutionDelay(20);

			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	// End TaxNow setting function

	/*******************************************************************************************************************
	 * Line Entry Function Description : Function for Add a product for
	 * transaction
	 * 
	 * @param sItemSelection
	 *            : - Which window we are going to add line item
	 ******************************************************************************************************************/
	public void fLine_Entry(String sItemSelection) throws Exception {

		// Order
		if (sItemSelection.equalsIgnoreCase("Orders")) {
			// sGLStatusID = "//tr[3]/td[4]/div";
			findAndClick("name", "addProd");

			ExecutionDelay(12);
			if (!sGLTestcaseId.contains(".1")) {
				clickonElement_Xpath("//input[@name='save']");
			}
			ExecutionDelay(6);
			// enterValue_LocatorID("search", sGLItem1Code);
			// Add Product from product page
			Thread.sleep(6000);
			enterValue_LocatorXpath("//input[@id='search']", sGLItem1Code);
			clickonElement_ID("save_filter_PricebookEntry");
			Thread.sleep(5000);
			ExecutionDelay(10);
			expectvisibilityOfElementLocated("//input[@id='allBox']");
			clickonElement_Xpath("//input[@id='allBox']");
			ExecutionDelay(10);
			expectelementToBeClickable("//input[@value='Select']");
			clickonElement_Xpath("//input[@value='Select']");
			ExecutionDelay(5);
			enterValue_LocatorXpath("//td[2]/input", sGLAmount);
			enterValue_LocatorXpath("//tr[5]/td/input", "1");
		}

		// Click on Add product if Opportunity or click on Add quote line
		// depending item selection passed
		if (sItemSelection.equalsIgnoreCase("Opportunities")) {
			findAndClick("xpath", "//input[@name='addProd']");
			ExecutionDelay(5);
			if (!sGLTestcaseId.contains(".1"))
				driver.findElement(By.name("save")).click();
			// Add Product from product page
			Thread.sleep(6000);
			enterValue_LocatorXpath("//input[@id='search']", sGLItem1Code);
			clickonElement_ID("save_filter_PricebookEntry");
			Thread.sleep(5000);
			ExecutionDelay(10);
			expectvisibilityOfElementLocated("//input[@id='allBox']");
			clickonElement_Xpath("//input[@id='allBox']");
			ExecutionDelay(10);
			expectelementToBeClickable("//input[@value='Select']");
			clickonElement_Xpath("//input[@value='Select']");
			ExecutionDelay(5);
			enterValue_LocatorXpath("//form[@id='editPage']/table/tbody/tr[5]/td[2]/input", sGLAmount);
			enterValue_LocatorXpath("//form[@id='editPage']/table/tbody/tr[5]/td/input", "1");

		}
		if (sItemSelection.equalsIgnoreCase("Quotes")) {
			// Status
			findAndClick("name", "addQuoteLine");
			Thread.sleep(5000);
			// Add Product from product page
			enterValue_LocatorID("search", sGLItem1Code);
			clickonElement_ID("save_filter_PricebookEntry");
			Thread.sleep(5000);
			ExecutionDelay(10);
			expectvisibilityOfElementLocated("//input[@id='allBox']");
			clickonElement_Xpath("//input[@id='allBox']");
			ExecutionDelay(10);
			expectelementToBeClickable("//input[@value='Select']");
			clickonElement_Xpath("//input[@value='Select']");
			ExecutionDelay(5);
			enterValue_LocatorXpath("//form[@id='editPage']/table/tbody/tr[5]/td[3]/input", sGLAmount);
			enterValue_LocatorXpath("//form[@id='editPage']/table/tbody/tr[5]/td[2]/input", "1");
		}
		clickonElement_Xpath("//input[@name='save']");
		ExecutionDelay(12);

	}

	// NEED TO DISCUSS
	public void addShippingAndHandlingCharges_Quotes() throws Exception {

		String editButton = "//div[@class='pbHeader']//input[@name='edit']";
		String saveButton = "//div[@class='pbHeader']//input[@name='save']";
		String shippingAndHandlingTextBox = "//label[text()='Shipping and Handling']/ancestor::td[1]/following-sibling::td[1]/input";
		clickonElement_Xpath(editButton);
		Thread.sleep(3000);
		// Enter Billing Contacts
		enterValue_LocatorXpath(shippingAndHandlingTextBox, "100");

		clickonElement_Xpath(saveButton);
		Thread.sleep(3000);

	}

	/*******************************************************************************************************************
	 * Description : Function for Add a product for transaction
	 * 
	 * @param sMenuOption
	 *            = On which window we are verifying tax
	 ******************************************************************************************************************/
	public String fVerifyTaxValues(String sMenuOption) throws InterruptedException {

		String sSalesTax = "//tr[7]/td[4]/div";

		String[] sTax;

		if (sMenuOption.equalsIgnoreCase("Opportunities")) {
			Thread.sleep(4000);
			expectvisibilityOfElementLocated(sSalesTax);
			sSalesTax = getText(sSalesTax);
			Thread.sleep(1000);
		} else if (sMenuOption.equalsIgnoreCase("Quotes")) // For Quotes
		{
			Thread.sleep(1000);
			sSalesTax = getText("//td[@id='Tax_ilecell']/div");
			Thread.sleep(1000);
			double d = Double.parseDouble(sGLTotalTax);
			d = d * 2;
			d = Double.parseDouble(new DecimalFormat("##.##").format(d));
			sGLTotalTax = String.valueOf(d);
			sGLTotalTax = sGLTotalTax + "0";

		}
		if (sMenuOption.equalsIgnoreCase("Orders")) {
			Thread.sleep(1000);
			sSalesTax = getText("//tr[8]/td[4]/div");
			Thread.sleep(1000);
		}
		System.out.println("Length of Sales tax" + sSalesTax.length());
		if (sSalesTax.length() < 2) {
			return sSalesTax;
		} else {
			sTax = sSalesTax.split("\\$");
			sExpectedSalesTax = sTax[1].replaceAll(",", "");
			sGLTotalTax = sGLTotalTax.trim();
			System.out.println("sGLTotalTax : " + sGLTotalTax);
			sSalesTax = sTax.toString();
			return sSalesTax;
		}
	}

	// Need to work on it to change return array value
	public String fVerifyMISCTaxValues(String sMenuOption) {

		String[] sExpectedShippingHandlingTax;

		String sMiscTaxID = null;

		if (sMenuOption.equalsIgnoreCase("Quotes")) { // For Quotes
			sMiscTaxID = "//tr[9]/td[4]/div";
		}
		// Check for Shipping and Handling
		if (sGLShippingHandling != null) {
			sGLActualShippingHandlingTax = driver.findElement(By.xpath(sMiscTaxID)).getText();
			sExpectedShippingHandlingTax = sGLActualShippingHandlingTax.split("\\$");
			sGLShippingHandlingTax = sGLShippingHandlingTax.trim();
			sGLActualShippingHandlingTax = sGLShippingHandlingTax;
		}
		return sGLActualShippingHandlingTax;

	}

	/*******************************************************************************************************************
	 * Function for MyTestProject Status / MyTestProject MyTestProjecteXAMPLE / Error Message Displayed
	 * Description : Function for Trigger Get Tax for Opportunity
	 *******************************************************************************************************************/
	public String fVerifyStatusAndTaxOnWindow(String sMenuOption) throws InterruptedException {
		System.out.println("In fVerifyStatusAndTaxOnWindow");
		String sStatusID = null;
		sGLMyTestProjectDocStatus = "Temporary";
		if (sMenuOption.equalsIgnoreCase("OpportuntiesPostGetTax")
				|| sMenuOption.equalsIgnoreCase("QuotesPostGetTax")) {
			System.out.println("In fVerifyStatusAndTaxOnWindow : " + sMenuOption);
			if (sGLEnableSalesOrder.equalsIgnoreCase("1") && sGLEnableTaxCalculation.equalsIgnoreCase("1")) {
				sGLMyTestProjectDocStatus = "Temporary"; // Temporary

			} else if (sGLEnableTaxCalculation.equalsIgnoreCase("0")) {
				sGLMyTestProjectDocStatus = "Tax calculation is disabled in MyTestProjecteXAMPLE configuration,(AVA_SFCLOUD)";
			}
		} else if (sMenuOption.equalsIgnoreCase("OrdersPostGetTax")) {
			if (sGLEnableTaxCalculation.equalsIgnoreCase("1") && sGLEnableSalesOrder.equalsIgnoreCase("1")) {
				sGLMyTestProjectDocStatus = "Saved";

			} else if (sGLEnableTaxCalculation.equalsIgnoreCase("1") && sGLEnableSalesOrder.equalsIgnoreCase("0")) {
				sGLMyTestProjectDocStatus = "  ";
			}
		} else

		if (sMenuOption.equalsIgnoreCase("OrdersPostCommitTax") || sMenuOption.equalsIgnoreCase("QuotesPostCommitTax")
				|| sMenuOption.equalsIgnoreCase("OpportunityPostCommitTax")) {
			if (sGLEnableTriggerForGetTax != null) {
				if (sGLEnableTaxCalculation != null)
					sGLMyTestProjectDocStatus = "Committed";
				else
					sGLMyTestProjectDocStatus = "Temporary";
			}
		}
		sStatusID = "//td[4]/div";
		sGLActualMyTestProjectDocStatus = getText(sStatusID);
		return sGLActualMyTestProjectDocStatus;
	}

	public String getInvoiceMessaging() {
		String invoiceMessagingValue = "";
		if (getText("//tr[6]/td[4]/div").length() > 0) {
			invoiceMessagingValue = getText("//tr[6]/td[4]/div");
		}
		return invoiceMessagingValue;
	}// End of fCalculateOpportunitySalesTax

	public void fTriggergetTax(String option) throws InterruptedException {
		String sCalculateOrFinalizeOption = "//td[@id='topButtonRow']//input[@name='ava_sfcloud__calculate_tax']";
		clickonElement_Xpath(sCalculateOrFinalizeOption);
		Thread.sleep(5000);
	}

	public String fVerifyTaxOverrideAmountOnWindow(String sMenuOption) {
		String actualSalesTax = "";
		if (sMenuOption.equalsIgnoreCase("Opportunities")) {

		} else if (sMenuOption.equalsIgnoreCase("Quotes")) {

		} else if (sMenuOption.equalsIgnoreCase("Orders")) {

		}
		return actualSalesTax;
	}

	// SF CLOUD
	public String fErrorMessageDisplayedResult() {
		try {
			WaitForPageLoad();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String errorHeaderMessage = "//tr[2]/td[4]/div";
		if (sGLErrorMessageDisplayed != null
				|| (sGLEnableTaxCalculation.equalsIgnoreCase("0") && sGLErrorMessageDisplayed != null)) {
			if (isDisplayed(errorHeaderMessage))//
			{

				sGLActualErrorMessage = getText(errorHeaderMessage);
				System.out.println(sGLTestCaseName + "Error message appeared as " + sGLActualErrorMessage);
			} else {
				System.out.println(sGLTestCaseName + "F:Error Message Test Cases verified not successfully");
			}
		}
		return sGLActualErrorMessage;
	}

	/*********************************************************************************************************************
	 * // Start -- Function for Ship To Address validation
	 *********************************************************************************************************************/
	public void fTriggerShipToAddress(String sOption) throws Exception {

		if (sOption.equalsIgnoreCase("ACcs")) {
			// Trigger validate Ship To address
			clickonElement_Xpath(
					"//div[@class='pbHeader']//input[@name='ava_sfcloud__MyTestProjecteXAMPLE_validate_shipping_address']");
		}
		// Check Condition for New Quotes
		if (sOption.equalsIgnoreCase("newQuote")) {
			// Trigger validate Ship To address
			clickonElement_Xpath(
					"//div[@class='pbHeader']//input[@name='ava_sfcloud__quote_validate_ship_to_address']");
		}
		// Check condition for TaxNow setting
		if (sOption.equalsIgnoreCase("MyTestProjecteXAMPLEConfiguration")) {
			// Click on Corporate Address
			expectelementToBeClickable("//a[text()='Corporate Address']");
			clickonElement_Xpath("//a[text()='Corporate Address']");
			Thread.sleep(3000);
			clickonElement_Xpath("//button[text()='Validate Address']");
			Thread.sleep(4000);
		}
		// Orders
		if (sOption.equalsIgnoreCase("Orders")) {
			// Trigger validate Validate mail To address
			expectelementToBeClickable(
					"//div[@class='pbHeader']//input[@name='ava_sfcloud__order_validate_shipping_address']");
		}
		// Calling function for verifying the validated address
	}

	// End of fShipToAddressValidation
	/*********************************************************************************************************************
	 * // Start -- Function for Bill To Address validation
	 *********************************************************************************************************************/
	public void fTriggerBillToAddress(String sOption) throws Exception {
		ExecutionDelay(20);
		if (sOption.equalsIgnoreCase("ACcs")) {
			// Trigger validate billing address
			clickonElement_Xpath(
					"//div[@class='pbHeader']//input[@name='ava_sfcloud__MyTestProjecteXAMPLE_validate_billing_address']");
		} else
		// Quotes window
		if (sOption.equalsIgnoreCase("newQuote")) {
			// Trigger validate Bill To address
			clickonElement_Xpath(
					"//div[@class='pbHeader']//input[@name='ava_sfcloud__quote_validate_bill_to_address']");
		} else if (sOption.equalsIgnoreCase("Orders")) {
			// Trigger validate billing address
			expectelementToBeClickable(
					"//div[@class='pbHeader']//input[@name='ava_sfcloud__order_validate_billing_address']");
		}

		ExecutionDelay(5);

	}

	// Method to verify Alert messages on Configuration page
	public String fVerifyErrorMassageOnMyTestProjecteXAMPLEConfiguration() {

		clickonElement_Xpath("//button[text()='Validate Address']");
		String ErrorPopupMessage = "//div[@class='slds-notify__content']/h2";

		sGLActualValidatedErrorMessage = getText(ErrorPopupMessage);
		return sGLActualValidatedErrorMessage;
	}

	public String fVerifyValidatedShipToAndBillToAddress(String sOption) throws Exception {
		String sOnScreenLine1;
		String sOnScreenCity;
		String sOnScreenState;
		String sOnScreenZip;
		String sOnScreenCountry;
		// Address validation for valid address

		Thread.sleep(5000);
		if ((sOption.equalsIgnoreCase("MyTestProjecteXAMPLEConfiguration"))) {
			ExecutionDelay(5);//
			sOnScreenLine1 = driver
					.findElement(By
							.xpath("//div[text()='Validated Address']//following-sibling::div[1]//label[text()='Line 1 ']//ancestor::td[1]//following-sibling::td[2]/label"))
					.getText();
			sOnScreenCity = driver
					.findElement(By
							.xpath("//div[text()='Validated Address']//following-sibling::div[1]//label[text()='City ']//ancestor::td[1]//following-sibling::td[2]/label"))
					.getText();
			sOnScreenState = driver
					.findElement(By
							.xpath("//div[text()='Validated Address']//following-sibling::div[1]//label[text()='State ']//ancestor::td[1]//following-sibling::td[2]/label"))
					.getText();
			sOnScreenZip = driver
					.findElement(By
							.xpath("//div[text()='Validated Address']//following-sibling::div[1]//label[text()='PostalCode ']//ancestor::td[1]//following-sibling::td[2]/label"))
					.getText();
			sOnScreenCountry = driver
					.findElement(By
							.xpath("//div[text()='Validated Address']//following-sibling::div[1]//label[text()='Country ']//ancestor::td[1]//following-sibling::td[2]/label"))
					.getText();
			sGLActualValidatedAddress = sOnScreenLine1 + ", " + sOnScreenCity + ", " + sOnScreenState + ","
					+ sOnScreenZip + "," + sOnScreenCountry;
			ExecutionDelay(2);
			System.out.println("Validated ACc Shipping Address:" + sGLActualValidatedAddress);
			if (sReplaceWithValidAddressFlag.equalsIgnoreCase("1")) {
				clickonElement_Xpath("//button[text()='Replace With Validated Address']");
			} else {
				clickonElement_Xpath("//button[text()='Keep Address As Entered']");
			}
			ExecutionDelay(2);
		}
		if (((sOption.equalsIgnoreCase("ACcs")) || sOption.equalsIgnoreCase("newQuote"))
				|| (sOption.equalsIgnoreCase("Orders"))) {
			System.out.println("fVerifyValidatedShipToAddress for : " + sOption);
			ExecutionDelay(20);
			if (IsElementPresent(By.xpath("//b[text()='Line 1:  ']"))) {
				ExecutionDelay(10);
				sOnScreenLine1 = getText("//td[2]/table/tbody/tr/td/div/div/div[2]/div/table/tbody/tr[2]/td");
				sOnScreenCity = getText("//td[2]/table/tbody/tr/td/div/div/div[2]/div/table/tbody/tr[5]/td");
				sOnScreenState = getText("//td[2]/table/tbody/tr/td/div/div/div[2]/div/table/tbody/tr[6]/td");
				sOnScreenZip = getText("//td[2]/table/tbody/tr/td/div/div/div[2]/div/table/tbody/tr[7]/td");
				sOnScreenCountry = getText("//td[2]/table/tbody/tr/td/div/div/div[2]/div/table/tbody/tr[8]/td");
				sGLActualValidatedAddress = sOnScreenLine1 + ", " + sOnScreenCity + ", " + sOnScreenState + ", "
						+ sOnScreenZip + ", " + sOnScreenCountry;
				System.out.println(sGLActualValidatedAddress);
				if (sReplaceWithValidAddressFlag.equalsIgnoreCase("1")) {
					clickonElement_Xpath("//a[text()='Update Validated Address']");
					ExecutionDelay(4);
					clickonElement_Xpath("//td[contains(@id, 'bottom')]//input[@value='Back']");
				} else {
					clickonElement_Xpath("//td[contains(@id, 'bottom')]//input[@value='Back']");
				}
			}
			// Address validation for disable address / Invalid address
			else {
				if (IsElementPresent(By.xpath("//td[@id='j_id0:j_id2:j_id3:j_id6:j_id14:0:j_id16:j_id18:0:j_id19']"))) {
					ExecutionDelay(5);
					sGLActualValidatedAddress = getText(
							"//td[@id='j_id0:j_id2:j_id3:j_id6:j_id14:0:j_id16:j_id18:0:j_id19']");
					ExecutionDelay(5);
					clickonElement_Xpath("//td[contains(@id, 'bottom')]//input[@value='Back']");
					ExecutionDelay(6);
					Thread.sleep(3000);
				}
			}
		}

		else {
		}
		return sGLActualValidatedAddress;

	}

	public boolean IsElementPresent(By by) {
		return driver.findElements(by).size() > 0;

	}

	//////////////////////////// Create products ///////////////////////////////
	public boolean fCreateProducts() {
		int iCount = 10000;

		try {
			for (int iTemp = 1; iTemp <= iCount; iTemp++) {

				driver.findElement(By.linkText("Products")).click();
				Thread.sleep(2000);
				clickonElement_ID("new");
				clickonElement_Name("new");
				Thread.sleep(2200);
				enterValue_LocatorID("Name", "Test" + iTemp);
				enterValue_LocatorID("ProductCode", "Test" + iTemp);
				clickonElement_ID("IsActive");
				clickonElement_Xpath("//form/div/div[3]/table/tbody/tr/td[2]/input");
				Thread.sleep(2000);
				clickonElement_Name("add");
				Thread.sleep(1000);
				enterValue_LocatorID("td0_2", "1000");
				clickonElement_Xpath("//div[@class='pbHeader']//input[@name='save']");
				Thread.sleep(2000);
				clickonElement_Name("add");
				Thread.sleep(2000);
				clickonElement_ID("ids0");
				clickonElement_Xpath("//div[3]/input");
				Thread.sleep(1000);
				enterValue_LocatorID("td0_8", "1000");
				clickonElement_Name("save");
				Thread.sleep(2000);
			}

			return true;
		} catch (Exception oException) {
			return false;
		}
	}

	public void TempOpportunity() {
		clickonElement_Xpath("//input[@name='newOpp']");
		ExecutionDelay(5);
		String sOpportunityName = fRamdomString(5);
		enterValue_LocatorID("opp3", sOpportunityName);
		ExecutionDelay(3000);
		enterValue_LocatorID("opp9", sGLDate);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		expectvisibilityOfElementLocated("//select[@id='opp11']");
		selectByVisibleText("//select[@id='opp11']", "Closed Won");
		clickonElement_Xpath("//div[@class='pbHeader']//input[@name='save']");
		ExecutionDelay(5);
	}

	public void findAndClick(String Locator, String sGLLocatorValue) {
		WebElement element = null;
		switch (Locator) {
		case "name":
			element = driver.findElement(By.name(sGLLocatorValue));
			break;
		case "id":
			element = driver.findElement(By.id(sGLLocatorValue));
			break;
		case "xpath":
			element = driver.findElement(By.xpath(sGLLocatorValue));
			break;
		case "cssSelector":
			element = driver.findElement(By.cssSelector(sGLLocatorValue));
			break;
		}

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void quit() {
		driver.quit();
	}

	// This method is for Tax Override feature
	public void fSalesTaxAmountOverride(String checkTaxOverrideCheckBoxLineLevel) throws InterruptedException {
		String productsEditLink = "//td[1]//a[text()='Edit']";
		expectvisibilityOfElementLocated(productsEditLink);
		clickonElement_Xpath(productsEditLink);
		Thread.sleep(3000);
		if (checkTaxOverrideCheckBoxLineLevel == "Yes") {
			clickonElement_Xpath("//label[text()='Tax Override']/ancestor::td[1]/following-sibling::td[1]//input");
		}
		// Enter Sales Tax Amount
		if (!sGLTOSalesTaxAmount.isEmpty()) {
			enterValue_LocatorXpath(
					"//label[text()='Sales Tax Amount']/ancestor::td[1]/following-sibling::td[1]//input",
					sGLTOSalesTaxAmount);
		}
		// Click on Save button
		clickonElement_Xpath("//div[@class='pbHeader']//input[@name='save']");
		Thread.sleep(3000);
	}

	// This method is for Activating the workflow rules
	public void fRefreshPage() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(5000);
	}

	public void fCheckCalculateTaxAndCommitCheckBox(String checkboxOption) throws InterruptedException {
		String editButton = "//div[@class='pbHeader']//input[@name='edit']";
		String saveButton = "//div[@class='pbHeader']//input[@name='save']";
		String CalculateTaxCheckBox = "//label[text()='Calculate Tax']/ancestor::td[1]/following-sibling::td[1]/input";
		String CommitCheckBox = "//label[text()='Commit Tax']/ancestor::td[1]/following-sibling::td[1]/input";

		clickonElement_Xpath(editButton);
		Thread.sleep(3000);
		if (checkboxOption.equalsIgnoreCase("CheckCalculateTaxCheckBox")) {
			clickonElement_Xpath(CalculateTaxCheckBox);
		}
		if (checkboxOption.equalsIgnoreCase("CheckCommitCheckBox")) {
			clickonElement_Xpath(CommitCheckBox);
		}
		clickonElement_Xpath(saveButton);
		Thread.sleep(4000);
	}

	public List<String> fSalesTaxDetailsfieldValidation() throws InterruptedException {
		String productsEditLink = "//td[1]//a[text()='Edit']";
		Thread.sleep(3000);
		String salesTaxAmount = "//label[text()='Sales Tax Amount']/ancestor::td[1]/following-sibling::td[1]/input";
		String salesTaxRate = "//label[text()='Sales Tax Rate']/ancestor::td[1]/following-sibling::td[1]/input";
		String salesTaxDetails = "//label[text()='Sales Tax Details']/ancestor::td[1]/following-sibling::td[1]/textarea";
		String cancelButton = "//div[@class='pbHeader']//input[@name='cancel']";
		clickonElement_Xpath(productsEditLink);
		Thread.sleep(3000);

		List<String> salesTaxDetailsfieldValues = new ArrayList<>();

		salesTaxDetailsfieldValues.add(getTextByValue(salesTaxAmount));
		salesTaxDetailsfieldValues.add(getTextByValue(salesTaxRate));
		salesTaxDetailsfieldValues.add(getText(salesTaxDetails));

		clickonElement_Xpath(cancelButton);

		return salesTaxDetailsfieldValues;
	}

	public void fUpdateOrRemoveEntityUseCodeOnTransaction(String action) throws InterruptedException {

		String editButton = "//div[@class='pbHeader']//input[@name='edit']";
		String savebutton = "//div[@class='pbHeader']//input[@name='save']";
		String entityUseCodelookUpField = "//label[text()='Entity/Use Code']/ancestor::td[1]/following-sibling::td[1]/span/input";
		// To Edit the object transaction
		clickonElement_Xpath(editButton);
		Thread.sleep(4000);
		if (action.equalsIgnoreCase("Update")) {
			// Update Entity use code on the Opportunity/Quotes/Order
			// transaction
			enterValue_LocatorXpath(entityUseCodelookUpField, "C");
		} else if (action.equalsIgnoreCase("Remove")) {
			// Update Entity use code on the Opportunity/Quotes/Order
			// transaction
			enterValue_LocatorXpath(entityUseCodelookUpField, " ");
		}
		clickonElement_Xpath(savebutton);
		Thread.sleep(3000);
	}

	public void fUpdateOrRemoveACcNameOnTransaction(String action) throws InterruptedException {

		String editButton = "//div[@class='pbHeader']//input[@name='edit']";
		String savebutton = "//div[@class='pbHeader']//input[@name='save']";
		String ACclookUpField = "//label[text()='ACc Name']/ancestor::td[1]/following-sibling::td[1]//span/input";
		// To Edit the object transaction
		clickonElement_Xpath(editButton);
		Thread.sleep(4000);
		if (action.equalsIgnoreCase("UpdateAccWithDiffEntityUseCode")) {
			// Update Entity use code on Opportunity/Quotes/Order transaction
			enterValue_LocatorXpath(ACclookUpField, "ACWithDiffEntityUseCode");
		} else if (action.equalsIgnoreCase("UpdateAccWithNoEntityUseCode")) {
			// Update Entity use code on Opportunity/Quotes/Order transaction
			enterValue_LocatorXpath(ACclookUpField, "ACWithNoEntityUseCode");
		}
		clickonElement_Xpath(savebutton);
		Thread.sleep(3000);
	}

	public void enterValidcred() throws InterruptedException {
		String ACcNumber = "";
		String licenceKey = "";
		ACcNumber = prop.getProperty("sbx.ACc.number");
		licenceKey = prop.getProperty("sbx.licence.key");
	
		Thread.sleep(5000);
		navigateToMyTestProjecteXAMPLEConfigurationPage();
		expectelementToBeClickable(ACcNumberTextBox);
		clickonElement_Xpath(ACcNumberTextBox);
		clearValue_LocatorXpath(ACcNumberTextBox);
		Thread.sleep(3000);
		driver.findElement(By.xpath(ACcNumberTextBox)).sendKeys(ACcNumber);
		// enterValue(ACcNumberTextBox, ACcNumber);
		expectelementToBeClickable(lickeyTextBox);
		clickonElement_Xpath(lickeyTextBox);
		clearValue_LocatorXpath(lickeyTextBox);
		driver.findElement(By.xpath(lickeyTextBox)).sendKeys(licenceKey);
		// enterValue(lickeyTextBox, licenceKey);
		ExecutionDelay(10);
		clickonElement_Xpath(lickeyLabel);
		expectelementToBeClickable(testConnectionButton);
		clickonElement_Xpath(testConnectionButton);
		ExecutionDelay(5);
		// verify failed error message
		expectvisibilityOfElementLocated(MyTestProjecteXAMPLEMessagePopUpLabel);
		clickonElement_Xpath(okButton);
		expectelementToBeClickable(companyCodeDropdown);
		clickonElement_Xpath(companyCodeDropdown);
		expectelementToBeClickable(companyCodeOptionSelection);
		clickonElement_Xpath(companyCodeOptionSelection);
		ExecutionDelay(6);
		clickonElement_Xpath(companyCodeLabel);
		ExecutionDelay(5);
		clickonElement_Xpath(saveButton);
		Thread.sleep(5000);
	}

	public void navigateToMyTestProjecteXAMPLEConfigurationPage() throws InterruptedException {
		String installedPackagesLink = "//a[text()='Installed Packages']";
		String configureLink = "//a[text()='Configure'][contains(@title,'MyTestProjecteXAMPLE')]";
		clickonElement_Xpath("//span[@id='userNavLabel']");
		clickonElement_Xpath("//a[text()='Setup']");
		enterValue_LocatorXpath("//input[@id='setupSearch']", "Installed Packages");
		Thread.sleep(3000);
		expectpresenceOfElementLocated(installedPackagesLink);
		expectelementToBeClickable(installedPackagesLink);
		clickonElement_Xpath(installedPackagesLink);
		expectvisibilityOfElementLocated(configureLink);
		clickonElement_Xpath(configureLink);
		ExecutionDelay(10);
		Thread.sleep(3000);
	}

	// MyTestProjecteXAMPLE Credential Verification
	public void fillACcNumberTextBox(String ACcNumber) {
		expectelementToBeClickable(ACcNumberTextBox);
		clickonElement_Xpath(ACcNumberTextBox);
		clearValue_LocatorXpath(ACcNumberTextBox);
		enterValue_LocatorXpath(ACcNumberTextBox, ACcNumber);
	}

	public void filllickeyTextBox(String lickey) {
		expectelementToBeClickable(lickeyTextBox);
		clickonElement_Xpath(lickeyTextBox);
		clearValue_LocatorXpath(lickeyTextBox);
		enterValue_LocatorXpath(lickeyTextBox, lickey);
	}

	public void clickOnlickeyLabel() {
		clickonElement_Xpath(lickeyLabel);
	}

	public void clickOnTestConnectionButton() {
		expectelementToBeClickable(testConnectionButton);
		clickonElement_Xpath(testConnectionButton);
		clickonElement_Xpath(testConnectionButton);
	}
	
	public String getTestConnectionFailureMessage() {
		expectvisibilityOfElementLocated(MyTestProjecteXAMPLEMessagePopUpLabel);
		return getText(testConnectionFailureMessage);
	}

	public String getTestConnectionSuccessMessage() {
		expectvisibilityOfElementLocated(MyTestProjecteXAMPLEMessagePopUpLabel);
		return getText(testConnectionSuccessMessage);
	}

	public String fVerifyAlertPopupForAut() {
		Alert alert = driver.switchTo().alert();
		sGLActualAutMessage = alert.getText();
		System.out.println("sGLActualAutMessage :" + alert.getText());
		alert.accept();
		return sGLActualAutMessage;
	}

	public void clickOnOkButton() {
		expectvisibilityOfElementLocated(okButton);
		clickonElement_Xpath(okButton);
	}

	public void clickOnCompanyCodeOptionSelection() {
		expectelementToBeClickable(companyCodeDropdown);
		clickonElement_Xpath(companyCodeDropdown);
		expectelementToBeClickable(companyCodeOptionSelection);
		clickonElement_Xpath(companyCodeOptionSelection);
	}

	public void clickOnCompanyCodeLabel() {
		clickonElement_Xpath(companyCodeLabel);
	}

	public void clickOnEnableProductionToggle() {
		if (getTextValue(enableSandboxToggle).equalsIgnoreCase("Sandbox")) {
			expectelementToBeClickable(sandboxToggle);
			clickonElement_Xpath(sandboxToggle);
		}
	}

	// Test MultiEntityConfigurationSettings class
	public void clickOnMultiCompanySettingsTab() {
		expectvisibilityOfElementLocated(multiCompanySettingsTab);
		clickonElement_Xpath(multiCompanySettingsTab);
	}

	public void clickOnDisplayMappingButton() {
		clickonElement_Xpath(displayMappingButton);
	}

	public void clickOnMyTestProjecteXAMPLECompanyCodeDropdown() {
		expectelementToBeClickable(MyTestProjecteXAMPLECompanyCodeDropdown);
		clickonElement_Xpath(MyTestProjecteXAMPLECompanyCodeDropdown);
	}

	public void clickOnMyTestProjecteXAMPLECompanyCodeOptionPSSelection() {
		expectelementToBeClickable(MyTestProjecteXAMPLECompanyCodeOptionPSSelection);
		clickonElement_Xpath(MyTestProjecteXAMPLECompanyCodeOptionPSSelection);
	}

	public void clickOnMyTestProjecteXAMPLECompanyCodeOptionAVASelection() {
		expectelementToBeClickable(MyTestProjecteXAMPLECompanyCodeOptionAVASelection);
		clickonElement_Xpath(MyTestProjecteXAMPLECompanyCodeOptionAVASelection);
	}

	public void clickOnMyTestProjecteXAMPLECompanyCodeLabel() {
		clickonElement_Xpath(MyTestProjecteXAMPLECompanyCodeLabel);
	}

	public void clickOnSaveButton() {
		clickonElement_Xpath(saveButton);
	}

	public void verifyIsWarningDisplayed() {
		isDisplayed(warnningSignImage);
	}

	public String getAddressForPSCompanyCode() {
		expectvisibilityOfElementLocated(verifyAddressForPSCompanyCode);
		return getText(verifyAddressForPSCompanyCode);
	}

	public void enterOriginAddress() {
		clickonElement_Xpath(editButton);
		expectelementToBeClickable(originStreetTextBox);
		enterValue_LocatorXpath(originStreetTextBox, sGLOriginAddress_Line1);
		enterValue_LocatorXpath(originCityTextBox, sGLOriginAddress_City);
		enterValue_LocatorXpath(originStateTextBox, sGLOriginAddress_State);
		enterValue_LocatorXpath(originZipTextBox, sGLOriginAddress_Zip);
		enterValue_LocatorXpath(originCountryTextBox, sGLOriginAddress_Country);
		clickonElement_Xpath(saveHeaderButton);
	}

	public void clickOnOriginAddressValidationButton() {
		expectelementToBeClickable(validateOriginAddressButton);
		clickonElement_Xpath(validateOriginAddressButton);
	}

	public void clickToSaveWithOrWithoutUpdatingValidatedAddress(String action) {
		if (action.equalsIgnoreCase("Update")) {
			clickonElement_Xpath("//a[text()='Update Validated Address']");
			ExecutionDelay(4);
			clickonElement_Xpath("//td[contains(@id, 'bottom')]//input[@value='Back']");
		} else {
			clickonElement_Xpath("//td[contains(@id, 'bottom')]//input[@value='Back']");
		}
	}

	public String verifyValidatedOriginAddress() {
		clickonElement_Xpath(editButton);
		String shippingAddressLine1 = getTextByValue(originStreetTextBox);
		String shippingCity = getTextByValue(originCityTextBox);
		String shippingState = getTextByValue(originStateTextBox);
		String shippingPostalCode = getTextByValue(originZipTextBox);
		String shippingCountry = getTextByValue(originCountryTextBox);
		clickonElement_Xpath(saveHeaderButton);
		String sActualValidateAddress = shippingAddressLine1 + ", " + shippingCity + ", " + shippingState + ","
				+ shippingPostalCode + "," + shippingCountry;
		System.out.println("sActualValidateAddress :" + sActualValidateAddress);
		return sActualValidateAddress;
	}

	public String verifyAddressValidationErrorMessage() throws InterruptedException {

		if (IsElementPresent(By.xpath("//td[@id='j_id0:j_id2:j_id3:j_id6:j_id14:0:j_id16:j_id18:0:j_id19']"))) {
			ExecutionDelay(5);
			sGLActualValidatedAddress = getText("//td[@id='j_id0:j_id2:j_id3:j_id6:j_id14:0:j_id16:j_id18:0:j_id19']");
			ExecutionDelay(5);
			clickonElement_Xpath("//td[contains(@id, 'bottom')]//input[@value='Back']");
			ExecutionDelay(6);
			Thread.sleep(3000);
		}
		return sGLActualValidatedAddress;

	}

	public void enterLineLevelAddressValidation(String obj) {
		clickonElement_Xpath(lineItemLinkText);
		try {
			Thread.sleep(3000);
			clickonElement_Xpath(editButton);
			if (obj.equalsIgnoreCase("Quote")) {
				expectelementToBeClickable(quoteLineLevelStreetTextBox);
				enterValue_LocatorXpath(quoteLineLevelStreetTextBox, sGLOriginAddress_Line1);
				enterValue_LocatorXpath(quoteLineLevelCityTextBox, sGLOriginAddress_City);
				enterValue_LocatorXpath(quoteLineLevelStateTextBox, sGLOriginAddress_State);
				enterValue_LocatorXpath(quoteLineLevelZipTextBox, sGLOriginAddress_Zip);
				enterValue_LocatorXpath(quoteLineLevelCountryTextBox, sGLOriginAddress_Country);
				ExecutionDelay(4);
			} else {
				expectelementToBeClickable(lineLevelStreetTextBox);
				enterValue_LocatorXpath(lineLevelStreetTextBox, sGLOriginAddress_Line1);
				enterValue_LocatorXpath(lineLevelCityTextBox, sGLOriginAddress_City);
				enterValue_LocatorXpath(lineLevelStateTextBox, sGLOriginAddress_State);
				enterValue_LocatorXpath(lineLevelZipTextBox, sGLOriginAddress_Zip);
				enterValue_LocatorXpath(lineLevelCountryTextBox, sGLOriginAddress_Country);
			}
			clickonElement_Xpath(saveHeaderButton);
		} catch (InterruptedException e) {
		}
	}

	public void clickOnLineLevelAddressValidationButton(String obj) {

		if (obj.equalsIgnoreCase("Quote")) {
			expectelementToBeClickable(validateQuoteLineLevelAddressButton);
			clickonElement_Xpath(validateQuoteLineLevelAddressButton);
		} else {
			expectelementToBeClickable(validateLineLevelAddressButton);
			clickonElement_Xpath(validateLineLevelAddressButton);
		}

	}

	public String verifyValidatedLineLevelAddress(String obj) {
		String shippingAddressLine1 = "";
		String shippingCity = "";
		String shippingState = "";
		String shippingPostalCode = "";
		String shippingCountry = "";
		clickonElement_Xpath(editButton);
		if (obj.equalsIgnoreCase("Quote")) {
			shippingAddressLine1 = getTextByValue(quoteLineLevelStreetTextBox);
			shippingCity = getTextByValue(quoteLineLevelCityTextBox);
			shippingState = getTextByValue(quoteLineLevelStateTextBox);
			shippingPostalCode = getTextByValue(quoteLineLevelZipTextBox);
			shippingCountry = getTextByValue(quoteLineLevelCountryTextBox);
		} else {
			shippingAddressLine1 = getTextByValue(lineLevelStreetTextBox);
			shippingCity = getTextByValue(lineLevelCityTextBox);
			shippingState = getTextByValue(lineLevelStateTextBox);
			shippingPostalCode = getTextByValue(lineLevelZipTextBox);
			shippingCountry = getTextByValue(lineLevelCountryTextBox);
		}
		clickonElement_Xpath(saveHeaderButton);
		String sActualValidateAddress = shippingAddressLine1 + ", " + shippingCity + ", " + shippingState + ","
				+ shippingPostalCode + "," + shippingCountry;
		System.out.println("sActualValidateAddress :" + sActualValidateAddress);
		return sActualValidateAddress;
	}

	// Line Level Entity use code
	public void enterLineLevelEntityUseCode(String lineNo) {
		try {
			if (lineNo.equalsIgnoreCase("1")) {
				// Update Entity use code on the Opportunity/Quotes/Order
				// transaction
				Thread.sleep(3000);
				clickonElement_Xpath(lineItemLinkText);
				clickonElement_Xpath(editButton);
				enterValue_LocatorXpath(entityUseCodeLookupField, "B");
			} else if (lineNo.equalsIgnoreCase("2")) {
				// Update Entity use code on the Opportunity/Quotes/Order
				// transaction
				Thread.sleep(3000);
				clickonElement_Xpath(lineItem2LinkText);
				clickonElement_Xpath(editButton);
				enterValue_LocatorXpath(entityUseCodeLookupField, "C");
			}
			clickonElement_Xpath(saveHeaderButton);
		} catch (InterruptedException e) {
		}
	}

	public void navigateBackToObjectPage(String obj) {
		try {
			Thread.sleep(3000);
			if (obj.equalsIgnoreCase("opportunity")) {
				clickonElement_Xpath(opportunityLinkText);
			}
			if (obj.equalsIgnoreCase("Quote")) {
				clickonElement_Xpath(quoteLinkText);
			} else if (obj.equalsIgnoreCase("order")) {
				clickonElement_Xpath(orderLinkText);
			}
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
	}

	public String verifyLineLevelEntityUseCode(String lineNo) {
		String entityUseCode = "";
		try {
			if (lineNo.equalsIgnoreCase("1")) {
				// Update Entity use code on the Opportunity/Quotes/Order
				// transaction
				Thread.sleep(3000);
				clickonElement_Xpath(lineItemLinkText);
				clickonElement_Xpath(editButton);
				entityUseCode = getTextByValue(entityUseCodeLookupField);

			} else if (lineNo.equalsIgnoreCase("2")) {
				// Update Entity use code on the Opportunity/Quotes/Order
				// transaction
				Thread.sleep(3000);
				clickonElement_Xpath(lineItem2LinkText);
				clickonElement_Xpath(editButton);
				entityUseCode = getTextByValue(entityUseCodeLookupField);
			}
			clickonElement_Xpath(saveHeaderButton);
		} catch (InterruptedException e) {
		}
		return entityUseCode;
	}
}
