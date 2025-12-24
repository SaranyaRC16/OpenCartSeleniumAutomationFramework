package com.qa.demoApp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.demoApp.utils.ElementUtils;
import static com.qa.demoApp.constants.AppConstants.*;

public class ProductSearchResultsPage {

	private WebDriver driver;
	private ElementUtils eUtil;
	
	private final By productResult = By.cssSelector("div[class='product-thumb']");

	public ProductSearchResultsPage(WebDriver driver) {

		this.driver = driver;
		eUtil = new ElementUtils(driver);

	}
	
	public int getProductSearchResultsCount() {
		int actualProductResultCount = eUtil.waitForAllElementsVisible(productResult,MEDIUM_DEFAULT_TIME).size();
		System.out.println("Search Product result count is "+actualProductResultCount);
		return actualProductResultCount;
		
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("Product Name is "+productName);
		eUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
		
	}
	
	

}
