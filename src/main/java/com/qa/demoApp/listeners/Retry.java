package com.qa.demoApp.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	private int count = 0;
	private int maxCount = 4;

	@Override
	public boolean retry(ITestResult result) {
		if(!(result.isSuccess())) {
			if(count<maxCount) {
				count++;
				result.setStatus(result.FAILURE);
				return true;
			}
			else {
				result.setStatus(result.FAILURE);
			}
		}
		else {
			result.setStatus(result.SUCCESS);
		}
		
		return false;
	}

}
