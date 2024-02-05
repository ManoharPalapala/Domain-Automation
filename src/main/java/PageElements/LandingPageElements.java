package PageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LandingPageElements extends AbstractComponents{

	WebDriver driver;
	
	By titleLocator = By.tagName("h1");
	By locationLocator = By.xpath("//*[contains(text(),'Location :')]");
	
	
	public LandingPageElements(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
}
