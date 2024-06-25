package testBase;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter{
	
	 	static ExtentReports setExtentReport() {
	 		
	 	File extentReportPath = new File(System.getProperty("user.dir")+"\\src\\main\\java\\reports\\extentReport.html");
		
	 	
	 	ExtentReports extentReport = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportPath);

		
		//		UI configuration
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Domain Automation");
		sparkReporter.config().setDocumentTitle("Test Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");


// connect the sparkreport report to the extent
		extentReport.attachReporter(sparkReporter);
		
		
//		System.getProperties().list(System.out); - for all system properties
		extentReport.setSystemInfo("user name",System.getProperty("user.name"));
		extentReport.setSystemInfo("java version",System.getProperty("java.version"));
		extentReport.setSystemInfo("os name",System.getProperty("os.name"));
		
		return extentReport;
	}

	
}