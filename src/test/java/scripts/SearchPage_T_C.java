package scripts;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import dataProvider.DataSupplier;
import pageElements.SearchPageElements;

public class SearchPage_T_C extends MultiExecution_T_C{

	
	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void validateCompanyFilter(String URL) {
		driver.get(URL);
		SearchPageElements searchPage = homePage.clickOnSearch();
		searchPage.clickOnCompanyFilter();
		Select sel = new Select(searchPage.selectCompanyOption());
		sel.selectByIndex(1);
		searchPage.clickOnSearch();
		String selected = searchPage.selectedCompany();
		String output = searchPage.searchResult(); 
		sf.assertTrue(output.contains(selected), selected+" "+output);
		sf.assertAll();
		}
	
	
	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void verifyClearButton(String URL) {
		driver.get(URL);
		SearchPageElements searchPage = homePage.clickOnSearch();
		searchPage.sendKeysToTitleFiled("driver");
		searchPage.clickOnSearch();
		searchPage.clickOnClearSearch();
		sf.assertTrue(searchPage.titleInputField().isEmpty() && driver.getCurrentUrl().equals(URL+"/search"));
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void validateMinimumJobCount(String URL) {
		driver.get(URL);
		SearchPageElements searchPage = homePage.clickOnSearch();
		sf.assertTrue(searchPage.totalApplyLinks() ==Integer.parseInt(dfh.readDataFromPropFile("minimumJobCountinSearch")),String.valueOf(searchPage.totalApplyLinks())+"   "+dfh.readDataFromPropFile("minimumJobCountinSearch"));	
		sf.assertAll();
	}
	
	
//	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void validateTotalJobCount(String URL) {
		driver.get(URL);
		SearchPageElements searchPage = homePage.clickOnSearch();
		if(searchPage.totalApplyLinks()<Integer.parseInt(searchPage.specifiedjobCount())) {
			while(String.valueOf(searchPage.totalApplyLinks())!=searchPage.specifiedjobCount()){
				searchPage.loadMoreButton().isDisplayed();
				searchPage.clickOnLoadMore();
				}}
		sf.assertTrue(searchPage.totalApplyLinks()==Integer.parseInt(searchPage.specifiedjobCount()),searchPage.totalApplyLinks()+" "+Integer.parseInt(searchPage.specifiedjobCount()));
		sf.assertAll();
	}	
	
	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void verifyApplyButton(String URL) {
		driver.get(URL);
		SearchPageElements searchPage = homePage.clickOnSearch();
		String applyButtonUrl=searchPage.applyButton().getAttribute("href");
		searchPage.clickOnApply();
		sf.assertTrue((driver.getCurrentUrl()).equals(applyButtonUrl));
		sf.assertAll();
	}
	

	
	
// Multiple execution
	
	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void verifyProtocol(String URL) throws Throwable
	{
		driver.get(URL);
		homePage.clickOnSearch();
		sf.assertTrue(urlProtocolType(driver.getCurrentUrl()).equals("https"));
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void verifyNetworkStatus(String URL) {
		driver.get(URL);
		homePage.clickOnSearch();
		sf.assertTrue(networkStatus(driver.getCurrentUrl())==200);
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void verifyScrollButton(String URL) throws InterruptedException {
		driver.get(URL);
		homePage.clickOnSearch();
		scrollDown();
		homePage.clickOnScroll();
		sf.assertTrue(getWindowPosition()<9, String.valueOf(getWindowPosition()));
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void verifyConsole(String URL)
	{
		driver.get(URL);
		homePage.clickOnSearch();
		sf.assertTrue(consoleResult().equals("null"),consoleResult().toString()); 
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void verifySubdomain(String URL) {
		driver.get(URL);
		homePage.clickOnSearch();
		sf.assertFalse(addSubDomainToUrl(driver.getCurrentUrl()).contains("www"));
		sf.assertAll();
	}
	
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void verifyMetaContent(String URL) {
		driver.get(URL);
		homePage.clickOnSearch();
		String actualSearchPageTitle = driver.getTitle();
		String expectedSearchPageTitle = metaExcel.readMetaContentFromExcel(dfh.readDataFromPropFile("domainMetaContentSheet") ,splitUrlForDomainName(URL),"Search","title");
		sf.assertTrue(actualSearchPageTitle.contains(expectedSearchPageTitle),actualSearchPageTitle+" "+expectedSearchPageTitle);

		String actualSearchPageDesc = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
		String expectedSearchPageDesc = metaExcel.readMetaContentFromExcel(dfh.readDataFromPropFile("domainMetaContentSheet") ,splitUrlForDomainName(URL),"Search","description");
		sf.assertTrue(actualSearchPageDesc.contains(expectedSearchPageDesc),actualSearchPageDesc+" "+expectedSearchPageDesc);
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void verifyHttpInPageSource(String URL) 
	{
	driver.get(URL);
	homePage.clickOnSearch();
	sf.assertFalse(isHttpInPageSource(driver));
	sf.assertAll();
	}
	
	
	
// view page source
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void verifyTitleRepetition(String URL) 
	{
	driver.get(URL);
	homePage.clickOnSearch();
	sf.assertTrue(driver.getTitle().equals(baseElements.getMetaTitle()) && driver.getTitle().equals(baseElements.getOgTitle()));
	sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void verifyDescRepetition(String URL) 
	{
	driver.get(URL);
	homePage.clickOnSearch();
	sf.assertTrue(baseElements.getMetaDesc().equals(baseElements.getOgDesc()));
	sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void verifyOgUrlRepetition(String URL) 
	{
	driver.get(URL);
	homePage.clickOnSearch();
	sf.assertTrue(driver.getCurrentUrl().equals(baseElements.getOgUrl()) && baseElements.getCanonical().equals(driver.getCurrentUrl()),"og url: "+baseElements.getOgUrl()+" canonical: "+baseElements.getCanonical());
	sf.assertAll();
	}
	
	
}

