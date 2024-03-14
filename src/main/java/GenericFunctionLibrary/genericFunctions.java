package GenericFunctionLibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import Repository.objectRepositoryClass;

public class genericFunctions extends objectRepositoryClass {

	// Initialize webdriver
	public void initializeDriver() {
		try {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(14, TimeUnit.SECONDS);
			prop = new Properties();
			InputStream input = new FileInputStream(System.getProperty("user.dir") + "/lib/config.properties");
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void fCloseBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void WaitForPageLoad() throws Exception {

		try {
			driver.manage().timeouts().implicitlyWait(lGL_MAX_WAIT_TIME_IN_Seconds, TimeUnit.SECONDS);

		} catch (Exception oException) {
			System.out.println(oException);
		}
	}// End WaitForPageLoad

	public String fRamdomString(int iStringLength) {

		StringBuilder sStringBuffer = new StringBuilder();
		for (int x = 0; x < iStringLength; x++) {
			sStringBuffer.append((char) ((int) (Math.random() * 26) + 97));
		}
		// System.out.println(sStringBuffer.toString());
		return sStringBuffer.toString();
	}

	public void ExecutionDelay(int iSeconds) {

		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		wait = new WebDriverWait(driver, iSeconds);
		wait.until(pageLoadCondition);
	}

	/******************************************************************************************************
	 * This fillo api excel sheet connection It will connect with excel sheet by
	 * Query sGLTestcaseId is used as unique id to identify the row
	 ******************************************************************************************************/
	public static String GetValueFromExcelSheet(String ExcelColomnName) throws FilloException {
		Fillo fillo = new Fillo();
		com.codoid.products.fillo.Connection connection;
		connection = fillo.getConnection("SALESCLOUD_Datapool_TaxCalculation.xls");
		String strQuery = "Select * from " + sExcelSheetBookName + " where UniqueID='" + sGLTestcaseId + "'";
		Recordset recordset = connection.executeQuery(strQuery);
		recordset.moveFirst();
		Value = recordset.getField(ExcelColomnName);
		System.out.println(Value);
		return Value;
	}

	/* Selenium Functions ******/
	public boolean isDisplayed(String locator) {
		element = driver.findElement(By.xpath(locator));
		System.out.println("Is Displayed :" + element.isDisplayed() + " For Locator :" + locator);
		return element.isDisplayed();
	}

	public boolean isEnabled(String locator) {
		element = driver.findElement(By.xpath(locator));
		System.out.println("Is Enabled :" + element.isEnabled() + " For Locator :" + locator);
		return element.isEnabled();
	}

	public boolean isSelected(String locator) {
		element = driver.findElement(By.xpath(locator));
		System.out.println("Is Selected :" + element.isSelected() + " For Locator :" + locator);
		return element.isSelected();
	}

	public void clickonElement_Xpath(String locator) {
		element = driver.findElement(By.xpath(locator));
		element.click();
		System.out.println("Clicked on : " + locator);
	}

	public void clickonElement_ID(String locator) {
		element = driver.findElement(By.id(locator));
		element.click();
		System.out.println("Clicked on : " + locator);
	}

	public void clickonElement_Name(String locator) {
		element = driver.findElement(By.name(locator));
		element.click();
		System.out.println("Clicked on : " + locator);
	}

	public void selectCheckbox(String locator) {
		if (!driver.findElement(By.xpath(locator)).isSelected()) {
			driver.findElement(By.xpath(locator)).click();
			System.out.println("Seleced the checkbox : " + locator);
		}
	}

	public void clearValue_LocatorXpath(String locator) {
		element = driver.findElement(By.xpath(locator));
		element.clear();
		System.out.println("Cleared element: " + locator);
	}

	public void enterValue_LocatorXpath(String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(value);
		System.out.println("Entered value on element: " + locator + "\t Value: " + value);
	}

	public void enterValue_LocatorID(String locator, String value) {
		element = driver.findElement(By.id(locator));
		element.clear();
		System.out.println("Value Fetched : " + value);
		element.sendKeys(value);
	}

	public void enterValue(String locator, String value) {
	//	expectvisibilityOfElementLocated(locator);
		element = driver.findElement(By.id(locator));
		element.click();
		element.clear();
		element.sendKeys(value);
	}

	public String getText(String locator) {
		String value = "";
		expectvisibilityOfElementLocated(locator);
		element = driver.findElement(By.xpath(locator));
		element.click();
		value = element.getText();
		System.out.println("Value Fetched : " + value);
		return value;
	}

	public String getTextByValue(String locator) {
		String value = "";
		expectvisibilityOfElementLocated(locator);
		element = driver.findElement(By.xpath(locator));
		value = element.getAttribute("value");
		System.out.println("Value Fetched : " + value);
		return value;
	}

	public void selectByValue(String locator, String value) {
		expectpresenceOfElementLocated(locator);
		Select select = new Select(driver.findElement(By.xpath(locator)));
		select.selectByValue(value);
		System.out.println("Selected value from dropdown for : " + locator + "\t Value: " + value);
	}

	public void selectByVisibleText(String locator, String value) {
		expectpresenceOfElementLocated(locator);
		Select select = new Select(driver.findElement(By.xpath(locator)));
		select.selectByVisibleText(value);
	}

	public void expectelementToBeClickable(String locator) {
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	}

	public void expectpresenceOfElementLocated(String locator) {
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
	}

	public void expectvisibilityOfElementLocated(String locator) {
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}

	// To fetch the visible text
	public String getTextValue(String locator) {
		String value = "";
		element = objectRepositoryClass.driver.findElement(By.xpath(locator));
		value = element.getText();
		System.out.println("Value Fetched : " + value);
		return value;
	}

	// To fetch the attribute of value
	public String getTextOfAttribute(String locator, String attribute) {
		// WebElement element = null;
		String text = "";
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		element = driver.findElement(By.xpath(locator));
		text = element.getAttribute(attribute);
		System.out.println("Fetched Text : " + text + "\tfor WebElement:" + locator);
		return text;
	}

	public static Properties getProperty() {
		Properties prop = new Properties();
		try {
			InputStream input = new FileInputStream("lib/config.properties");
			prop.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
}
