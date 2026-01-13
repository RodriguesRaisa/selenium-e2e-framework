package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.ElementUtil;
import static com.qa.opencart.constants.AppConstants.*;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//private locators:
	private final By searchBox = By.xpath("//input[@placeholder='Search']");
	private final By searchBtn = By.xpath("//i[contains(@class,'fa-search')]");
	public static Logger log = LogManager.getLogger(AccountsPage.class);
	
	public AccountsPage(WebDriver driver) {
		
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccountPageTitle() {
		
		return eleUtil.waitForTitleIs(HOME_PAGE_TITLE,DEFAULT_TIMEOUT);
		
	}

	public String getAccountPageURL() {
		
		return eleUtil.waitForUrlPresence(HOMEPAGE_PAGE_URL, DEFAULT_TIMEOUT);
		
	}
	
	public SearchResultsPage doSearchProduct(String searchValue)
	{
		eleUtil.doSendKeys(searchBox,searchValue);
		eleUtil.doClick(searchBtn);
		return new SearchResultsPage(driver);
	}
	
	
}
