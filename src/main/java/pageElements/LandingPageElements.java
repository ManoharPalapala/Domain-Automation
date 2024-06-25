package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.internal.invokers.ExpectedExceptionsHolder;

import java.util.ArrayList;
import java.util.List;

public class LandingPageElements extends AbstractComponents{

    WebDriver driver;

    public LandingPageElements(WebDriver driver){
        super(driver);
        this.driver=driver;
    }

    private By domainLogoTag = By.xpath("//img");
    private By titleTag = By.xpath("//h1[not(contains(@class,'text-truncate'))]");
    private By descriptionTag = By.xpath("//div[@class='description-lp' or @class='main-content' or @class='main-description']/parent::div[contains(@class,'border')]");
    private By keywordsTag = By.xpath("//p[@class='j-tags']");
    private By footerTag = By.xpath("//footer//child::a");
    private By ld_json = By.xpath("//script[@type='application/ld+json']");




    public void clickOnLogo(){
        clickElement(elementStore(domainLogoTag));
    }
    public void getTitleOnUI(){
        elementStore(titleTag).getText();
    }

    public WebElement getDescription(){
        return elementStore(descriptionTag);
    }
    public List<String> getKewords(){
        List<String> keywords = new ArrayList<String>();
        for(WebElement key:elementsStore(keywordsTag)){
            keywords.add(key.getText());
        }
        return keywords;
    }

    public Boolean getFooterTags(){
        Boolean isPresent = null;
        try{
            elementStore(footerTag);
        }catch(Exception e){
            isPresent=true;
        }
        return isPresent;
    }


}
