package testBase;

import java.net.MalformedURLException;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

	public WebDriver createBrowserInstance(String browserName) throws MalformedURLException {

		DesiredCapabilities desCap = new DesiredCapabilities();
		WebDriver driver = null;

		if (browserName.equalsIgnoreCase("chrome")) {

			desCap.setPlatform(Platform.WIN10);
			desCap.setBrowserName(browserName);

			ChromeOptions chromeOptions = new ChromeOptions();
//			chromeOptions.addArguments("--incognito");
			chromeOptions.addArguments("--headless=new");
			chromeOptions.addArguments("--disable-extensions");
			chromeOptions.addArguments("--disable-cookies");
			chromeOptions.addArguments("--disable-infobars");
			chromeOptions.addArguments("--disable-popup-blocking");
			chromeOptions.addArguments("--start-maximized");
			chromeOptions.merge(desCap);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);

		} else if (browserName.equalsIgnoreCase("edge")) {

			desCap.setPlatform(Platform.WIN10);
			desCap.setBrowserName(browserName);

			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("-private");
//			edgeOptions.merge(desCap);
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(edgeOptions);
		}

//		driver = new RemoteWebDriver(new URL("http://192.168.0.7:4444"),desCap);
		return driver;
	}

}
