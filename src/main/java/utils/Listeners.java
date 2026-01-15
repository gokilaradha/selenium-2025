package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {
	
    public void onTestSuccess(ITestResult result) {
    	System.out.println("Test passed");
    }
    
    public void onTestFailure(ITestResult result) {
    	System.out.println("Test failed");
    }
}
