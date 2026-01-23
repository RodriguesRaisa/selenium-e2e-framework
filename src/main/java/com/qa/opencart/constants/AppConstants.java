package com.qa.opencart.constants;

public class AppConstants {
	
	//final variables should be in uppercase with _
	//making it static and public because i dont want others to create object of this class AppConstants to access variables
	public static final String LOGIN_PAGE_TITLE = "Account Login";	
	public static final String HOME_PAGE_TITLE = "My Account";
	public static final String LOGIN_PAGE_URL = "route=account/login";
	public static final String HOMEPAGE_PAGE_URL = "route=account/account";
	public static final String REGISTER_SUCCESS_MESSAGE = "Your Account Has Been Created!";
	
	public static final int DEFAULT_TIMEOUT = 5;
	public static final int MEDIUM_DEFAULT_TIMEOUT = 10;
	public static final int LONG_DEFAULT_TIMEOUT = 15;
	
	//Sheet Name Excel File
	public static final String REGISTER_SHEET_NAME = "register";
	

}
