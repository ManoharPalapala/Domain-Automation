package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageElements extends AbstractComponents {

	WebDriver driver;

// ELEMENTS	
	private By search = By.xpath("//button[@type='submit'] | //a[@href='/search' and @rel='nofollow']");
	private By about = By.xpath("//a[contains(text(),'About') or text()='ABOUT' or text()='ABOUT US' or text()='About Us']");
	private By jobsByCompanyPage = By.xpath(
			"//a[contains(text(),'BROWSE ALL') or contains(text(),'View All') or contains(text(),'VIEW ALL') or text()='View Employers' or contains(text(),'Browse All') or contains(text(),'COMPANIES') or contains(text(),'Companies') or text()='Locations' or contains(text(),'View More') or contains(text(),'VIEW MORE') or contains(text(),'companies') and not(contains(@rel,'nofollow'))]");
	private By termsPage = By.xpath(
			"//a[text()='Terms of Service' or text()='TERMS OF SERVICE' or text()='terms of service' or text()='Terms Of Service' or text()='Terms of Use' or text()='Terms & Conditions' or text()='Terms And Conditions' or text()='Terms and Conditions']");
	private By privacyPolicyPage = By.xpath(
			"//a[text()='Privacy policy' or text()='Privacy Policy' or text()='privacy policy' or text()='PRIVACY POLICY']");
	private By cookiePolicyPage = By.xpath("//a[text()='Cookie policy' or text()='Cookie Policy' or text()='cookie policy' or text()='COOKIE POLICY']");
	private By scrollUpLocator = By.xpath("//button[@onclick='scrollToTop()' or text()='↑']");
	@FindBy(xpath = "//a[text()='CONTACT' or text()='Contact' or text()='Contact Us' or text()='CONTACT US']")
	private WebElement contactPage;
	@FindBy(xpath = "//a[contains(text(),'BROWSE ALL') or contains(text(),'View All') or contains(text(),'VIEW ALL') or contains(text(),'Browse All' or text()='COMPANIES' or text()='Companies')]")
	private WebElement jbc;

// INTIALIZATION	

	public HomePageElements(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

// ACTIONS

	public SearchPageElements clickOnSearch() {
		WebElement searchButton = elementStore(search);
		clickElement(searchButton);
		return new SearchPageElements(driver);
	}

	public JBCPageElements clickOnJobByCompany() {
		clickElement(elementStore(jobsByCompanyPage));
		return new JBCPageElements(driver);
	}

	public void clickOnAbout() {clickElement(elementStore(about));}
	public ContactPageElements clickOnContact() {
		clickElement(contactPage);
		return new ContactPageElements(driver);
	}
	public void clickOnPrivacy() {
		clickElement(elementStore(privacyPolicyPage));
	}
	public void clickOnTerms() {
		clickElement(elementStore(termsPage));
	}
	public void clickOnCookie() {clickElement(elementStore(cookiePolicyPage));}

	public String getAboutPageUrl(){
		return elementStore(about).getAttribute("href");
	}
	public String getTermsPageUrl() {return elementStore(termsPage).getAttribute("href");}
	public String getTermsPageText() {return elementStore(termsPage).getText();}
	public String getPrivacyPageUrl() {
		return elementStore(privacyPolicyPage).getAttribute("href");
	}
	public String getPrivacyPageText() {
		return elementStore(privacyPolicyPage).getText();
	}
	public String getCookiePageUrl() {return elementStore(cookiePolicyPage).getAttribute("href");}
	public String getCookiePageText() {return elementStore(cookiePolicyPage).getText();}
	public String getContactPageUrl() {return contactPage.getAttribute("href");}

	public void clickOnScroll() {
		clickElement(elementStore(scrollUpLocator));
	}

	public WebElement getScrollButton() {
		return elementStore(scrollUpLocator);
	}

	public String getScrollInitValue() {
		String scrollButtonClass = elementStore(scrollUpLocator).getAttribute("class");
		String scrollButtonStyle = elementStore(scrollUpLocator).getAttribute("style");
		String scrollWaitLocator = null;
		if (scrollButtonStyle.contains("display")) {
			scrollWaitLocator = scrollButtonStyle.replace("inline-block", "none");
		} else if (!(scrollButtonClass.contains("d-none"))) {
			scrollWaitLocator = scrollButtonClass + " d-none";
		} else if (scrollButtonClass.contains("d-none")) {
			scrollWaitLocator = scrollButtonClass;
		}

		return scrollWaitLocator;
	}

	public String getScrollInitAttr() {
		String scrollButtonClass = elementStore(scrollUpLocator).getAttribute("class");
		String scrollButtonStyle = elementStore(scrollUpLocator).getAttribute("style");
		String scrollWaitLocator = null;
		if (scrollButtonStyle.contains("display")) {
			scrollWaitLocator = "style";
		} else if (!(scrollButtonClass.contains("d-none"))) {
			scrollWaitLocator = "class";
		} else if (scrollButtonClass.contains("d-none")) {
			scrollWaitLocator = "class";
		}

		return scrollWaitLocator;
	}

}
