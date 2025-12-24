package com.qa.demoApp.Factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.demoApp.exceptions.BrowserExceptions;

public class DriverFactory {
	
	static WebDriver driver;
	Properties prop;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	/**
	 * This method is used to initialize the browser according to the browser name.
	 * 
	 * @param browser value cane be chrome, firefox, edge and safari.
	 */
	public void initDriver(String browser) {
		System.out.println("Browser name: " + browser);

		switch (browser.toLowerCase().trim()) {
		case "chrome":
			tlDriver.set(new ChromeDriver());
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver());
			break;
		case "edge":
			tlDriver.set(new EdgeDriver());
			break;
		case "safari":
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println("Please pass the right browser name");
			throw new BrowserExceptions("=====INVALID BROWSER=====");

		}
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public WebDriver launchApplication(Properties prop) {
		String browser = prop.getProperty("browser");
		String url = prop.getProperty("url");
		initDriver(browser);
		getDriver().get(url);
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();
	}

	

	/**
	 * This is a method to load the config properties file data
	 * @return
	 */
	
	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	/**
	 * take screenshot
	 * @return 
	 */

	public static String takeScreenshot() {
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir") + File.separator + "\\screenshot"+File.separator+"screenshot_"+System.currentTimeMillis()+".png";
		File destination = new File(filePath);
		
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filePath;
	}
	
	public static byte[] getScreenshotFile() {
		return ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES);
	}
	
	
}
