package com.absa.usermanagement.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
	Logger log = Logger.getLogger(HomePage.class);
	public HomePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	//web element locators
	By user = By.xpath("//button[@class='btn btn-link pull-right']");
	By fname = By.name("FirstName");
	By lname = By.name("LastName");
	By uname = By.name("UserName");
	By password = By.name("Password");
	By company = By.xpath("//input[@value='15']");
	By role = By.name("RoleId");
	By email = By.name("Email");
	By phone = By.name("Mobilephone");
	By save = By.xpath("//button[@class='btn btn-success']");
	By table = By.tagName("table");
	By tr = By.tagName("tr");
	String row;

	//getting the name of the title
	public String getHomePageTitle() {
		return driver.getTitle();
	}
	//Entering the userdata from excel file
	public void addUserData(String FirstName, String LastName, String UserName, String Password,String Company, String Role, String Mail, String Phone) {
		List<String> UserNames = webTableData(UserName);
		if(!UserNames.contains(UserName)) {
			forClick(user);
			forClear(fname);
			forSendKeys(fname, FirstName);
			forClear(lname);
			forSendKeys(lname, LastName);
			forClear(uname);
			forSendKeys(uname, UserName);
			forClear(password);
			forSendKeys(password, Password);
			List<WebElement> radiobuttons = driver.findElements(By.name("radiobuttonname"));
		    for(WebElement radiobutton: radiobuttons) { 

		    if(radiobutton.getAttribute("value").equals(Company))
		        radiobutton.click();
		}
			forSelect(role,Role);
			forClear(email);
			forSendKeys(email,Mail);
			forClear(phone);
			forSendKeys(phone,Phone);
			forClick(save);
			log.info("user is added" +":"+ UserName);
		} else {
			log.info("User already exists");
		}
	}
	/**
	 * @param UserName
	 * @return
	 */
	//For reading the webtable data from webpage and returning the username for verifying whether it is unique or not
	public List<String> webTableData(String UserName) {
		WebElement tables = driver.findElement(table);
		List<WebElement> tableRows =tables.findElements(tr);
		int row_num=1,col_num=1;
		List<String> UserNames = new ArrayList<String>();
		for(WebElement webElement:tableRows) {
			List<WebElement> td_collection = webElement.findElements(By.tagName("td")); 
			int n = td_collection.size();
			for(int i=0;i<n;i++)
				col_num=1;
			for (WebElement tdElement  :td_collection)
			{
				if(row_num==1 && col_num == 3 && tdElement.getText().equals(UserName)) {
					UserNames.add(tdElement.getText());
					System.out.println(UserNames + "##########");
				}
				col_num++;
			}
		}
		return UserNames;
	}
}