package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Default_Checkbox_Radio {
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

	//@Test
	public void TC_01_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		sleepInSecond(3);
		driver.findElement(By.xpath("//button[text()='Accept Cookies']")).click();
		sleepInSecond(3);
		if (!driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected()) {
			driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
		}
		
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		
		if (driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected()) {
			driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
		}
		
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		
	}
	
	@Test
	public void TC_01_Checkbox_01() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		sleepInSecond(3);
		driver.findElement(By.xpath("//button[text()='Accept Cookies']")).click();
		sleepInSecond(3);
		
		selectCheckbox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		
		unSelectCheckbox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		
	}

	//@Test
	public void TC_02_Radio() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		sleepInSecond(3);
		driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).click();
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).isSelected());
	}

	//@Test
	public void TC_03_AllCheckboxes() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		sleepInSecond(3);
		driver.findElement(By.xpath("//label[contains(text(),'Gout')]/preceding-sibling::input")).click();
		
		List<WebElement> checkboxItems = driver.findElements(By.xpath("//input[@class='form-checkbox']"));
		for (WebElement tempItem : checkboxItems) {
		
		if (!tempItem.isSelected()) {
			tempItem.click();
		}
		}
		//Verify all checkboxes are selected)
		for (WebElement tempItem : checkboxItems) {
		Assert.assertTrue(tempItem.isSelected());
		}
	}
	
	//@Test
	public void TC_04_AllCheckboxes() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		sleepInSecond(3);
		
		List<WebElement> checkboxItems = driver.findElements(By.xpath("//input[@class='form-checkbox']"));
		for (WebElement tempItem : checkboxItems) {
		
		if (tempItem.getAttribute("value").equals("Gout")) {
			tempItem.click();
		}
		}
	}
	
	public void selectCheckbox(By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public void unSelectCheckbox(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
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
		//driver.quit();
	}
	
}