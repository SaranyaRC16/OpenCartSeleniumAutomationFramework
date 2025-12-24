package com.qa.demoApp.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.demoApp.base.BaseTest;
import static com.qa.demoApp.constants.AppConstants.*;

public class LoginPageTest extends BaseTest{
	
	@Test (priority = 1)
	public void loginPageTitleVerification() {
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, LOGIN_PAGE_TITLE);
	}
	
	@Test (priority = 2)
	public void loginPageUrlVerification() {
		String actualUrl = loginPage.getLoginPageUrl();
		Assert.assertTrue(actualUrl.contains(LOGIN_PAGE_FRACTION_URL));
	}
	
	
	@Test (priority = 3)
	public void forgotPasswordLinkExists() {
		Assert.assertTrue(loginPage.isForgotPasswordLinkExists());
	}
	
	@Test (priority = 4)
	public void login() throws InterruptedException {
		acctPage = loginPage.performLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(acctPage.getAccountPageTitle(),ACCOUNT_PAGE_TITLE);
	}
	

}
