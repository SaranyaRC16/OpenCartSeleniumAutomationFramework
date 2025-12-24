package com.qa.demoApp.pages;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qa.demoApp.constants.AppConstants.*;
import com.qa.demoApp.utils.ElementUtils;

public class LoginPage {

	//declaring WebDriver variable as private
	private WebDriver driver;
	private ElementUtils eleUtils;
	
	
	private static final Logger log = LogManager.getLogger(LoginPage.class);
	
	//constructor to pass driver instance to class instance variable
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtils = new ElementUtils(driver);
	}
	
	//locators
	private final By loginUsername = By.id("input-email");
	private final By loginPassword = By.id("input-password");
	private final By loginButton = By.cssSelector("input[value='Login']");
	private final By forgotPasswordLink = By.xpath("(//a[text()='Forgotten Password'])[1]");
	private final  By registerLink = By.xpath("//div[contains(@class,'list-group')]//a[text()='Register']");
	private final By LogoutLink = By.xpath("//div[contains(@class,'list-group')]//a[text()='Logout']");
	
	//page actions
	
	public String getLoginPageTitle() {
		String title = eleUtils.waitForTitleContains(LOGIN_PAGE_TITLE, SHORT_DEFAULT_TIME);
		log.info("Page title is "+title);
		//System.out.println("Page Title is "+title);	
		return title;
	}
	
	public String getLoginPageUrl() {
		String currentUrl = eleUtils.waitForUrlContains(LOGIN_PAGE_FRACTION_URL, SHORT_DEFAULT_TIME);
		log.info("Page Url is "+currentUrl);
		//System.out.println("Page url is "+currentUrl);
		return currentUrl;
	}
	
	public Boolean isForgotPasswordLinkExists()
	{
		return eleUtils.isElementDisplayed(forgotPasswordLink, SHORT_DEFAULT_TIME);
	}
	
	public AccountPage performLogin(String username, String password) {
		log.info("Login Credentials is "+username +" : "+password);
		//System.out.println("Login Credentials "+username + ":" +password);
		eleUtils.sendKeysWithWait(loginUsername, SHORT_DEFAULT_TIME, username);
		eleUtils.sendKeys(loginPassword, password);
		eleUtils.doClick(loginButton);
		return new AccountPage(driver);
		
	}
	
	public RegistrationPage navigateToRegisterPage() {
		eleUtils.clickWhenReady(registerLink, SHORT_DEFAULT_TIME);
		return new RegistrationPage(driver);
		
	}
	
	public Boolean logoutApplication() {
		eleUtils.clickWhenReady(LogoutLink, SHORT_DEFAULT_TIME);
		if(eleUtils.isElementDisplayed(registerLink, MEDIUM_DEFAULT_TIME)) {
			return true;
		}
		else {
			return false;
		}
		
	}

}
