package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StuckOpportunitiesPage extends BasePage {

	public StuckOpportunitiesPage(WebDriver driver) {
        super(driver);
    }
	
	@FindBy(xpath = "//h1[contains(text(),'Stuck Opportunities')]")
    WebElement stuckOppHeader;
    
    public boolean isStuckOpportunitiesReportDisplayed() {
        return waitForVisibility(stuckOppHeader).isDisplayed();
    }

}
