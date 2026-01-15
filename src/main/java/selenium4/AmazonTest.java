package selenium4;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


public class AmazonTest {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();

        //
        driver.get("https://www.amazon.com");

        // 2. Search for kindle
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("field-keywords")));
        searchBox.sendKeys("kindle");
        searchBox.submit();

        // 3. Click a product which has "Amazon's Choice" tag
        
        WebElement amazonsChoiceProduct = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"B0CNVCQZG1-amazons-choice-label\"]/span/div/span[1]")
        ));
        amazonsChoiceProduct.click();

        // Switch to new tab 
        for (String window : driver.getWindowHandles()) {
            driver.switchTo().window(window);
        }

        // 4. Add to cart
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("a-autoid-1-announce")
        ));
        addToCartBtn.click();
        // 5. Go to cart
        WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("nav-cart")
        ));
     // Scroll to top 
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

        // Scroll the cart into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartBtn);
        cartBtn.click();

        // 6. Verify item is available in cart
        WebElement cartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@class='a-truncate-cut']")
        ));

        if (cartItem.isDisplayed()) {
            System.out.println("Item successfully added to cart: " + cartItem.getText());
        } else {

	}
     driver.close();

	}

}
