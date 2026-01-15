package utils;
import org.openqa.selenium.WebDriver;

import pages.AccountsHomePage;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.MergeAccountsStep1Page;

public class TestDataHelper {

    private WebDriver driver;

    public TestDataHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureThreeAccountsExist(String baseName) {

        MergeAccountsStep1Page step1 = new MergeAccountsStep1Page(driver);
        CreateAccountPage createPage = new CreateAccountPage(driver);
        HomePage homePage =new HomePage(driver);
        step1.searchAccount(baseName);
        int available = step1.getAvailableAccountsCount();

        if (available < 3) {
            int missing = 3 - available;

            for (int i = 0; i < missing; i++) {
                String name = baseName + " " + System.currentTimeMillis();
                createPage.createTestAccount(name);
            }
            //  Navigate back to Merge Accounts Step 1
            homePage.clickAccounts();
            AccountsHomePage accountsHome = new AccountsHomePage(driver);
            accountsHome.clickMergeAccounts();

            // Now search again on the correct page
            step1 = new MergeAccountsStep1Page(driver);

            step1.searchAccount(baseName);
        }
    }
}


