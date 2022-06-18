package com.pnmac.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pnmac.pageObjects.AddCustomerPage;
import com.pnmac.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass
{
	
	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		logger.info("User name is provided");
		lp.setPassword(password);
		logger.info("Passsword is provided");
		lp.clickSubmit();
		
		AddCustomerPage addcust = new AddCustomerPage(driver);
		addcust.clickAddNewCustomer();
		driver.navigate().back();
		addcust.clickAddNewCustomer();
		logger.info("providing customer details....");
		addcust.custName("Sarovar");
		addcust.custgender("male");
		addcust.custdob("10", "15", "1995");
		Thread.sleep(5000);
		addcust.custaddress("INDIA");
		addcust.custcity("Pune");
		addcust.custstate("MH");
		addcust.custpinno("411027");
		addcust.custtelephoneno("9893981682");
		
		String email=randomestring()+"@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		addcust.custsubmit();
		
		Thread.sleep(3000);
		
		logger.info("validation started....");
		
		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("test case passed");
		}
		else
		{
			logger.error("test case failed");
			captureScreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
		}
			
	}
	
	
}
