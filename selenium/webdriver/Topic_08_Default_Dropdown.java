package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, emailAddress, companyName, passWord, confirmPassWord, day, month, year;
	//Select select = new Select(driver.findElement(By.name("DateOfBirthDay")))   ==> ko can khoi tao nhu the nay, dung truc tiep nhu TC_01

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
		
		firstName = "Sam";
		emailAddress = "auto" + getRandomNumber() + "@gmail.com";
		lastName = "Mit";
		companyName = "BCA";
		passWord = "Abcd5678";
		confirmPassWord = "Abcd5678";
		day = "1";
		month = "May";
		year = "1987";
	}

	@Test
	public void TC_01_Register_New_Account() {
		driver.get("https://demo.nopcommerce.com/");
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.cssSelector("#gender-female")).click();
		driver.findElement(By.cssSelector("#FirstName")).sendKeys(firstName);
		driver.findElement(By.cssSelector("#LastName")).sendKeys(lastName);
		
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText(day);
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);
		
		driver.findElement(By.cssSelector("#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("#Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(passWord);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(confirmPassWord);
		driver.findElement(By.id("register-button")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
		
		driver.findElement(By.xpath("//a[text()='My account']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector(".email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector(".password")).sendKeys(passWord);
		driver.findElement(By.cssSelector(".login-button")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);
		
	}

	@Test
	public void TC_02_Add_Address() {
		
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