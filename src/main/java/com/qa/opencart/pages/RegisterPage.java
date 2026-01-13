package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.StringUtils;

import static com.qa.opencart.constants.AppConstants.*;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//private final By locators
	private final By firstNameText = By.id("input-firstname");
	private final By lastNameText = By.id("input-lastname");
	private final By emailText = By.id("input-email");
	private final By telephoneText = By.id("input-telephone");
	private final By passwordText = By.id("input-password");
	private final By passwordConfirmText = By.id("input-confirm");
	private final By subscribeToNewsletterYes = By.xpath("//label[@class='radio-inline']/input[@type='radio' and @value='1']");
	private final By subscribeToNewsletterNo = By.xpath("//label[@class='radio-inline']/input[@type='radio' and @value='0']");
	private final By agreecheckBox = By.xpath("//input[@type='checkbox']");
	private final By continueBtn = By.xpath("//input[@value='Continue']");
	private final By logoutLink = By.linkText("Logout");
	private final By registerLink = By.linkText("Register");	
	private final By successMesg = By.xpath("//div[@id='content']/h1");
	
	//public constructor
	
	public RegisterPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//public action/methods
	public boolean fillRegisterForm(String firstName,String lastName,String telephone,String password,String subscribe)
	{
		eleUtil.waitForElementVisible(firstNameText, DEFAULT_TIMEOUT).sendKeys(firstName);
		eleUtil.doSendKeys(lastNameText,lastName);
		eleUtil.doSendKeys(emailText,StringUtils.getRandomEmailId());
		eleUtil.doSendKeys(telephoneText,telephone);
		eleUtil.doSendKeys(passwordText,password);
		eleUtil.doSendKeys(passwordConfirmText,password);
		if(subscribe.equalsIgnoreCase("yes"))
		{
			eleUtil.doClick(subscribeToNewsletterYes);
		}
		else
		{
			eleUtil.doClick(subscribeToNewsletterNo);
		}
		
		eleUtil.doClick(agreecheckBox);
		
		eleUtil.doClick(continueBtn);
		eleUtil.waitForElementVisible(successMesg, DEFAULT_TIMEOUT);
		String eleText = eleUtil.getElementText(successMesg);
		if(eleText.contains(REGISTER_SUCCESS_MESSAGE))
		{
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}

			return false;
		
	}

}
