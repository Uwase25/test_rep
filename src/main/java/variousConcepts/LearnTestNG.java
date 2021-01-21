
package variousConcepts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LearnTestNG {

	WebDriver driver;
	String browser= null;// Global variable for any browser
	String url= null;

	@BeforeMethod
	public void init() {

		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("url");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority = 1) // means i want it to run first
	public void loginTest() {

//		before everything we need an Assertion

		Assert.assertEquals(driver.getTitle(), "Login - iBilling", "Login not found!!");

		WebElement USERNAME_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement PASSWORD_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement SIGNIN_BUTTON_ELEMENT = driver.findElement(By.xpath("/html/body/div/div/div/form/div[3]/button"));

		USERNAME_FIELD_ELEMENT.clear(); // TO Clear the user name field
		USERNAME_FIELD_ELEMENT.sendKeys("demo@techfios.com");

		PASSWORD_FIELD_ELEMENT.clear();
		PASSWORD_FIELD_ELEMENT.sendKeys("abc123");
		SIGNIN_BUTTON_ELEMENT.click();

		WebElement DASHBORD_BUTTON_ELEMENT = driver.findElement(By.linkText("Dashboard"));

//	Creating a string variable

		String actualDashboardElement = DASHBORD_BUTTON_ELEMENT.getText();
		
// After logging in we need an Assertion to validate
	Assert.assertEquals(actualDashboardElement, "Dashboard", "Dashboard page not found!!");
		


	}

	@Test(priority = 2)
	public void addCustomerTest() {

		
//	element library

		By USERNAME_FIELD_LOCATOR = By.id("username");
		By PASSWORD_FIELD_LOCATOR = By.id("password");
		By SIGNIN_BUTTON_LOCATOR = By.name("login");

//	how to save to a container( String container): Login Data

		String loginId = "demo@techfios.com";
		String password = "abc123";

//	perform Log in

		driver.findElement(USERNAME_FIELD_LOCATOR).sendKeys(loginId);
		driver.findElement(PASSWORD_FIELD_LOCATOR).sendKeys(password);
		driver.findElement(SIGNIN_BUTTON_LOCATOR).click();
	}

	@AfterMethod
	public void teatDown() {
		driver.close();
		driver.quit();
	}

}
