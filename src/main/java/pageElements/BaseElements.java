package pageElements;

import java.util.List;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseElements extends AbstractComponents {

	public BaseElements(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private By metaTitleTag = By.xpath("//meta[@name='title']");
	private By ogTitle = By.xpath("//meta[@property='og:title']");
	private By metaDescriptionTag = By.xpath("//meta[@name='description']");
	private By ogDescriptionTag = By.xpath("//meta[@property='og:description']");
	private By ogSiteNameTag = By.xpath("//meta[@property='og:site_name']");
	private By ogImageTag = By.xpath("//meta[@property='og:image']");
	private By ogUrlTag = By.xpath("//meta[@property='og:url']");
	private By canonicalTag = By.xpath("//link[@rel='canonical']");
	private By faviconTag = By.xpath("//link[@rel='icon']");
	private By metaViewPortTag = By.xpath("//meta[@name='viewport']");
	private By styleSheetsTags = By.xpath("//link[@rel='stylesheet']");
	private By srcBootstrapFile = By.xpath("//script[contains(@src,'bootstrap')]");
	private By copyRightContent = By.xpath("//*[contains(text(),'©') or contains(text,'All Rights Reserved')]");
	private By imgTags = By.xpath("//img");
	private By domainLogoTag = By.xpath("//img[1]");
	private By externalScriptTags = By.xpath("//script[not(contains(@src,'GTM')) and @src]");
	private By pageHeadingTag = By.xpath("//h1");
	private By headerTag = By.xpath("//header");
	private By pageContentTag = By.xpath("//main | (//div[@class='container'])[2]");
	private By scrollUpLocator = By.xpath("//button[@onclick='scrollToTop()' or text()='↑']");
	private By ld_json = By.xpath("//script[@type='application/ld+json']");




	public String getMetaTitle() {
		return elementStore(metaTitleTag).getAttribute("content");
	}
	public String getOgTitle() {
		return elementStore(ogTitle).getAttribute("content");
	}

	public String getMetaDesc() {
		return elementStore(metaDescriptionTag).getAttribute("content");
	}

	public String getOgDesc() {
		return elementStore(ogDescriptionTag).getAttribute("content");
	}

	public String getOgSiteName() {
		return elementStore(ogSiteNameTag).getAttribute("content");
	}

	public String getOgImage() {
		return elementStore(ogImageTag).getAttribute("content");
	}

	public String getOgUrl() {
		return elementStore(ogUrlTag).getAttribute("content");
	}

	public String getCanonical() {
		return elementStore(canonicalTag).getAttribute("href");
	}

	public String getFavicon() {
		return elementStore(faviconTag).getAttribute("href");
	}

	public String getMetaViewPort() {
		return elementStore(metaViewPortTag).getAttribute("content");
	}

	public String getDomainNameFromCopyRight() {
		String[] copyRightText = elementStore(copyRightContent).getText().split("2024");
		String[] domainName = copyRightText[1].split("\\.");
		return domainName[0].strip();
	}

	public String getLogoPath() {
		return elementStore(domainLogoTag).getAttribute("src");
	}

	public List<WebElement> getStyleSheets() {
		return elementsStore(styleSheetsTags);
	}

	public List<WebElement> getImages() {
		return elementsStore(imgTags);
	}

	public List<WebElement> getExternalScripts() {
		return elementsStore(externalScriptTags);
	}

	public String getFolderNameInPath() {
		String[] text = elementStore(ogImageTag).getAttribute("content").split("/");
		System.out.println(text[2]+"/"+text[3]);
		return text[3];
	}

	public String getBootstrapUrlPageSource() {
		driver.get(elementStore(srcBootstrapFile).getAttribute("src"));
		return driver.getPageSource();
	}

	public String getPageHeading() {
		return elementStore(pageHeadingTag).getText();
	}

	public String getPageContent() {
		return elementStore(pageContentTag).getText();
	}

	public int[] getNavDimensions(){
		Dimension navDimension = elementStore(headerTag).getSize();
		Dimension logoDimension = elementStore(domainLogoTag).getSize();
		return new int[]{navDimension.getHeight(),navDimension.getWidth(),logoDimension.getHeight(),logoDimension.getWidth()};
	}

	public int[] getScrollDimensions(){
		Dimension scrollDimension = elementStore(scrollUpLocator).getSize();
		return new int[]{scrollDimension.getHeight(),scrollDimension.getWidth()};
	}

	public String getLd_Json(String key){
		JSONObject jo = new JSONObject(elementStore(ld_json).getAttribute("innerHTML"));
		return jo.get(key).toString();
    }

}
