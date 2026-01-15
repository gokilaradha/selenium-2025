package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utils.WaitUtils;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="userNavLabel")	
	WebElement loggedInUserName;
	
	@FindBy(id="home_Tab")
	WebElement homeTab;
	
	@FindBy(id="Account_Tab")
	WebElement accountTab;
	
	@FindBy(id="Opportunity_Tab")
	WebElement opportunityTab;
	
	@FindBy(id="AllTab_Tab")
	WebElement allTabsPlusIcon;
	
	@FindBy(id="Lead_Tab")
	WebElement leadsTab;
	
	@FindBy(id="Contact_Tab")
	WebElement contactsTab;
	
	@FindBy(xpath = "//a[normalize-space()='Logout']")
    WebElement logoutLink;
	
	//Username link on Home Page(top-left)
	@FindBy(xpath = "//h1[@class='currentStatusUserName']/a")
	WebElement userNameLink;
	
	By userNameTopLeft = By.id("tailBreadcrumbNode");
	By topRightUserName = By.id("userNavLabel");
	
	//All Tabs Page
	@FindBy(xpath = "//h1[text()='All Tabs']")
    WebElement allTabsHeader;

    @FindBy(xpath = "//input[@value='Customize My Tabs']")
    WebElement customizeMyTabsButton;
    
    //Current Date Link
    @FindBy(xpath = "//span[@class='pageDescription']/a")
    WebElement currentDateLink;

    public void waitForUpdatedUserName(String expectedLastName) {
	     wait.until(ExpectedConditions.textToBePresentInElementLocated(
	            userNameTopLeft, expectedLastName));
	}
	public String getTopLeftUserName() {
	    return driver.findElement(userNameTopLeft).getText().trim();
	}

	public String getTopRightUserName() {
	    return driver.findElement(topRightUserName).getText().trim();
	}

	public String getLoggedInUserName() {
		return loggedInUserName.getText();
	}
	
	public boolean isHomePageDisplayedForHomeTab() {
        waitForVisibility(userNameLink);
        return userNameLink.isDisplayed();
    }
	public String getDisplayedUserName() {
        return getText(userNameLink);
    }

    public UserProfilePage clickUserNameLink() {
        click(userNameLink);
        return new UserProfilePage(driver);
    }

	public boolean isHomePageDisplayed(WebDriver driver) {
		try {
        waitForVisibility(homeTab);
        return homeTab.isDisplayed();
    } catch (Exception e ) {
    	return false;
    }
		
	}		
/*	public void clickOnPlus() {
		click(allTabsPlusIcon);
	}*/
	
	public AccountsHomePage clickAccounts() {
		waitForVisibility(accountTab).click();
		return new AccountsHomePage(driver);
	}
	public OpportunitiesPage clickOpportunities() {
	    waitForVisibility(opportunityTab).click();
	    return new OpportunitiesPage(driver);
	}
	
	public boolean isOpportunitiesLinkDisplayed() {
	    return waitForVisibility(opportunityTab).isDisplayed();
	}
    
	public LeadsPage clickLeads() {
	   // waitForVisibility(leadsTab).click();
		WaitUtils.waitForElementVisibility(leadsTab, driver);
		leadsTab.click();
	    return new LeadsPage(driver);
	}
	
	
	public ContactsPage clickContactsTab() {
    	waitForVisibility(contactsTab).click();
	    return new ContactsPage(driver);
    }
	public HomePage clickHomeTab() {
        click(homeTab);
        return this;
    }
	public boolean isUserNameLinkDisplayed() {
        return userNameLink.isDisplayed();
    }
    
	public void logout() {
        waitForVisibility(loggedInUserName).click();
        waitForVisibility(logoutLink).click();
    }
	
	public boolean isAllTabsPageDisplayed() {
        return allTabsHeader.isDisplayed();
    }

	public AllTabsPage clickAllTabs() {
        click(allTabsPlusIcon);
        return new AllTabsPage(driver);
    }
	public boolean isTabPresentInTabBar(String tabName) {
	    List<WebElement> tabs = driver.findElements(By.xpath("//ul[@id='tabBar']//a"));
	    for (WebElement tab : tabs) {
	        if (tab.getText().trim().equalsIgnoreCase(tabName)) {
	            return true;
	        }
	    }
	    return false;
	}

	//Blocking an event in the calendar
	public String getCurrentDateText() {
        return currentDateLink.getText().trim();
    }

    public CalendarPage clickCurrentDate() {
        click(currentDateLink);
        return new CalendarPage(driver);
    }
    public boolean isHomePageDisplayedWithCurrentDate() {
        return currentDateLink.isDisplayed();
    }
}