package com.domains.automation;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.domain.DataSupplier.DataSupplier;

import PageElements.HomePageElements;

public class HomePage_T_C extends Base{

		WebDriver driver;
		SoftAssert sf;
		String landingPageUrl;
		HomePageElements homePage;
		JavascriptExecutor js;
		URL url;

		
		@BeforeMethod
		public void testSetup() {

			this.driver=setUp(driver);
			homePage = new HomePageElements(driver);
			js = (JavascriptExecutor)driver;
			sf = new SoftAssert();
		}
		
		@AfterMethod
		public void tearDown() {
			driver.quit();
		}
		@Ignore
		@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
		public void verifyRobots(String URL) {
			driver.get(URL+"/robots.txt");
			String robotsStatus = driver.findElement(By.tagName("pre")).getText();
			sf.assertTrue(robotsStatus.equals("allow"));
			sf.assertAll();
		}
		

		
		
		
		
	}

	
	

