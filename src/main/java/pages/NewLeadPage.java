package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewLeadPage extends BasePage {
	
	public NewLeadPage(WebDriver driver) {
        super(driver);
    }

	@FindBy(id = "name_lastlea2")
    private WebElement lastNameField;

    @FindBy(id = "lea3")
    WebElement companyField;

    @FindBy(name = "save")
    WebElement saveButton;

    @FindBy(xpath = "//h2[@class='pageDescription']")
    WebElement leadNameHeader;

    public void enterLastName(String lastName) {
        waitForVisibility(lastNameField).sendKeys(lastName);
    }

    public void enterCompany(String company) {
        waitForVisibility(companyField).sendKeys(company);
    }

    public LeadDetailsPage clickSave() {
        waitForVisibility(saveButton).click();
        return new LeadDetailsPage(driver);
    }

}
