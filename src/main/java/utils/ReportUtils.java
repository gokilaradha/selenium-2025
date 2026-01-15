package utils;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import constants.FileConstants;

@Listeners(utils.Listeners.class)
public class ReportUtils {
      @Test
      public void test01() {
    	
    }
	public static void main(String[] args) {
		
		ExtentReports report = new ExtentReports();
		ExtentSparkReporter sReport =new ExtentSparkReporter(FileConstants.REPORT_PATH);
		report.attachReporter(sReport);
		
		ExtentTest test = report.createTest("TC01");
		test.log(Status.INFO, "username entered");
		test.log(Status.INFO, "password entered");
		test.log(Status.INFO,"login button clicked");
		test.log(Status.PASS,"TC01 passed");
		
		report.flush();
	}
}
