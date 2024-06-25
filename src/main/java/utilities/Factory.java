package utilities;

import java.util.LinkedHashMap;
import java.util.Map;

public class Factory {


//	public static void main(String[] args) {
//	System.out.println(charReplacer("Your Responsibilities, Our Commitment - Learn Our Terms &and; conditions", "Your Responsibilities, Our Commitment - Learn Our Terms & Conditions"));
//	}

	
	public static String charReplacer(String actual, String expected ) {

		Map<String, String> words = Factory.wordsChecker(actual, expected);
	
		if(!(words.isEmpty())) {
			
			if(actual.split(" ").length==expected.split(" ").length) {
				for(Map.Entry<String, String> e:words.entrySet()) {
					
					actual = actual.replace(e.getKey(), e.getValue());
				}
			}
			
		}
		return actual;
	}
	

	
	
	private static Map<String, String> wordsChecker(String actual, String expected) {
		
		Map<String,String> misMatchedWords = new LinkedHashMap<String,String>();
		
		String[] actualStringWords = actual.split(" ");
		String[] expectedStringWords = expected.split(" ");
		 
		int wordCount = Math.min(actualStringWords.length, expectedStringWords.length);
		
		for(int i=0;i<wordCount;i++) {
			if(!actualStringWords[i].equals(expectedStringWords[i])) {
				misMatchedWords.put(actualStringWords[i],expectedStringWords[i]);
			}
			}
		
		return misMatchedWords;
	}

	public void getCurrentTimeUnFormatted(){
		long end = System.currentTimeMillis()+(1000*60*5);
		System.out.println(end);
		while(end>=System.currentTimeMillis()) {
			if (System.currentTimeMillis() % 2 == 0) {
				System.out.println(System.currentTimeMillis());
			}
		}
	}










}