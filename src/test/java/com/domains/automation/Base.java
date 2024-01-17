package com.domains.automation;

import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;

public class Base {
	
	public static WebDriver driver;


	public static String splitUrlAndAppendSubDomain(String currentPageUrl) {
		String[] splittedUrl = currentPageUrl.split("//");
		String modifiedPageUrl = splittedUrl[0] + "//www." + splittedUrl[1];
		return modifiedPageUrl;
	}
	
	

	


	}
	
	
