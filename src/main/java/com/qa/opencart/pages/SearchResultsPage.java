package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import static com.qa.opencart.constants.AppConstants.*;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//private locators
	private final By results = By.xpath("//div[@class='product-thumb']");
	
	//public constructor
	public SearchResultsPage(WebDriver driver) {
		
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//public action/methods
	
	public int getSearchedProductResultsCount()
	{
		List<WebElement> productList = eleUtil.waitForAllElementsVisibility(results,DEFAULT_TIMEOUT);
		int searchCount = productList.size();
		System.out.println("Total number of products available are:"+searchCount);
		return searchCount;
		
	}
	
	public ProductInfoPage selectProduct(String productName)
	{
		eleUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}
}
