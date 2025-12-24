package com.qa.demoApp.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.demoApp.utils.ElementUtils;

import io.qameta.allure.Step;

import static com.qa.demoApp.constants.AppConstants.*;

import java.util.ArrayList;
import java.util.List;

public class AccountPage {

	private static Logger log = LogManager.getLogger(AccountPage.class);

	private WebDriver driver;
	private ElementUtils eUtil;

	private By acctPageHeaders = By.cssSelector("div[id='content'] h3");
	private By searchProduct = By.cssSelector("input[placeholder='Search1']");
	private By searchProductButton = By.cssSelector("div[id='search'] button");

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtils(driver);
	}

	@Step("Get Account Page Title")
	public String getAccountPageTitle() {
		return eUtil.waitForTitleContains(ACCOUNT_PAGE_TITLE, SHORT_DEFAULT_TIME);
	}

	@Step("Get Account Page Url")
	public String getAccountPageUrl() {
		return eUtil.waitForUrlContains(ACCOUNT_PAGE_FRACTION_URL, SHORT_DEFAULT_TIME);
	}

	@Step("Get Account Page Headers")
	public List<String> getAccountHeadersInfo() {
		List<WebElement> elements = eUtil.getElementsList(acctPageHeaders);
		List<String> actualHeadersInfo = new ArrayList<String>();
		for (WebElement e : elements) {
			String text = e.getText();
			actualHeadersInfo.add(text);
		}
		log.info("Account Pag headers are " + actualHeadersInfo);
		return actualHeadersInfo;
	}

	public ProductSearchResultsPage searchProduct(String productName) {
		log.info("Search product is" + productName);
		System.out.println("Search product is " + productName);
		eUtil.sendKeysClear(searchProduct);
		eUtil.sendKeys(searchProduct, productName);
		eUtil.doClick(searchProductButton);
		return new ProductSearchResultsPage(driver);

	}
}
