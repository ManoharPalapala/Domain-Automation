package com.domainAutomation.utils;

import org.openqa.selenium.WebDriver;

public class Helper {

	public static WebDriver driver;
	
	public static String splitUrlAndAppendSubDomain(String currentPageUrl) {
		String[] splittedUrl = currentPageUrl.split("//");
		String modifiedPageUrl = splittedUrl[0] + "//www." + splittedUrl[1];
		return modifiedPageUrl;
	}
	
	public static String splitUrlForMetaContentDomainName(String domainUrl) {
		String[] splittedUrl = domainUrl.split("/");
		String domainNameForMetaContent = splittedUrl[2];
		return domainNameForMetaContent;
	}
}
