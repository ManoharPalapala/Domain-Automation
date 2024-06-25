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
		String selected = searchPage.selectedCompany().toLowerCase();
		String output = searchPage.searchResult().toLowerCase();
		sf.assertTrue(output.contains(selected), selected+"\n"+output);
		sf.assertAll();
		}
	
	
	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void verifyClearButton(String URL) {
		driver.get(URL);
		SearchPageElements searchPage = homePage.clickOnSearch();
		searchPage.sendKeysToTitleFiled("driver");
		searchPage.clickOnSearch();
		searchPage.clickOnClearSearch();
		sf.assertTrue(searchPage.titleInputField().isEmpty() && driver.getCurrentUrl().equals(URL+"search"));
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void validateMinimumJobCount(String URL) {
		driver.get(URL);
		SearchPageElements searchPage = homePage.clickOnSearch();
		sf.assertTrue(searchPage.totalApplyLinks() ==Integer.parseInt(ph.readDataFromPropFile("minimumJobCountinSearch")),String.valueOf(searchPage.totalApplyLinks())+"   "+ph.readDataFromPropFile("minimumJobCountinSearch"));	
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
		sf.assertTrue((driver.getCurrentUrl()).equals(applyButtonUrl),applyButtonUrl);
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
		String expectedSearchPageTitle = metaExcel.readMetaContentFromExcel(ph.readDataFromPropFile("domainMetaContentSheet") ,splitUrlForDomainName(URL),"Search","title").trim();
		sf.assertTrue(actualSearchPageTitle.contains(charReplacer(actualSearchPageTitle,expectedSearchPageTitle)),actualSearchPageTitle+"\n"+charReplacer(actualSearchPageTitle,expectedSearchPageTitle));
		System.out.println(actualSearchPageTitle);
		System.out.println(charReplacer(actualSearchPageTitle,expectedSearchPageTitle));

		String actualSearchPageDesc = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
		String expectedSearchPageDesc = metaExcel.readMetaContentFromExcel(ph.readDataFromPropFile("domainMetaContentSheet") ,splitUrlForDomainName(URL),"Search","description").trim();
		sf.assertTrue(actualSearchPageDesc.contains(charReplacer(actualSearchPageDesc,expectedSearchPageDesc)),actualSearchPageDesc+"\n"+charReplacer(actualSearchPageDesc,expectedSearchPageDesc));
		System.out.println(actualSearchPageDesc);
		System.out.println(charReplacer(actualSearchPageDesc,expectedSearchPageDesc));

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

