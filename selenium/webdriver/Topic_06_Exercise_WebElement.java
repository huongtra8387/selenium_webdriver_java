package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Exercise_WebElement {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	By emailTextbox = By.id("mail");
	By ageUnder18 = By.cssSelector("#under_18");
	By educationTextArea = By.xpath("//textarea[@id='edu']");
	By nameUser5 = By.xpath("//h5[text() = 'Name: User5']");
	By jobRole1 = By.id("job1");
	By interestDevelopCheckbox = By.id("development");
	By slide01 = By.id("slider-1");
	By password = By.xpath("//input[@id='disable_password']");
	By disabledRadioButton = By.id("radio-disabled");
	By biography = By.id("bio");
	By jobRole3 = By.id("job3");
	By disabledCheckbox = By.id("check-disbaled");
	By slide02 = By.xpath("//input[@id='slider-2']");
	
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

	//@Test
	public void TC_01_isDisplayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(3);
		
		//textbox
		if (driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("Automation Testing");
			System.out.println("Textbox is displayed");
		} else {
			System.out.println("Textbox is not displayed");
		}
		
		//textarea
		if (driver.findElement(educationTextArea).isDisplayed()) {
			driver.findElement(educationTextArea).sendKeys("Automation Testing");
			System.out.println("Textarea is displayed");
		} else {
			System.out.println("Textarea is not displayed");
		}
		
		//radio button
		if (driver.findElement(ageUnder18).isDisplayed()) {
			driver.findElement(ageUnder18).click();
			System.out.println("Radio button is displayed");
		} else {
			System.out.println("Radio button is displayed");
		}
		
		//user 5
		if (driver.findElement(nameUser5).isDisplayed()) {
			System.out.println("User 5 is displayed");
		} else {
			System.out.println("User 5 is not displayed");
		}
		
	}

	//@Test
	public void TC_02_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(3);
		
		if(driver.findElement(emailTextbox).isEnabled()) {
			System.out.println("Email textbox is enabled");
		} else {
			System.out.println("Email textbox is disabled");
		}
		
		if(driver.findElement(ageUnder18).isEnabled()) {
			System.out.println("Age radio is enabled");
		} else {
			System.out.println("Age radio is disabled");
		}
		
		if(driver.findElement(educationTextArea).isEnabled()) {
			System.out.println("Education text area is enabled");
		} else {
			System.out.println("Education text area is disabled");
		}
		
		if(driver.findElement(jobRole1).isEnabled()) {
			System.out.println("Job Role 1 is enabled");
		} else {
			System.out.println("Job Role 1 is disabled");
		}
		
		if(driver.findElement(interestDevelopCheckbox).isEnabled()) {
			System.out.println("Develop checkbox is enabled");
		} else {
			System.out.println("Develop checkbox is disabled");
		}
		
		//slide 01
		if(driver.findElement(slide01).isEnabled()) {
			System.out.println("Slide 01 is enabled");
		} else {
			System.out.println("Slide 01 is disabled");
		}
	}

	//@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(3);
		
		Assert.assertFalse(driver.findElement(ageUnder18).isSelected());
		
		driver.findElement(ageUnder18).click();
		
		Assert.assertTrue(driver.findElement(ageUnder18).isSelected());
	}
	
	@Test
	public void TC_04_mailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.id("email")).sendKeys("tra@gmail.com");
		By passwordTextbox = By.id("new_password");
		
		driver.findElement(passwordTextbox).sendKeys("abc");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("ABC");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("123");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("!");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("12345678");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}