package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageElements extends AbstractComponents {
	
	
	WebDriver driver;
	
	
// ELEMENTS	
	private By search = By.xpath("//*[text()='SEARCH' or text()='FIND JOBS' or text()='Search'or text()='Browse Jobs' or text()='BROWSE JOBS' or @type='submit' or text()='More Jobs']");
	private By jobsByCompanyPage = By.xpath("//a[text()='Browse All' or text()='BROWSE ALL' or text()='COMPANIES' or text()='Companies']");
	
	@FindBy(xpath="//a[text()='ABOUT' or text()='About' or text()='About Us' or text()='ABOUT US']")
	private WebElement aboutPage;
	
	@FindBy(xpath="//a[text()='CONTACT' or text()='Contact' or text()='Contact Us' or text()='CONTACT US']")
	private WebElement contactPage;
	
	@FindBy(xpath="//*[text()='APPLY NOW' or text()='Apply Now' or text()='Browse Jobs' or contains(text(),'APPLY')]")
	private WebElement applyButton;
	
	private By termsPage=By.xpath("//a[text()='Terms of Service' or text()='TERMS OF SERVICE' or text()='terms of service' or text()='Terms Of Service' or text()='Terms of Use']");
	
	private By privacyPolicyPage = By.xpath("//a[text()='Privacy policy' or text()='Privacy Policy' or text()='privacy policy' or text()='PRIVACY POLICY']");
	
	private By cookiePolicyPage = By.xpath("//a[text()='Cookie policy' or text()='Cookie Policy' or text()='cookie policy' or text()='COOKIE POLICY']");
	
	private By scrollUpLocator = By.xpath("//button[@onclick='scrollToTop()' or text()='↑']");

	
	
// INTIALIZATION	
	
		public HomePageElements(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
	}
	
	
	
	
// ACTIONS
		
	public void clickOnScroll() {
		clickElement(elementStore(scrollUpLocator));
	}

	public WebElement getScrollButton() {
		return elementStore(scrollUpLocator);
	}
	
	public SearchPageElements clickOnSearch() {
		WebElement searchButton = elementStore(search);
		clickElement(searchButton);
		return new SearchPageElements(driver);
	}
	
	public void clickOnJobByCompany() {
		clickElement(elementStore(jobsByCompanyPage)); 
	}
	
	public void clickOnAbout() {
		clickElement(aboutPage);
	}
	
	public ContactPageElements clickOnContact() {
		clickElement(contactPage);
		return new ContactPageElements(driver);
	}
	
	public void clickOnTerms() {
		clickElement(elementStore(termsPage));
	}
	
	public String getTermsPageUrl() {
		return elementStore(termsPage).getAttribute("href");
	}
	
	public void clickOnPrivacy() {
		clickElement(elementStore(privacyPolicyPage));
	}
	
	public String getPrivacyPageUrl() {
		return elementStore(privacyPolicyPage).getAttribute("href");
	}
	
	public void clickOnCookie() {
		clickElement(elementStore(cookiePolicyPage));
	}
	
	public String getCookiePageUrl() {
		return elementStore(cookiePolicyPage).getAttribute("href");
	}
	
	public WebElement getApplyButton() {
		return applyButton;
	}

	
}
