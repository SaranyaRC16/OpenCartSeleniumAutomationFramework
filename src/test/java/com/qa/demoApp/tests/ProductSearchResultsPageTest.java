package com.qa.demoApp.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.demoApp.base.BaseTest;

public class ProductSearchResultsPageTest extends BaseTest {

	@BeforeClass
	public void loginToApplication() {
		acctPage = loginPage.performLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void verifySearchProductResultCountTest() {
		searchPage = acctPage.searchProduct("macbook");
		int actualProductResultsCount = searchPage.getProductSearchResultsCount();
		Assert.assertEquals(actualProductResultsCount, 3);
		
	}

	
	
}
