package scripts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import dataProvider.DataSupplier;
import pageElements.HomePageElements;
import pageElements.SearchPageElements;

public class LandingPage_T_C extends MultiExecution_T_C{
	
	
	String landingPageUrl;
	HomePageElements homePage;
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
	JavascriptExecutor js;

	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void duplicateLocationInLocBucket(String URL) { //change state abbr to excel

		driver.get(URL);
		SearchPageElements searchPage = homePage.clickOnSearch();
		searchPage.clickOnApply();
	
//		driver.get("https://jobzwaves.com/data/hooters-of-america-indiana-zionsville-dining-attendant-n9ev6wh7gzwb");
		
		Map<String,String> locationMap = new HashMap<String,String>();

		String location = driver.findElement(By.xpath("//h1/following-sibling::*[contains(text(),'Location')]")).getText(); //pass the location value here
		List<WebElement> keywordsList = driver.findElements(By.xpath("//*[contains(text(),'Trending Searches in')]/following::p[@class='j-tags']")); //pass the keywords here
		
		String[] splitLocation = location.replace("Location","").replace(":","").split(",");
				
		locationMap.put("City", splitLocation[0].replace(",","").trim());
		locationMap.put("StateCode", splitLocation[1].replace(",","").trim());
		locationMap.put("CountryCode", splitLocation[2].replace(",","").trim());
		locationMap.put("PostalCode", splitLocation[3].trim());

		for(WebElement keyword:keywordsList) {
			
			if(wordCounter(keyword.getText(),locationMap.get("City"),locationMap.get("StateCode"),dfh.readDataFromPropFile(locationMap.get("StateCode")))>1) {
				
				System.out.println(keyword.getText()+" in "+driver.getCurrentUrl());
			}
		}
	}
	

	
	@Test(dependsOnMethods="landingPageUrl")
	public void listingPage() {
		
		String fetchLandingPage = landingPageUrl;
		String[] splittingLandingPageUrl= fetchLandingPage.split("/");
		
// emptyArrayWithFixedLength
		String[]  modifiedSplittedUrl= new String[splittingLandingPageUrl.length-1];
		
//	Copying from 1 array to another array
		System.arraycopy(splittingLandingPageUrl, 0, modifiedSplittedUrl, 0, splittingLandingPageUrl.length-1);
		String listingPageUrl = String.join("/",modifiedSplittedUrl);
		

//		String modifiedListingPageUrl = splitUrlAndAppendSubDomain(listingPageUrl);
//		driver.get(modifiedListingPageUrl);
		String afterModifyingUrl = driver.getCurrentUrl();
		sf.assertEquals(listingPageUrl, afterModifyingUrl);
		sf.assertAll();
		}	

	
	
}
