package scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import dataProvider.DataSupplier;
import pageElements.JBCPageElements;
import pageElements.SearchPageElements;
import testBase.DriverFactory;

import java.net.URL;
import java.sql.Driver;
import java.sql.SQLOutput;

public class JBC_T_C extends MultiExecution_T_C {

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyPageHeading(String URL) {
		driver.get(URL);
		homePage.clickOnJobByCompany();
		sf.assertTrue(
				baseElements.getPageHeading().equalsIgnoreCase("Jobs By Company")
				| baseElements.getPageHeading().equalsIgnoreCase("List Of Companies")
				| baseElements.getPageHeading().equalsIgnoreCase("Companies")
						| baseElements.getPageHeading().equalsIgnoreCase("Companies List"),baseElements.getPageHeading());

		sf.assertAll();
	}


	// Multiple execution

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyProtocol(String URL) throws Throwable {
		driver.get(URL);
		homePage.clickOnJobByCompany();
		sf.assertTrue(urlProtocolType(driver.getCurrentUrl()).equals("https"));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyNetworkStatus(String URL) {
		driver.get(URL);
		homePage.clickOnJobByCompany();
		sf.assertTrue(networkStatus(driver.getCurrentUrl()) == 200);
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyConsole(String URL) {
		driver.get(URL);
		homePage.clickOnJobByCompany();
		sf.assertTrue(consoleResult().equals("null"), consoleResult().toString());
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifySubdomain(String URL) {
		driver.get(URL);
		homePage.clickOnJobByCompany();
		sf.assertFalse(addSubDomainToUrl(driver.getCurrentUrl()).contains("www"));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyMetaContent(String URL) {
		driver.get(URL);
		homePage.clickOnJobByCompany();
		String actualJBCPageTitle = driver.getTitle();
		String expectedJBCPageTitle = metaExcel
				.readMetaContentFromExcel(ph.readDataFromPropFile("domainMetaContentSheet"),
						splitUrlForDomainName(URL), "Jobs by company", "title")
				.trim();
		sf.assertTrue(actualJBCPageTitle.contains(charReplacer(actualJBCPageTitle, expectedJBCPageTitle)),
				actualJBCPageTitle + "\n" + charReplacer(actualJBCPageTitle, expectedJBCPageTitle));

		String actualJBCPageDesc = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
		String expectedJBCPageDesc = metaExcel
				.readMetaContentFromExcel(ph.readDataFromPropFile("domainMetaContentSheet"),
						splitUrlForDomainName(URL), "Jobs by company", "description")
				.trim();
		sf.assertTrue(actualJBCPageDesc.contains(charReplacer(actualJBCPageDesc, expectedJBCPageDesc)),
				actualJBCPageDesc + "\n" + charReplacer(actualJBCPageDesc, expectedJBCPageDesc));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyHttpInPageSource(String URL) {
		driver.get(URL);
		homePage.clickOnJobByCompany();
		sf.assertFalse(isHttpInPageSource(driver));
		sf.assertAll();
	}

	// view page source

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyTitleRepetition(String URL) {
		driver.get(URL);
		homePage.clickOnJobByCompany();
		sf.assertTrue(driver.getTitle().equals(baseElements.getMetaTitle())
				&& driver.getTitle().equals(baseElements.getOgTitle()));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyDescRepetition(String URL) {
		driver.get(URL);
		homePage.clickOnJobByCompany();
		sf.assertTrue(baseElements.getMetaDesc().equals(baseElements.getOgDesc()));
		sf.assertAll();
	}

	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyOgUrlRepetition(String URL) {
		driver.get(URL);
		homePage.clickOnJobByCompany();
		sf.assertTrue(
				driver.getCurrentUrl().equals(baseElements.getOgUrl())
						&& driver.getCurrentUrl().equals(baseElements.getCanonical()),
				"og url: " + baseElements.getOgUrl() + " canonical: " + baseElements.getCanonical());
		sf.assertAll();
	}
}
