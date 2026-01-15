package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class QuarterlySummaryPage extends BasePage{
	
	public QuarterlySummaryPage(WebDriver driver) {
        super(driver);
    }
	
	@FindBy(id = "quarter_q")   
    WebElement intervalDropdown;

    @FindBy(id = "open")        
    WebElement includeDropdown;

    @FindBy(xpath = "//input[@value='Run Report']")
    WebElement runReportButton;

    @FindBy(xpath = "//h1[contains(text(),'Opportunity Report')]")
    WebElement reportHeader;

    public void selectInterval(String interval) {
        new Select(waitForVisibility(intervalDropdown)).selectByVisibleText(interval);
    }

    public void selectInclude(String include) {
        new Select(waitForVisibility(includeDropdown)).selectByVisibleText(include);
    }

    public void runReport() {
        waitForVisibility(runReportButton).click();
    }

    public boolean isReportDisplayed() {
        return waitForVisibility(reportHeader).isDisplayed();
    }


}
