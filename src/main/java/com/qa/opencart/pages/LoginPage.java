package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

import static com.qa.opencart.constants.AppConstants.*;

public class LoginPage {
	
	//Every page class will have its own private driver & element util
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1. private By locators. Declare final so that no one can update the locators. Not static else it will prevent parallel execution
	private final By email = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//input[contains(@class,'btn-primary')]");
	private final By forgotPasswordLink = By.linkText("Forgotten Password");
	private final By registerLink = By.xpath("//div/a[text()='Register']");
	public static Logger log = LogManager.getLogger(LoginPage.class);
	
	//2. public page constructor
	//if i want to supply driver to this class , then create constructor
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
		
	
	//3. public page actions/methods
	@Step("getting login page title")
	public String getLoginPageTitle() {
		
		//String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE,AppConstants.DEFAULT_TIMEOUT);
		//String title = driver.getTitle();
		String title = eleUtil.waitForTitleIs(LOGIN_PAGE_TITLE,DEFAULT_TIMEOUT);
		//System.out.println("login page title: "+title);
		log.info("login page title: "+title);
		return title;	
	}
	
	@Step("getting login page url")
	public String getLoginPageUrl() {
		
		String url = eleUtil.waitForUrlPresence(LOGIN_PAGE_URL,DEFAULT_TIMEOUT);
		//String url = driver.getCurrentUrl();
		//System.out.println("login page url is : "+url);
		log.info("login page url is : "+url);
		return url;
	}
	
	@Step("checking forgot pwd link exist")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.isElementDisplayed(forgotPasswordLink);
		//return driver.findElement(forgotPasswordLink).isDisplayed();
	}
	
	@Step("navigating to register page")
	public RegisterPage navigateToRegisterPage()
	{
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	@Step("loggin with valid username:{0} and password:{1}")
	public AccountsPage doLogin(String username,String pwd) {
		
		//System.out.println("User credentials for:"+username+":"+pwd);	
		log.info("User credentials for:"+username+":"+pwd);
		eleUtil.waitForElementVisible(email,DEFAULT_TIMEOUT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		//Whenever we are clicking and its navigating to next page , 
		//then its this method's responsibility to return the next page class object
		
		return new AccountsPage(driver);
		
		//driver.findElement(email).sendKeys(username);
		//driver.findElement(password).sendKeys(pwd);
		//driver.findElement(loginBtn).click();
		//String pageTitle = driver.getTitle();
//		String pageTitle = eleUtil.waitForTitleIs(HOME_PAGE_TITLE,DEFAULT_TIMEOUT);
//		System.out.println("Home page title is "+pageTitle);
//		return pageTitle;

	}
	
	

	//instaead of using AppConstants.variablename everywhere in this class we will use import static packagename.AppConstants.*;
	//means import all static variables from this class without creating the object
}
