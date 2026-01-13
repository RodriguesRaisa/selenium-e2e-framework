package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import static com.qa.opencart.constants.AppConstants.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//private By Locators
	private final By productHeader = By.tagName("h1");
	private final By productImages = By.xpath("//li/a[@class='thumbnail']");
	private final By productDetails = By.xpath("//div[@class='col-sm-4']//ul[1][@class='list-unstyled']/li");
	private final By productPriceData = By.xpath("//div[@class='col-sm-4']//ul[2][@class='list-unstyled']/li");
	
	private Map<String,String> productMap;
	
	public ProductInfoPage(WebDriver driver) {
		
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//public actions/methods
	
	public String getProductHeader()
	{
		String header =  eleUtil.waitForElementVisible(productHeader,DEFAULT_TIMEOUT).getText();
		System.out.println("Header of Product Info page is :"+header);
		return header;
	}
	
	public int getProductImagesCount()
	{
		int productImagesCount = eleUtil.waitForAllElementsVisibility(productImages, DEFAULT_TIMEOUT).size();
		System.out.println("Number of Product images are :"+productImagesCount);
		return productImagesCount;
		
	}
	
	public Map<String, String> getProductMap()
	{
		//productMap =  new HashMap<String,String>();//This give unordered list
		//Hence we will use LinkedHashMap to maintain the insertion order, gives ordered list in which it was ordered:
		//productMap =  new LinkedHashMap<String,String>();
		//To sort the keys: We will use TreeMap
		productMap =  new TreeMap<String,String>();
		productMap.put("product header", getProductHeader());
		productMap.put("product images", String.valueOf(getProductImagesCount()));
		getPricingDetails();
		getProductPriceData();
		System.out.println("The entire product data: "+productMap);
		return productMap;
		
	}
	
	private void getPricingDetails()
	{
		//productMap = new HashMap<String,String>();
		List<WebElement> productDetailsKey = eleUtil.getElements(productDetails);
		for(WebElement p :productDetailsKey)
		{
			String productData = p.getText();
			String product[] = productData.split(":");
			String productKey = product[0];
			String productValue = product[1].trim();
			productMap.put(productKey, productValue);
		}
	}
	
	private void getProductPriceData()
	{
		List<WebElement> productPriceList = eleUtil.getElements(productPriceData);
		String productPrice = productPriceList.get(0).getText();
		String productPriceExTax = productPriceList.get(1).getText().split(":")[1].trim();
		int productpriceExTax = Integer.parseInt(productPriceExTax.replace("$", "").replace(",", "").split("\\.")[0]);
		productMap.put("productprice", productPrice);
		productMap.put("productextaxprice", String.valueOf(productpriceExTax));
	}
	

}
