package com.domains.automation;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.domain.DataSupplier.DataSupplier;

import io.github.bonigarcia.wdm.WebDriverManager;


public class MultiExecution_T_C{

	
	WebDriver driver;
	JavascriptExecutor js;
	URL url;
	SoftAssert sf;

	@BeforeMethod
	public void testSetup() {
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		js = (JavascriptExecutor)driver;
		sf = new SoftAssert();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void verifyProtocol(String URL) throws Throwable {
		
// behavior
		URL url = new URL(URL);
		String protocol = url.getProtocol();
		String actualProtocol=null;;
		switch(protocol) {
		case "https":  actualProtocol = protocol;
		case "http": actualProtocol = protocol;
		}
// assertions
		sf.assertTrue(actualProtocol.equals("https"));
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void verifyNetworkStatus(String URL){
		
// declarations
		int actualStatusCode = 0;
// behavior		
		try {
			URL url = new URL(URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			int statusCode = connection.getResponseCode();
			switch(statusCode) {
			case 200: actualStatusCode=200;
			break;
			case 400: actualStatusCode=400;
			break;
			}
		} catch (Exception e) {
			e.printStackTrace();}
// assertions
		sf.assertTrue(actualStatusCode==200);
		sf.assertAll();
	}
	











}
	

