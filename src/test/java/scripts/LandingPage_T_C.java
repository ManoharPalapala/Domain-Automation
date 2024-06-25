package scripts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import dataProvider.DataSupplier;
import pageElements.SearchPageElements;
import testBase.DriverFactory;

public class LandingPage_T_C extends MultiExecution_T_C{

//	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void duplicateLocationInLocBucket(String URL) { //change state abbr to excel

		driver.get(URL);
		SearchPageElements searchPage = homePage.clickOnSearch();
		searchPage.clickOnApply();
		
		Map<String,String> locationMap = new HashMap<String,String>();

		String location = driver.findElement(By.xpath("//h1/following-sibling::*[contains(text(),'Location')]")).getText(); //pass the location value here
		List<WebElement> keywordsList = driver.findElements(By.xpath("//*[contains(text(),'Trending Searches in')]/following::p[@class='j-tags']")); //pass the keywords here
		
		String[] splitLocation = location.replace("Location","").replace(":","").split(",");
				
		locationMap.put("City", splitLocation[0].replace(",","").trim());
		locationMap.put("StateCode", splitLocation[1].replace(",","").trim());
		locationMap.put("CountryCode", splitLocation[2].replace(",","").trim());
		locationMap.put("PostalCode", splitLocation[3].trim());

		for(WebElement keyword:keywordsList) {
			
			if(wordCounter(keyword.getText(),locationMap.get("City"),locationMap.get("StateCode"),ph.readDataFromPropFile(locationMap.get("StateCode")))>1) {
				
				System.out.println(keyword.getText()+" in "+driver.getCurrentUrl());
			}
		}
	}
	

	
//	@Test(dependsOnMethods="baseElementsUrl")
	public void listingPage() {

		String baseElementsUrl = null;
		String[] splittingbaseElementsUrl= baseElementsUrl.split("/");
		
// emptyArrayWithFixedLength
		String[]  modifiedSplittedUrl= new String[splittingbaseElementsUrl.length-1];
		
//	Copying from 1 array to another array
		System.arraycopy(splittingbaseElementsUrl, 0, modifiedSplittedUrl, 0, splittingbaseElementsUrl.length-1);
		String listingPageUrl = String.join("/",modifiedSplittedUrl);
		

//		String modifiedListingPageUrl = splitUrlAndAppendSubDomain(listingPageUrl);
//		driver.get(modifiedListingPageUrl);
		String afterModifyingUrl = driver.getCurrentUrl();
		sf.assertEquals(listingPageUrl, afterModifyingUrl);
		sf.assertAll();
		}

// Multiple execution

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyProtocol(String URL) throws Throwable {
		driver.get(URL);
		sf.assertTrue(urlProtocolType(URL).equals("https"));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyNetworkStatus(String URL) {
		driver.get(URL);
		sf.assertTrue(networkStatus(driver.getCurrentUrl()) == 200);
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyScrollButton(String URL) throws InterruptedException {
		driver.get(URL);
		scrollDown();
		homePage.clickOnScroll();
		homePage.waitUsingAttribute(homePage.getScrollButton(), homePage.getScrollInitAttr(),
				homePage.getScrollInitValue());
		sf.assertTrue(consoleResult().equals("null"));
		sf.assertTrue(getWindowPosition() <= 10, String.valueOf(getWindowPosition()));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyConsole(String URL) {
		driver.get(URL);
		sf.assertTrue(consoleResult().equals("null"), consoleResult());
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifySubdomain(String URL) {
		driver.get(URL);
		sf.assertFalse(addSubDomainToUrl(driver.getCurrentUrl()).contains("www"),driver.getCurrentUrl());
		sf.assertAll();
	}



	// view page source

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyTitleRepetition(String URL) {
		driver.get(URL);
		sf.assertTrue(driver.getTitle().equals(baseElements.getOgTitle()));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyDescRepetition(String URL) {
		driver.get(URL);
		sf.assertTrue(baseElements.getMetaDesc().equals(baseElements.getOgDesc()));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyDomainPipeline(String URL) {
		driver.get(URL);
		String[] title = driver.getTitle().split("\\s*\\|\\s*"); // removing trailing spaces around pipeline
		sf.assertTrue(baseElements.getDomainNameFromCopyRight().equals(title[title.length - 1]));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyOgSiteName(String URL) {
		driver.get(URL);
		sf.assertTrue(baseElements.getDomainNameFromCopyRight().replace(" ", "").equals(baseElements.getOgSiteName()),
				"Domain name:" + baseElements.getDomainNameFromCopyRight() + " og:sitename: " + baseElements.getOgSiteName());
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyOgUrlRepetition(String URL) {
		driver.get(URL);
		sf.assertTrue(
				driver.getCurrentUrl().equals(baseElements.getOgUrl())
						&& driver.getCurrentUrl().equals(baseElements.getCanonical()),
				"og url: " + baseElements.getOgUrl() + " canonical: " + baseElements.getCanonical());
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyOgImageNetworkAndFormat(String URL) {
		driver.get(URL);
		sf.assertTrue(networkStatus(baseElements.getOgImage()) == 200 && baseElements.getOgImage().contains(".webp"),
				"og:image: "+baseElements.getOgImage());
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyOgImageRepetition(String URL) {
		driver.get(URL);
		sf.assertTrue(baseElements.getOgImage().equals(baseElements.getLogoPath()),"og:image: "+baseElements.getOgImage());
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyFaviconNetwork(String URL) {
		driver.get(URL);
		sf.assertTrue(networkStatus(baseElements.getFavicon()) == 200);
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyFolderNameInPath(String URL) {
		driver.get(URL);

		sf.assertTrue(baseElements.getOgImage().contains(baseElements.getFolderNameInPath()));

		for (WebElement externalScript : baseElements.getExternalScripts()) {
			sf.assertTrue(networkStatus(externalScript.getAttribute("src"))==200 &&
							externalScript.getAttribute("src").contains(baseElements.getFolderNameInPath()),
					"external scripts path doesn't contain folder name");
		}

		for (WebElement styleSheet : baseElements.getStyleSheets()) {
			sf.assertTrue(networkStatus(styleSheet.getAttribute("href"))==200 &&
							styleSheet.getAttribute("href").contains(baseElements.getFolderNameInPath()),
					"style sheets path doesn't contain folder name");
		}

		for (WebElement imgContent : baseElements.getImages()) {
			sf.assertTrue(networkStatus(imgContent.getAttribute("src"))==200 &&
							imgContent.getAttribute("src").contains(baseElements.getFolderNameInPath()),
					"images path doesn't contain folder name: "+imgContent.getAttribute("src"));
		}

		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyDomainViewPort(String URL) {
		driver.get(URL);
		sf.assertTrue(baseElements.getMetaViewPort().equals("width=device-width, initial-scale=1, maximum-scale=5, minimal-ui"));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyHttpInPageSource(String URL) {
		driver.get(URL);
		sf.assertFalse(isHttpInPageSource(driver));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyStyleSheets(String URL) throws InterruptedException {
		driver.get(URL);

		for (WebElement styleSheet : baseElements.getStyleSheets()) {
			String hrefUrl = styleSheet.getAttribute("href");
			String parentTab = DriverFactory.getInstance().openNewTab(hrefUrl);
			sf.assertFalse(driver.getPageSource().contains("source"), hrefUrl);
			DriverFactory.getInstance().switchToParentTab(parentTab);
			}
		sf.assertFalse((baseElements.getBootstrapUrlPageSource()).contains("source"));
		sf.assertAll();
	}




//	UI test cases

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyExpiration(String URL){
		driver.get(URL+"/");
		sf.assertTrue(networkStatus(driver.getCurrentUrl())==410,"Network status is not 410");
		sf.assertTrue(baseElements.getPageHeading().equals("410! Page is removed"),"Expired message is not displayed");
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyUtm(String URL) {
		driver.get(URL + "?utm_campaign=google_jobs_apply&utm_source=google_jobs_apply&utm_medium=organic");
		sf.assertTrue(networkStatus(driver.getCurrentUrl()) == 200, "UTM is not working");
		sf.assertAll();
	}

//	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
//	public void verifyPathFlow(String URL){
//		String con="https://";
//		String x="https://abc/def/ghi";
//		for(String y:x.split("/")){
//
//		}

//	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyNavDimensions(String URL) {
		driver.get(URL);
		System.out.println(driver.getCurrentUrl());
		int lanNavwidth = baseElements.getNavDimensions()[1];
		int lanNavHeight = baseElements.getNavDimensions()[0];
		int lanlogoWidth = baseElements.getNavDimensions()[3];
		int lanlogoHeight = baseElements.getNavDimensions()[2];
		int scrollHeightLan = baseElements.getScrollDimensions()[0];
		int scrollWidthLan= baseElements.getScrollDimensions()[1];
		landingPage.clickOnLogo();
		System.out.println(driver.getCurrentUrl());
		int domNavwidth = baseElements.getNavDimensions()[1];
		int domNavHeight = baseElements.getNavDimensions()[0];
		int domlogoWidth = baseElements.getNavDimensions()[3];
		int domlogoHeight = baseElements.getNavDimensions()[2];
		int scrollHeightDom = baseElements.getScrollDimensions()[0];
		int scrollWidthDom = baseElements.getScrollDimensions()[1];
		sf.assertTrue(lanNavwidth==domNavwidth && lanNavHeight==domNavHeight && lanlogoWidth==domlogoWidth && lanlogoHeight==domlogoHeight);
		sf.assertTrue(scrollHeightLan==scrollHeightDom && scrollWidthLan==scrollWidthDom,"lan h: "+scrollHeightLan+"lan w: "+scrollWidthLan+"dom h: "+scrollHeightDom+"dom w: "+scrollWidthDom);
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyDescription(String URL){
		driver.get(URL);
		sf.assertTrue(landingPage.getDescription().isDisplayed());
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyDuplicatesInKeywords(String URL){
		driver.get(URL);
		sf.assertTrue(duplicateChecker(landingPage.getKewords()).isEmpty());
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyFooterTag(String URL){
		driver.get(URL);
		sf.assertTrue(landingPage.getFooterTags());
	}





}
