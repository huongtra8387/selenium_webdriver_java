package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_By_Locator {
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
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("email"));
	}

	@Test
	public void TC_02_Class() {
		// Chỉ lấy 1 phần của giá trị attribute khi có space trong do
		// driver.findElement(By.className("button search-button"));
		driver.findElement(By.className("search-button"));
	}

	@Test
	public void TC_03_Name() {
		driver.findElement(By.name("login[username]"));
	}
	
	@Test
	public void TC_04_TagName() {
		//Verify xem 1 page có bao nhiêu element giống nhau: link (thẻ a)/textbox (thẻ input)
		driver.findElement(By.tagName("a"));
	}
	
	@Test
	public void TC_05_LinkText() {
		// Chi dung cho link
		//Lấy tuyệt đối 
		driver.findElement(By.linkText("Search Terms"));
	}
	
	@Test
	public void TC_06_Partial_LinkText() {
		//Chi dung cho link
		// Lấy tương đối - contain
		driver.findElement(By.partialLinkText("Site"));
	}
		
	@Test
	public void TC_07_Css() {
		// Format: tagname[attribute_name='attribute_value']
		// Nếu có space trong attribute value thì thêm dấu . để tìm/nối 
	}

	@Test
	public void TC_08_Xpath() {
		// Format: //tagname[@attribute_name='attribute_value']
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	
}