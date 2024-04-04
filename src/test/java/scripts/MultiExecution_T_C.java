package scripts;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;




public class MultiExecution_T_C extends Base{
	
//	public static void main(String[] args) throws Throwable {
//		
//		MultiExecution_T_C m = new MultiExecution_T_C();
//		m.pageHeading("https://desiredcareerz.com/terms-of-service", "Terms of Service");
//	}
	
	private Set<String> inputSetForDuplicates;
	private Set<String> outputSetForDuplicates;
	boolean result;
	
	
	
	
	

	// --------------------------------------------------------------------------------------------------------------------------
	// methods	
		
		public String splitUrlForDomainName(String domainUrl) {
			String[] splittedUrl = domainUrl.split("/");
			return splittedUrl[2];
		}
		
		
		public int getWindowPosition(){
			WebElement header = driver.findElement(By.tagName("nav"));
		    return header.getRect().y;
		}
		
		public void scrollDown() 
		
		{
//			js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
//	      jsExecutor.executeScript("window.scrollBy(0,350)", "");
		     
			new Actions(driver).scrollByAmount(0, 1000).perform();
		}
		
		
		public int wordCounter(String keyword, String city, String stateCode, String stateName) {
			
			int initialCount=0;
			int count=0;
				
			for(String e:keyword.split(" ")) {
					
				if(keyword.contains(city) && (keyword.contains(stateCode) || keyword.contains(stateName))) {
					if(e.equals(city) || e.equals(stateCode) || e.equals(stateName)) {
						initialCount++;
							if(initialCount>2) {count=initialCount;}
					}
				}
					else if(e.equals(city) || e.equals(stateCode) || e.equals(stateName)) {
						count++;
					}
				}
				
			return count;
		}
		
		public String charReplacer(String actual, String expected ) {
			
			Map<String, String> words = wordsChecker(actual, expected);
			
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
		
		
		public Set<String> duplicateChecker(String[] input) {
			inputSetForDuplicates=new HashSet<>();
			outputSetForDuplicates=new HashSet<>();
			for(String x:input) {
				if(!(inputSetForDuplicates.contains(x))) {
					inputSetForDuplicates.add(x);
				}else {
					outputSetForDuplicates.add(x);
				}
			}
			return outputSetForDuplicates;
		}
		
		
		public boolean checkWordsInContent (String content, String[] words) {
			for(String word:words) {
				if(content.contains(word)) {
					result=true;
				}
			}
			return result;
		}




}
	

