package com.absa.usermanagement.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends Page{


	public BasePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
//basic operations performing on the web page
	public void forClick(By locator) {
		driver.findElement(locator).click();
	}

	public void forSendKeys(By locator, String text) {
		driver.findElement(locator).sendKeys(text);
	}

	public String forGetText(By locator) {
		return driver.findElement(locator).getText();
}
	
	public void forSelect(By locator, String text) {
		new Select(driver.findElement(locator)).selectByVisibleText(text);
	}
	
	public void forClear(By locator) {
		driver.findElement(locator).clear();
	}
	
}
