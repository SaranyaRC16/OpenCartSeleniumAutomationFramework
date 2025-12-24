package com.qa.demoApp.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.demoApp.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.qa.demoApp.constants.AppConstants.*;

import java.util.List;

@Feature(value="Demo QA Accounts Page")
@Epic(value="Accounts Page functionality")
@Story(value="Accounts verification")
public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void loginToApplication() {
		acctPage = loginPage.performLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	
	@Description("Verify Account Page Title")
	@Owner("Sarah")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void acctPageTitleTest() {
		String acctPageTitle = acctPage.getAccountPageTitle();
		Assert.assertEquals(acctPageTitle, ACCOUNT_PAGE_TITLE);
	}

	@Description("Verify Account Page Url")
	@Owner("Sarah")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void acctPageURLTest() {
		String acctPageUrl = acctPage.getAccountPageUrl();
		Assert.assertTrue(acctPageUrl.contains(ACCOUNT_PAGE_FRACTION_URL));
	}

	@Description("Verify Account Page Headers")
	@Owner("Sarah")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void acctPageHeadersVerificationTest() {
		List<String> expectedAcctHeadersInfo = acctPage.getAccountHeadersInfo();
		Assert.assertEquals(expectedAcctHeadersInfo, ACTUAL_ACCOUNT_PAGE_HEADERS);
	}

}
