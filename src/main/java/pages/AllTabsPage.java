package pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class AllTabsPage extends BasePage{
     
	public AllTabsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[contains(text(),'All Tabs')]")
    WebElement allTabsHeader;

    @FindBy(xpath = "//input[@value='Customize My Tabs']")
    WebElement customizeMyTabsButton;

    @FindBy(xpath = "//div[@class='bPageTitle']")
    WebElement customizeMyTabsHeader;

    //Customize Tabs section 
    @FindBy(id = "duel_select_1")
    WebElement selectedTabsList;

    @FindBy(id = "duel_select_0")
    WebElement availableTabsList;

    @FindBy(xpath = "//img[@title='Remove']")
    WebElement removeButton;

    @FindBy(xpath = "//input[@class='btn primary']")
    WebElement saveButton;

    public boolean isAllTabsPageDisplayed() {
        return allTabsHeader.isDisplayed();
    }
    public void clickCustomizeMyTabs() {
        click1(customizeMyTabsButton);
    }
    public String removeAnyTab() {
    	Select select =new Select(selectedTabsList);
    	List<WebElement> tabs = select.getOptions();

    	for (WebElement tab : tabs) {
    	 String tabName = tab.getText().trim();
    	    // Skip Home tab
    	    if (!tabName.equalsIgnoreCase("Home (default)")) {
    	    	selectByVisibleText(selectedTabsList,tabName);
    	    	click(removeButton);
    	        //  Handle alert (if it appears)
    	        try {
    	            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
    	            System.out.println("Alert displayed: " + alert.getText());
    	            alert.accept();
    	            System.out.println("alert accepted");
    	        } catch (TimeoutException e) {
    	            // No alert appeared 
    	        }

    	        System.out.println("Removed tab: " + tabName);
    	        break; // Remove only one tab
    	    }
    	}

    	click(saveButton);
    	System.out.println("after save button");
        return "Tab removed successfully"; 
  }
    
    public void clickSave() {
        click1(saveButton);
    }
    public boolean isCutomizeMyTabsPageDisplayed() {
        return customizeMyTabsHeader.isDisplayed();
    }
}
