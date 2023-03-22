package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_ID() {
		driver.get("https://www.fahasa.com/customer/account/create");
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
		By loginButton = By.cssSelector("button.fhs-btn-login");
		
		driver.findElement(loginButton).isEnabled();
		//Verify button is disabled
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		driver.findElement(By.id("login_username")).sendKeys("0905444333");
		driver.findElement(By.id("login_password")).sendKeys("Abcd1234");
		//sleepInSecond(2);
		Color colorButton = Color.fromString(driver.findElement(loginButton).getCssValue("background-color"));
		String aa = driver.findElement(loginButton).getCssValue("background-color");
		System.out.print(aa);
		Assert.assertEquals(colorButton.asHex().toUpperCase(), "#C92127");
		
	}

	@Test
	public void TC_02_ValidatePageTitle() {
		
	}

	@Test
	public void TC_03_LoginFormDisplayed() {

	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}