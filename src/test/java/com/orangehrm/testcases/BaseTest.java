package com.orangehrm.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public EventFiringWebDriver driver;
	public WebDriver driver1;

	@BeforeMethod
	public void init() {

		WebDriverManager.chromedriver().setup();
		driver1 = new ChromeDriver();
		driver = new EventFiringWebDriver(driver1);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
