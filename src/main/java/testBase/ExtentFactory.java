package testBase;

import com.aventstack.extentreports.ExtentTest;

public class ExtentFactory {
	
	

	private ExtentFactory() {
		
	}
	
	private static ExtentFactory instance = new ExtentFactory();
	
	public static ExtentFactory getInstance() {
		return instance;
	}
	
	
	ThreadLocal<ExtentTest> extentTestInstacne = new ThreadLocal<ExtentTest>();
	
	public ExtentTest getExtentTest() {
		return extentTestInstacne.get();
	}
	
	public void setExtentTest(ExtentTest extentTestParam) {
		extentTestInstacne.set(extentTestParam);
	}
	
	public void removeExtentTest() {
		extentTestInstacne.remove();
	}

}
