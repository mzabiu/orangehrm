package com.orangehrm.testcases.login;

import org.testng.annotations.Test;

import com.orangehrm.pages.login.LoginPage;
import com.orangehrm.testcases.BaseTest;

public class LoginTestCase extends BaseTest {

	@Test
	public void checkLogin() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToHrm();
		loginPage.logout();

	}

}
