package com.qa.demoApp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.demoApp.utils.ElementUtils;
import static com.qa.demoApp.constants.AppConstants.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtils eUtil;
	private Map<String, String> productMap;

	private final By productTitle = By.tagName("h1");
	private final By productThumnails = By.cssSelector("ul[class='thumbnails']>li");
	private final By productPrice = By.xpath("(//h2)[2]");
	private final By prdouctMetaData = By.xpath("//li[contains(text(),'Brand:')]/../li");
	private final By productPriceData = By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[2]//li");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtils(driver);
	}

	public String getProductTitle() {
		return eUtil.getElementText(productTitle);
	}

	public int getProductThumbnailsCount() {
		return eUtil.waitForAllElementsVisible(productThumnails, SHORT_DEFAULT_TIME).size();
	}

	public String getProductPrice() {
		return eUtil.getElementText(productPrice);
	}
	
	public Map<String, String> getProductDetails() {
		productMap = new TreeMap<String,String>();
		//productMap = new LinkedHashMap<String,String>();
		//productMap = new LinkedHashMap<String,String>();
		productMap.put("productTitle", getProductTitle());
		productMap.put("productThumbnails", String.valueOf(getProductThumbnailsCount()));
		getProductMetaData();
		getProductPriceData();
		System.out.println("Product Details: "+productMap);
		return productMap;
		
	}

	private void getProductMetaData() {
		List<WebElement> elements = eUtil.waitForAllElementsVisible(prdouctMetaData, SHORT_DEFAULT_TIME);
		for (WebElement e : elements) {
			String metaData = e.getText();
			String metaKey = metaData.split(":")[0].trim();
			String metaValue = metaData.split(":")[1].trim();
			productMap.put(metaKey, metaValue);

		}

	}

	private void getProductPriceData() {
		List<WebElement> elements = eUtil.waitForAllElementsVisible(productPriceData, SHORT_DEFAULT_TIME);
		String productPrice = elements.get(0).getText().trim();
		String extTaxPrice = elements.get(1).getText().split(":")[1].trim();
		productMap.put("productPrice", productPrice);
		productMap.put("extTaxPrice", extTaxPrice);
	}

}
