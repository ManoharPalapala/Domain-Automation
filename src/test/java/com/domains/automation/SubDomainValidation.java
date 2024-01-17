package com.domains.automation;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import PageElements.HomePageElements;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubDomainValidation extends Base{

	WebDriver driver;
	SoftAssert sf = new SoftAssert();
	String landingPageUrl;
	HomePageElements homePage;
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
	JavascriptExecutor js = (JavascriptExecutor)driver;
	
	
//	String URL;

	
	@BeforeMethod
	public void testSetup() {
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		homePage = new HomePageElements(driver);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	
	@Test(priority=1,dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void homePage(String URL) {
		driver.get(URL);
		String modifiedHomePageUrl = splitUrlAndAppendSubDomain(URL);
		driver.get(modifiedHomePageUrl);
		String afterModifyingUrl = driver.getCurrentUrl();
		sf.assertEquals(URL, afterModifyingUrl);
		sf.assertAll();	
	}
	
	@Test(priority=2,dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void searchPage(String URL) {
		driver.get(URL);
		homePage.clickOnSearch();
		String searchPageUrl = driver.getCurrentUrl();
		String modifiedSearchPage = splitUrlAndAppendSubDomain(searchPageUrl);
		driver.get(modifiedSearchPage);
		String afterModifyingUrl = driver.getCurrentUrl();
		sf.assertEquals(searchPageUrl,afterModifyingUrl);
		sf.assertAll();
	}
	

	@Test(priority=3,dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void aboutPage(String URL) {
		driver.get(URL);
		homePage.clickOnAbout();
		String aboutPageUrl = driver.getCurrentUrl();
		String modifiedAboutPage = splitUrlAndAppendSubDomain(aboutPageUrl);
		driver.get(modifiedAboutPage);
		String afterModifyingUrl = driver.getCurrentUrl();
		System.out.println(aboutPageUrl);
		System.out.println(afterModifyingUrl);
		sf.assertEquals(aboutPageUrl, afterModifyingUrl);
		sf.assertAll();
	}
	
	@Test(priority=4,dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void contactPage(String URL) {
		driver.get(URL);
		homePage.clickOnContact();
		String contactPageUrl = driver.getCurrentUrl();
		String modifiedContactPage = splitUrlAndAppendSubDomain(contactPageUrl);
		driver.get(modifiedContactPage);
		String afterModifyingUrl = driver.getCurrentUrl();
		sf.assertEquals(contactPageUrl, afterModifyingUrl);
		sf.assertAll();
	}
	
	@Test(priority=5,dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void landingPageUrl(String URL) {
		driver.get(URL);
		homePage.clickOnSearch();
		js.executeScript("arguments[0].click();", homePage.ApplyButtonLocator());
		landingPageUrl = driver.getCurrentUrl();
		String modifiedLandingPageUrl = splitUrlAndAppendSubDomain(landingPageUrl);
		driver.get(modifiedLandingPageUrl);
		String afterModifyingUrl = driver.getCurrentUrl();
		System.out.println(landingPageUrl);
		System.out.println(afterModifyingUrl);
		sf.assertEquals(landingPageUrl, afterModifyingUrl);
		sf.assertAll();
	}
	
	@Test(dependsOnMethods="LandingPageUrl")
	public void listingPage() {
		
		String fetchLandingPage = landingPageUrl;
		String[] splittingLandingPageUrl= fetchLandingPage.split("/");
		
// emptyArrayWithFixedLength
		String[]  modifiedSplittedUrl= new String[splittingLandingPageUrl.length-1];
		
//	Copying from 1 array to another array
		System.arraycopy(splittingLandingPageUrl, 0, modifiedSplittedUrl, 0, splittingLandingPageUrl.length-1);
		String listingPageUrl = String.join("/",modifiedSplittedUrl);
		

		String modifiedListingPageUrl = splitUrlAndAppendSubDomain(listingPageUrl);
		driver.get(modifiedListingPageUrl);
		String afterModifyingUrl = driver.getCurrentUrl();
		sf.assertEquals(listingPageUrl, afterModifyingUrl);
		sf.assertAll();
		}	
		
	}
	
	
	





















