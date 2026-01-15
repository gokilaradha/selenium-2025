package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CalendarPage extends BasePage{

	public CalendarPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(text(),'8:00 PM')]")
    WebElement eightPmLink;

    @FindBy(xpath="//h1[contains(text(),'Calendar for Radha Abcd - Day View')]")
    WebElement calendarForFirstAndLastName;
    
    @FindBy(xpath = "//h1[@class='pageType']")
    WebElement calendarTitle;

    @FindBy(xpath="//div[contains(@class,'eventBlockDivDnD') and contains(@class,'eventBusy')]")
    WebElement otherEventLink;
    
    public NewEventPage clickEightPm() {
        click1(eightPmLink);
        return new NewEventPage(driver);
    }
    public boolean isCalendarPageDisplayed() {
    	waitForVisibility(calendarForFirstAndLastName);
        return calendarForFirstAndLastName.isDisplayed();
    }
    public String getCalendarTitle() {
        return calendarTitle.getText().trim();
    }

    public boolean isOtherEventPresentAt8PM() {
          return otherEventLink.isDisplayed();
    }

}
