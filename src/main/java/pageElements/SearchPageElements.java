package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPageElements extends AbstractComponents{

	
	WebDriver driver;
	
	
	private By titleInputFieldLocator = By.xpath("//input[contains(@placeholder,'Title') or contains(@placeholder,'Keyword') or contains(@placeholder,'Company')]");
	private By locationInputFieldLocator = By.xpath("//input[contains(@placeholder,'Location')]");
	private By searchButtonLocator = By.xpath("//*[text()='SEARCH' or text()='FIND JOBS' or text()='Search' or text()='Find Jobs' or text()='Browse Jobs' or text()='BROWSE JOBS' or @type='submit']");
	private By applyButtonLocator = By.xpath("//*[text()='APPLY NOW' or text()='Apply Now' or text()='Browse Jobs' or text()='Apply Now ']");
	private By companyDropdownLocator = By.xpath("//select[@name='company']");
	private By categoryDropdownLocator = By.xpath("//select[@name='category']");
	private By jobtypeDropdownLocator = By.xpath("//select[@name='jtype']");
	private By selectedCompanyDropdownLocator = By.xpath("//select[@name='company']/child::option[@selected]");
	private By jobCountLocator = By.xpath("//*[text()='Search Results']/following::*[contains(text(),'Jobs Available')]");
	private By clearSearchLocator = By.xpath("//*[text()='Clear Search' or text()='CLEAR SEARCH']");
	private By loadMoreLocator = By.xpath("//*[text()='Load More Jobs...' or text()='LOAD MORE' or text()='Load More Jobs' or text()='Load More']");
	
	public SearchPageElements(WebDriver driver) {
		super(driver);
		this.driver=driver;
//		PageFactory.initElements(driver, this);
	}
	
	
	public int totalApplyLinks() {
		return elementsStore(applyButtonLocator).size();
	}
	
	public String titleInputField() {
		return elementStore(titleInputFieldLocator).getAttribute("value");
	}
	
	public void sendKeysToTitleFiled(String titleText) {
		WebElement titleField = elementStore(titleInputFieldLocator);
		titleField.sendKeys(titleText);
	}
	
	public void sendKeysToLocationFiled(String locationText) {
		WebElement locationField = elementStore(locationInputFieldLocator);
		locationField.sendKeys(locationText);
	}
	
	public void clickOnSearch() {
		WebElement searchButton = elementStore(searchButtonLocator);
		clickElement(searchButton);
	}
	
	public void clickOnApply() {
		WebElement applyButton = elementStore(applyButtonLocator);
		clickElement(applyButton);
	}
	
	public WebElement applyButton() {
		return elementStore(applyButtonLocator);
	}
	
	public void clickOnCompanyFilter() {
		WebElement companyFilter = elementStore(companyDropdownLocator);
		clickElement(companyFilter);
	}
	
	public void clickOnCategoryFilter() {
		WebElement categoryFilter = elementStore(categoryDropdownLocator);
		clickElement(categoryFilter);
	}
	
	public WebElement selectCompanyOption() {
		return elementStore(companyDropdownLocator);
	}
	
	public String selectedCompany() {
		return elementStore(selectedCompanyDropdownLocator).getAttribute("value");
	}
	
	public String searchResult() {
		return elementStore(jobCountLocator).getText();
	}
	
	public String specifiedjobCount() {
		String[] x = elementStore(jobCountLocator).getText().split(" ");
		return x[0];
	}
	
	public void clickOnClearSearch() {
		clickElement(elementStore(clearSearchLocator));
	}
	
	public void clickOnLoadMore() {
		clickElement(elementStore(loadMoreLocator));	
	}
	
	public WebElement loadMoreButton() {
		return elementStore(loadMoreLocator);
	}
}

