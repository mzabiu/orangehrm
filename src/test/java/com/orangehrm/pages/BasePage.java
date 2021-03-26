package com.orangehrm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriverWait wait;

	protected EventFiringWebDriver driver;
	protected String windowTitle = "";

	@FindBy(id = "menu_dashboard_index")
	protected WebElement linkDashboard;

	@FindBy(xpath = "//div[@class='head']/*[text()='Dashboard']")
	protected WebElement lblDashboard;

	@FindBy(id = "welcome")
	protected WebElement linkWelcome;

	@FindBy(xpath = "//div[@id='welcome-menu']//a[text()='Logout']")
	protected WebElement linkLogout;

	public BasePage(EventFiringWebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 15);
		initPage();
	}

	public void initPage() {
		PageFactory.initElements(driver, this);
	}



}
