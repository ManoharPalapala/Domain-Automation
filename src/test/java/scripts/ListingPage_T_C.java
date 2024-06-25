package scripts;

import dataProvider.DataSupplier;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pageElements.JBCPageElements;
import testBase.DriverFactory;

import java.net.URL;
import java.sql.Driver;

public class ListingPage_T_C extends MultiExecution_T_C{

    @Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
    public void verifyDuplicateTitles(String URL){
        driver.get(URL);
        JBCPageElements jbc = homePage.clickOnJobByCompany();
        for(String link:jbc.getCompanyLinks()){
            String parent = DriverFactory.getInstance().openNewTab(link);
            sf.assertTrue(networkStatus(driver.getCurrentUrl())==200,"");
            sf.assertTrue(duplicateChecker(jbc.getJobTitles(driver.getCurrentUrl())).isEmpty(),"Duplicates are present in: "+driver.getCurrentUrl()+duplicateChecker(jbc.getJobTitles(driver.getCurrentUrl())));
            sf.assertFalse(jbc.getJobTitles(driver.getCurrentUrl()).size()>5,"No of job count is more than 5 in: "+driver.getCurrentUrl()+jbc.getJobTitles(driver.getCurrentUrl()));
            DriverFactory.getInstance().switchToParentTab(parent);
        }
    sf.assertAll();
    }

    @Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
    public void verifySoftLogic(String URL) throws InterruptedException {
        driver.get(URL);
        JBCPageElements jbc = homePage.clickOnJobByCompany();
        driver.get(jbc.getCompanyLink()+"1");
        sf.assertTrue(networkStatus(driver.getCurrentUrl())==404);
        sf.assertTrue(jbc.softLogicText().isDisplayed());
        sf.assertAll();
    }


    // Multiple execution

    @Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
    public void verifyProtocol(String URL) throws Throwable {
        driver.get(URL);
        JBCPageElements jbc = homePage.clickOnJobByCompany();
        jbc.clickOnCompany();
        sf.assertTrue(urlProtocolType(driver.getCurrentUrl()).equals("https"));
        sf.assertAll();
    }

    @Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
    public void verifyNetworkStatus(String URL) {
        driver.get(URL);
        JBCPageElements jbc = homePage.clickOnJobByCompany();
        jbc.clickOnCompany();
        sf.assertTrue(networkStatus(driver.getCurrentUrl()) == 200);
        sf.assertAll();
    }

    @Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
    public void verifyConsole(String URL) {
        driver.get(URL);
        JBCPageElements jbc = homePage.clickOnJobByCompany();
        jbc.clickOnCompany();
        sf.assertTrue(consoleResult().equals("null"), consoleResult().toString());
        sf.assertAll();
    }

    @Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
    public void verifySubdomain(String URL) {
        driver.get(URL);
        JBCPageElements jbc = homePage.clickOnJobByCompany();
        jbc.clickOnCompany();
        sf.assertFalse(addSubDomainToUrl(driver.getCurrentUrl()).contains("www"));
        sf.assertAll();
    }

//    @Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
    public void verifyMetaContent(String URL) {
        driver.get(URL);
        JBCPageElements jbc = homePage.clickOnJobByCompany();
        jbc.clickOnCompany();
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
        JBCPageElements jbc = homePage.clickOnJobByCompany();
        jbc.clickOnCompany();
        sf.assertFalse(isHttpInPageSource(driver));
        sf.assertAll();
    }

    // view page source

    @Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
    public void verifyTitleRepetition(String URL) {
        driver.get(URL);
        JBCPageElements jbc = homePage.clickOnJobByCompany();
        jbc.clickOnCompany();
        sf.assertTrue(driver.getTitle().equals(baseElements.getMetaTitle())
                && driver.getTitle().equals(baseElements.getOgTitle()));
        sf.assertAll();
    }

    @Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
    public void verifyDescRepetition(String URL) {
        driver.get(URL);
        JBCPageElements jbc = homePage.clickOnJobByCompany();
        jbc.clickOnCompany();
        sf.assertTrue(baseElements.getMetaDesc().equals(baseElements.getOgDesc()));
        sf.assertAll();
    }

    @Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
    public void verifyOgUrlRepetition(String URL) {
        driver.get(URL);
        JBCPageElements jbc = homePage.clickOnJobByCompany();
        jbc.clickOnCompany();
        sf.assertTrue(
                driver.getCurrentUrl().equals(baseElements.getOgUrl())
                        && driver.getCurrentUrl().equals(baseElements.getCanonical()),
                "og url: " + baseElements.getOgUrl() + " canonical: " + baseElements.getCanonical());
        sf.assertAll();
    }
}
