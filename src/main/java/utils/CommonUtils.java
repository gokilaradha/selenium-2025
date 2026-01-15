package utils;

import java.io.File;
import java.text.SimpleDateFormat;

import java.util.Date;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import pages.HomePage;
import tests.BaseTest;

public class CommonUtils {
	public static String getScreenshotOfPage(WebDriver driver) {
		String screenshotPath=System.getProperty("user.dir")+"/src/main/resources/screenshots/"+System.currentTimeMillis()+".png";
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File dst=new File(screenshotPath);
		source.renameTo(dst);
		return screenshotPath;
	}
    
	public static String getScreenshotOfWebElement(WebDriver driver , WebElement element) {
		String screenshotPath=System.getProperty("user.dir")+"/src/main/resources/screenshots/"+System.currentTimeMillis()+".png";
		File source=element.getScreenshotAs(OutputType.FILE);
		File dst=new File(screenshotPath);
		source.renameTo(dst);
		return screenshotPath;
	}
	
	public static void mouseHover(WebDriver driver,WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	public static void clickUsingJs(WebDriver driver,WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public static void setAttributeValueOfElement(WebDriver driver,WebElement element,String attributeName,String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('"+attributeName+"',"+value+")", element);
	}	
	public static String getTimeStamp() {
			return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		}	
	
	public static HomePage OAuthHomePage(WebDriver driver)
	{
		String url = BaseTest.getSfdcDirectLoginUrl();
		driver.get(url);
		return new HomePage(driver);
		
	}
	
}
