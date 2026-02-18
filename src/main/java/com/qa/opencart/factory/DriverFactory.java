package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import com.qa.opencart.exception.BrowserException;
import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;//class instance. Properties is a class in java
	OptionsManager optionsMgr;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	//ThreadLocal --> for every thread create a local copy of webdriver
	
	public static Logger log = LogManager.getLogger(DriverFactory.class);
	//warning,error,info
	
	/**
	 * This method is used to initialize the driver based on the given browser Name
	 * @param browserName
	 * @return 
	 */
	
	public WebDriver initDriver(Properties prop) {
		
		log.info("properties: "+prop);
		
		String browserName = prop.getProperty("browser");
		String appUrl = prop.getProperty("url");
		optionsMgr = new OptionsManager(prop);
		
		//System.out.println("browser name is "+browserName);
		log.info("browser name is : "+browserName);
		
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			
			tlDriver.set(new ChromeDriver(optionsMgr.getChromeOptions()));
			//driver = new ChromeDriver(optionsMgr.getChromeOptions());
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(optionsMgr.getFirefoxOptions()));
			//driver = new FirefoxDriver(optionsMgr.getFirefoxOptions());
			break;
		case "safari":
			tlDriver.set(new SafariDriver());
			//driver = new SafariDriver();
			break;
		default:
			//System.out.println("please pass the right browser name");
			log.error("please pass the right browser name..."+browserName);
			throw  new BrowserException("===INVALID BROWSER===");
	
		}
		//driver.get(appUrl);
		getDriver().get(appUrl);
		//driver.manage().window().maximize();
		getDriver().manage().window().maximize();
		//driver.manage().deleteAllCookies();
		getDriver().manage().deleteAllCookies();
		return getDriver();
	}
	
	/**
	 * get the local copy of the driver
	 * @return 
	 */
	public static WebDriver getDriver()
	{
		return tlDriver.get();
	}
	
	
/**
 * to read property from config.prperties file
 * @return
 */
	//mvn clean install -Denv="qa"
	public Properties initProp()
	{
		prop = new Properties();
		FileInputStream ip = null;
		
		String envName = System.getProperty("env");
		//System.out.println("Running tests on env: "+envName);
		log.info("Running tests on env: "+envName);
		
		try {
			
		if(envName == null)
		{
			//System.out.println("env is null, hence running tests on QA env...");
			log.warn("env is null, hence running tests on QA env...");
			ip = new FileInputStream("./src/test/resources/config/config.properties");
		}
		else
		{
			System.out.println("Running tests on env: "+envName);
			switch (envName.toLowerCase().trim()) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			case "dev":
				ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			case "stage":
				ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
			case "uat":
				ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
				break;
			default:
				log.error("----invalid env name----"+envName);
				throw new FrameworkException("===INVALID ENV NAME==="+envName);
				
			}
		}
		}
		 catch (FileNotFoundException e)
			{
				
				e.printStackTrace();
			}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		return prop;
		
//		prop = new Properties();//this prop will be used in other methods also . Hence delcared at class level
//		try {
//			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties"); //we dont need it globally hence initialized in this method only
//			prop.load(ip);//this prop will load all the properties from FileInputStream
//		} catch (FileNotFoundException e) {
			
//			e.printStackTrace();
//		}
//		catch(IOException e)
//		{
//			e.printStackTrace();
//		}
		
		
	}
	
	
	public static File getScreenshotFile()
	{
		TakesScreenshot ts = (TakesScreenshot) getDriver();
		File file = ts.getScreenshotAs(OutputType.FILE);//temp dir
		return file;
	}
}
