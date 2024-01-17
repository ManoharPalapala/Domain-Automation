package PageElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageElements {
	
	
	WebDriver driver;
	
	
// ELEMENTS	
	
	@FindBy(xpath="//button[text()='SEARCH' or text()='FIND JOBS' or text()='Search']")
	private WebElement searchPage;
	
	@FindBy(xpath="//a[text()='ABOUT' or text()='About' or text()='About Us' or text()='ABOUT US']")
	private WebElement aboutPage;
	
	@FindBy(xpath="//a[text()='CONTACT' or text()='Contact' or text()='Contact Us' or text()='CONTACT US']")
	private WebElement contactPage;
	
	@FindBy(xpath="//a[text()='APPLY NOW' or text()='Apply Now' or text()='Browse Jobs' or contains(text(),'APPLY')]")
	private WebElement applyButton;
	
	@FindBy(xpath="//a[text()='Terms of Service' or text()='TERMS OF SERVICE' or text()='terms of service' or text()='Terms Of Service']")
	private WebElement termsPage;
	
	@FindBy(xpath="//a[text()='Privacy policy' or text()='Privacy Policy' or text()='privacy policy' or text()='PRIVACY POLICY']")
	private WebElement privacyPolicyPage;
	
	@FindBy(xpath="//a[text()='Cookie policy' or text()='Cookie Policy' or text()='cookie policy' or text()='COOKIE POLICY']")
	private WebElement cookiePolicyPage;
	
	
	
	
	
// INTIALIZATION	
	
		public HomePageElements(WebDriver driver) {
//		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	
// ACTIONS
		
	public void clickOnSearch() {
		searchPage.click();
	}
	
	public void clickOnAbout() {
		aboutPage.click();
	}
	
	public void clickOnContact() {
		contactPage.click();
	}
	
	public WebElement termsLocator() {
		return termsPage;
	}
	
	public WebElement privacyLocator() {
		return privacyPolicyPage;
	}
	
	public WebElement cookieLocator() {
		return cookiePolicyPage;
	}
	
	public WebElement ApplyButtonLocator() {
		return applyButton;
	}
	
}
