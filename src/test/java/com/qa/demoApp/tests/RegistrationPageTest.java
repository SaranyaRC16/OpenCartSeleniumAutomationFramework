package com.qa.demoApp.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.demoApp.base.BaseTest;
import com.qa.demoApp.constants.AppConstants;
import com.qa.demoApp.utils.ExcelUtils;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeMethod
	public void registrationSetup() {
		registrationPage = loginPage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getUserRegisterTestData() {
		return new Object[][] { 
			{"Sarah","C","8787878787","sarah@123","Yes"},
			{"Diya","C","8787878777","diya@123","No"},
			{"Viva","C","8787878999","viva@123","Yes"}};
	}
	
	@DataProvider
	public Object[][] getUserRegisterData() {
		Object[][] data = ExcelUtils.getTestDataFromExcel(AppConstants.REGISTER_SHEET_NAME);
		return data;
	}
	@Test(dataProvider="getUserRegisterData")
	public void verifyUserRegistrationTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		String registerSuccessMsg = registrationPage.doUserRegistration(firstName, lastName, telephone, password, subscribe);
	    Assert.assertEquals(registerSuccessMsg, "Your Account Has Been Created!");
	    Assert.assertTrue(loginPage.logoutApplication());	
	}
	

}
