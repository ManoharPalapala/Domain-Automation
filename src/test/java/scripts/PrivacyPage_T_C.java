package scripts;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import dataProvider.DataSupplier;

public class PrivacyPage_T_C extends MultiExecution_T_C {

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyStaticContent(String URL) {
		driver.get(URL);
		homePage.clickOnPrivacy();
		sf.assertTrue(findWordsInContent(baseElements.getPageContent(), staticWords));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyPageHeading(String URL) {
		driver.get(URL);
		homePage.clickOnPrivacy();
		sf.assertTrue(pageHeading(homePage.getPrivacyPageUrl(), baseElements.getPageHeading()) & baseElements.getPageHeading().equals(homePage.getPrivacyPageText()), "page heading: "+baseElements.getPageHeading()+"\n"+"url: "+homePage.getPrivacyPageUrl()+"url text: "+homePage.getPrivacyPageText());
		sf.assertAll();
	}

// Multiple execution	

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyProtocol(String URL) throws Throwable {
		driver.get(URL);
		homePage.clickOnPrivacy();
		sf.assertTrue(urlProtocolType(URL).equals("https"));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyNetworkStatus(String URL) {
		driver.get(URL);
		homePage.clickOnPrivacy();
		sf.assertTrue(networkStatus(driver.getCurrentUrl()) == 200);
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyConsole(String URL) {
		driver.get(URL);
		homePage.clickOnPrivacy();
		sf.assertTrue(consoleResult().equals("null"), consoleResult());
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifySubdomain(String URL) {
		driver.get(URL);
		homePage.clickOnPrivacy();
		sf.assertFalse(addSubDomainToUrl(driver.getCurrentUrl()).contains("www"));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyMetaContent(String URL) {
		driver.get(URL);
		homePage.clickOnPrivacy();
		String actualPrivacyPageTitle = driver.getTitle();
		String expectedPrivacyPageTitle = metaExcel.readMetaContentFromExcel(
				ph.readDataFromPropFile("domainMetaContentSheet"), splitUrlForDomainName(URL), "Privacy Policy",
				"title");
		sf.assertTrue(actualPrivacyPageTitle.contains(charReplacer(actualPrivacyPageTitle, expectedPrivacyPageTitle)),
				actualPrivacyPageTitle + " " + expectedPrivacyPageTitle);

		String actualPrivacyPageDesc = driver.findElement(By.xpath("//meta[@name='description']"))
				.getAttribute("content");
		String expectedPrivacyPageDesc = metaExcel.readMetaContentFromExcel(
				ph.readDataFromPropFile("domainMetaContentSheet"), splitUrlForDomainName(URL), "Privacy Policy",
				"description");
		sf.assertTrue(actualPrivacyPageDesc.contains(charReplacer(actualPrivacyPageDesc, expectedPrivacyPageDesc)),
				actualPrivacyPageDesc + " " + expectedPrivacyPageDesc);
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyHttpInPageSource(String URL) {
		driver.get(URL);
		homePage.clickOnPrivacy();
		sf.assertFalse(isHttpInPageSource(driver));
		sf.assertAll();
	}

// view page source

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyTitleRepetition(String URL) {
		driver.get(URL);
		homePage.clickOnPrivacy();
		sf.assertTrue(driver.getTitle().equals(baseElements.getMetaTitle())
				&& driver.getTitle().equals(baseElements.getOgTitle()));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyDescRepetition(String URL) {
		driver.get(URL);
		homePage.clickOnPrivacy();
		sf.assertTrue(baseElements.getMetaDesc().equals(baseElements.getOgDesc()));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyOgUrlRepetition(String URL) {
		driver.get(URL);
		homePage.clickOnPrivacy();
		sf.assertTrue(
				driver.getCurrentUrl().equals(baseElements.getOgUrl())
						&& baseElements.getCanonical().equals(driver.getCurrentUrl()),
				"og url: " + baseElements.getOgUrl() + " canonical: " + baseElements.getCanonical());
		sf.assertAll();
	}
}
