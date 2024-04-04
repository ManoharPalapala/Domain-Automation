package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactPageElements extends AbstractComponents{

	WebDriver driver;
	
	private By mailIdLocator = By.xpath("//a[starts-with(@href,'mailto')]");
	private By nameInputLocator = By.xpath("//input[@name='name']");
	private By subjectInputLocator = By.xpath("//input[@name='subject']");
	private By emailInputLocator = By.xpath("//input[@name='email']");
	private By phoneInputLocator = By.xpath("//input[@name='phone']");
	private By messageInputLocator = By.xpath("//textarea[@name='message']");
	private By submitButtonLocator = By.xpath("//button[@type='submit']");
	private By nameErrorMessageLocator = By.xpath("//label[@id='name-error']");
	private By subjectErrorMessageLocator = By.xpath("//label[@id='subject-error']");
	private By emailErrorMessageLocator = By.xpath("//label[@id='email-error']");
	private By phoneErrorMessageLocator = By.xpath("//label[@id='phone-error']");
	private By successMessageLocator = By.xpath("//*[@id='successmsg' or contains(@class,'alert-success')]");
	
	public ContactPageElements(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
		
	public String mailIdText() {
		return elementStore(mailIdLocator).getText();
	}
	
	public WebElement nameInputField() {
		return elementStore(nameInputLocator);
	}
	
	public WebElement subjectInputField() {
		return elementStore(subjectInputLocator);
	} 
	
	public WebElement emailInputField() {
		return elementStore(emailInputLocator);
	} 
	
	public WebElement phoneInputField() {
		return elementStore(phoneInputLocator);
	} 
	
	public WebElement messageInputField() {
		return elementStore(messageInputLocator);
	}
	
	public String nameErrorMessage() {
		return elementStore(nameErrorMessageLocator).getText();
	} 
	
	public String subjectErrorMessage() {
		return elementStore(subjectErrorMessageLocator).getText();
	} 
	
	public String mailErrorMessage() {
		return elementStore(emailErrorMessageLocator).getText();
	} 
	
	public String phoneErrorMessage() {
		return elementStore(phoneErrorMessageLocator).getText();
	} 
	
	public WebElement successMessage() {
		return waitUsingElement(successMessageLocator); 
	}
	
	public void submitclick() {
		clickElement(elementStore(submitButtonLocator));
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
