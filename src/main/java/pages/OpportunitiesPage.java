package pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class OpportunitiesPage extends BasePage{
	
	public OpportunitiesPage(WebDriver driver) {
		super(driver);
	}
    
	@FindBy(id="Opportunity_Tab")
	WebElement opportunityTab;
	
	@FindBy(xpath="//div[@class='content']")
	WebElement opportunitiesHeader;
	
	@FindBy(id="fcf")
	WebElement opportunitiesViewDropdown;
			
	@FindBy(xpath = "//select[@id='fcf']")
	private WebElement opportunitiesDropdown;
	
	//Create New Opportunity
	@FindBy(xpath = "//input[@value=' New ']")
	WebElement newButton;
    
	@FindBy(xpath="//div[@class='bPageTitle']")
	WebElement opportunityEditPageTitle;
	
	@FindBy(id = "opp3") 
	WebElement opportunityName;

	@FindBy(id = "opp4") 
	WebElement accountName;

	@FindBy(id = "opp9") 
	WebElement closeDate;

	@FindBy(id = "opp11") 
	WebElement stageDropdown;

	@FindBy(id = "opp12") 
	WebElement probability;

	@FindBy(id = "opp6")
	WebElement leadSourceDropdown;
	
	@FindBy(name = "save")
	WebElement saveButton;
	
	@FindBy(xpath = "//h1[@class='pageType']")
	WebElement opportunityPage;
	
	@FindBy(xpath ="//h2[contains(text(),' New Opportunity')]")
	WebElement editOpportunity;

	@FindBy(xpath = "//h2[contains(normalize-space(),'Opportunity Detail')]")
	WebElement opportunityDetailPage;

	@FindBy(xpath = "//a[normalize-space()='Opportunity Pipeline']")
	WebElement opportunityPipelineLink;
	
	@FindBy(xpath = "//a[normalize-space()='Stuck Opportunities']")
	WebElement stuckOpportunitiesLink;
	
	@FindBy(xpath = "//h3[contains(text(),'Quarterly Summary')]")
	WebElement quarterlySummaryLink;


	public boolean isOpportunitiesHomeDisplayed() {
	    return waitForVisibility(opportunityPage).isDisplayed();
	}
	
	public boolean iseditOpportunitiesDisplayed() {
	    return waitForVisibility(editOpportunity).isDisplayed();
	}

	public List<String> getOpportunitiesDropdownValues() {
	    Select select = new Select(waitForVisibility(opportunitiesDropdown));
	    return select.getOptions()
	                 .stream()
	                 .map(WebElement::getText)
	                 .collect(Collectors.toList());
	}
	
	
	public void createNewOpportunity(String oppName, String accName, String date,
			String stage, String prob, String leadSource) {
		 waitForVisibility(opportunityName).sendKeys(oppName);
	    accountName.sendKeys(accName);
	    closeDate.sendKeys(date);
	    new Select(stageDropdown).selectByVisibleText(stage);
	    probability.clear();
	    probability.sendKeys(prob);
	    new Select(leadSourceDropdown).selectByVisibleText(leadSource);
	    saveButton.click();
	}
	public void clickNewButton() {
		waitForVisibility(newButton).click();
	}
	public boolean isOpportunityDetailDisplayed() {
	    return waitForVisibility(opportunityDetailPage).isDisplayed();
	}

	public OpportunityPipelinePage clickOpportunityPipeline() {
	    waitForVisibility(opportunityPipelineLink).click();
	    return new OpportunityPipelinePage(driver);
	}
	
	public StuckOpportunitiesPage clickStuckOpportunities() {
	    waitForVisibility(stuckOpportunitiesLink).click();
	    return new StuckOpportunitiesPage(driver);
	}

	public QuarterlySummaryPage clickQuarterlySummary() {
	    waitForVisibility(quarterlySummaryLink).click();
	    return new QuarterlySummaryPage(driver);
	}


}

