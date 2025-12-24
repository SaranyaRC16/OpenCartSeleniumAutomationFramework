package com.qa.demoApp.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.demoApp.Factory.DriverFactory;
import com.qa.demoApp.pages.AccountPage;
import com.qa.demoApp.pages.LoginPage;
import com.qa.demoApp.pages.ProductInfoPage;
import com.qa.demoApp.pages.ProductSearchResultsPage;
import com.qa.demoApp.pages.RegistrationPage;

public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
    protected LoginPage loginPage;
    protected Properties prop;
    protected AccountPage acctPage;
    protected ProductSearchResultsPage searchPage;
    protected ProductInfoPage productInfoPage;
    protected RegistrationPage registrationPage;
	
	@BeforeTest
	public void setUp() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.launchApplication(prop); //call By Reference - Passing Object as a value
		loginPage = new LoginPage(driver);
		
	}
	
	@AfterMethod
	public void attachScreenshot(ITestResult result) {
	 
		if(!result.isSuccess()) {
			ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		}
		
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
