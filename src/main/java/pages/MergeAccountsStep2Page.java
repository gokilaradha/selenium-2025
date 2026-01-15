package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MergeAccountsStep2Page extends BasePage {
	
    By step2Header = By.xpath("//h2[contains(text(),'Step 2')]");
	
	@FindBy(xpath = "//table[contains(@class,'mergeEntity')]")
    WebElement mergeComparisonTable;
	
    @FindBy(name = "save")
    WebElement mergeButton;

    public MergeAccountsStep2Page(WebDriver driver) {
        super(driver);
    }
    
    //Validations
    public boolean isStep2FormDisplayed() {
        waitForVisibility(step2Header);
        return driver.findElement(step2Header).isDisplayed();
    }

    public boolean isComparisonTableDisplayed() {
        waitForVisibility(mergeComparisonTable);
        return mergeComparisonTable.isDisplayed();
    }
    public void clickMerge() {
    	waitForClick(mergeButton);
    	System.out.println("Clicking Merge button...");
    	click(mergeButton);
        System.out.println("Merge button clicked. Waiting for alert...");
        waitForAlert();
    }

    public void acceptMergeAlert() {
        Alert alert = driver.switchTo().alert();
        System.out.println("Alert text: " + alert.getText());
        alert.accept();
        System.out.println("Merge alert accepted.");
    }
    
    public void proceedWithMerge() {
        clickMerge();        
        acceptMergeAlert();  
    }


}
