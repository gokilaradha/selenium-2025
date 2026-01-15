package pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WaitUtils;

public class ForgotPasswordPage extends BasePage {
	
	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h1[contains(.,'Reset Your Password')]")
	WebElement resetPassword;
	
	@FindBy(id="un")
	WebElement usernameField;
	
	@FindBy(id="continue")
	WebElement continueButton;
	
	@FindBy(xpath=("//p[contains(text(),'We’ve sent you an email')]"))
	WebElement confirmationMessage;
	
	public void resetPassword(String username) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(resetPassword));
		wait.until(ExpectedConditions.visibilityOf(usernameField));
		usernameField.clear();
		usernameField.sendKeys(username);
		continueButton.click();
	}
	
	public boolean isConfirmationDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		return wait.until(driver1 ->
        driver1.getPageSource().toLowerCase().contains("check your email"));
	}
	
	public String getEmailSentMessage(WebDriver driver)  {
    	if(WaitUtils.waitForElementVisibility(confirmationMessage, driver)) {
    	return confirmationMessage.getText().trim();
        } else {
        	return null;
        }
}
}
