package scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dataProvider.DataSupplier;

public class HomePage_T_C extends MultiExecution_T_C{
	

		@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
		public void verifyRobotsBeforeSubmission(String URL) 
		{
			driver.get(URL+"/robots.txt");
			String robotsStatus = driver.findElement(By.tagName("pre")).getText();
			sf.assertTrue(robotsStatus.toLowerCase().contains("disallow"));
			sf.assertAll();
		}

	@Parameters
		@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
		public void verifySitemap(String URL)
		{
			driver.get(URL+"/sitemap.xml");
			sf.assertTrue(URL.equals(driver.findElement(By.xpath("(//*[contains(text(),'https:')])[2]")).getText()+"/"));
		}
		
		
		
		
		
		
		
// Multiple execution	
		
		@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
		public void verifyProtocol(String URL) throws Throwable
		{
			driver.get(URL);
			sf.assertTrue(urlProtocolType(URL).equals("https"));
			sf.assertAll();
		}
		
		@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
		public void verifyNetworkStatus(String URL) {
			driver.get(URL);
			sf.assertTrue(networkStatus(driver.getCurrentUrl())==200);
			sf.assertAll();
		}
		

		@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
		public void verifyScrollButton(String URL) throws InterruptedException {
			driver.get(URL);
			scrollDown();
			homePage.clickOnScroll();
			homePage.waitUsingAttribute(homePage.getScrollButton(),"class","d-none");
			sf.assertTrue(getWindowPosition()<9, String.valueOf(getWindowPosition()));
			sf.assertAll();
			}

		
		@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
		public void verifyConsole(String URL)
		{
			driver.get(URL);
			sf.assertTrue(consoleResult().equals("null"),consoleResult()); 
			sf.assertAll();
		}
		

		@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
		public void verifySubdomain(String URL)
		{
			driver.get(URL);
			sf.assertFalse(addSubDomainToUrl(driver.getCurrentUrl()).contains("www"));
			sf.assertAll();
		}
	
		
		@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
		public void verifyMetaContent(String URL) {
			driver.get(URL);
			String actualHomePageTitle = driver.getTitle();
			String expectedHomePageTitle = metaExcel.readMetaContentFromExcel(dfh.readDataFromPropFile("domainMetaContentSheet") ,splitUrlForDomainName(URL),"Home page","title");
			sf.assertTrue(actualHomePageTitle.contains(expectedHomePageTitle),actualHomePageTitle+" "+expectedHomePageTitle);

			String actualHomePageDesc = driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
			String expectedHomePageDesc = metaExcel.readMetaContentFromExcel(dfh.readDataFromPropFile("domainMetaContentSheet") ,splitUrlForDomainName(URL),"Home page","description");
			sf.assertTrue(actualHomePageDesc.contains(expectedHomePageDesc),actualHomePageDesc+" "+expectedHomePageDesc);
			sf.assertAll();
		}
		
		
		@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
		public void verifyHttpInPageSource(String URL) 
		{
		driver.get(URL);
		sf.assertFalse(isHttpInPageSource(driver));
		sf.assertAll();
		}
		
// view page source
		
		
		@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
		public void verifyTitleRepetition(String URL) 
		{
		driver.get(URL);
		sf.assertTrue(driver.getTitle().equals(baseElements.getMetaTitle()) && driver.getTitle().equals(baseElements.getOgTitle()));
		sf.assertAll();
		}
		
		@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
		public void verifyDescRepetition(String URL) 
		{
		driver.get(URL);
		sf.assertTrue(baseElements.getMetaDesc().equals(baseElements.getOgDesc()));
		sf.assertAll();
		}
		
		@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
		public void verifyDomainPipeline(String URL) 
		{
		driver.get(URL);
		String[] title = driver.getTitle().split("\\s*\\|\\s*");  //removing trailing spaces around pipeline   
		sf.assertTrue(baseElements.getDomainName().equals(title[1]));
		sf.assertAll();
		}
		
		@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
		public void verifyOgSiteName(String URL) 
		{
		driver.get(URL);
		sf.assertTrue(baseElements.getDomainName().replace(" ","").equals(baseElements.getOgSiteName()),"Domain name:"+baseElements.getDomainName()+" og:sitename: "+baseElements.getOgSiteName());
		sf.assertAll();
		}
		
		@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
		public void verifyOgUrlRepetition(String URL) 
		{
		driver.get(URL);
		sf.assertTrue(driver.getCurrentUrl().equals(baseElements.getOgUrl()+"/") && baseElements.getCanonical().equals(driver.getCurrentUrl()),"og url: "+baseElements.getOgUrl()+" canonical: "+baseElements.getCanonical());
		sf.assertAll();
		}
		
		@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
		public void verifyOgImageNetwork(String URL) 
		{
		driver.get(URL);
		sf.assertTrue(networkStatus(baseElements.getOgImage())==200 && baseElements.getOgImage().contains(".webp"));
		sf.assertAll();
		}
		
		@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
		public void verifyOgImageRepetition(String URL) 
		{
		driver.get(URL);
		sf.assertTrue(baseElements.getOgImage().equals(baseElements.getLogoPath()));
		sf.assertAll();
		}
		
		@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
		public void verifyFaviconNetwork(String URL) 
		{
		driver.get(URL);
		sf.assertTrue(networkStatus(baseElements.getFavicon())==200);
		sf.assertAll();
		}
		
		@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
		public void verifyImageFormat(String URL) 
		{
		driver.get(URL);
		for(WebElement imgContent:baseElements.getImages()) 
			{
				sf.assertTrue(imgContent.getAttribute("src").contains(".webp"),imgContent.getAttribute("src"));	
			}
		sf.assertAll();
		}
	
		@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
		public void verifyFolderNameInPath(String URL) 
		{
		driver.get(URL);
		
		sf.assertTrue(baseElements.getOgImage().contains(baseElements.getFolderNameInPath()));
		
		for(WebElement externalScript:baseElements.getExternalScripts()) 
		{
			sf.assertTrue(externalScript.getAttribute("src").contains((CharSequence) baseElements.getFolderNameInPath()),"external scripts path doesn't contain folder name");	
		}
		
		for(WebElement styleSheet:baseElements.getStyleSheets()) 
		{
			sf.assertTrue(styleSheet.getAttribute("href").contains(baseElements.getFolderNameInPath()),"style sheets path doesn't contain folder name");
		}
		
		for(WebElement imgContent:baseElements.getImages()) 
		{
			sf.assertTrue(imgContent.getAttribute("src").contains(baseElements.getFolderNameInPath()),"images path doesn't contain folder name");	
		}
		
		sf.assertAll();
		}
		
		@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
		public void verifyDomainViewPort(String URL) 
		{
		driver.get(URL);
		sf.assertTrue(baseElements.getMetaViewPort().equals("width=device-width, minimum-scale=1, initial-scale=1"));	
		sf.assertAll();
		}
		
		@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
		public void verifyStyleSheets(String URL) 
		{
		driver.get(URL);
		String mainWindow = driver.getWindowHandle();
		for(WebElement styleSheet:baseElements.getStyleSheets()) 
		{
			String hrefUrl = styleSheet.getAttribute("href");
			driver.switchTo().newWindow(WindowType.TAB);
			driver.get(hrefUrl);
			sf.assertFalse(driver.getPageSource().contains(".map"));
			driver.close();
			driver.switchTo().window(mainWindow);
		}
		sf.assertAll();
		}
		
		
		
		
		
		
		
		
		
		
	}

	
	

