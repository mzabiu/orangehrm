package com.orangehrm.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.xml.XmlTest;

import com.orangehrm.framework.components.Validator;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	Validator validator = new Validator();
	public EventFiringWebDriver driver;
	public WebDriver driver1;

	@BeforeTest(alwaysRun = true)
	public void initBrowser(XmlTest data, ITestContext context) {

		String browserName = data.getParameter("browser");

		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver1 = new ChromeDriver();
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", "./src/main/resources/com/orangehrm/drivers/msedgedriver.exe");
			driver1 = new EdgeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver1 = new FirefoxDriver();
			break;

		default:
			throw new RuntimeException("Please provide valid browser name");
		}
		driver = new EventFiringWebDriver(driver1);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");

		context.setAttribute("WebDriver", driver);

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
