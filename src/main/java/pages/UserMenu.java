package pages;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserMenu extends BasePage{
     
   @FindBy(id="userNavLabel")	
   public WebElement userMenu;	
   
   @FindBy(css ="[id=\"userNav-menuItems\"] > a")
   public List <WebElement> usermenuOptions;
   
   @FindBy(xpath="//a[contains(text(), 'My Profile')]")
   WebElement myProfile;

   @FindBy(xpath="//a[contains(text(), 'My Settings')]")
   WebElement mySettings;

   @FindBy(xpath="//a[contains(text(), 'Developer Console')]")
   WebElement developerConsole;
  
   @FindBy(xpath="//a[contains(text(), 'Logout')]")
   WebElement logout;
   
   public UserMenu(WebDriver driver) {
	   super(driver);
   }
   
      
   public ArrayList<String>getUserMenuOptions(){
	   if((myProfile.isDisplayed())) {
		   System.out.println("Do nothing");
	   } else {
		   userMenu.click();
	   }
	   ArrayList<String>menuOptions = new ArrayList<String>();
	   for(WebElement element :usermenuOptions) {
		   menuOptions.add(element.getText());
	   }
	   return menuOptions;
   }
   
   public MyProfilePage selectMyProfile(WebDriver driver) {
	   if(!(myProfile.isDisplayed())) {
		   userMenu.click();
		   myProfile.click();
	   } else {
		   myProfile.click();
	   }
	   return new MyProfilePage(driver);
   }
   
   public MySettingsPage selectMySettings() {
	   if(!(mySettings.isDisplayed())) {
		   userMenu.click();
		   mySettings.click();
	   } else {
		   mySettings.click();
	   }
	   return new MySettingsPage(driver);

	}
   
   public void  selectDeveloperConsole() {
	      click(userMenu);
	      // Wait for dropdown container to appear
	      try {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userNav-menuItems")));
	      } catch (TimeoutException e) {
	        // Retry click if dropdown didn't open
	        userMenu.click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userNav-menuItems")));
	    }
	    click(developerConsole);
	}

    public boolean isDeveloperConsolePageLoaded() {
	    // Switch to new window
	    String parent = driver.getWindowHandle();
	    switchToNewWindow();
	    // Get the title
	    String title = driver.getTitle();
	    System.out.println("Developer Console Title: " + title);
	    // Verify title
	    boolean isLoaded = title.contains("Developer Console");
	    // Close the popup
	    driver.close();
	    // Switch back
	    driver.switchTo().window(parent);
	    return isLoaded;

	}
	
    public void logout() {
    	//  Open user menu
    	click(userMenu);
    	// Wait for dropdown container to appear
	    try {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userNav-menuItems")));
	    } catch (TimeoutException e) {
	        // Retry click if dropdown didn't open
	        userMenu.click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userNav-menuItems")));
	    }
	    click(logout);
     }
   
     public boolean isLoginPageLoadedFromLogout() {
    	return driver.getCurrentUrl().contains("salesforce.com");
     }
}
