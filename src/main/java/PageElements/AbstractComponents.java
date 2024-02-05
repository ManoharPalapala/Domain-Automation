package PageElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
		List<WebElement> webels = driver.findElements(by);
		return webels;
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
}