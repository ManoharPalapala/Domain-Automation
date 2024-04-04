package scripts;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import dataProvider.DataSupplier;

public class JobsByCompany_T_C extends MultiExecution_T_C{

	@Test(dataProvider="supplier", dataProviderClass = DataSupplier.class)
	public void validateTruncateInCompany(String URL) {
		driver.get(URL);
		homePage.clickOnJobByCompany();
		for(WebElement company:jbc.getCompanyElement()){
			System.out.println(company.getText());
			break;
		}
	}
}
