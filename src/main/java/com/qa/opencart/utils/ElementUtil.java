package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.chaintest.plugins.ChainTestListener;

import io.qameta.allure.Step;

public class ElementUtil {

	private WebDriver driver;
	private Actions action;
	private JavascriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver)
	{
		this.driver = driver;
		action = new Actions(driver);
		jsUtil = new JavascriptUtil(driver);
	}
	
	
	//creating utility so don't add static keyword for the methods
	public void doSendKeys(By locator,String value) {
			getElement(locator).clear();
		 driver.findElement(locator).sendKeys(value);
	}
	
	public void doSendKeys(String locatorType,String locatorValue,String value)
	{
		
		getElement(locatorType,locatorValue).sendKeys(value);
	}
	
	public void doClick(By locator)
	{
		getElement(locator).click();
	}
	
	public void doClick(String locatorType,String locatorValue)
	{
		getElement(locatorType,locatorValue).click();
	}
	
	public void doSendKeysCharSeq(By locator,CharSequence... value)
	{
		getElement(locator).clear();
		 driver.findElement(locator).sendKeys(value);
	}
	
	public void doSendKeysCharSeq(String locatorType,String locatorValue,CharSequence... value)
	{
		getElement(locatorType,locatorValue).sendKeys(value);
	}
	
	//Wait Utils
	
	public WebElement waitForElementPresence(By locator,int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		 
	}
	
	//overloaded
	
	public WebElement waitForElementVisible(By locator,int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		 
	}
	
	public void clickWhenReady(By locator,int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void clickWithWait(By locator,int timeout)
	{
		waitForElementVisible(locator,timeout).click();
	}

	public void sendKeysWithWait(By locator,int timeout,CharSequence... value)
	{
		waitForElementVisible(locator,timeout).sendKeys(value);
	}
	
	public Alert waitForAlert(int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		return alert;
	}
	
	public void waitForAlertAccept(int timeout)
	{
		waitForAlert(timeout).accept();
	}
	
	public void waitForAlertDismiss(int timeout)
	{
		waitForAlert(timeout).dismiss();
	}
	
	public String getAlertText(int timeout)
	{
		return waitForAlert(timeout).getText();
	}
	
	public void sendKeysAlert(int timeout,String value)
	{
		waitForAlert(timeout).sendKeys(value);;
	}
	
	//partial title if title is too long
	public String waitForTitlePresence(String expectedTitleValue,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		try 
		{
			wait.until(ExpectedConditions.titleContains(expectedTitleValue));
			return driver.getTitle();
		}
		catch(TimeoutException e)
		{
			return null;
		}
	}
	
	//small title
	public String waitForTitleIs(String expectedTitleValue,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		try 
		{
			wait.until(ExpectedConditions.titleIs(expectedTitleValue));
			return driver.getTitle();
		}
		catch(TimeoutException e)
		{
			return null;
		}
	}
	
	public String waitForUrlPresence(String expectedUrlValue,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
				
		try 
		{
			wait.until(ExpectedConditions.urlContains(expectedUrlValue));
			return driver.getCurrentUrl();
		}
		catch(TimeoutException e)
		{
			return null;
		}
		
	}
	
	public String waitForUrlToBe(String expectedUrlValue,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
				
		try 
		{
			wait.until(ExpectedConditions.urlToBe(expectedUrlValue));
			return driver.getCurrentUrl();
		}
		catch(TimeoutException e)
		{
			return null;
		}
		
	}
	
	
	public void waitForFramePresent(By locator,int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		
	}
	
	public void waitForFramePresentUsingNameID(String frameIdName,int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIdName));
		
	}
	
	public void waitForFramePresentUsingFrameIndex(String frameIndex,int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
		
	}
	
	public void waitForFramePresentUsingWebElement(WebElement frameElement,int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
		
	}
	
	public Boolean waitForWindow(int numOfWindowCount,int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		
		try
		{
		return wait.until(ExpectedConditions.numberOfWindowsToBe(numOfWindowCount));
		}
		catch(TimeoutException e)
		{
			System.out.println("Expected window count is not correct");
			return false;
		}
			
	}
	
	public List<WebElement> waitForAllElementsPresence(By locator, int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public List<WebElement> waitForAllElementsVisibility(By locator, int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		try {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		catch(TimeoutException e)
		{
			return Collections.EMPTY_LIST;
		}
	}
	
 public WebElement waitForElementVisibilityFluentWait(By Locator,int timeout,int pollingTime) {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.withMessage("Element is not found");
		
		return wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
	}
 
 public WebElement waitForElementPresentFluentWait(By Locator,int timeout,int pollingTime) {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.withMessage("Element is not found");
		
		return wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
	}
 
 public boolean isPageLoaded(int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState =='complete'")).toString();
		return Boolean.parseBoolean(flag);
	}
	
//Example:
//By firstNameLocator = By.id("input-firstname");
//	locatorType = By.id--> ID;
//	locatorValue = "input-firstname" --> firstName_ID;
	
	public By getBy(String locatorType,String locatorValue)
	{
		By locator = null;
		switch (locatorType.toUpperCase()) {
		case "ID":
			locator = By.id(locatorValue);
			break;
		case "XPATH":
			locator = By.xpath(locatorValue);
			break;
		case "CLASSNAME":
			locator = By.className(locatorValue);
			break;
		case "CSS":
			locator = By.cssSelector(locatorValue);
			break;
		case "LINKTEXT":
			locator = By.linkText(locatorValue);
			break;
		case "PARTIALLINKTEXT":
			locator = By.partialLinkText(locatorValue);
			break;
		case "TAGNAME":
			locator = By.tagName(locatorValue);
			break;
		default:
			System.out.println("Please pass the right locator type"+locatorType);
			break;
		}
		return locator;
	}
	
	@Step("entering the value {1} into the element :{0}")
	public WebElement getElement(String locatorType,String locatorValue) {
		
		return driver.findElement(getBy(locatorType,locatorValue));
	}

	@Step("finding the element using:{0}")
	public WebElement getElement(By locator)
	{
		 //log
		 ChainTestListener.log("locator:"+locator.toString());
		return driver.findElement(locator);
		
	}
	
	public List<WebElement> getElements(By locator) {
		
		return driver.findElements(locator);
	}
	
	public String getElementText(By Locator) {
		String eleText =  driver.findElement(Locator).getText();
		System.out.println("Header Text is :"+eleText); 
		return eleText;
	}
	
	public List<String> getElementTextList(By Locator)
	{
		List<WebElement> allLinks = driver.findElements(Locator);
		List<String> listOfTexts = new ArrayList<String>();
		
		for(WebElement l:allLinks)
		{
		
			String text = l.getText();
			if(text.length() != 0)
			{
				listOfTexts.add(text);
			}
			
		}
		return listOfTexts;
		
	}
	
	public String getElementDOMAttribute(By Locator, String domAttribute)
	{
		String attributeText = driver.findElement(Locator).getDomAttribute(domAttribute);
		System.out.println(attributeText);
		return attributeText;
	}
	
	public String getElementDOMProperty(By Locator,String domProperty)
	{
		String propertyText = driver.findElement(Locator).getDomProperty(domProperty);
		System.out.println(propertyText);
		return propertyText;
	}
	
	public boolean isElementDisplayed(By Locator)
	{
		try {
			Boolean isElementDisplayed =  driver.findElement(Locator).isDisplayed();
			System.out.println(isElementDisplayed);
			return isElementDisplayed;	
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			System.out.println("Element is not present on the page");
			return false;
		}
		
	}
	
	public boolean checkElementDisplayed(By locator,int count)
	{
		if(getElements(locator).size() == count)
		{
			System.out.println("Element is displayed"+count+"times");
			return true;
		}
		return false;
	}
	
	public boolean selectDropDownByText(By locator,String text)
	{
		WebElement ele = getElement(locator);
		Select select = new Select(ele);
		
		try
		{
			select.selectByVisibleText(text);
			return true;
		}
		catch(NoSuchElementException e) {
			System.out.println(text+"is present in the dropdown");
			return false;
		}
	}
	
	public boolean selectDropDownByIndex(By locator,int index)
	{
		WebElement ele = getElement(locator);
		Select select = new Select(ele);
		try {
			select.selectByIndex(index);
			return true;
		}
		catch(NoSuchElementException e) {
			System.out.println(index+"is not present in dropdown");
			return false;
		}
		
	}
	
	public boolean selectDropDownByValue(By locator,String value)
	{
		WebElement ele = getElement(locator);
		Select select = new Select(ele);
		
		try
		{
			select.selectByValue(value);
			return true;
		}
		catch(NoSuchElementException e)
		{
			System.out.println(value+"is not present in dropdown");
			return false;
		}
	}


	public boolean selectValueByOptions(By locator,String value) {
		
		WebElement selectLocator = getElement(locator);
		
		Select select = new Select(selectLocator);
		List<WebElement> selectList = select.getOptions();
		
		
		boolean flag = false;
		
		for(WebElement e:selectList)
		{
			String selectText = e.getText();
			System.out.println(e.getText());
			if(selectText.equals(value))
			{
				e.click();
				flag=true;
				break;
			}
		}
		if(flag)
		{
			System.out.println(value+"is selected");
			return true;
		}
		
		else
		{
			System.out.println(value+"is not selected");
			return false;
		}
	
	}
	
	
	public List<String> getSelectOptions(By locator)
	{
		WebElement ele = getElement(locator);
		Select select = new Select(ele);
		List<WebElement> preselectedList = select.getAllSelectedOptions();
		System.out.println(preselectedList.size());
		
		List<String> textList = new ArrayList<String>();
		for(WebElement e:preselectedList)
		{
			String text = e.getText();
			System.out.println(text);
			textList.add(text);
		}
		return textList;
	}
	
	public boolean getSelectOptions(By locator,List<String> expectedList)
	{
		WebElement ele = getElement(locator);
		Select select = new Select(ele);
		List<WebElement> preselectedList = select.getAllSelectedOptions();
		System.out.println(preselectedList.size());
		
		List<String> textList = new ArrayList<String>();
		for(WebElement e:preselectedList)
		{
			String text = e.getText();
			System.out.println(text);
			textList.add(text);
		}
		if(preselectedList.containsAll(expectedList))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public void doActionsClick(By locator) {

		action.click(getElement(locator)).perform();
	}
	
	public void doActionsSendKeys(By locator,String value) {

		action.sendKeys(getElement(locator),value).perform();
	}
	
	public void doActionSendKeysWithPause(By locator,String value,long pauseTime)
	{
		
		char val[] = value.toCharArray();
		for(char ch : val)
		{
			action.sendKeys(getElement(locator),String.valueOf(ch)).pause(pauseTime).perform();
		}
	}
	
	
	private void doMoveAction(By locator) throws InterruptedException
	{
		
		action.moveToElement(getElement(locator)).build().perform();
		Thread.sleep(2000);
	}
	
	public void doMoveToElement(By parentLocator,By childLocator) throws InterruptedException
	{

		doMoveAction(parentLocator);
		doClick(childLocator);
	}
	
	public void handle4LevelMenuHandle(By parentMenu,By childMenu1,By childMenu2,By childMenu3) throws InterruptedException
	{
		
		doClick(parentMenu);
		Thread.sleep(2000);
		
		doMoveAction(childMenu1);
		Thread.sleep(2000);
		
		doMoveAction(childMenu2);
		Thread.sleep(2000);
				
		doClick(childMenu3);
	}
	
	public void dragdropElement(By sourceLocator,By targetLocator)
	{

		action.dragAndDrop(getElement(sourceLocator), getElement(targetLocator)).perform();
	}
	
	public void doRightClickBtn(By locator)
	{

		action.contextClick(getElement(locator)).perform();
		
	}
	
	/**
	 * this method is used to select the choices with three different usecases:
	 * 1.single selection:to select single option "choice 2"
	 * 2.multi selection:to select few options "choice 1","choice 3","choice 6 2 1"
	 * 3.all selection: use "all" to select all values
	 * @param locator
	 * @param locatorList
	 * @param value
	 */
	
	public void nonselectbaseddropdown(By locator,By locatorList,String... value) {
		
		getElement(locator).click();
		
		List<WebElement> dropdownList = getElements(locatorList);
	
		System.out.println(dropdownList.size());
		
		if(value[0].equalsIgnoreCase("all"))
		{
			for(WebElement e :dropdownList)
			{
				e.click();
			}
		}
		else
		{
		
		for(WebElement e : dropdownList)
		{
			String listText = e.getText();
			System.out.println(listText);
			for(String v : value)
			{
				if(listText.equals(v))
				{
					e.click();
					break;
				}
			}
		}
		
		}
	}
}
