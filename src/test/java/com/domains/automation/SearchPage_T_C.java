package com.domains.automation;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.domain.DataSupplier.DataSupplier;
import com.domainAutomation.utils.Utils;

import PageElements.HomePageElements;
import PageElements.SearchPageElements;

public class SearchPage_T_C extends Base{

	
	@BeforeMethod
	public void testSetup() {

		this.driver=setUp(driver);
		homePage = new HomePageElements(driver);
		searchPage = new SearchPageElements(driver);
		sf = new SoftAssert();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void validateCompanyFilter(String URL) {
		driver.get(URL);
		homePage.clickOnSearch();
		searchPage.clickOnCompanyFilter();
		Select sel = new Select(searchPage.selectCompanyOption());
		sel.selectByIndex(1);
		searchPage.clickOnSearch();
		String selected = searchPage.selectedCompany();
		String output = searchPage.searchResult(); 
		sf.assertTrue(output.contains(selected));
		sf.assertAll();
		}
	
	
	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void verifyClearButton(String URL) {
		driver.get(URL);
		homePage.clickOnSearch();
		searchPage.sendKeysToTitleFiled("driver");
		searchPage.clickOnSearch();
		searchPage.clickOnClearSearch();
		sf.assertTrue(searchPage.titleInputField().isEmpty()); 
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void validateMinimumJobCount(String URL) {
		driver.get(URL);
		homePage.clickOnSearch();
		sf.assertTrue(String.valueOf(searchPage.totalApplyLinks()).equals(Utils.readDataFromPropFile("minimumJobCountinSearch")),String.valueOf(searchPage.totalApplyLinks())+""+Utils.readDataFromPropFile("minimumJobCountinSearch"));	
		sf.assertAll();
	}
	
	
	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void validateTotalJobCount(String URL) {
		driver.get(URL);
		homePage.clickOnSearch();
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
		homePage.clickOnSearch();
		String applyButtonUrl=searchPage.applyButton().getAttribute("href");
		searchPage.clickOnApply();
		sf.assertTrue((driver.getCurrentUrl()).equals(applyButtonUrl));
		sf.assertAll();
	}
	

	
	
	
	
	
	
	
	
	
	
	
}

