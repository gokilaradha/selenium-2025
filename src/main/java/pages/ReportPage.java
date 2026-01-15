package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.WaitUtils;

public class ReportPage extends BasePage{
	
	   public ReportPage (WebDriver driver) {
	        super(driver);
	    }

	    @FindBy(id = "ext-gen149") 
	    WebElement dateFieldDropdown;

	    @FindBy(id = "ext-comp-1042") 
	    WebElement fromDate;

	    @FindBy(id = "ext-comp-1045") 
	    WebElement toDate;

	    @FindBy(id = "saveReportBtn") 
	    WebElement saveBtn;
	    
	    @FindBy(xpath="//h2[@class='pageDescription']")
        WebElement unsavedReportsHeader;
	    
	    @FindBy(xpath = "//div[@class='x-grid3-body']") 
	    WebElement previewTable;
 
	    @FindBy(id = "ext-gen153") 
	    WebElement fromDateCalendarIcon;

	    @FindBy(xpath = "//td[contains(@class,'x-date-active') and contains(@class,'x-date-today')]")
	    WebElement todayDateCell;
	    
	    @FindBy(id="saveReportDlg")
	    WebElement saveReportModalPopup;

	    @FindBy(xpath="//input[@id='saveReportDlg_reportNameField']")
	    WebElement reportNameInput;

	    @FindBy(xpath="//div[@id='x-form-el-saveReportDlg_DeveloperName']")
	    WebElement reportUniqueNameInput;

	    @FindBy(xpath = "//button[contains(normalize-space(), 'Save and Run Report')]")
	    WebElement saveAndRunButton;
	    
	    @FindBy(css="ext-el-mask")
	    WebElement maskLocator;
	    
	    @FindBy(id="stateCountryPicklistWarning")
	    WebElement StateCountryModalWindow;
	    
	    @FindBy(xpath = "//h1[contains(@class,'pageType')]")
	    WebElement reportHeader;
	    	    	    
	    @FindBy(id="buttonNever")
	    WebElement neverUpdateButton;
	    

	    public void selectDateField(String dateField,String today) {
	    	waitForVisibility(dateFieldDropdown).click();
	    	WebElement option = driver.findElement(By.xpath("//div[contains(@class,'x-combo-list-item')"
	    			+ " and normalize-space()='" + dateField + "']"));
	    	    waitForVisibility(option).click();
	    	 // Enter today's date in From field
	        waitForVisibility(fromDateCalendarIcon).click();;
	       // Wait for calendar popup and click today's date
	        waitForVisibility(todayDateCell);
	        click(todayDateCell);
	        // Wait for Salesforce to auto-fill the To field
	        waitForVisibility(toDate);
	    }

	    public void clickSave() {
	    	
	    	waitForVisibility(saveBtn).click();
	    }
	    public boolean isUnsavedReportPageDisplayed() {
	        try {
	          waitForVisibility(unsavedReportsHeader);
	           return unsavedReportsHeader.isDisplayed(); 
	        } catch (Exception e) {
	            return false;
	        }
	    }

	     public boolean isPreviewTableDisplayed() {
	        try {
	             waitForVisibility(previewTable);
	             return previewTable.isDisplayed();
	        } catch (Exception e) {
	            return false;
	        }
	    }
	    
	     public void enterAndSaveReport(String reportName) throws InterruptedException {
            waitForVisibility(saveReportModalPopup);
            waitForVisibility(reportNameInput);
            reportNameInput.clear();
            reportNameInput.sendKeys(reportName);
            reportUniqueNameInput.click();
            wait.until(ExpectedConditions.elementToBeClickable(saveAndRunButton));
            Thread.sleep(1000);
            By saveAndRunReport = By.xpath("//button[contains(normalize-space(), 'Save and Run Report')]");
            driver.findElement(saveAndRunReport).click();
            WaitUtils.waitForElementToDisappear(driver,saveReportModalPopup);
            wait.until(ExpectedConditions.visibilityOf(StateCountryModalWindow));
            wait.until(ExpectedConditions.elementToBeClickable(neverUpdateButton)).click();
         }

	    public boolean verifyReportHeader(String expectedReportName) {
	            String actualHeader = waitForVisibility(reportHeader).getText();
	            return actualHeader.contains(expectedReportName);
	    }
	    
	    
}

