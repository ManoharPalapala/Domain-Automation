// Only applicable for & and '
// & can be modified either as 'html entity (&amp;)' or as 'and' in same string not both
// returns original string if both actual and expected are same

package com.domainAutomation.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class MetaHelper {
	
//	public static MetaHelper m = new MetaHelper();
	
//htmlEntityToChar.put("&amp;", "&");
//htmlEntityToChar.put("&", "and");
//htmlEntityToChar.put("&#39;", "'");
//htmlEntityToChar.put("&#8209;", "-");
	
	public static void main(String[] args) {

		System.out.println(MetaHelper.charReplacer("Clear Guidelines: Our & Condition's Explained in - location", "Clear Guidelines: Our and Condition&#39;s Explained in &#8209; location"));
//		System.out.println(MetaHelper.charReplacer("Setting the Standard: Our Terms of Service", "Setting the Standard: Our Terms of Use"));
	}

	
	
	public static String charReplacer(String actual, String expected ) {
		
		String replacedString=actual;
		String outputString=null;

		Map<String, String> words = MetaHelper.wordsChecker(actual, expected);
		
//		if(actual.length()!=expected.length()) {
//			outputString2 = "Length of Actual & Expected is not equal";
//		}else 
		if(words.isEmpty()) {
			outputString = actual;
		}else {
			for(Map.Entry<String, String> e:words.entrySet()) {
				System.out.println("This is mapping "+e.getKey()+"   "+e.getValue());
				
				if(e.getKey().contains("&")) {
					if(e.getValue().contains("&amp;")) {
						outputString = replacedString.replace("&", "&amp;");
					}else if(e.getValue().contains("and")) {
						outputString = replacedString.replace("&", "and");
					}
				} 
				
				if(e.getKey().contains("'")) {
					if(e.getValue().contains("&#39;")) {
						outputString = replacedString.replace("'", "&#39;");
					}
				}
				
				if(e.getKey().contains("Service")) {
					if(e.getValue().contains("Use")) {
						outputString = replacedString.replace("service", "use");
					}
				}

//				else {
//					System.out.println(e.getKey().getClass().getName()); //gets the type of key
//				}
//				else {
//					outputString = "Other characters defined";
//				}
				
			}
		}
		
		return outputString;
	}
		
		
	
	
	
	private static Map<String, String> wordsChecker(String actual, String expected) {
		
		Map<String,String> misMatchedWords = new LinkedHashMap<String,String>();
		
		String[] actualStringWords = actual.split(" ");
		String[] expectedStringWords = expected.split(" ");
		 
		int wordCount = Math.min(actualStringWords.length, expectedStringWords.length);
		
		for(int i=0;i<wordCount;i++) {
			if(!actualStringWords[i].equals(expectedStringWords[i])) {
				misMatchedWords.put(actualStringWords[i],expectedStringWords[i]);
			}}
		
		return misMatchedWords;
}
	
	
	
	
	
	
	
}
