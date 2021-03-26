package com.orangehrm.pages.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.orangehrm.pages.BasePage;

public class LoginPage extends BasePage {

	public LoginPage(EventFiringWebDriver driver) {
		super(driver);
	}

	@FindBy(id = "txtUsername")
	private WebElement txtUserName;

	@FindBy(id = "txtPassword")
	private WebElement txtPassword;

	@FindBy(id = "btnLogin")
	private WebElement btnLogin;

	public void loginToHrm() {
		txtUserName.sendKeys("Admin");
		txtPassword.sendKeys("admin123");
		btnLogin.click();
	}

	public void returnToMainPage() {
		driver.switchTo().defaultContent();
		
		linkDashboard.click();

		wait.until(ExpectedConditions.visibilityOf(lblDashboard));
	}

	public void logout() {
		wait.until(ExpectedConditions.visibilityOf(linkWelcome));
		validator.assertEquals(linkWelcome.isDisplayed(), true);
		linkWelcome.click();
		wait.until(ExpectedConditions.visibilityOf(linkLogout));
		validator.assertEquals(linkLogout.isDisplayed(), true);
		linkLogout.click();
	}

}
