package com.orangehrm.pages.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

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
		txtPassword.sendKeys("admmin123");
		btnLogin.click();
	}

}
