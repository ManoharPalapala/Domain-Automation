package scripts;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.logging.Level;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import pageElements.BaseElements;
import pageElements.HomePageElements;
import pageElements.JobByCompanyPageElements;
import utilities.DataFileHandler;
import utilities.MetaFileHandler;

public class Base {

	public WebDriver driver;
	private DesiredCapabilities desCap = new DesiredCapabilities();
	private String browserName;
	protected HomePageElements homePage;
	protected JobByCompanyPageElements jbc;
	protected BaseElements baseElements;
	protected DataFileHandler dfh;
	protected MetaFileHandler metaExcel = new MetaFileHandler();
	protected SoftAssert sf;
	protected WebDriverWait wait;
	
	
	protected String[] staticWords = {"[","Last Updated Date","Effective Date"};

 	
//	public static void main(String[] args) {
//	
//		Base b= new Base();
//		String y = "";
//		String[] staticWords = {"[","Last Updated Date","Effective Date"};
//	System.out.println(b.checkWordsInContent(y, staticWords));	
//		
//	}
	
// --------------------------------------------------------------------------------------------------------------------------	
// setUp
	
	
	@BeforeClass
	public void init() {
		 dfh = new DataFileHandler();	 
	}

	@Parameters("browserName")
	@BeforeMethod
	public void setUp(@Optional("chrome") String browserName) throws MalformedURLException
	{
		this.browserName=browserName;
		
		if(browserName.equals("chrome")){
		desCap.setPlatform(Platform.WIN10);
		desCap.setBrowserName(browserName);

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.merge(desCap);
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
	}else if(browserName.equals("MicrosoftEdge"))
	{
		desCap.setPlatform(Platform.WIN10);
		desCap.setBrowserName(browserName);

		EdgeOptions edgeOptions = new EdgeOptions();
		edgeOptions.merge(desCap);
	}


		driver = new RemoteWebDriver(new URL("http://192.168.0.7:4444"),desCap);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		homePage = new HomePageElements(driver);
		baseElements = new BaseElements(driver);
		jbc = new JobByCompanyPageElements(driver);
		sf = new SoftAssert();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	
	
//	------------------------------------------------------------------------------------------------------------------
//	methods
public String urlProtocolType(String URL) throws Throwable 
	
	{
		URL url = new URL(URL);
		String protocol = url.getProtocol();
		String actualProtocol=null;;
		switch(protocol) {
		case "https":  actualProtocol = protocol;
		case "http": actualProtocol = protocol;
		}
		return actualProtocol;
	}
	

	public int networkStatus(String URL)
	
	{
		int statusCode =0;
		try {
			URL url = new URL(URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			statusCode = connection.getResponseCode();
//			switch(statusCode) {
//			case 200: actualStatusCode=200;
//			break;
//			case 400: actualStatusCode=400;
//			break;
//			}
		} catch (Exception e) {
			e.printStackTrace();}
		return statusCode;
	}
	
	
	
	public String consoleResult() 
	
	{
		String msg = "null";
		LogEntries logs = driver.manage().logs().get("browser");
		
        for (org.openqa.selenium.logging.LogEntry entry : logs){
            if (entry.getLevel() == Level.SEVERE){
                 msg =  entry.getMessage();
            }
        }
		
		return msg;
	}
	
	
	public String addSubDomainToUrl(String currentPageUrl) 
	
	{
		String[] splittedUrl = currentPageUrl.split("//");
		String modifiedPageUrl = splittedUrl[0] + "//www." + splittedUrl[1];
		driver.get(modifiedPageUrl);		
		return driver.getCurrentUrl();
	}


	public boolean isHttpInPageSource(WebDriver driver) 
	{
		return driver.getPageSource().contains("http:");
	}
	
	public boolean pageHeading(String url,String heading) {
		String[] pageUrl = url.split("/");
		if(heading.contains(" ")) {
			heading = heading.replace(" ","-");
		}
		return pageUrl[3].equalsIgnoreCase(heading);
		
	}
	

}
	
