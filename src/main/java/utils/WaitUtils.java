package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.WaitConstants;

public class WaitUtils {
	
	public static boolean waitForElementVisibility(WebElement elementToWait,WebDriver driver) {
		boolean isElementVisible=false;
		try {
			WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOf(elementToWait));
			isElementVisible=true;
		}catch(WebDriverException e) {
			e.printStackTrace();
		}
		return isElementVisible;
	}
public static boolean waitForElementInVisibility(By locator,WebDriver driver,int timeoutInSeconds) {
		
		try {
			WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		}catch(TimeoutException e) {
			System.out.println("Element not visible within " +locator);
		}
		return false;
	}
	
	public static boolean waitForElementInvisibility(WebElement elementToWait,WebDriver driver) {
		boolean isElementInvisible=false;
		try {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
			wait.until(ExpectedConditions.invisibilityOf(elementToWait));
			isElementInvisible=true;
		}catch (WebDriverException e) {
			e.printStackTrace();
		}
		return isElementInvisible;
	}
	
	public static WebElement waitForElementVisibility(By locator, WebDriver driver) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    } catch (WebDriverException e) {
	        return null;
	    }
	}

	public static boolean waitForElement(WebDriver driver, WebElement element) {
		boolean isElementFound = false;
		WebDriverWait wait = new WebDriverWait(driver, WaitConstants.WAIT_FOR_ELEMENT_TO_BE_CLICKABLE);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			isElementFound = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isElementFound;
	}
	
	public static boolean waitForElementToDisappear(WebDriver driver, WebElement element) {
		boolean isElementDisappeared = false;
		WebDriverWait wait = new WebDriverWait(driver, WaitConstants.WAIT_FOR_ELEMENT_TO_DISAPPEAR);
		try {
			wait.until(ExpectedConditions.invisibilityOf(element));
			isElementDisappeared = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isElementDisappeared;
	}
	
	
}





