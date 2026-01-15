package pages;
import java.util.Objects;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CreateAccountPage extends BasePage {
	
	public CreateAccountPage(WebDriver driver) {
		super(driver);
	}
	
	//Page objects for create accounts
	
	@FindBy(id="Account_Tab")
	WebElement accountTab;
	
	@FindBy(xpath="//input[@name='new']")
	WebElement newButton;
	
	@FindBy(xpath="//h2[text()='New Account']")
	WebElement newAccountPage;
	
	@FindBy(id="acc2")
	WebElement accountNameField;
	
	@FindBy(xpath="//td[@id='bottomButtonRow']//input[@name='save']")
	WebElement bottomSaveButton;
	
	@FindBy(xpath="//h2[@class='pageDescription']")
	WebElement newAccountName;
	
	@FindBy(xpath="//h2[@class='mainTitle' and text()='Account Detail']")
	WebElement accountDetailTitle;
	
	//Page objects for create new view
	
	@FindBy(xpath="//h2[contains(text(),'Create New View')]")
	WebElement createNewViewHeader;
	
	@FindBy(id="fname")
	WebElement viewNameField;
	
	@FindBy(id="devname")
	WebElement viewUniqueNameField;
	
	@FindBy(xpath="//div[@class='pbBottomButtons']//input[@name='save']")
	WebElement saveButton;
	
	//Edit view Page objects
	@FindBy(name="fcf")
	WebElement viewDropdown;
	
	@FindBy(xpath="//a[contains(text(),'Edit')]")
	WebElement editLink;
	
	@FindBy(xpath="//h2[@class='pageDescription']")
    WebElement editViewPageHeader;
	
	@FindBy(name = "fname")
	WebElement viewNameTextbox;

	
	@FindBy(id="fcol1")
	WebElement filterFieldDropdown;
	
	@FindBy(id="fop1")
	WebElement filterOperatorDropdown;
	
	@FindBy(id="fval1")
	WebElement valueField;
	
	@FindBy(id="colselector_select_0")
	WebElement availableFieldsList;
	
	@FindBy(id="colselector_select_0_right")
	WebElement addButton;
	
	@FindBy(id="colselector_select_0_left")
	WebElement removeButton;
	
	@FindBy(id="colselector_select_1")
	WebElement selectedFieldsList;
	
	//Merge Accounts Page objects
	@FindBy(xpath="//h3[text()='Tools']")
	WebElement toolsHeader;
	

	@FindBy(xpath="//a[normalize-space()='Merge Accounts']")
	WebElement mergeAccountsLink;
	
	@FindBy(xpath="//h3[text()='Recent Accounts']")
	WebElement recentAccountsHeader;
	
	@FindBy(xpath="//table//tr/td[1]/a") 
	List<WebElement> recentAccountLinks;
			
	public void createAccount(String accountName) {
		waitForVisibility(accountNameField);
		accountNameField.clear();
		accountNameField.sendKeys(accountName);
		waitForVisibility(bottomSaveButton);
		bottomSaveButton.click();
			
		}
		
	public boolean verifyAccountCreated(String expectedName) { 
		waitForVisibility(accountDetailTitle);
		waitForVisibility(newAccountName);
		return newAccountName.getText().trim().equalsIgnoreCase(expectedName);
	}
		
    public void createNewView(String viewName,String viewUniqueName) {
		waitForVisibility(createNewViewHeader);
		waitForVisibility(viewNameField);
		viewNameField.clear();
		viewNameField.sendKeys(viewName);
			
		waitForVisibility(viewUniqueNameField);
		viewUniqueNameField.click();
		viewUniqueNameField.clear();
		viewUniqueNameField.sendKeys(viewUniqueName);
		waitForVisibility(saveButton);
		saveButton.click();
			
		}
		
	public boolean verifyNewlyAddedViewIsVisible(String expectedViewName) { 
		waitForVisibility(viewDropdown);
		Select select =new Select(viewDropdown);
		return select.getOptions().stream().anyMatch((option->option.getText().trim().equalsIgnoreCase(expectedViewName)));
					
		
		}	
   //Edit view Actions
   public boolean isEditFormDisplayed() {
    	return editViewPageHeader.isDisplayed();
    }

    public void clickEditView() {
        waitForVisibility(editLink);
        click(editLink);
    }

    public void editView(String newViewName) {
        waitForVisibility(viewNameField);
        viewNameField.clear();
        viewNameField.sendKeys(newViewName);
    }

    public void setFilter(String field, String operator, String value) {
        selectDropdownByVisibleText(filterFieldDropdown, field);
        selectDropdownByVisibleText(filterOperatorDropdown, operator);
        valueField.clear();
        valueField.sendKeys(value);
    }

    public void addFieldToDisplay(String fieldName) {
    	Select available = new Select(availableFieldsList);
        List<WebElement> availableOptions = available.getOptions();

        boolean isAvailable = availableOptions.stream()
                .anyMatch(o -> o.getText().trim().equalsIgnoreCase(fieldName));

        if (isAvailable) {
            // Field is on the left → add it
            available.selectByVisibleText(fieldName);
            addButton.click();
            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.id("colselector_select_1"), fieldName));
            return;
        }

        // Otherwise, check if already selected
        List<WebElement> selectedFields = driver.findElements(By.xpath("//select[@id='colselector_select_1']/option"));
        boolean alreadySelected = selectedFields.stream()
                .anyMatch(o -> o.getText().trim().equalsIgnoreCase(fieldName));

        if (!alreadySelected) {
            throw new RuntimeException("Field '" + fieldName + "' not found in either list");
        }

        // If already selected, do nothing
        System.out.println(fieldName + " already selected. Skipping add.");

    	}


    public void saveView() {
        saveButton.click();
     // Wait for the table to reload
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'x-grid3-row')]")
        ));

    }
    //verify the new view name is selected
    public String getSelectedViewName() {
    	Select s = new Select(viewDropdown);
        return s.getFirstSelectedOption().getText().trim();
    }
    
    public void selectViewSafe(String originalName, String editedName) {
        Select s = new Select(viewDropdown);

    	List<String> options = s.getOptions()
                .stream()
                .map(o -> o.getText().trim())
                .toList();

        if (options.contains(originalName)) {
            s.selectByVisibleText(originalName);
            System.out.println("Selected original view: " + originalName);
        } else if (options.contains(editedName)) {
            s.selectByVisibleText(editedName);
            System.out.println("Selected edited view: " + editedName);
        } else {
            throw new RuntimeException("Neither " + originalName + " nor " + editedName + " found in dropdown");
        }

    }
    
    public boolean isLastActivityColumnPresent() {
        List<WebElement> headers = driver.findElements(By.xpath("//div[@class='x-grid3-hd-inner x-grid3-hd-ACCOUNT_LAST_ACTIVITY']"));
        return headers.stream()
        		.map(h -> h.getAttribute("title"))
                .filter(Objects::nonNull)
                .map(t -> t.toLowerCase().replace("\u00A0", " ").replaceAll("\\s+", " ").trim())
                .peek(t -> System.out.println("HEADER TITLE: [" + t + "]"))
                .anyMatch(text -> text.contains("last activity"));
    }

    
    //verify all Account Names contain "a"
    public boolean allAccountNamesContain(String letter) {
    	List<WebElement> accountNames = driver.findElements(
                By.xpath("//div[contains(@class,'x-grid3-row')]//td[2]")
        );

        accountNames.forEach(a -> System.out.println("ACCOUNT NAME: [" + a.getText() + "]"));

        return accountNames.stream()
                .map(e -> e.getText().trim().toLowerCase())
                .filter(text -> !text.isEmpty())
                .allMatch(text -> text.contains(letter.toLowerCase()));

    }

    public void waitForAccountsTableToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'x-grid3-row')]")
        ));
    }
    public void createTestAccount(String accountName) {
    	waitForVisibility(accountTab).click();
        waitForVisibility(newButton).click();
        accountNameField.clear();
        accountNameField.sendKeys(accountName);
        click(bottomSaveButton);
    }

 }


