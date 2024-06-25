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

	protected WebDriver driver;
//	protected DataFileHandler dfh = new DataFileHandler();

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
	}

//	public WebElement elementStore(By by) {
//        return driver.findElement(by);
//	}
//
//	public List<WebElement> elementsStore(By by) {
//        return driver.findElements(by);
//	}

	public WebElement elementStore(By elem) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
			return wait.until(ExpectedConditions.presenceOfElementLocated(elem));
		} catch (Exception e) {
			throw new org.openqa.selenium.NoSuchElementException("Element not found " + elem, e);
		}
	}

	public List<WebElement> elementsStore(By elems){
		try{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elems));
		} catch (Exception e) {
			throw new org.openqa.selenium.NoSuchElementException("Elements not found " + elems, e);
		}
	}

	public void clickElement(WebElement elem) {
		try {
			elem.click();
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", elem);
		}
	}

	public WebElement waitUsingElement(By elem) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
	}

	public boolean waitUsingAttribute(WebElement elem, String attr, String value) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		return wait.until(ExpectedConditions.attributeToBe(elem, attr, value));
	}


}