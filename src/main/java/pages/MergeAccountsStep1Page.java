package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MergeAccountsStep1Page extends BasePage {
	
	public  MergeAccountsStep1Page(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath = "//h1[contains(text(),'Merge My Accounts')]")
    WebElement mergeHeader;

    @FindBy(id = "srch")
    WebElement accountSearchBox;

    @FindBy(xpath = "//input[@title='Find Accounts' and @type='submit']")
    WebElement findAccountsBtn;
    
    @FindBy(name = "goNext")
    WebElement nextBtn;

    By resultsTableRows = By.xpath("//table[contains(@class,'list')]//tr");

    By checkboxLocator = By.xpath("//input[@type='checkbox' and contains(@id,'cid')]");

    String masterRecordName;

    public String getMasterRecordName() {
        return masterRecordName;
    }

    public boolean isMergePageDisplayed() {
        return mergeHeader.isDisplayed();
    }
    public void clickFindAccounts() {
        List<WebElement> buttons = driver.findElements(By.xpath("//input[@name='srchbutton' and @value='Find Accounts']"));

        for (WebElement btn : buttons) {
            if (btn.isDisplayed() && btn.isEnabled()) {
                waitForClick(btn);
                btn.click();
                return;
            }
        }

        throw new RuntimeException("No visible Find Accounts button found");
    }
    public void searchAccount(String accountName) {
        accountSearchBox.clear();
        accountSearchBox.sendKeys(accountName);
        clickFindAccounts();
        waitForVisibility(resultsTableRows);
       }
    public void proceedToStep2(String accountName) {

    	//Search
        searchAccount(accountName);

        //Count available accounts
        int available = getAvailableAccountsCount();
        System.out.println("Accounts found: " + available);

        if (available == 3) {
            System.out.println("Exactly 3 accounts found. Auto-selected by Salesforce.");
            clickNext();
            return;
        }

        if (available > 3) {
            System.out.println("More than 3 accounts found. Selecting first 3 manually...");
            selectAccounts(3);
            clickNext();
            return;
        }

        throw new RuntimeException("Not enough accounts to merge. Need at least 2.");

    }

    
    public int getAvailableAccountsCount() {
        waitForVisibility(resultsTableRows);
        return driver.findElements(checkboxLocator).size();

    }

    public void selectAccounts(int count) {
    	// Wait for the results table to load
        waitForVisibility(resultsTableRows);
        // Fetch checkboxes dynamically using By
        List<WebElement> checkboxes = driver.findElements(checkboxLocator);
        int available = checkboxes.size();
        if (available < count) {
            throw new RuntimeException(
                "Requested " + count + " accounts but only found " + available
            );
        }
        // Click them safely
        for (int i = 0; i < count; i++) {
            WebElement cb = checkboxes.get(i);
            waitForClick(cb);
            cb.click();
        }
    }

    public void clickNext() {
    	waitForClick(nextBtn);
        click(nextBtn);
    }
    
    public String getFirstAccountName() {
        By firstAccountName = By.xpath("(//table[contains(@class,'list')]//tr//th/a)[1]");
        waitForVisibility(firstAccountName);
        return driver.findElement(firstAccountName).getText().trim();
    }

}
