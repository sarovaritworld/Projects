package com.pnmac.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pnmac.pageObjects.LoginPage;
import com.pnmac.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {

	@Test(dataProvider="LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		logger.info("User name entered");
		lp.setPassword(pwd);
		logger.info("User password entered");
		lp.clickSubmit(); 
		
		if(isAlertPresent()==true)
		{	
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			logger.error("Login Failed");
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
			lp.clickLogoutLink();
			logger.info("Login Passed");
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
		
	}
	
	public boolean isAlertPresent()
	{
		try
		{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/pnmac/testData/LoginData.xlsx";
		int rowcount = XLUtils.getRowCount(path, "sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		String logindata[][] = new String[rowcount][colcount];
		
		for(int i=1;i<=rowcount;i++)
		{
			for(int j=0;j<colcount;j++) 
			{
				logindata[i-1][j] = XLUtils.getCellData(path, "sheet1", i, j);
			}
		}
		
		return logindata;
	}
}
