package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CSVUtil;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void productInfoSetup()
	{
		accountspage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	//creating method to provide data to other methods
	//in testng , dataprovider method will always return 2 dimension object array
	//2D because excel sheet has two columns one with key and one with value
	//1D is only for maintaining linear data
	//maintain dataprovider in same testclass where its used so that its easy to maintain the data
	
//	@DataProvider
//	public Object[][] getProductTestData()
//	{
//		return new Object[][] {
//			//row number 1
//			{"macbook","MacBook Pro"},
//			{"macbook","MacBook Air"},
//			{"imac","iMac"},
//			{"samsung","Samsung SyncMaster 941BW"},
//			{"samsung","Samsung Galaxy Tab 10.1"}
//		};
//	}
	
	@DataProvider
	public Object[][] getProductCSVData()
	{
		return CSVUtil.csvData("product");
	}
	
	@Test(dataProvider="getProductCSVData")
	public void verifyProductImagesCountViaCSV(String searchKey,String productName,String imagesCount)
	{
		resultspage = accountspage.doSearchProduct(searchKey);
		productinfopage = resultspage.selectProduct(productName);
		int actImagesCount=productinfopage.getProductImagesCount();
		Assert.assertEquals(String.valueOf(actImagesCount), imagesCount);
	}
	
//	@Test(dataProvider="getProductTestData")
//	//To hold data in following method we will pass parameters
//	public void verifyProductHeader(String searchKey,String productName)
//	{
//		//resultspage = accountspage.doSearchProduct("macbook");
////		productinfopage = resultspage.selectProduct("MacBook Pro");
////		Assert.assertEquals(actualProductHeader,"MacBook Pro");
//		resultspage = accountspage.doSearchProduct(searchKey);
//		productinfopage = resultspage.selectProduct(productName);
//		String actualProductHeader=productinfopage.getProductHeader();
//		Assert.assertEquals(actualProductHeader,productName);
//	}
//	
//	@DataProvider
//	public Object[][] getProductImagesTestData()
//	{
//		return new Object[][] {
//			//row number 1
//			{"macbook","MacBook Pro",4},
//			{"macbook","MacBook Air",4},
//			{"imac","iMac",3},
//			{"samsung","Samsung SyncMaster 941BW",1},
//			{"samsung","Samsung Galaxy Tab 10.1",7}
//		};
//	}
	
//	@Test(dataProvider="getProductImagesTestData")
//	public void verifyProductImagesCount(String searchKey,String productName,int imagesCount)
//	{
////		resultspage = accountspage.doSearchProduct("macbook");
////		productinfopage = resultspage.selectProduct("MacBook Pro");
//		resultspage = accountspage.doSearchProduct(searchKey);
//		productinfopage = resultspage.selectProduct(productName);
//		int actImagesCount=productinfopage.getProductImagesCount();
////		Assert.assertEquals(actImagesCount, 4);
//		Assert.assertEquals(actImagesCount, imagesCount);
//	}
//	
//	@Test(dataProvider="getProductTestData")
//	public void verifyProductDetails()
//	{
//		resultspage = accountspage.doSearchProduct("macbook");
//		productinfopage = resultspage.selectProduct("MacBook Pro");
//		Map<String,String> actualProductDetailsMap  = productinfopage.getProductMap();
////		Assert.assertEquals(actualProductDetailsMap.get("Brand"),"Apple");
////		Assert.assertEquals(actualProductDetailsMap.get("Product Code"),"Product 18");
////		Assert.assertEquals(actualProductDetailsMap.get("Availability"),"Out Of Stock");
////		Assert.assertEquals(actualProductDetailsMap.get("productprice"),"$2,000.00");
////		Assert.assertEquals(actualProductDetailsMap.get("productextaxprice"),"2000");
//		
//		//Creating object of SoftAssert class because its not static
//		SoftAssert softAssert = new SoftAssert();
//		softAssert.assertEquals(actualProductDetailsMap.get("Brand"),"Apple");
//		softAssert.assertEquals(actualProductDetailsMap.get("Product Code"),"Product 18");
//		softAssert.assertEquals(actualProductDetailsMap.get("Availability"),"Out Of Stock");
//		softAssert.assertEquals(actualProductDetailsMap.get("productprice"),"$2,000.00");
//		softAssert.assertEquals(actualProductDetailsMap.get("productextaxprice"),"2000");
//		softAssert.assertAll();
//		
//		//SoftAssert will not terminate the code if any of the assertion fails and it will keep on executing
//		//It is used when we have multiple assertions
//		//assert all will tell you how many assertions got failed
//		//whereas HardAssertion will terminate the program when to use --> single functionality with single assertion
//	}
	
}
