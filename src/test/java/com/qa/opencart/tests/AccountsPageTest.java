package com.qa.opencart.tests;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.qa.opencart.constants.AppConstants.*;
import com.qa.opencart.base.BaseTest;

public class AccountsPageTest extends BaseTest{
	
	//Precondition for AccountsPageTest is LoginPageTest do login and come to this page
	
	@BeforeClass
	public void accountPageSetup() {
		
		accountspage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void accountPageTitleTest() {
		
		String accountsPageTitle = accountspage.getAccountPageTitle();
		System.out.println("Home page title is :"+accountsPageTitle);
		Assert.assertEquals(accountsPageTitle, HOME_PAGE_TITLE);
	}
	
	@Test
	public void accountPageURLTest()
	{
		String homePageURL = accountspage.getAccountPageURL();
		System.out.println("Home page url contains :"+homePageURL);
		Assert.assertTrue(homePageURL.contains(HOMEPAGE_PAGE_URL));
	}

}
