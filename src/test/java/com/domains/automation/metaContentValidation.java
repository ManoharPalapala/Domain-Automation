//	(String sheetName, String domainUrl, String page, String typeOfContent)
// Reference of Classes should be invoked/created in Before Method's method
// JavaScript executor can be called without creating a reference by typeCasting the driver '((JavascriptExecutor) driver)'

//JavascriptExecutor js = (JavascriptExecutor) driver; js.executeScript("window.scrollBy(0,250)", "”); 
//Actions action = new Actions(driver); action.moveToElement(element).perform(); 
//WebElement element = driver.findElement(By.<locator>)); element.sendKeys(Keys.DOWN);

//
//Jobs by company
//Jobs by location
//Listings Page


package com.domains.automation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.domainAutomation.utils.ExcelUtils;

import PageElements.HomePageElements;
import io.github.bonigarcia.wdm.WebDriverManager;

public class metaContentValidation extends ExcelUtils{


	WebDriver driver;
	SoftAssert sf = new SoftAssert();
	HomePageElements homePageElements;
	
	@BeforeMethod
	public void testSetup() {
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		homePageElements = new HomePageElements(driver);
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}

	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void homePage(String URL) {
		driver.get(URL);
		String actualHomePageTitle = driver.getTitle();
		String expectedHomePageTitle = readMetaContentFromExcel("metaContent","epicareerz.com","Home page","title");
		sf.assertTrue(actualHomePageTitle.contains(expectedHomePageTitle),"Home page title content is not matching");

		String actualHomePageDesc = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
		String expectedHomePageDesc = readMetaContentFromExcel("metaContent","epicareerz.com","Home page","description");
		sf.assertTrue(actualHomePageDesc.contains(expectedHomePageDesc),"Home page title content is not matching");
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void SearchPage(String URL) {
		driver.get(URL);
		homePageElements.clickOnSearch();
		String actualSearchPageTitle = driver.getTitle();
		String expectedSearchPageTitle = readMetaContentFromExcel("metaContent","epicareerz.com","Search","title");
		sf.assertTrue(actualSearchPageTitle.contains(expectedSearchPageTitle),"Search page title content is not matching");

		String actualSearchPageDesc = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
		String expectedSearchPageDesc = readMetaContentFromExcel("metaContent","epicareerz.com","Search","description");
		sf.assertTrue(actualSearchPageDesc.contains(expectedSearchPageDesc),"Search page title content is not matching");
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void AboutPage(String URL) {
		driver.get(URL);
		homePageElements.clickOnAbout();
		String actualAboutPageTitle = driver.getTitle();
		String expectedAboutPageTitle = readMetaContentFromExcel("metaContent","epicareerz.com","About","title");
		sf.assertTrue(actualAboutPageTitle.contains(expectedAboutPageTitle),"About page title content is not matching");

		String actualAboutPageDesc = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
		String expectedAboutPageDesc = readMetaContentFromExcel("metaContent","epicareerz.com","About","description");
		sf.assertTrue(actualAboutPageDesc.contains(expectedAboutPageDesc),"About page title content is not matching");
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void ContactPage(String URL) {
		driver.get(URL);
		homePageElements.clickOnContact();
		String actualContactPageTitle = driver.getTitle();
		String expectedContactPageTitle = readMetaContentFromExcel("metaContent","epicareerz.com","Contact","title");
		sf.assertTrue(actualContactPageTitle.contains(expectedContactPageTitle),"Contact page title content is not matching");

		String actualContactPageDesc = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
		String expectedContactPageDesc = readMetaContentFromExcel("metaContent","epicareerz.com","Contact","description");
		sf.assertTrue(actualContactPageDesc.contains(expectedContactPageDesc),"Contact page title content is not matching");
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void TermsPage(String URL) {
		driver.get(URL);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", homePageElements.termsLocator());
		String actualTermsPageTitle = driver.getTitle();
		String expectedTermsPageTitle = readMetaContentFromExcel("metaContent","epicareerz.com","Terms & Conditions","title");
		sf.assertTrue(actualTermsPageTitle.contains(expectedTermsPageTitle),"Terms page title content is not matching");

		String actualTermsPageDesc = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
		String expectedTermsPageDesc = readMetaContentFromExcel("metaContent","epicareerz.com","Terms & Conditions","description");
		sf.assertTrue(actualTermsPageDesc.contains(expectedTermsPageDesc),"Terms page title content is not matching");
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void PrivacyPage(String URL) {
		driver.get(URL);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", homePageElements.privacyLocator());
		String actualPrivacyPageTitle = driver.getTitle();
		String expectedPrivacyPageTitle = readMetaContentFromExcel("metaContent","epicareerz.com","Privacy Policy","title");
		sf.assertTrue(actualPrivacyPageTitle.contains(expectedPrivacyPageTitle),"Privacy page title content is not matching");

		String actualPrivacyPageDesc = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
		String expectedPrivacyPageDesc = readMetaContentFromExcel("metaContent","epicareerz.com","Privacy Policy","description");
		sf.assertTrue(actualPrivacyPageDesc.contains(expectedPrivacyPageDesc),"Privacy page desc content is not matching");
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void CookiePage(String URL) {
		driver.get(URL);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", homePageElements.cookieLocator());
		String actualCookiePageTitle = driver.getTitle();
		String expectedCookiePageTitle = readMetaContentFromExcel("metaContent","epicareerz.com","Cookie Policy","title");
		sf.assertTrue(actualCookiePageTitle.contains(expectedCookiePageTitle),"Cookie page title content is not matching");

		String actualCookiePageDesc = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
		String expectedCookiePageDesc = readMetaContentFromExcel("metaContent","epicareerz.com","Cookie Policy","description");
		sf.assertTrue(actualCookiePageDesc.contains(expectedCookiePageDesc),"Cookie page desc content is not matching");
		sf.assertAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
