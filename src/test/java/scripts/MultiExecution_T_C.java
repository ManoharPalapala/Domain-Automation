package scripts;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;

import testBase.Base;

public class MultiExecution_T_C extends Base {

//	public static void main(String[] args) throws Throwable {
//		MultiExecution_T_C m = new MultiExecution_T_C();
//		System.out.println(m.pageHeading("https://hopefulhirings.com/terms-and-conditions","Terms & Conditions"));
//
////		System.out.println(m.charReplacer(
////				"Learn about our terms of service. Understand the guidelines and rules for using our job search platform.",
////				"Learn about our terms and conditions. Understand the guidelines and rules for using our job search platform."));
//
//	}

	public String urlProtocolType(String URL) throws Throwable {

		URL url = new URL(URL);
		return url.getProtocol();

	}

	public int networkStatus(String URL) {

		int statusCode = 0;
		try {
			URL url = new URL(URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			statusCode = connection.getResponseCode();
		} catch (Exception e) {
//			e.printStackTrace();
			e.getMessage();
			System.out.println("Failed$");
		}
		return statusCode;

	}

	public String consoleResult() {

		String msg = "null";
		LogEntries logs = driver.manage().logs().get("browser");

		for (org.openqa.selenium.logging.LogEntry entry : logs) {
			if (entry.getLevel() == Level.SEVERE) {
				msg = entry.getMessage();
			}
		}
		return msg;
	}

	public String addSubDomainToUrl(String currentPageUrl) {

		String[] splittedUrl = currentPageUrl.split("//");
		String modifiedPageUrl = splittedUrl[0] + "//www." + splittedUrl[1];
		driver.get(modifiedPageUrl);
		return driver.getCurrentUrl();

	}

	public boolean isHttpInPageSource(WebDriver driver) {
		return driver.getPageSource().contains("http:");
	}


	public boolean pageHeading(String url, String heading) {

		String[] pageUrl = url.split("/");
		if (heading.contains(" ")) {
			heading = heading.replace(" ", "-");
		}
		return charReplacer(heading,pageUrl[3]).equalsIgnoreCase(heading);
	}

//-------------------------------------------------------------------------------------------------------------------------------

	public String splitUrlForDomainName(String domainUrl) {
		String[] splittedUrl = domainUrl.split("/");
		return splittedUrl[2];
	}

	public int getWindowPosition() {
		WebElement header = driver.findElement(By.tagName("nav"));
		return header.getRect().y;
	}

	public void scrollDown() {
//			js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
//	      jsExecutor.executeScript("window.scrollBy(0,350)", "");
		new Actions(driver).scrollByAmount(0, 1000).perform();
	}

	public int wordCounter(String keyword, String city, String stateCode, String stateName) {

		int initialCount = 0;
		int count = 0;

		for (String e : keyword.split(" ")) {

			if (keyword.contains(city) && (keyword.contains(stateCode) || keyword.contains(stateName))) {
				if (e.equals(city) || e.equals(stateCode) || e.equals(stateName)) {
					initialCount++;
					if (initialCount > 2) {
						count = initialCount;
					}
				}
			} else if (e.equals(city) || e.equals(stateCode) || e.equals(stateName)) {
				count++;
			}
		}

		return count;
	}

	public String charReplacer(String actual, String expected) {

//		Map<Integer, String> words = mismatchInSentenceWithIndex(actual, expected);
		Map<String, String> words = mismatchInSentenceWithWords(actual, expected);

		if (!words.isEmpty()) {
	//				if(actual.split(" ").length==expected.split(" ").length) {
				for (Map.Entry<String, String> e : words.entrySet()) {
					expected = expected.replace(e.getValue(), e.getKey());
				}
		}
		return expected;
	}

	private Map<String, String> mismatchInSentenceWithWords(String actual, String expected) {

		Map<String, String> misMatchedWords = new LinkedHashMap<String, String>();

		String[] actualStringWords = actual.split(" ");
		String[] expectedStringWords = expected.split(" ");

		int wordCount = Math.min(actualStringWords.length, expectedStringWords.length);

		for (int i = 0; i < wordCount; i++) {
			if (!actualStringWords[i].equals(expectedStringWords[i])) {
				misMatchedWords.put(actualStringWords[i], expectedStringWords[i]);
			}
		}

		return misMatchedWords;
	}

	private Map<Integer, String> mismatchInSentenceWithIndex(String actual, String expected) {

		Map<Integer, String> misMatchedWordWithIndex = new LinkedHashMap<Integer, String>();

		String[] actualStringWords = actual.split(" ");
		String[] expectedStringWords = expected.split(" ");

		int wordCount = Math.min(actualStringWords.length, expectedStringWords.length);

		for (int i = 0; i < wordCount; i++) {
			if (!actualStringWords[i].equals(expectedStringWords[i])) {
				misMatchedWordWithIndex.put(i, actualStringWords[i]);
			}
		}

		return misMatchedWordWithIndex;
	}

	public Set<String> duplicateChecker(List<String> input) {
		Set<String> inputSetForDuplicates = new HashSet<>();
		Set<String> outputSetForDuplicates = new HashSet<>();
		for (String x : input) {
			if (!(inputSetForDuplicates.contains(x))) {
				inputSetForDuplicates.add(x);
			} else {
				outputSetForDuplicates.add(x);
				System.out.println("Duplicate value is: "+x);
			}

		}
		return outputSetForDuplicates;
	}

	public boolean findWordsInContent(String content, String[] words) {
		boolean result = false;
		for (String word : words) {
			if (!content.contains(word)) {
				result = true;
			}
		}
		return result;
	}

}
