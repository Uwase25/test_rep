package variousConcepts;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LearnWindowHandle {

	WebDriver driver;

	@Test
	public void init() {

		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("https://www.yahoo.com/");
		// driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//		 Method of getting Header or Title 

		System.out.println(driver.getTitle()); // it will print"Yahoo"

//		how to get window handle: ID
//		It returns a handle of the current page/tab
// we don't need coded 33, 34 but i'm keeping them for my better understanding
		String handle = driver.getWindowHandle(); // created a variable to return a string
		System.out.println(handle); // it will print window ID

		// how to transfer driver from one tab to another tab

		driver.findElement(By.xpath("//input[@id='ybar-sbq']")).sendKeys("xpath"); // write xpath on search
		driver.findElement(By.xpath("//input[@id='ybar-search']")).click(); // click the search button

//		to click the 1st opened link

		driver.findElement(By.linkText("XPath Tutorial - W3Schools")).click();

		System.out.println(driver.getTitle());

//		 returns a set of handles of the all the pages available
		Set<String> handle2 = driver.getWindowHandles();
		System.out.println(handle2);

//		to separate the ID we will use for each loop
		for (String i : handle2) {
			System.out.println(i);

//			using for loop to transfer our driver
			driver.switchTo().window(i); // will switch to the new window

			System.out.println(driver.getTitle()); // the title of the new window xpath

//			to go back to previous window
			
			driver.switchTo().window(handle);
			
			System.out.println(driver.getTitle()); // we don't need this it just helps us to see the result in console
			
			
			
		}

	}
}