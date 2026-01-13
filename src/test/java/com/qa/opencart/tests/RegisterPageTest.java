package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;
import static com.qa.opencart.constants.AppConstants.*;

public class RegisterPageTest extends BaseTest{
	
	
	
	@BeforeClass
	public void registerSetup()
	{
		registerpage = loginpage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getUserData()
	{
		return new Object[][] {
			{"vishal","sharma","9877689803","vishal@123","yes"},
			{"jyoti","sharma","9877689805","jyoti@123","yes"}
		};
	}
	
	@DataProvider
	public Object[][] getUserRegistrationData()
	{
		//This method required to pick data from excel file
		Object regData[][] = ExcelUtil.getTestData(REGISTER_SHEET_NAME);
		return regData;
	}
	@Test(dataProvider = "getUserRegistrationData")
	public void verifyRegisterUser(String firstName,String lastName,String telephone,String password,String subscribe)
	{
		Assert.assertTrue(registerpage.fillRegisterForm(firstName,lastName,telephone,password,subscribe));
	}

}
