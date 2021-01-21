package variousConcepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginWithMultipleBrowser {

	WebDriver driver;
	String browser = null; // global variable for any browser
	String url = null;
//	method to read the config properties

	@BeforeTest
	public void readConfig() {

		Properties prop = new Properties(); // properties object

		// in order to read a file we always do it in try and catch
//		we can use InputStream, BufferReader, FileReader, Scanner to read any file
		try {
			InputStream input = new FileInputStream("./src/main/java/config/config.properties"); // inputStream helps us
																									// to read a file
			prop.load(input); // prop from config properties
			browser = prop.getProperty(browser); // to get the property

			System.out.println("Browser used: " + browser);
			// copied config.properties in file input stream

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void init() {

//		to know which one to run we use if ,else if

		if (browser.equalsIgnoreCase("chrome")) {

//		1st browser
			System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Firefox")) {
//		2nd browser
			System.setProperty("webdriver.gecko.driver", "driver\\geckodriver.exe");
			driver = new FirefoxDriver();

		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("url"); // get to the site

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void loginTest() {

//		Element library

		By USERNAME_FIELD_LOCATOR = By.id("username");
		By PASSWORD_FIELD_LOCATOR = By.id("password");
		By SIGNIN_BUTTON_LOCATOR = By.name("login");
		By DASHBOAD_BUTTON_LOCATOR = By.xpath("//span[contains(text( ),'Dashboard' )]");

//		how to save to a container( String container): Login Data

		String loginId = "demo@techfios.com";
		String password = "abc123";

		driver.findElement(USERNAME_FIELD_LOCATOR).sendKeys(loginId);
		driver.findElement(PASSWORD_FIELD_LOCATOR).sendKeys(password);
		driver.findElement(SIGNIN_BUTTON_LOCATOR).click();

		waitForElement(driver, 4, DASHBOAD_BUTTON_LOCATOR);

//		finding dashboard element and storing it in a String container
		String dashboardValidationTest = driver.findElement(DASHBOAD_BUTTON_LOCATOR).getText();
		Assert.assertEquals("Dashboard", dashboardValidationTest, "Wrong page!!"); // Assertion to validate
	}

	@Test(priority = 2)

	public void addCustomerTest() {

//	Element Library

		By USERNAME_FIELD = By.id("username");
		By PASSWORD_FIELD = By.id("password");
		By SIGNIN_BUTTON = By.name("login");
		By DASHBOAD_BUTTON = By.xpath("//span[contains(text( ),'Dashboard' )]");
		By CUSTOMERS_BUTTON = By.xpath("//span[contains(text( ),'Customers' )]");
		By ADDCUSTOMER_BUTTON = By.xpath("//a[contains(text( ),'Add Customer' )]");
		By ADD_CONTACT_LOCATOR = By.xpath("//h5[contains(text( ),'Add Contact' )]");
		By FULLNAME_FIELD = By.xpath("//input[@id='account']");
		// By COMPANY_NAME_FIELD= By.xpath("//select[@id='cid']");

		By EMAILADRESS_FIELD = By.xpath("//input[@id='email']");
		By PHONE_FIELD = By.xpath("//input[@id='phone']");

		By ADDRESS_FIELD = By.xpath("//input[@id='address']");
		By CITY_FIELD_LOCATOR = By.xpath("//input[@id='city']");

		By STATE_REGION_FIELD = By.xpath("//input[@id='state']");
		By ZIP_FIELD = By.xpath("//input[@id='zip']");

		By SUBMIT_BUTTON_LOCATOR = By.xpath("//button[@id='submit']");
		By LIST_CONTACT_BUTTON = By.xpath("//h2[contains(text(), ' Contacts ')]");

//	Login Data
		String loginId = "demo@techfios.com";
		String password = "abc123";

//	Test Data
		String fullName = "JO US";
		String companyName = "Techfios";
		String emailAddress = "Jo@gmail.com";
		String phoneNumber = "2312233212";

//	Perform Login In
		driver.findElement(USERNAME_FIELD).sendKeys(loginId);
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		driver.findElement(SIGNIN_BUTTON).click();

//	 when it fails to identify an another Web Element we apply some wait(explicitly wait)
//	 validate Dashboard page

		waitForElement(driver, 3, DASHBOAD_BUTTON);

		String dashboardValidationTest = driver.findElement(DASHBOAD_BUTTON).getText();
		Assert.assertEquals("Dashboard", dashboardValidationTest, "Wrong Page!!");

		driver.findElement(CUSTOMERS_BUTTON).click();
		driver.findElement(ADDCUSTOMER_BUTTON).click();
		waitForElement(driver, 3, ADD_CONTACT_LOCATOR);

//	Generate random number : when you want to add some numbers on a name and email
		Random rdn = new Random();
		int randomNum = rdn.nextInt(999); // saved to an Integer container

//    Fill out add customers form

		driver.findElement(FULLNAME_FIELD).sendKeys(fullName + randomNum);
		driver.findElement(EMAILADRESS_FIELD).sendKeys(randomNum + emailAddress);
	}

//@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();

	}

//  creating a method that will be used in different locations

	public void waitForElement(WebDriver driver, int timeInSeconds, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until((ExpectedConditions.invisibilityOfElementLocated(locator)));

	}
}
