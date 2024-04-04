package scripts;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import dataProvider.DataSupplier;

public class TermsPage_T_C extends MultiExecution_T_C{

	
	
	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void verifyStaticContent(String URL) {
		driver.get(URL);
		homePage.clickOnTerms();
		sf.assertFalse(checkWordsInContent(baseElements.getPageContent(),staticWords));
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void verifyPageHeading(String URL) {
		driver.get(URL);
		homePage.clickOnTerms();
		sf.assertTrue(pageHeading(homePage.getTermsPageUrl(), baseElements.getPageHeading()),baseElements.getPageHeading()); 
		sf.assertAll();
	}
	
	
// Multiple execution	
	
		@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
		public void verifyProtocol(String URL) throws Throwable
		{
			driver.get(URL);
			homePage.clickOnTerms();
			sf.assertTrue(urlProtocolType(URL).equals("https"));
			sf.assertAll();
		}
		
		@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
		public void verifyNetworkStatus(String URL) {
			driver.get(URL);
			homePage.clickOnTerms();
			sf.assertTrue(networkStatus(driver.getCurrentUrl())==200);
			sf.assertAll();
		}
		
	
		@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
		public void verifyScrollButton(String URL) throws InterruptedException {
			driver.get(URL);
			homePage.clickOnTerms();
			scrollDown();
			homePage.clickOnScroll();
			sf.assertTrue(getWindowPosition()<9, String.valueOf(getWindowPosition()));
			sf.assertAll();
			}

	
		@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
		public void verifyConsole(String URL)
		{
			driver.get(URL);
			homePage.clickOnTerms();
			sf.assertTrue(consoleResult().equals("null"),consoleResult()); 
			sf.assertAll();
		}
		

		@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
		public void verifySubdomain(String URL)
		{
			driver.get(URL);
			homePage.clickOnTerms();
			sf.assertFalse(addSubDomainToUrl(driver.getCurrentUrl()).contains("www"));
			sf.assertAll();
		}
	
		
		@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
		public void verifyMetaContent(String URL) {
			driver.get(URL);
			homePage.clickOnTerms();
			String actualHomePageTitle = driver.getTitle();
			String expectedHomePageTitle = metaExcel.readMetaContentFromExcel(dfh.readDataFromPropFile("domainMetaContentSheet") ,splitUrlForDomainName(URL),"Terms & Conditions","title");
			sf.assertTrue(actualHomePageTitle.contains(expectedHomePageTitle),actualHomePageTitle+" "+expectedHomePageTitle);

			String actualHomePageDesc = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
			String expectedHomePageDesc = metaExcel.readMetaContentFromExcel(dfh.readDataFromPropFile("domainMetaContentSheet") ,splitUrlForDomainName(URL),"Terms & Conditions","description");
			sf.assertTrue(actualHomePageDesc.contains(expectedHomePageDesc),actualHomePageDesc+" "+expectedHomePageDesc);
			sf.assertAll();
		}
		
		
		@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
		public void verifyHttpInPageSource(String URL) 
		{
		driver.get(URL);
		homePage.clickOnTerms();
		sf.assertFalse(isHttpInPageSource(driver));
		sf.assertAll();
		}	
		
// view page source
		
		@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
		public void verifyTitleRepetition(String URL) 
		{
		driver.get(URL);
		homePage.clickOnTerms();
		sf.assertTrue(driver.getTitle().equals(baseElements.getMetaTitle()) && driver.getTitle().equals(baseElements.getOgTitle()));
		sf.assertAll();
		}
		
		@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
		public void verifyDescRepetition(String URL) 
		{
		driver.get(URL);
		homePage.clickOnTerms();
		sf.assertTrue(baseElements.getMetaDesc().equals(baseElements.getOgDesc()));
		sf.assertAll();
		}
		
		@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
		public void verifyOgUrlRepetition(String URL) 
		{
		driver.get(URL);
		homePage.clickOnTerms();
		sf.assertTrue(driver.getCurrentUrl().equals(baseElements.getOgUrl()) && baseElements.getCanonical().equals(driver.getCurrentUrl()),"og url: "+baseElements.getOgUrl()+" canonical: "+baseElements.getCanonical());
		sf.assertAll();
		}

		
}
