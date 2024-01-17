package com.domainAutomation.utils;

public class CharFinder {
	
	
//	public static void main(String[] args) {
//		
//		String input = "Hello#";
//		char[] charArray = {'#','@','%'};
// 		compareEntireString(input,charArray);
//		compareStartAndEndOfString(input,charArray);
//	}
	
	public static void compareEntireString(String text, char[] charactersList) {
		
		for(char c: charactersList) {
			
			if(text.indexOf(c)!=-1){
				System.out.println(text);
			}
	}
	
	}
	
	public static void compareStartAndEndOfString(String text, char[] charactersList) {
		
//		char[] textCharatcers = text.toCharArray();
//		char lastIndex = textCharatcers[-1];

		char firstIndex = text.charAt(0);
		char lastIndex = text.charAt(text.length()-1);
		
		for(char c: charactersList) {
		
			if(firstIndex==c || lastIndex==c) {
				System.out.println(text);
			}
		}
	}
}
