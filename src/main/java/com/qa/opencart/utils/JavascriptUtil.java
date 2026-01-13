package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptUtil {
	
	private WebDriver driver;
	private JavascriptExecutor js;
	
	public JavascriptUtil(WebDriver driver)
	{
		this.driver = driver;
		js = (JavascriptExecutor) this.driver;
		
	}
	public String getTitleByJS()
	{
		String title = js.executeScript("return document.title;").toString();
		return title;
	}

	public String getUrlByJS()
	{
		String url = js.executeScript("return document.url;").toString();
		return url;
	}
	
	public void navigateToBackPageByJS()
	{
		js.executeScript("history.go(-1)");
		
	}
	
	public void navigateToForwardPageByJS()
	{
		js.executeScript("history.go(1)");
		
	}
	
	public void refreshBrowserByJS()
	{
		js.executeScript("history.go(0)");
		
	}
	
	public void generateJSAlert(String message)
	{
		js.executeScript("alert('"+message+"')");
		try {
			Thread.sleep(1000);		
			}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		
		driver.switchTo().alert().dismiss();
		
	}
	
	public String getPageInnerTextByJS()
	{
		return js.executeScript("return document.documentElement.innerText;").toString();
	}
	
	public void scrollPageDownByJS()
	{
		 js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public void scrollPageUpByJS()
	{
		 js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}
	
	public void scrollPageDownByHeightJS(String height)
	{
		 js.executeScript("window.scrollTo(0,'"+height+"')");
	}
	
	public void scrollIntoViewByJS(WebElement element)
	{
		js.executeScript("arguments[0].scrollIntoView(true);",element);
	}
	
	public void highlightElementByJS(WebElement element)
	{
		String bgColor = element.getCssValue("backgroundColor");
		for(int i =0;i<7;i++)
		{
			changeColorByJS("rgb(0,200,0)",element);//green
			changeColorByJS(bgColor,element);
		}
	}
	
	private void changeColorByJS(String color,WebElement element)
	{
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '"+color+"'",element);
		try
		{
			Thread.sleep(20);
		}
		catch(InterruptedException e) {
		
		}
	}
	
	//Useful when actions class is not working
	//WebClick -->ActionClick --> JS Click --> JsClick is a last option and should be used only if nothing is working
	//As JSClick is kind of backend operation and not simulating actual user behaviour
	public void clickElementByJS(WebElement ele)
	{
		js.executeScript("arguments[0].click();", ele);
	}
	
	public void sendKeysByJSById(String Id, String value)
	{
		js.executeScript("document.getElementById('"+Id+"').value='"+value+"'");
	}
}
