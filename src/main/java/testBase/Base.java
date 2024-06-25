package testBase;

import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import pageElements.BaseElements;
import pageElements.HomePageElements;
import pageElements.LandingPageElements;
import utilities.ExcelHandler;
import utilities.MetaFileHandler;
import utilities.PropHandler;

public class Base {
	
	public WebDriver driver;
	private BrowserFactory bf = new BrowserFactory();
	public HomePageElements homePage;
	public BaseElements baseElements;
	public LandingPageElements landingPage;
	public ExcelHandler dfh;
	public PropHandler ph;
	public MetaFileHandler metaExcel = new MetaFileHandler();
	public SoftAssert sf;
	
	protected String[] staticWords = {"[","Last Updated Date","Effective Date", "Domain Name", "domainname","Domain"};



	@BeforeTest
	public void testSetUp(){
		ph = new PropHandler();

		for(Object[] x:dfh.readDataFromExcel("list")) {
			for(Object y:x){
				System.out.println(y);
			}
		}
	}


	@Parameters
	@BeforeMethod
	public void setUp(@Optional("chrome") String browserName) throws MalformedURLException {
		
		DriverFactory.getInstance().setDriver(bf.createBrowserInstance(browserName));
		driver = DriverFactory.getInstance().getDriver();
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		homePage = new HomePageElements(driver);
		baseElements = new BaseElements(driver);
		landingPage = new LandingPageElements(driver);
		dfh = new ExcelHandler();
		sf = new SoftAssert();
	}


	@AfterMethod
	public void tearDown() {
		DriverFactory.getInstance().closeDriver();
	}



}

