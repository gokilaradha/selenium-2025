package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountsHomePage extends BasePage {
	
	public AccountsHomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="userNavLabel")	
	WebElement loggedInUserName;
	
	@FindBy(xpath="//h1[@class='pageType']")
	WebElement accountHomePage;
	
	@FindBy(xpath = "//h3[text()='Tools']")
    WebElement toolsHeader;
	
	@FindBy(xpath="//h3[text()='Reports']")
	WebElement reportsHeader;
	
	@FindBy(xpath="//h3[text()='Recent Accounts']")
	WebElement recentAccountsHeader;
	
	@FindBy(xpath="//input[@name='new']")
	WebElement newButton;
	
	@FindBy(xpath="//a[text()='Create New View']")
	WebElement createNewViewLink;
		
	@FindBy(xpath = "//h2[contains(text(),'Create New View')]")
    WebElement createNewViewHeader;

	
	@FindBy(xpath="//a[normalize-space() = 'Accounts with last activity > 30 days']")
    WebElement reportLink;

	@FindBy(xpath="//a[normalize-space()='Merge Accounts']")
	WebElement mergeAccountsLink;
	
	By homeHeader = By.xpath("//h2[@class='pageDescription' and contains(text(),'Home')]");
    By firstRecentAccountLink = By.xpath("//table[contains(@class,'list')]//tr[2]/th/a");
    
    //Page objects for Edit Opportunity to get the recent account name
    @FindBy(xpath = "//table[@class='list']//tr[2]//a")
    WebElement firstRecentAccountName;

    public void clickNewButton() {
		waitForVisibility(newButton); 
		click(newButton);
    }
    
    public void clickCreateNewView() {
		waitForVisibility(createNewViewLink); 
		click(createNewViewLink);
	   }
	public void clickMergeAccounts() {
        waitForVisibility(toolsHeader);
        click(mergeAccountsLink);
    }
	public boolean verifyAccountPage(String expectedUsername) {
		waitForVisibility(accountHomePage);
		waitForVisibility(recentAccountsHeader);
		waitForVisibility(reportsHeader);
		waitForVisibility(toolsHeader);
		   	
		//Verify username from top-right dropdown
		waitForVisibility(loggedInUserName);
		return  loggedInUserName.getText().trim().equalsIgnoreCase(expectedUsername);
}	
	public boolean isAccountsHomePageLoaded() {
        waitForVisibility(homeHeader);
        return driver.findElement(homeHeader).isDisplayed();
    }

    //  Get first account in Recent Accounts
    public String getFirstRecentAccountName() {
        waitForVisibility(firstRecentAccountLink);
        return driver.findElement(firstRecentAccountLink).getText().trim();
    }
    public void openLastActivityReport() {
        click(reportLink);
    }
    
    public String getAccountNameForOpportunity() {
    	return waitForVisibility(firstRecentAccountName).getText().trim();
    }
}
