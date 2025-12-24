package com.qa.demoApp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.demoApp.base.BaseTest;


public class ProductInfoTest extends BaseTest{
	
	@BeforeClass
	public void loginToApplication() {
		acctPage = loginPage.performLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@DataProvider
	public Object[][] getProductTitleTestData(){
		return new Object[][]{{"macbook","MacBook"},
			{"macbook","MacBook Air"},
			{"macbook","MacBook Pro"},
			{"iphone","iPhone"}};
	}
		
	@Test(dataProvider="getProductTitleTestData")
	public void verifyProductTitleTest(String searchKey, String productName) {
		searchPage = acctPage.searchProduct(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		String actualProductTitle = productInfoPage.getProductTitle();
		Assert.assertEquals(actualProductTitle, productName);
	}
	
	@DataProvider
	public Object[][] getProductPriceTestData(){
		return new Object[][]{{"macbook","MacBook","$602.00"},
			{"macbook","MacBook Air","$1,202.00"},
			{"macbook","MacBook Pro","$2,000.00"},
			{"iphone","iPhone","$123.20"}};
	}
	
	@Test(dataProvider="getProductPriceTestData")
	public void verifyProductPriceTest(String searchKey, String productName, String productPrice) {
		searchPage = acctPage.searchProduct(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		String actualProductPrice = productInfoPage.getProductPrice();
		Assert.assertEquals(actualProductPrice, productPrice);
	}
	
	

	@Test
	public void verifyProductThumbnailsCountTest() {
		searchPage = acctPage.searchProduct("macbook");
		productInfoPage = searchPage.selectProduct("MacBook Air");
		int productThumbnailsCount = productInfoPage.getProductThumbnailsCount();
		Assert.assertEquals(productThumbnailsCount, 4);
	}
	
	@Test
	public void verifyProductDetailsTest() {
		searchPage = acctPage.searchProduct("macbook");
		productInfoPage = searchPage.selectProduct("MacBook Air");
		Map<String,String> productDetails = productInfoPage.getProductDetails();
		SoftAssert sAssert = new SoftAssert();
		sAssert.assertEquals(productDetails.get("Brand"), "Apple");
		sAssert.assertEquals(productDetails.get("Product Code"), "Product 17");
		sAssert.assertEquals(productDetails.get("Reward Points"), "700");
		sAssert.assertEquals(productDetails.get("Availability"), "Out Of Stock");
		sAssert.assertEquals(productDetails.get("productPrice"), "$1,202.00");
		sAssert.assertEquals(productDetails.get("extTaxPrice"), "$1,000.00");
		sAssert.assertEquals(productDetails.get("productTitle"), "MacBook Air");
		sAssert.assertEquals(productDetails.get("productThumbnails"), "4");
		sAssert.assertAll();
		
		
		
	}

}
