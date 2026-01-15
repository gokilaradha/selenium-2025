package selenium4;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertSelenium {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://selenium-prd.firebaseapp.com/");
		driver.manage().window().maximize();
		WebElement usernameField=driver.findElement(By.id("email_field"));
		usernameField.sendKeys("admin123@gmail.com");
		WebElement passwordField=driver.findElement(By.id("password_field"));
		passwordField.sendKeys("admin123");
		
		//Login Button
		WebElement loginBtn=driver.findElement(By.xpath("//button[@onclick='login()']"));
		loginBtn.click();
		
	//	driver.close();
	//	WebElement alertbox = driver.findElement(By.id("alert"));
	//	alertbox.click();
		
	//	Alert alert = driver.switchTo().alert();
	//	String alertText = alert.getText();
	//	System.out.println(alertText);
		
	//	Thread.sleep(4000);
	//	alert.accept();
		

	}

}
