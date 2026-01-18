package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class LeadsPage extends BasePage {
	
	public LeadsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h1[@class='pageType']")
    WebElement leadsHeader;

	@FindBy(id="fcf")
	WebElement leadsViewDropdown;
	
	@FindBy(xpath="//input[@title='Go!']")  // Go button
    WebElement goButton;
	
	@FindBy(xpath="//select[@name='fcf']")
	WebElement selectedViewPage;

	@FindBy(name = "new")   // New button in Recent Leads
    WebElement newButton;

	public boolean isLeadsHomePageDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(leadsViewDropdown));
	    return leadsViewDropdown.isDisplayed();

    }
	
	public List<String> getLeadsDropdownValues() {
	    Select select = new Select(waitForVisibility(leadsViewDropdown));
	    return select.getOptions().stream()
	                 .map(WebElement::getText)
	                 .map(String::trim)
	                 .toList();
	}
    
	public void selectView(String viewName) {
        Select select = new Select(waitForVisibility(leadsViewDropdown));
        select.selectByVisibleText(viewName);
    }

    public String getSelectedView() {
        Select select = new Select(waitForVisibility(leadsViewDropdown));
        return select.getFirstSelectedOption().getText().trim();
    }
    public void selectTodaysLeads() {
        Select select = new Select(waitForVisibility(leadsViewDropdown));
        select.selectByVisibleText("Today's Leads");
    }

    public String isLeadDefaultViewSelected() {
        Select select = new Select(waitForVisibility(selectedViewPage));
        return select.getFirstSelectedOption().getText().trim();
        
    }

    public boolean isTodaysLeadsSelected() {
        Select select = new Select(waitForVisibility(selectedViewPage));
        return select.getFirstSelectedOption().getText().trim().equals("Today's Leads");
   }
    
    public void clickGo() {
        waitForVisibility(goButton).click();
    }

    public NewLeadPage clickNewButton() {
        waitForVisibility(newButton).click();
        return new NewLeadPage(driver);
    }

	
}
