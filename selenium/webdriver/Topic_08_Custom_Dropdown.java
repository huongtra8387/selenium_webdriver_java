package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
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
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		sleepInSecond(3);
		
		selectItemDropdown("span[id='speed-button']", "//div[@role='option']", "Faster");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(), "Faster");
	}

	//@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		sleepInSecond(3);
		
		selectItemDropdown("div[role='listbox']", "//span[@class='text']", "Matt");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='listbox']/div[@role='alert']")).getText(), "Matt");
	}

	//@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		sleepInSecond(3);
		
		selectItemDropdown("li.dropdown-toggle", "//ul[@class='dropdown-menu']//a", "Second Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");
	}
	
	@Test
	public void TC_04_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		sleepInSecond(3);
		
		inputAndSelectItemDropdown("//input[@class='search']", "be", "//div[@role='option']/span", "Benin");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Benin");
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectItemDropdown(String parentCss, String allItemCss, String expectedItem) {
		
		driver.findElement(By.cssSelector(parentCss)).click();
		//locator lay dai dien cho tat ca item
		//Lay đến thẻ chứa 
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemCss)));
		List<WebElement> speedDropdownItems = driver.findElements(By.xpath(allItemCss));
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText().trim();
			System.out.println(itemText);
			
		if (itemText.equals(expectedItem)) {
			tempItem.click();
			//thoat khoi vong lap khong xet cac case con lai
			break;
		}
		}
	}
	public void inputAndSelectItemDropdown(String inputTextbox, String inputText, String allItemCss, String expectedItem) {
		
		driver.findElement(By.xpath(inputTextbox)).sendKeys(inputText);
		List<WebElement> speedDropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemCss)));
		for (WebElement tempItem : speedDropdownItems) {
		
		if (tempItem.getText().equals(expectedItem)) {
			tempItem.click();
		}
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