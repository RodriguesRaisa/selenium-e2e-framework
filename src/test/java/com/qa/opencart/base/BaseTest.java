package com.qa.opencart.base;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opencart.utils.LogUtil;

//@Listeners(ChainTestListener.class)
public class BaseTest {
	
	//precondition and postcondition
	
	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage loginpage;//protected because it can be used in child class such LoginPageTest
	protected AccountsPage accountspage;
	protected SearchResultsPage resultspage;
	protected ProductInfoPage productinfopage;
	protected RegisterPage registerpage;
	
	public static Logger log = LogManager.getLogger(BaseTest.class);
	
	//to read parameter from testng
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) {
		
		df = new DriverFactory();
		prop =  df.initProp();
		if(browserName !=null)
		{
			prop.setProperty("browser", browserName);
		}
		
		driver =  df.initDriver(prop);
		loginpage = new LoginPage(driver);
		
	}
	
	
	
	@AfterMethod //will be running after each after method 
	public void attachScreenshot(ITestResult result)
	{
		if(!result.isSuccess())
		{
			log.info("----screenshot is taken----");
			 ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");//embed means attach the screenshot for failed testcase
		}
		
		
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
		log.info("----closing the browser---");
	}
}
