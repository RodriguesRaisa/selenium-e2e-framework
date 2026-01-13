package com.qa.opencart.tests;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.qa.opencart.constants.AppConstants.*;

@Feature("Feature 50: OpenCart App Feature")
@Epic("Epic 100: design login page for open cart application")
@Story("User Story 101: implement loginpage for opencart app")
public class LoginPageTest extends BaseTest{

	@Description("checking open cart login page title..")
	@Severity(SeverityLevel.MINOR)
	@Owner("Raisa")
	@Issue("JIRA-123")
	@Test(description="check login page title")
	public void loginPageTitleTest()
	{
		
		String actualTitle = loginpage.getLoginPageTitle();
//		String expectedTitle = "Account Login";
		 //log
		ChainTestListener.log("Checking loginpage title"+actualTitle);
		Assert.assertEquals(actualTitle, LOGIN_PAGE_TITLE);		
	}
	
	@Test(description="check login page url")
	public void loginPageUrlTest()
	{
		String actualUrl = loginpage.getLoginPageUrl();
		//String expectedUrl = "route=account/login";
		Assert.assertTrue(actualUrl.contains(LOGIN_PAGE_URL));		
	}
	
	@Test(description="check forgot password link functionality")
	public void forgotPasswordLinkExistTest()
	{
		Assert.assertTrue(loginpage.isForgotPwdLinkExist());
			
	}
	
	@Test(priority=Short.MAX_VALUE,description="login with valid credentials") //To make the this test run last
	public void doLoginTest()
	{
		accountspage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertEquals(accountspage.getAccountPageTitle(),HOME_PAGE_TITLE);
	}
	

	//This test is disabled so it will not be considered in testng execution
	@Test(enabled=false,description="work in progress-forgot password check")
	public void forgotPassword()
	{
		
			System.out.println("test creation is still in progress");
	}
	
}
