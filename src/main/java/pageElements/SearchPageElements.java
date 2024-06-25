package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPageElements extends AbstractComponents {

	WebDriver driver;

	private final By titleInputFieldLocator = By.xpath(
			"//input[contains(@placeholder,'Title') or contains(@placeholder,'Keyword') or contains(@placeholder,'Company')]");
	private final By locationInputFieldLocator = By.xpath("//input[contains(@placeholder,'Location')]");
	private final By searchButtonLocator = By.xpath(
			"//a[text()='SEARCH' or contains(text(),'FIND JOBS') or text()='Search' or text()='Find Jobs' or text()='Browse Jobs' or text()='BROWSE JOBS' or @type='submit'] | //button[contains(text(),'FIND JOBS') or contains(text(),'SEARCH')]");
	private final By applyButtonLocator = By
			.xpath("//*[text()='APPLY NOW' or text()='Apply Now'or text()='Apply Now ' or text()='View Job Details']");
	private final By companyDropdownLocator = By.xpath("//select[@name='company']");
	private final By totalCompanies = By.xpath("//select[@name='company']/child::option");
	private final By firstCompanyName = By.xpath("//select[@name='company']/option[2]");
	private final By categoryDropdownLocator = By.xpath("//select[@name='category']");
//	private By jobtypeDropdownLocator = By.xpath("//select[@name='jtype']");
	private final By selectedCompanyDropdownLocator = By.xpath("//select[@name='company']/child::option[@selected]");
	private final By jobCountLocator = By.xpath(
			"//*[text()='Search Results' or text()='SEARCH RESULTS']/following::*[contains(text(),'Jobs Available') or contains(text(),'Job Available')]");
	private final By clearSearchLocator = By.xpath("//*[text()='Clear Search' or text()='CLEAR SEARCH']");
	private final By loadMoreLocator = By.xpath(
			"//*[text()='Load More Jobs...' or text()='LOAD MORE' or text()='Load More Jobs' or text()='Load More']");

	public SearchPageElements(WebDriver driver) {
		super(driver);
		this.driver = driver;
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

	public int getCompaniesCount(){
		return elementsStore(totalCompanies).size();
	}

	public String getFirstCompanyName(){
		return elementStore(firstCompanyName).getText();
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
