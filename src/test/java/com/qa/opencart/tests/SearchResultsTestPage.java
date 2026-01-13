package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchResultsTestPage extends BaseTest{
	
	@BeforeClass
	public void searchSetup()
	{
		accountspage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test
	public void verifyProductSearchResultsCount()
	{
		resultspage = accountspage.doSearchProduct("macbook");
		int productsCount = resultspage.getSearchedProductResultsCount();
		Assert.assertEquals(productsCount, 3);
	}
	
	

}
