package pageElements;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {

	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public WebElement elementStore(By by) {
		WebElement element = driver.findElement(by);
		return element;
	}
	public List<WebElement> elementsStore(By by) {
		List<WebElement> webelems = driver.findElements(by);
		return webelems;
	}
	
	
	
	public void clickElement(WebElement elem) {
		try {
			elem.click();
		}
		catch(Exception e) {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", elem);
		}
	}
	
	public WebElement waitUsingElement(By elem) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
	}
	
	public boolean waitUsingAttribute(WebElement elem, String attr, String value) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
		return wait.until(ExpectedConditions.attributeToBe(elem, attr, value));
	}
	
	
}