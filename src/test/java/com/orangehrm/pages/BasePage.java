package com.orangehrm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	EventFiringWebDriver driver;
	String windowTitle = "";

	@FindBy(id = "menu_dashboard_index")
	private WebElement linkDashboard;
	
	@FindBy(xpath = "//div[@class='head']/*[text()='Dashboard']")
	private WebElement lblDashboard;
	

	public BasePage(EventFiringWebDriver driver) {

		this.driver = driver;
		initPage();
	}

	public void initPage() {
		PageFactory.initElements(driver, this);
	}

	public void returnToMainPage() {
		driver.switchTo().defaultContent();
		linkDashboard.click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(lblDashboard));
	}

}
