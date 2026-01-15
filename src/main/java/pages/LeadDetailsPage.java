package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LeadDetailsPage extends BasePage {

	@FindBy(xpath = "//h2[@class='pageDescription']")
    WebElement leadNameHeader;

    public LeadDetailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLeadDetailsDisplayed() {
        return waitForVisibility(leadNameHeader).isDisplayed();
    }
}
