package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OpportunityPipelinePage extends BasePage{
	
	  public  OpportunityPipelinePage(WebDriver driver) {
		  super(driver);
	  }
	  
	  @FindBy(xpath = "//h1[contains(text(),'Opportunity Pipeline')]")
	  WebElement pipelineHeader;

	  public boolean isPipelineReportDisplayed() {
	      return waitForVisibility(pipelineHeader).isDisplayed();
	  }


}
