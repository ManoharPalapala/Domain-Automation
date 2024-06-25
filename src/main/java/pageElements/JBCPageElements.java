package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class JBCPageElements extends AbstractComponents {

	public JBCPageElements(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}


	private final By companyLinks = By.xpath("//div/descendant-or-self::*[contains(@class,'text-truncate')]/ancestor::a");
	private final By listingPageTitle = By.xpath("//*[contains(@class,'text-truncate')]//child::a | //*[contains(@class,'text-truncate')]//parent::a");
	private final By noJobsFoundText = By.xpath("//*[contains(text(),'No Jobs')]");


	public WebElement softLogicText(){
		return waitUsingElement(noJobsFoundText);
	}

	public void clickOnCompany(){
		clickElement(elementStore(companyLinks));
	}

	public ArrayList<String> getCompanyLinks(){
		ArrayList<String> links = new ArrayList<String>();
		for(WebElement link:elementsStore(companyLinks)){
			links.add(link.getAttribute("href"));
		}
		return links;
	}

	public String getCompanyLink(){
		return elementStore(companyLinks).getAttribute("href");
	}

	public List<String> getJobTitles(String failure) {
//		String[] titleList = new String[0];
		List<String> titles = new ArrayList<String>();

		try{
            for (WebElement title : elementsStore(listingPageTitle)) {
				titles.add(title.getText());
			}
//            titleList = new String[titles.size()];
//            titleList = titles.toArray(titleList);
        } catch (Exception e) {
            System.out.println("No jobs in: "+failure);
		}

        return titles;
    }



}
