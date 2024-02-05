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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.domain.DataSupplier.DataSupplier;
import com.domainAutomation.utils.Helper;
import com.domainAutomation.utils.MetaExcelUtils;
import com.domainAutomation.utils.Utils;

import PageElements.HomePageElements;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MetaContentValidation extends MetaExcelUtils{


	WebDriver driver;
	SoftAssert sf;
	HomePageElements homePage;
	
	@BeforeMethod
	public void testSetup() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		homePage = new HomePageElements(driver);
		sf = new SoftAssert();
		}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}

	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void homePage(String URL) {
		driver.get(URL);
		String actualHomePageTitle = driver.getTitle();
		String expectedHomePageTitle = readMetaContentFromExcel(Utils.readDataFromPropFile("domainMetaContentSheet") ,Helper.splitUrlForMetaContentDomainName(URL),"Home page","title");
		sf.assertTrue(actualHomePageTitle.contains(expectedHomePageTitle),actualHomePageTitle+" "+expectedHomePageTitle);

		String actualHomePageDesc = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
		String expectedHomePageDesc = readMetaContentFromExcel(Utils.readDataFromPropFile("domainMetaContentSheet") ,Helper.splitUrlForMetaContentDomainName(URL),"Home page","description");
		sf.assertTrue(actualHomePageDesc.contains(expectedHomePageDesc),actualHomePageDesc+" "+expectedHomePageDesc);
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void SearchPage(String URL) {
		driver.get(URL);
		homePage.clickOnSearch();
		String actualSearchPageTitle = driver.getTitle();
		String expectedSearchPageTitle = readMetaContentFromExcel(Utils.readDataFromPropFile("domainMetaContentSheet") ,Helper.splitUrlForMetaContentDomainName(URL),"Search","title");
		sf.assertTrue(actualSearchPageTitle.contains(expectedSearchPageTitle),actualSearchPageTitle+" "+expectedSearchPageTitle);

		String actualSearchPageDesc = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
		String expectedSearchPageDesc = readMetaContentFromExcel(Utils.readDataFromPropFile("domainMetaContentSheet") ,Helper.splitUrlForMetaContentDomainName(URL),"Search","description");
		sf.assertTrue(actualSearchPageDesc.contains(expectedSearchPageDesc),actualSearchPageDesc+" "+expectedSearchPageDesc);
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void AboutPage(String URL) {
		driver.get(URL);
		homePage.clickOnAbout();
		String actualAboutPageTitle = driver.getTitle();
		String expectedAboutPageTitle = readMetaContentFromExcel(Utils.readDataFromPropFile("domainMetaContentSheet") ,Helper.splitUrlForMetaContentDomainName(URL),"About","title");
		sf.assertTrue(actualAboutPageTitle.contains(expectedAboutPageTitle),actualAboutPageTitle+" "+expectedAboutPageTitle);

		String actualAboutPageDesc = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
		String expectedAboutPageDesc = readMetaContentFromExcel(Utils.readDataFromPropFile("domainMetaContentSheet") ,Helper.splitUrlForMetaContentDomainName(URL),"About","description");
		sf.assertTrue(actualAboutPageDesc.contains(expectedAboutPageDesc),actualAboutPageDesc+" "+expectedAboutPageDesc);
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void ContactPage(String URL) {
		driver.get(URL);
		homePage.clickOnContact();
		String actualContactPageTitle = driver.getTitle();
		String expectedContactPageTitle = readMetaContentFromExcel(Utils.readDataFromPropFile("domainMetaContentSheet") ,Helper.splitUrlForMetaContentDomainName(URL),"Contact","title");
		System.out.println(actualContactPageTitle);
		System.out.println(expectedContactPageTitle);
		sf.assertTrue(actualContactPageTitle.contains(expectedContactPageTitle),actualContactPageTitle+" "+expectedContactPageTitle);

		String actualContactPageDesc = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
		String expectedContactPageDesc = readMetaContentFromExcel(Utils.readDataFromPropFile("domainMetaContentSheet") ,Helper.splitUrlForMetaContentDomainName(URL),"Contact","description");
		System.out.println(actualContactPageDesc);
		System.out.println(expectedContactPageDesc);
		sf.assertTrue(actualContactPageDesc.contains(expectedContactPageDesc),actualContactPageDesc+" "+expectedContactPageDesc);
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void TermsPage(String URL){
		driver.get(URL);
		homePage.clickOnTerms();
		String actualTermsPageTitle = driver.getTitle();
		String expectedTermsPageTitle = readMetaContentFromExcel(Utils.readDataFromPropFile("domainMetaContentSheet") ,Helper.splitUrlForMetaContentDomainName(URL),"Terms & Conditions","title");
//		String replacedExpectedTermsPageTitle = MetaHelper.charReplacer(actualTermsPageTitle, expectedTermsPageTitle);	
		System.out.println(actualTermsPageTitle);
		System.out.println(expectedTermsPageTitle);
		sf.assertTrue(actualTermsPageTitle.contains(expectedTermsPageTitle),actualTermsPageTitle+" "+expectedTermsPageTitle);

		String actualTermsPageDesc = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
		String expectedTermsPageDesc = readMetaContentFromExcel(Utils.readDataFromPropFile("domainMetaContentSheet") ,Helper.splitUrlForMetaContentDomainName(URL),"Terms & Conditions","description");
//		String replacedExpectedTermsPageDesc = MetaHelper.charReplacer(actualTermsPageDesc, expectedTermsPageDesc);	
		sf.assertTrue(actualTermsPageDesc.contains(expectedTermsPageDesc),actualTermsPageDesc+" "+expectedTermsPageDesc);
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void PrivacyPage(String URL) {
		driver.get(URL);
		homePage.clickOnPrivacy();
		String actualPrivacyPageTitle = driver.getTitle();
		String expectedPrivacyPageTitle = readMetaContentFromExcel(Utils.readDataFromPropFile("domainMetaContentSheet") ,Helper.splitUrlForMetaContentDomainName(URL),"Privacy Policy","title");
		sf.assertTrue(actualPrivacyPageTitle.contains(expectedPrivacyPageTitle),actualPrivacyPageTitle+" "+expectedPrivacyPageTitle);

		String actualPrivacyPageDesc = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
		String expectedPrivacyPageDesc = readMetaContentFromExcel(Utils.readDataFromPropFile("domainMetaContentSheet") ,Helper.splitUrlForMetaContentDomainName(URL),"Privacy Policy","description");
		sf.assertTrue(actualPrivacyPageDesc.contains(expectedPrivacyPageDesc),actualPrivacyPageDesc+" "+expectedPrivacyPageDesc);
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void CookiePage(String URL) {
		driver.get(URL);
		homePage.clickOnCookie();
		String actualCookiePageTitle = driver.getTitle();
		String expectedCookiePageTitle = readMetaContentFromExcel(Utils.readDataFromPropFile("domainMetaContentSheet") ,Helper.splitUrlForMetaContentDomainName(URL),"Cookie Policy","title");
		sf.assertTrue(actualCookiePageTitle.contains(expectedCookiePageTitle),actualCookiePageTitle+" "+expectedCookiePageTitle);

		String actualCookiePageDesc = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
		String expectedCookiePageDesc = readMetaContentFromExcel(Utils.readDataFromPropFile("domainMetaContentSheet") ,Helper.splitUrlForMetaContentDomainName(URL),"Cookie Policy","description");
		sf.assertTrue(actualCookiePageDesc.contains(expectedCookiePageDesc),actualCookiePageDesc+" "+expectedCookiePageDesc);
		sf.assertAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
