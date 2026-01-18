package tests;

import java.io.File;
import java.time.Duration;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.salesforce.oauth.SalesforceAuth;
import constants.FileConstants;
public class BaseTest {
	public WebDriver driver;
/*	@BeforeTest
	public void generateOAuthURLToLogin() {
		BaseTest.loginURLOauth= BaseTest.getSfdcDirectLoginUrl();
	}
	public static String loginURLOauth="";
	
	public static ThreadLocal<WebDriver>threadLocal = new ThreadLocal<WebDriver>();
	
	public static void setDriver(String browserName,boolean headless) {
		WebDriver driver = BaseTest.getDriver(browserName, headless);
		if(driver == null) {
			throw new RuntimeException("WebDriver initialization failed");
			
		}
		threadLocal.set(driver);
	}
public static WebDriver getBrowser() {
	return threadLocal.get();
}*/
	
public static WebDriver getDriver(String browserName,boolean isHeadless) {
		WebDriver driver = null;
		switch(browserName.toLowerCase()) {
		case "chrome":
			//We need to set ChromeDriver path if not using WebDriverManager
			if(isHeadless) {
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--headless");
				driver = new ChromeDriver(co);
			} else {
			driver = new ChromeDriver();
			break;
			}
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "safari" :
			driver = new SafariDriver();
			break;
		default:
			System.out.println("Browser not supported! Launching Chrome by default.");
			break;
			}
		    if(driver!=null) {
		    	driver.manage().window().maximize();
		    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    }
		    return driver;

		}
	
	public static String getSfdcDirectLoginUrl() {
		 final String CONSUMER_KEY ="";
		 final String CONSUMER_SECRET	=""	;
		 SalesforceAuth auth= new SalesforceAuth(CONSUMER_KEY,CONSUMER_SECRET,false);
		 String url= auth.start();
		 System.out.println(url);
		 return url;
	}
	
	public static void  getScreenshot(WebDriver driver) {
		String screenshotPath=System.getProperty("user.dir")+"/src/main/resources/screenshots/"+System.currentTimeMillis()+".png";
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File dst=new File(screenshotPath);
		source.renameTo(dst);
	}
	
    public void tearDown() {
    	if(driver != null) {
    		driver.quit();
    	}
    }
}
