package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners implements ITestListener{

	ExtentReports report;
	ExtentTest test;

	
	
	@Override 
	public void onTestStart(ITestResult result) {
		
		test = report.createTest((result.getInstanceName())+" ("+result.getName()+")");
		ExtentFactory.getInstance().setExtentTest(test);
	}	
	
	@Override
	public void onTestSuccess(ITestResult result) {
		
		ExtentFactory.getInstance().getExtentTest().log(Status.PASS, result.getParameters()[0]+" ("+result.getName()+")" );
		ExtentFactory.getInstance().removeExtentTest();
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		ExtentFactory.getInstance().getExtentTest().log(Status.INFO,result.getParameters()[0]+" ("+result.getName()+")");
		ExtentFactory.getInstance().getExtentTest().log(Status.FAIL,result.getThrowable());


//		File srcScreenshot = ((TakesScreenshot)DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
//
//		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
//		Date date = new Date();
//		String actualDate = format.format(date);
//
//		String destScreenshotPath = System.getProperty("user.dir")+"/src/main/java/reports/Screenshots"+result.getMethod().getMethodName()+actualDate+".png";
//		try {
//			FileUtils.copyFile(srcScreenshot, new File(destScreenshotPath));
//
//		} catch (IOException e) {
//			System.out.println("Output File not found");
//		}
		
//		ExtentFactory.getInstance().getExtentTest().addScreenCaptureFromPath(destScreenshotPath);
		ExtentFactory.getInstance().removeExtentTest();
	
	}

	
	@Override
	public void onTestSkipped(ITestResult result) {
		
		ExtentFactory.getInstance().getExtentTest().log(Status.INFO,result.getThrowable());
		ExtentFactory.getInstance().getExtentTest().log(Status.SKIP,(String)result.getParameters()[0]);
		ExtentFactory.getInstance().removeExtentTest();
	
	}
	
	
	
//	Creates a new report
	@Override
	public void onStart(ITestContext context) {
		
		report = ExtentReporter.setExtentReport();
		
	}
	
//	Closes current report			
	@Override
	public void onFinish(ITestContext context) {
		
		report.flush();
	}

	



	
	
}
