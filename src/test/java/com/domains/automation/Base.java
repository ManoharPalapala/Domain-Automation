package com.domains.automation;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

import PageElements.HomePageElements;
import PageElements.SearchPageElements;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	WebDriver driver;
	HomePageElements homePage;
	SearchPageElements searchPage;
	JavascriptExecutor js;
	SoftAssert sf;
	static String result;
	
	public static void main(String[] args) {
	
		
		
	}
	
	public WebDriver setUp(WebDriver driver){
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
}
	
