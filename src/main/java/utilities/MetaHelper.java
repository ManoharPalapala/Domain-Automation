// Only applicable for & and '
// & can be modified either as 'html entity (&amp;)' or as 'and' in same string not both
// returns original string if both actual and expected are same

package utilities;

import java.util.LinkedHashMap;
import java.util.Map;

public class MetaHelper {

	
	public static String charReplacer(String actual, String expected ) {
		
		Map<String, String> words = MetaHelper.wordsChecker(actual, expected);
		
		if(!(words.isEmpty())) {
			for(Map.Entry<String, String> e:words.entrySet()) {
				
				actual = actual.replace(e.getKey(), e.getValue());
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
			}}
		
		return misMatchedWords;
}
	
	
	
	
	
	
	
}
//if(e.getKey().contains("&")) {
//if(e.getValue().contains("&amp;")) {
//	outputString = replacedString.replace("&", "&amp;");
//}else if(e.getValue().contains("and")) {
//	outputString = replacedString.replace("&", "and");
//}else if(e.getValue().contains("of")) {
//	outputString = replacedString.replace("&", "of");
//}
//} 
//
//if(e.getKey().contains("'")) {
//if(e.getValue().contains("&#39;")) {
//	outputString = replacedString.replace("'", "&#39;");
//}
//}
//
//if(e.getKey().contains("Conditions")) {
//if(e.getValue().contains("Use")) {
//	outputString = replacedString.replace("Conditions", "Use");
//}
//}
	
	
