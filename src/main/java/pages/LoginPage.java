package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import constants.FileConstants;
import utils.FileUtils;
import utils.GmailUtil;
import utils.WaitUtils;

public class LoginPage extends BasePage{
	
	//CONSTRUCTOR
		public LoginPage(WebDriver driver) {
			super(driver); //passes driver to Basepage
		}
		
	//PAGE FACTORY LOCATORS
	@FindBy(id="username")
	WebElement username;
	
	@FindBy(id="password")
	WebElement password; 
	
	@FindBy(id="Login")
	WebElement loginButton;
	
	@FindBy(id="error")
	WebElement errorMessage;
	
	@FindBy(id="rememberUn")
	WebElement rememberMeCheckbox;
	
	@FindBy(id="forgot_password_link")
	WebElement forgotPasswordLink;
	
	@FindBy(id="emc")
	WebElement verificationCodeInput;
	
	@FindBy(css="#save")
	WebElement verifyButton;
	
	@FindBy(id="home_Tab")
	WebElement homeTab;
	
	// This appears only when Remember Me is active
	@FindBy(id = "idcard-identity")
	WebElement rememberedUsernameLabel;
	
		
	public boolean isLoginPageVisible() {
		return this.loginButton.isDisplayed();
	}
	public HomePage loginToApp(WebDriver driver)throws InterruptedException {
		enterUsername(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "username"));
		enterPassword(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "password"));
		rememberMeCheckbox.click();
		clickLogin();
		Thread.sleep(10000);
		String otp = GmailUtil.getOTP();
		enterVerificationCode(otp);
		clickOnVerifyButton(driver);
		return new HomePage(driver);
	}
	
	public HomePage loginToAppLogout(WebDriver driver)throws InterruptedException {
		enterPassword(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "password"));
		clickLogin();
		return new HomePage(driver);

	}
	public void enterUsername(String userId) {
		 username.sendKeys(userId);
	}
			
    public void enterPassword(String pass) {
    	password.sendKeys(pass);
    }    
    public void clearPassword() {
    	password.clear();
    }
    public void clickLogin() {
    	
    	loginButton.click();
    }
    
    public boolean isRememberedUsernameDisplayed() {
        try {
            return rememberedUsernameLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    
    public void enterVerificationCode(String otp) {
    	if(verificationCodeInput.isDisplayed()) {
       		verificationCodeInput.sendKeys(otp);
    }else {
    	System.out.println("Verification code not visible");
    }
    }
    
    public HomePage clickOnVerifyButton(WebDriver driver) {
    	verifyButton.click();
    	return new HomePage(driver);
     }
    public String getErrorMessage(WebDriver driver)  {
    	if(WaitUtils.waitForElementVisibility(errorMessage, driver)) {
    	return errorMessage.getText();
        } else {
        	return null;
        }
    } 	
    public void clickRememberMe() {
    if (!rememberMeCheckbox.isSelected()) {	
    	rememberMeCheckbox.click();
    }
   }    	
    public void ClickForgotPassword() {
    	forgotPasswordLink.click();
    }
    public String getRememberedUsername() {
	    try {
	        return rememberedUsernameLabel.getText().trim();
	    } catch (Exception e) {
	        return null;
	    }
    }
	    public void waitForLoginPageToLoad() {
	        waitForVisibility(username);
	    }
	    public void waitForLoginPage() {
	        waitForVisibility(By.id("username")); 
	        
	    }
	    public HomePage loginToAppAfterLogout(WebDriver driver) {
            boolean usernameVisible;

	        try {
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
	            usernameVisible = true;
	        } catch (Exception e) {
	            usernameVisible = false;
	        }

	        // If username  is visible , type username
	        if (usernameVisible) {
	        	username.clear();
	        	username.sendKeys(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.username"));
	        }
	        // enter password (field is empty after logout)
	        password.clear();
	        password.sendKeys(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.password"));
	        loginButton.click();
	        return new HomePage(driver);
	    }
	    public void waitForLoginPageToVisible() {
	        	wait.until(ExpectedConditions.or(
	            ExpectedConditions.visibilityOfElementLocated(By.id("idcard")),     
	            ExpectedConditions.visibilityOfElementLocated(By.id("username"))    
	        ));
	    }

   }
