package scripts;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import dataProvider.DataSupplier;

public class TermsPage_T_C extends MultiExecution_T_C {

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyStaticContent(String URL) {
		driver.get(URL);
		homePage.clickOnTerms();
		sf.assertTrue(findWordsInContent(baseElements.getPageContent(), staticWords));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyPageHeading(String URL) {
		driver.get(URL);
		homePage.clickOnTerms();
		sf.assertTrue(pageHeading(homePage.getTermsPageUrl(), baseElements.getPageHeading()) & baseElements.getPageHeading().equalsIgnoreCase(charReplacer(baseElements.getPageHeading(),homePage.getTermsPageText())),
				"page heading: "+baseElements.getPageHeading()+"\n"+"url: "+homePage.getTermsPageUrl()+"\n"+"url text: "+homePage.getTermsPageText());
		sf.assertAll();
	}

// Multiple execution	

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyProtocol(String URL) throws Throwable {
		driver.get(URL);
		homePage.clickOnTerms();
		sf.assertTrue(urlProtocolType(URL).equals("https"));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyNetworkStatus(String URL) {
		driver.get(URL);
		homePage.clickOnTerms();
		sf.assertTrue(networkStatus(driver.getCurrentUrl()) == 200);
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyConsole(String URL) {
		driver.get(URL);
		homePage.clickOnTerms();
		sf.assertTrue(consoleResult().equals("null"), consoleResult());
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifySubdomain(String URL) {
		driver.get(URL);
		homePage.clickOnTerms();
		sf.assertFalse(addSubDomainToUrl(driver.getCurrentUrl()).contains("www"));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyMetaContent(String URL) {
		driver.get(URL);
		homePage.clickOnTerms();
//		String actualTermsPageTitle = driver.getTitle();
//		String expectedTermsPageTitle = metaExcel.readMetaContentFromExcel(
//				ph.readDataFromPropFile("domainMetaContentSheet"), splitUrlForDomainName(URL), "Terms & Conditions",
//				"title");
//		sf.assertTrue(actualTermsPageTitle.contains(charReplacer(actualTermsPageTitle, expectedTermsPageTitle)),
//				actualTermsPageTitle + " " + charReplacer(actualTermsPageTitle, expectedTermsPageTitle));

		String actualTermsPageDesc = driver.findElement(By.xpath("//meta[@name='description']"))
				.getAttribute("content");
		String expectedTermsPageDesc = metaExcel.readMetaContentFromExcel(
				ph.readDataFromPropFile("domainMetaContentSheet"), splitUrlForDomainName(URL), "Terms & Conditions",
				"description");
		System.out.println("actual " + actualTermsPageDesc);
		System.out.println("expected " + expectedTermsPageDesc);
		sf.assertTrue(actualTermsPageDesc.contains(charReplacer(actualTermsPageDesc, expectedTermsPageDesc)),
				actualTermsPageDesc + " " + charReplacer(actualTermsPageDesc, expectedTermsPageDesc));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyHttpInPageSource(String URL) {
		driver.get(URL);
		homePage.clickOnTerms();
		sf.assertFalse(isHttpInPageSource(driver));
		sf.assertAll();
	}

// view page source

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyTitleRepetition(String URL) {
		driver.get(URL);
		homePage.clickOnTerms();
		sf.assertTrue(driver.getTitle().equals(baseElements.getMetaTitle())
				&& driver.getTitle().equals(baseElements.getOgTitle()),"og:title: "+baseElements.getOgTitle()+"\n"+"meta:title: "+baseElements.getMetaTitle()+"\n"+"title: "+driver.getTitle());
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyDescRepetition(String URL) {
		driver.get(URL);
		homePage.clickOnTerms();
		sf.assertTrue(baseElements.getMetaDesc().equals(baseElements.getOgDesc()));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyOgUrlRepetition(String URL) {
		driver.get(URL);
		homePage.clickOnTerms();
		sf.assertTrue(
				driver.getCurrentUrl().equals(baseElements.getOgUrl())
						&& baseElements.getCanonical().equals(driver.getCurrentUrl()),
				"og url: " + baseElements.getOgUrl() + " canonical: " + baseElements.getCanonical());
		sf.assertAll();
	}

}
