package com.tekarch.eu.automation;




import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;

public class FirstAutomation {
				
	public static void main(String[] args) throws InterruptedException {
        // Set Chrome options
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--incognito");  // Open Chrome in incognito mode

        // Launch Chrome with options
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        Thread.sleep(1500);

        driver.get("https://selenium-prd.firebaseapp.com/home.html");
        driver.findElement(By.id("email_field")).sendKeys("admin123@gmail.com");
        driver.findElement(By.id("password_field")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@onclick=\"login()\"]")).click();
        //click Home tab
       
        //driver .findElement(By.xpath("//a[text()='Home']")).click();
        //Wait until the Female radio button is visible
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement homeTab=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Home']")));
        homeTab.click();
        WebElement femaleRadio=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='female']")));
        		//Click the Female radio button
        femaleRadio.click();
        if(femaleRadio.isSelected()) {
        	System.out.println("Female radio button is selected");
        } else {
        	System.out.println("Selection failed");
        }
        		
       // driver.findElement(By.linkText("Home")).click();  //Another options for Home because it is a link tab
       // driver.findElement(By.tagName("a")).click(); //if the tagname is unique
       // driver.findElement(By.className("main-div")).click(); //we can use the class name
       // driver.quit();
	}
	
    }
	
	


