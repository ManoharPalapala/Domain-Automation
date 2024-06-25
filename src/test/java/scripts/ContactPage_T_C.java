package scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import dataProvider.DataSupplier;
import pageElements.ContactPageElements;

public class ContactPage_T_C extends MultiExecution_T_C{


	@Test(dataProvider = "supplier", dataProviderClass = DataSupplier.class)
	public void verifyPageHeading(String URL) {
		driver.get(URL);
		homePage.clickOnContact();
		sf.assertTrue(baseElements.getPageHeading().equalsIgnoreCase("Contact")|baseElements.getPageHeading().equalsIgnoreCase("Contact Us"),"Name in h1 tag"+baseElements.getPageHeading());
		sf.assertAll();
	}

	@Test(dataProvider="supplier", dataProviderClass=DataSupplier.class)
	public void verifyMailId(String URL) 
	{
	driver.get(URL);
	ContactPageElements contactPage = homePage.clickOnContact();
	sf.assertTrue(contactPage.mailIdText().contains(splitUrlForDomainName(URL))); 
	sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass=DataSupplier.class)
	public void validateErrorMessages(String URL) 
	{
	driver.get(URL);
	ContactPageElements contactPage = homePage.clickOnContact();
	contactPage.submitclick();
	sf.assertTrue(contactPage.nameErrorMessage().equals("Please enter name"),"Name error is not matching");
	sf.assertTrue(contactPage.subjectErrorMessage().equals("Please enter subject"),"Subject error is not matching");
	sf.assertTrue(contactPage.mailErrorMessage().equals("Please enter email"),"Mail error is not matching");
	sf.assertTrue(contactPage.phoneErrorMessage().equals("Enter your contact number") || contactPage.phoneErrorMessage().equals("Please enter phone number"),"Phone error is not matching");
	sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass=DataSupplier.class)
	public void clientSideValidation(String URL) 
	{
	driver.get(URL);
	ContactPageElements contactPage = homePage.clickOnContact();
	contactPage.nameInputField().sendKeys("ab");
	contactPage.nameInputField().sendKeys(Keys.TAB);
	contactPage.subjectInputField().sendKeys("ab");
	contactPage.subjectInputField().sendKeys(Keys.TAB);
	contactPage.emailInputField().sendKeys("ab@mail");
	contactPage.emailInputField().sendKeys(Keys.TAB);
	contactPage.phoneInputField().sendKeys("000000000");
	contactPage.phoneInputField().sendKeys(Keys.TAB);
	sf.assertTrue(contactPage.nameErrorMessage().equals("Name should be at least 3 characters"));
	sf.assertTrue(contactPage.subjectErrorMessage().equals("Subject should be at least 3 characters"));
	sf.assertTrue(contactPage.mailErrorMessage().equals("Please Enter a Valid Email Address"));
	sf.assertTrue(contactPage.phoneErrorMessage().equals("Mobile Number is invalid"));
	sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass=DataSupplier.class)
	public void serverSideValidationWithInvalidDetails(String URL) 
	{
	driver.get(URL);
	ContactPageElements contactPage = homePage.clickOnContact();
	contactPage.nameInputField().sendKeys("ab");
	contactPage.subjectInputField().sendKeys("ab");
	contactPage.emailInputField().sendKeys("ab@mail");
	contactPage.phoneInputField().sendKeys("000000000");
	contactPage.submitclick();
	sf.assertTrue(contactPage.nameErrorMessage().equals("Name should be at least 3 characters"));
	sf.assertTrue(contactPage.subjectErrorMessage().equals("Subject should be at least 3 characters"));
	sf.assertTrue(contactPage.mailErrorMessage().equals("Please Enter a Valid Email Address"));
	sf.assertTrue(contactPage.phoneErrorMessage().equals("Mobile Number is invalid"));
	sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass=DataSupplier.class)
	public void serverSideValidationWithValidDetails(String URL) 
	{
	driver.get(URL);
	ContactPageElements contactPage = homePage.clickOnContact();
	contactPage.nameInputField().sendKeys("test");
	contactPage.subjectInputField().sendKeys("test");
	contactPage.emailInputField().sendKeys("test@mail.co");
	contactPage.phoneInputField().sendKeys("9000000000");
	contactPage.submitclick();
	sf.assertTrue(contactPage.successMessage().isDisplayed());
	sf.assertAll();
	}
	
	
// Multiple execution

	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void verifyProtocol(String URL) throws Throwable
	{
		driver.get(URL);
		homePage.clickOnContact();
		sf.assertTrue(urlProtocolType(driver.getCurrentUrl()).equals("https"));
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void verifyNetworkStatus(String URL) {
		driver.get(URL);
		homePage.clickOnContact();
		sf.assertTrue(networkStatus(driver.getCurrentUrl())==200);
		sf.assertAll();
	}

	
	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void verifyConsole(String URL)
	{
		driver.get(URL);
		homePage.clickOnContact();
		sf.assertTrue(consoleResult().equals("null"),consoleResult().toString()); 
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void verifySubdomain(String URL) {
		driver.get(URL);
		homePage.clickOnContact();
		sf.assertFalse(addSubDomainToUrl(driver.getCurrentUrl()).contains("www"));
		sf.assertAll();
	}
	
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void verifyMetaContent(String URL) {
		driver.get(URL);
		homePage.clickOnContact();
		String actualContactPageTitle = driver.getTitle();
		String expectedContactPageTitle = metaExcel.readMetaContentFromExcel(ph.readDataFromPropFile("domainMetaContentSheet") ,splitUrlForDomainName(URL),"Contact","title");
		sf.assertTrue(actualContactPageTitle.contains(charReplacer(actualContactPageTitle,expectedContactPageTitle)),actualContactPageTitle+" "+expectedContactPageTitle);

		String actualContactPageDesc = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
		String expectedContactPageDesc = metaExcel.readMetaContentFromExcel(ph.readDataFromPropFile("domainMetaContentSheet") ,splitUrlForDomainName(URL),"Contact","description");
		sf.assertTrue(actualContactPageDesc.contains(charReplacer(actualContactPageDesc,expectedContactPageDesc)),actualContactPageDesc+" "+expectedContactPageDesc);
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void verifyHttpInPageSource(String URL) 
	{
	driver.get(URL);
	homePage.clickOnContact();
	sf.assertFalse(isHttpInPageSource(driver));
	sf.assertAll();
	}
	
	
	
// view page source
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void verifyTitleRepetition(String URL) 
	{
	driver.get(URL);
	homePage.clickOnContact();
	sf.assertTrue(driver.getTitle().equals(baseElements.getMetaTitle()) && driver.getTitle().equals(baseElements.getOgTitle()));
	sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void verifyDescRepetition(String URL) 
	{
	driver.get(URL);
	homePage.clickOnContact();
	sf.assertTrue(baseElements.getMetaDesc().equals(baseElements.getOgDesc()));
	sf.assertAll();
	}
	
	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void verifyOgUrlRepetition(String URL) 
	{
	driver.get(URL);
	homePage.clickOnContact();
	sf.assertTrue(driver.getCurrentUrl().equals(baseElements.getOgUrl()) && baseElements.getCanonical().equals(driver.getCurrentUrl()),"og url: "+baseElements.getOgUrl()+" canonical: "+baseElements.getCanonical());
	sf.assertAll();
	}

	
}
