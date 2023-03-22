package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Iteraction2 {
	WebDriver driver;
	Actions action;
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
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_ClickAndHold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		//Click so 1, giu chuot chua nha ra
		action.clickAndHold(listNumber.get(0))
		
		//Di chuot den so target
		.moveToElement(listNumber.get(7))
		//Nha chuot
		.release()
		
		//Execute
		.perform();
		
		sleepInSecond(5);
		
		List<WebElement> listSelectedNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(listSelectedNumber.size(), 8);
		
	}

	@Test
	public void TC_02_ClickAndSelect() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		Keys key = null;
		if (osName.contains("Windows")) {
			key = Keys.CONTROL;
		} else {
			key = Keys.COMMAND;
		}
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		//Nhan Ctr xuong
		action.keyDown(key).perform();
		//Click cho so
		action.click(listNumber.get(0))
		.click(listNumber.get(5))
		.click(listNumber.get(7))
		.click(listNumber.get(10)).perform();
		//Nha phim ctr
		action.keyUp(key).perform();
		sleepInSecond(5);
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