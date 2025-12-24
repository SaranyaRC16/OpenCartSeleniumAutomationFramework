package com.qa.demoApp.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	public static final int SHORT_DEFAULT_TIME = 5;
	public static final int MEDIUM_DEFAULT_TIME = 10;
	public static final int LONG_DEFAULT_TIME = 15;
	public static final int LONGER_DEFAULT_TIME = 20;
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";
	public static final String ACCOUNT_PAGE_FRACTION_URL = "route=account/account";
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	
	
	public static final List<String> ACTUAL_ACCOUNT_PAGE_HEADERS = List.of("My Account", "My Orders","My Affiliate Account","Newsletter");

	
	//*********** TEST DATA SHEET NAME ***************
	public static final String REGISTER_SHEET_NAME = "Register";
}
