package testBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class DriverFactory {

	// Design Pattern: Singleton design and Factory design
	// Singleton design pattern - limiting creation of the object instance only once but provides global access to that instance by calling getInstance method
	// Factory design pattern - defines seperate methods to create objects and creating objects globally bu calling the methods
	
	
	// Singleton design: Used to restrict access to methods by other classes until the request is satisfied in the getInstance method
	// private constructor such that no class can inherit the objects of this class
	// private instance (object creation of current class) is created 
	// public getInstance method is created to provide global access of current class to remaining classes
	
	private DriverFactory() {
		
	}
	
	private static DriverFactory instance = new DriverFactory();
	
	public static DriverFactory getInstance() {
		return instance;
	}
	
		private ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();
//	private ThreadLocal<WebDriver> threadLocalDriver = ThreadLocal.withInitial(() -> null);
	
	
	// Factory design
	
	public WebDriver getDriver() {
		WebDriver driver = threadLocalDriver.get();
		if (driver == null) {
            throw new IllegalStateException("WebDriver instance not initialized");
        }
		return driver;
	}
	
	public void setDriver(WebDriver driverParam) {
		threadLocalDriver.set(driverParam);
	}
	
	public void closeDriver() {
		 WebDriver driver = getDriver(); 
	        if (driver != null) {
	            driver.quit();
	            threadLocalDriver.remove(); 
	        }
	}
	
	public String openNewTab(String URL) {
		WebDriver driver = getDriver();
		String parentTab = driver.getWindowHandle();
		WebDriver newDriver = driver.switchTo().newWindow(WindowType.TAB);
		newDriver.get(URL);
		return parentTab;
	}
	
	public void switchToParentTab(String parentTab){
		WebDriver driver = getDriver();
		driver.close();
		driver.switchTo().window(parentTab);
	}
	
}
