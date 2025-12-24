package com.qa.demoApp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.demoApp.utils.ElementUtils;
import com.qa.demoApp.utils.StringUtils;
import com.qa.demoApp.constants.AppConstants;
import static com.qa.demoApp.constants.AppConstants.*;

public class RegistrationPage {
	
	private WebDriver driver;
	private ElementUtils eUtils;
	
	private final By firstName =  By.id("input-firstname");
	private final By lastName =  By.id("input-lastname");
	private final By email  =  By.id("input-email");
	private final By telephone =  By.id("input-telephone");
	private final By password  =  By.id("input-password");
	private final By confirmPassword =  By.id("input-confirm");
	private final By subscribeYes =  By.xpath("//label[contains(.,'Yes')]/input");
	private final By subscribeNo = By.xpath("//label[contains(.,'No')]/input");
	private final By agreeTerms = By.cssSelector("input[name='agree']");
	private final By continueButton = By.cssSelector("input[type='submit']");
	private final By registrationSuccessMsg = By.xpath("//h1[text()='Your Account Has Been Created!']");
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eUtils = new ElementUtils(driver);
	}
	
	
	public String doUserRegistration(String firstName, String lastName, String telephone, String password, String subscribe) {
		eUtils.sendKeysWithWait(this.firstName, SHORT_DEFAULT_TIME, firstName);
		eUtils.sendKeys(this.lastName, lastName);
		eUtils.sendKeys(this.email, StringUtils.getRandomEmail());
		eUtils.sendKeys(this.telephone, telephone);
		eUtils.sendKeys(this.password, password);
		eUtils.sendKeys(this.confirmPassword, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eUtils.doClick(subscribeYes);
		}
		else {
			eUtils.doClick(subscribeNo);
		}
		
		eUtils.doClick(agreeTerms);
		eUtils.doClick(continueButton);
		return eUtils.waitForElementVisible(registrationSuccessMsg, MEDIUM_DEFAULT_TIME).getText();
	   		
	}

}
