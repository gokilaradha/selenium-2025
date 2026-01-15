package pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage{
	    public WebDriver driver;
	    public WebDriverWait wait;
	    public String mainWindowHandle;

	    public BasePage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        PageFactory.initElements(driver, this);
	    }

	    // Generic click method with wait
	    public void click(WebElement element) {
	        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	    }

	    // Generic type method with wait
	    public void type(WebElement element, String text) {
	        wait.until(ExpectedConditions.visibilityOf(element)).clear();
	        element.sendKeys(text);
	    }
	    public void click(By locator) {
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
	        element.click();
	    }
	    // Generic getText method
	    public String getText(WebElement element) {
	        return wait.until(ExpectedConditions.visibilityOf(element)).getText().trim();
	    }

	    // Generic dropdown select
	    public void selectByVisibleText(WebElement element, String text) {
	        wait.until(ExpectedConditions.visibilityOf(element));
	        new Select(element).selectByVisibleText(text);
	    }

	    // Generic wait for clickability
	    public void waitForClick(WebElement element) {
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	    }
	    
	    public void selectDropdownByValue(WebElement dropdownElement, String value) {
	        Select select = new Select(dropdownElement);
	        select.selectByValue(value);
	    }

	    public void selectDropdownByIndex(WebElement dropdownElement, int index) {
	        Select select = new Select(dropdownElement);
	        select.selectByIndex(index);
	    }
	    public void selectDropdownByVisibleText(WebElement dropdownElement, String visibleText) {
	    	waitForVisibility(dropdownElement);
	        Select select = new Select(dropdownElement);
	        select.selectByVisibleText(visibleText);
	    }
	    public WebElement waitForVisibility(By locator) {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }
	    
	    public void waitForAlert() {
	        new WebDriverWait(driver, Duration.ofSeconds(20))
	                .until(ExpectedConditions.alertIsPresent());
	    }
	    
	    public void storeMainWindow() {
	        mainWindowHandle = driver.getWindowHandle();
	    }

	    public void switchToFrameContaining(By locator) {
	        driver.switchTo().defaultContent();
	        for (WebElement frame : driver.findElements(By.tagName("iframe"))) {
	            driver.switchTo().frame(frame);
	            if (driver.findElements(locator).size() > 0) {
	                return;
	            }
	            driver.switchTo().defaultContent();
	        }
	    }
	    public void closeChildWindowsAndReturn() {
	        for (String handle : driver.getWindowHandles()) {
	            if (!handle.equals(mainWindowHandle)) {
	                driver.switchTo().window(handle);
	                driver.close();
	            }
	        }
	        driver.switchTo().window(mainWindowHandle);
	    }

	    public void switchToNewWindow() {
	        String current = driver.getWindowHandle();

	        // Wait until a new window appears
	        new WebDriverWait(driver, Duration.ofSeconds(10))
	                .until(driver -> driver.getWindowHandles().size() > 1);

	        // Switch to the new window
	        for (String handle : driver.getWindowHandles()) {
	            if (!handle.equals(current)) {
	                driver.switchTo().window(handle);
	                break;
	            }
	        }
	    }

	    public WebElement waitForVisibility(WebElement element) {
	        return wait.until(ExpectedConditions.visibilityOf(element));
	    }
	    public void click1(WebElement element) {
	        waitForVisibility(element);
	        element.click();
	    }

}




	

