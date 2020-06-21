package com.absa.usermanagement.tests;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.absa.usermanagement.pages.HomePage;

public class HomePageTest extends BaseTest{
	Logger log = Logger.getLogger(HomePageTest.class);
	@Test(priority = 1)
	public void homePageTitleTest() {
		//verifying the page title whether we are loaded on correct page or not
		String title=page.getInstance(HomePage.class).getHomePageTitle();
		System.out.println("home page title is: " + title);
		Assert.assertEquals(title, "Protractor practice website - WebTables");
	}
	@Test (priority = 2, dataProvider = "Data", dataProviderClass = BaseTest.class)
	public void addUserDataTest(String FirstName, String LastName, String UserName, String Password, String Company, String Role, String Mail, String Phone) {
		//verifying the userdata entered into web page or not
		 page.getInstance(HomePage.class).addUserData(FirstName, LastName, UserName, Password, Company, Role, Mail, Phone);
		
		 List<String> list = page.getInstance(HomePage.class).webTableData(UserName);
		 String userName1 = UserName;
		 if(list.contains(UserName)) {
			 log.info("User Verified after adding to userdata");
		 }
		
	}
}
