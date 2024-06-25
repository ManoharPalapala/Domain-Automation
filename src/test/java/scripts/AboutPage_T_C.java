package scripts;

import dataProvider.DataSupplier;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class AboutPage_T_C extends MultiExecution_T_C{


    @Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
    public void verifyPageHeading(String URL) {
        driver.get(URL);
        homePage.clickOnAbout();
        sf.assertTrue(baseElements.getPageHeading().equalsIgnoreCase("About")|baseElements.getPageHeading().equalsIgnoreCase("About Us"),
                baseElements.getPageHeading());
        sf.assertAll();
    }


    // Multiple execution

    @Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
    public void verifyProtocol(String URL) throws Throwable {
        driver.get(URL);
        homePage.clickOnAbout();
        sf.assertTrue(urlProtocolType(URL).equals("https"));
        sf.assertAll();
    }

    @Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
    public void verifyNetworkStatus(String URL) {
        driver.get(URL);
        homePage.clickOnAbout();
        sf.assertTrue(networkStatus(driver.getCurrentUrl()) == 200);
        sf.assertAll();
    }

    @Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
    public void verifyConsole(String URL) {
        driver.get(URL);
        homePage.clickOnAbout();
        sf.assertTrue(consoleResult().equals("null"), "Result is: "+consoleResult());
        sf.assertAll();
    }

    @Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
    public void verifySubdomain(String URL) {
        driver.get(URL);
        homePage.clickOnAbout();
        sf.assertFalse(addSubDomainToUrl(driver.getCurrentUrl()).contains("www"));
        sf.assertAll();
    }

    @Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
    public void verifyMetaContent(String URL) {
        driver.get(URL);
        homePage.clickOnAbout();
        String actualAboutPageTitle = driver.getTitle();
        String expectedAboutPageTitle = metaExcel.readMetaContentFromExcel(
                ph.readDataFromPropFile("domainMetaContentSheet"), splitUrlForDomainName(URL), "About",
                "title");
        sf.assertTrue(actualAboutPageTitle.contains(charReplacer(actualAboutPageTitle, expectedAboutPageTitle)),
                actualAboutPageTitle + " " + expectedAboutPageTitle);

        String actualAboutPageDesc = driver.findElement(By.xpath("//meta[@name='description']"))
                .getAttribute("content");
        String expectedAboutPageDesc = metaExcel.readMetaContentFromExcel(
                ph.readDataFromPropFile("domainMetaContentSheet"), splitUrlForDomainName(URL), "About",
                "description");
        sf.assertTrue(actualAboutPageDesc.contains(charReplacer(actualAboutPageDesc, expectedAboutPageDesc)),
                actualAboutPageDesc + " " + expectedAboutPageDesc);
        sf.assertAll();
    }

    @Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
    public void verifyHttpInPageSource(String URL) {
        driver.get(URL);
        homePage.clickOnAbout();
        sf.assertFalse(isHttpInPageSource(driver));
        sf.assertAll();
    }

// view page source

    @Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
    public void verifyTitleRepetition(String URL) {
        driver.get(URL);
        homePage.clickOnAbout();
        sf.assertTrue(driver.getTitle().equals(baseElements.getMetaTitle())
                && driver.getTitle().equals(baseElements.getOgTitle()),"og:title: "+baseElements.getOgTitle()+"\n"+"meta:title: "+baseElements.getMetaTitle()+"\n"+"title: "+driver.getTitle());
        sf.assertAll();
    }

    @Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
    public void verifyDescRepetition(String URL) {
        driver.get(URL);
        homePage.clickOnAbout();
        sf.assertTrue(baseElements.getMetaDesc().equals(baseElements.getOgDesc()));
        sf.assertAll();
    }

    @Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
    public void verifyOgUrlRepetition(String URL) {
        driver.get(URL);
        homePage.clickOnAbout();
        sf.assertTrue(
                driver.getCurrentUrl().equals(baseElements.getOgUrl())
                        && baseElements.getCanonical().equals(driver.getCurrentUrl()),
                "og url: " + baseElements.getOgUrl() + " canonical: " + baseElements.getCanonical());
        sf.assertAll();
    }
}
