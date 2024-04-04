package pageElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JobByCompanyPageElements extends AbstractComponents{

	public JobByCompanyPageElements(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	By company = By.xpath("//span/parent::div");
	
	public List<WebElement> getCompanyElement() {
		return elementsStore(company); 
	}
	
	public void clickOnCompany() {
		clickElement(elementStore(company)); 
	}
}
