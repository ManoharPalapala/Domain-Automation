package scripts;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import dataProvider.DataSupplier;

public class CookiePage_T_C extends MultiExecution_T_C {

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyStaticContent(String URL) {
		driver.get(URL);
		homePage.clickOnCookie();
		sf.assertTrue(findWordsInContent(baseElements.getPageContent(), staticWords));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyPageHeading(String URL) {
		driver.get(URL);
		homePage.clickOnCookie();
		sf.assertTrue(pageHeading(homePage.getCookiePageUrl(), baseElements.getPageHeading()),
				"page heading: "+baseElements.getPageHeading()+"\n"+"url: "+homePage.getCookiePageUrl()+"\n"+"url text: "+homePage.getCookiePageText());
		sf.assertAll();
	}

// Multiple execution	

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyProtocol(String URL) throws Throwable {
		driver.get(URL);
		homePage.clickOnCookie();
		sf.assertTrue(urlProtocolType(URL).equals("https"));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyNetworkStatus(String URL) {
		driver.get(URL);
		homePage.clickOnCookie();
		sf.assertTrue(networkStatus(driver.getCurrentUrl()) == 200);
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyConsole(String URL) {
		driver.get(URL);
		homePage.clickOnCookie();
		sf.assertTrue(consoleResult().equals("null"), consoleResult());
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifySubdomain(String URL) {
		driver.get(URL);
		homePage.clickOnCookie();
		sf.assertFalse(addSubDomainToUrl(driver.getCurrentUrl()).contains("www"));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyMetaContent(String URL) {
		driver.get(URL);
		homePage.clickOnCookie();
		String actualCookiePageTitle = driver.getTitle();
		String expectedCookiePageTitle = metaExcel.readMetaContentFromExcel(
				ph.readDataFromPropFile("domainMetaContentSheet"), splitUrlForDomainName(URL), "Cookie Policy",
				"title");
		sf.assertTrue(actualCookiePageTitle.contains(charReplacer(actualCookiePageTitle, expectedCookiePageTitle)),
				actualCookiePageTitle + " " + expectedCookiePageTitle);

		String actualCookiePageDesc = driver.findElement(By.xpath("//meta[@name='description']"))
				.getAttribute("content");
		String expectedCookiePageDesc = metaExcel.readMetaContentFromExcel(
				ph.readDataFromPropFile("domainMetaContentSheet"), splitUrlForDomainName(URL), "Cookie Policy",
				"description");
		sf.assertTrue(actualCookiePageDesc.contains(charReplacer(actualCookiePageDesc, expectedCookiePageDesc)),
				actualCookiePageDesc + " " + expectedCookiePageDesc);
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyHttpInPageSource(String URL) {
		driver.get(URL);
		homePage.clickOnCookie();
		sf.assertFalse(isHttpInPageSource(driver));
		sf.assertAll();
	}

// view page source

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyTitleRepetition(String URL) {
		driver.get(URL);
		homePage.clickOnCookie();
		sf.assertTrue(driver.getTitle().equals(baseElements.getMetaTitle())
				&& driver.getTitle().equals(baseElements.getOgTitle()));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyDescRepetition(String URL) {
		driver.get(URL);
		homePage.clickOnCookie();
		sf.assertTrue(baseElements.getMetaDesc().equals(baseElements.getOgDesc()));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyOgUrlRepetition(String URL) {
		driver.get(URL);
		homePage.clickOnCookie();
		sf.assertTrue(
				driver.getCurrentUrl().equals(baseElements.getOgUrl())
						&& baseElements.getCanonical().equals(driver.getCurrentUrl()),
				"og url: " + baseElements.getOgUrl() + " canonical: " + baseElements.getCanonical());
		sf.assertAll();
	}
}
