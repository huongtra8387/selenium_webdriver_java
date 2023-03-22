package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
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
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		//ko wait ma tuong tac voi alert luon
		//alert = driver.switchTo().alert();
		//Wait den khi nao xuat hien de switch qua tuong tac voi alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
		
	}

	//@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
	}

	//@Test
	public void TC_03_Prompt_Alert() {
		String courseName = "Name of course";	
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		alert.sendKeys(courseName);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: " + courseName);
	}
	
	@Test
	public void TC_04_Authen_Alert() {
		//truyen truc tiep user/pass vao url thi tu dong sign in
		//driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		driver.get("http://the-internet.herokuapp.com/");
		String url = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		System.out.print(url);
		driver.get(passAndUserToURL(url, "admin", "admin"));
		sleepInSecond(10);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='example']/p")).getText().trim(), "Congratulations! You must have the proper credentials.");
	}
	
	public String passAndUserToURL (String url, String user, String pass) {
		String[] arrayURL = url.split("//");
		url = arrayURL[0] + "//" + user + ":" + pass + "@" + arrayURL[1];
		return url;
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