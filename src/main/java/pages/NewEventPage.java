package pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewEventPage extends BasePage {

	public NewEventPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "evt5")   
    WebElement subjectField;

    @FindBy(xpath = "//img[@title='Subject Combo (New Window)']")
    WebElement subjectComboIcon;
    
    @FindBy(xpath = "//a[text()='Other']")
    WebElement otherOption;

    @FindBy(id = "EndDateTime_time")
    WebElement endTimeField;
    
    @FindBy(id="simpleTimePicker")
    WebElement endTimeDropdownContainer;

    @FindBy(xpath = "//div[@id='timePickerItem_2100']") 
    WebElement ninePmOption;

    @FindBy(xpath = "//input[@name='save']")
    WebElement saveButton;

    @FindBy(xpath = "//h2[@class='pageDescription']")
    WebElement newEventHeader;
    
    public boolean isCursorInSubjectField() {
        return subjectField.equals(driver.switchTo().activeElement());
    }

    public void switchToMainWindow(String mainHandle) {
        driver.switchTo().window(mainHandle);
    }
 
    public void clickEndTime() {
    	waitForVisibility(endTimeField).click();
    }

    public void selectNinePm() {
        click(ninePmOption);
    }

    public CalendarPage clickSave() {
        click(saveButton);
        return new CalendarPage(driver);
    }
    public String openSubjectPopup() {
    	subjectComboIcon.click();

        String main = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();

        for (String h : handles) {
            if (!h.equals(main)) {
                driver.switchTo().window(h);
                return h; // popup handle
            }
        }
        throw new RuntimeException("Popup window not found");
    }
    public void selectOtherInPopup() {
        waitForVisibility(otherOption).click();
    }
    public boolean isPopupClosed(String popupHandle) {
        try {
            driver.switchTo().window(popupHandle);
            return false; // still open
        } catch (NoSuchWindowException e) {
            return true; // popup is gone
        }
    }

    public boolean isComboPopupClosed() {
    	return driver.getWindowHandles().size() == 1;
      }
    public String getSubjectFieldValue() {
    	wait.until(ExpectedConditions.attributeToBeNotEmpty(subjectField, "value"));
        return subjectField.getAttribute("value").trim();
    }
    public boolean isEndTimeDropdownDisplayed() {
    	waitForVisibility(endTimeDropdownContainer);
        return endTimeDropdownContainer.isDisplayed();
    }
    public boolean dropdownContainsRange(String start, String end) {
        List<WebElement> items = driver.findElements(By.xpath("//div[contains(@id,'timePickerItem_')]"));
        boolean foundStart = false, foundEnd = false;

        for (WebElement item : items) {
            String text = item.getText().trim();
            if (text.equals(start)) foundStart = true;
            if (text.equals(end)) foundEnd = true;
        }
        return foundStart && foundEnd;
    }
    //check
    public void selectEndTime(String timeText) {
        By option = By.xpath("//div[contains(@id,'timePickerItem') and normalize-space()='" + timeText + "']");
        waitForVisibility(option).click();
    }
    public void clickSave1() {
        waitForVisibility(saveButton).click();
    }
    
    public String getEndTimeValue() {
        return endTimeField.getAttribute("value").trim();
    }
    public boolean isNewEventPageDisplayed() {
    	return newEventHeader.isDisplayed();
    }
    

}


