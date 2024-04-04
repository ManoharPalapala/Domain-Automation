package reports;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners implements ITestListener{

	ExtentReports extentReport;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
		
		extentReport = ExtentReporter.generateReport();
		
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		
		extentTest = extentReport.createTest(result.getName());
		
	}	
	
	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.log(Status.PASS, (String)result.getParameters()[0] );
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		
//		WebDriver driver = null;
//		
//		try {
//		    Class<?> testClass = result.getTestClass().getRealClass();
//		    // Traverse the class hierarchy to find the field in the test class or its superclasses
//		    Field driverField = null;
//		    Class<?> currentClass = testClass;
//		    while (driverField == null && currentClass != null) {
//		        try {
//		            driverField = currentClass.getDeclaredField("driver");
//		            driverField.setAccessible(true);
//		        } catch (NoSuchFieldException e) {
//		            currentClass = currentClass.getSuperclass();
//		        }
//		    }
//		    // If driverField is still null, the field was not found in the class hierarchy
//		    if (driverField != null) {
//		        // Get the driver instance from the field
//		        driver = (WebDriver) driverField.get(result.getInstance());
//		    } else {
//		        System.out.println("Driver field not found in the class hierarchy.");
//		    }
//		} catch (IllegalAccessException e) {
//		    // Handle any exceptions that might occur during reflection.
//		    // If the driver field is not accessible, print a message.
//		    System.out.println("Driver field not accessible: " + e.getMessage());
//		}
//		
//		File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		String destScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\.png";
//		try {
//			FileHandler.copy(srcScreenshot, new File(destScreenshotPath));
//		} catch (IOException e) {
//			System.out.println("Output File not found");
//		}
		
//		extentTest.addScreenCaptureFromPath(destScreenshotPath);
		
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,(String)result.getParameters()[0]);
	}

	
	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP,(String)result.getParameters()[0]);
	}
				
	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
	}

	



	
	
}
