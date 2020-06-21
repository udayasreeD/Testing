package com.absa.usermanagement.tests;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import com.absa.usermanagement.pages.*;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class BaseTest {
	Logger log = Logger.getLogger(BaseTest.class);
	public WebDriver driver;
	public WebDriverWait wait;
	public Page page;

	//performing intial setup like opening browser,opening the URL etc
	@BeforeClass
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver","C:\\Udaya\\Softwares\\Selenium_Software\\chromedriver.exe");
		driver=new ChromeDriver();
		wait = new WebDriverWait(driver, 15);
		driver.manage().window().fullscreen();
		driver.get("http://www.way2automation.com/angularjs-protractor/webtables/");
		page= new Page(driver, wait);
		 PropertyConfigurator.configure("C:\\Udaya\\Selenium\\AbsaAssessment\\usermanagement\\src\\syslogs\\log4j.properties");
	}
//Dataprovider annotation is used for reading the data from the excel
	@DataProvider(name="Data")
	public Object[][] excelData() throws BiffException, IOException {
		Object[][] arrayObject = getData("C:\\Udaya\\Selenium\\AbsaAssessment\\usermanagement\\Testdata\\userData.xls","Sheet1");
		return arrayObject;
	}
	public Object[][] getData(String fileName, String sheetName) throws BiffException, IOException 
	{
		Object[][] excelData = null;
		FileInputStream fis = new FileInputStream(fileName);
		Workbook wb = Workbook.getWorkbook(fis);

		// TO get the access to the sheet
		Sheet sheet = wb.getSheet("Sheet1");
		//HSSFRow row=sheet.getRow(0);    
		int totalNoOfRows = sheet.getRows();

		// To get the number of columns present in sheet
		int totalNoOfCols = sheet.getColumns();
		excelData = new String[totalNoOfRows-1][totalNoOfCols];
		for (int row = 1; row < totalNoOfRows; row++) {
			for (int col = 0; col < totalNoOfCols; col++) {
				excelData[row-1][col]=sheet.getCell(col,row).getContents();
			}
		}
		return excelData;
	}
//Closing instance of driver object
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
