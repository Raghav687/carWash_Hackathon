package ui;

import org.testng.annotations.Test;

import Drivers.GridDrivers;
import Drivers.StaticDrivers;
import Utils.PropertiesFiles;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Invalid_Details {
	WebDriver driver;
	String getBrowser;
	String getWebsiteURL;
	String getInputValue;
	String getDriverSetup ;
	@BeforeTest
	public void openSite() 
	{
		PropertiesFiles file = new PropertiesFiles();
		getBrowser = file.properties("getBrowser");
		getWebsiteURL = file.properties("getWebsiteURL");
		getInputValue = file.properties("getInputValue");
		getDriverSetup = file.properties("getDriverSetup");
		
		if(getDriverSetup.equalsIgnoreCase("NormalDriverSetup")) {
			StaticDrivers getWebDriver = new StaticDrivers();
			driver = getWebDriver.getDriver(getBrowser);
		}
		else if(getDriverSetup.equalsIgnoreCase("GridDriverSetup")) {
			GridDrivers getGridDriver = new GridDrivers();
			driver = getGridDriver.getGridDriver(getBrowser);
		}

		//Create Chrome Instance
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("--disable-blink-features=AutomationControlled");

		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options); 

		 System.out.println("chrome browser is opened"); 

		driver.get("https://www.justdial.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@Test(priority=0) 
	public void search_service() 
	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.findElement(By.id("srchbx")).sendKeys("Car Wash Services");
		driver.findElement(By.id("srIconwpr")).click();
				
	}
	
	// 'Ratings' command not entered 
	
		@Test(priority=1)
		public void best_deal_tc003() 
		{
			System.out.print("TC003:");

			System.out.println("Ratings are not sorted");

			driver.findElement(By.xpath("//*[@id=\"bcard0\"]/div[1]/section/div[2]/ul/li/a")).click();
			System.out.println("Test name entered is Parth Sethi");
			driver.findElement(By.id("bd_name")).sendKeys("Parth Sethi");
			System.out.println("Test mobile number entered is 8800469299");
			driver.findElement(By.id("bd_mobile")).sendKeys("8800469299");
			driver.findElement(By.xpath("//*[@id=\"best_deal_div\"]/section/section/section/form/aside/p[4]/button")).click();
			driver.navigate().refresh();  


		}
		
	// Invalid Name 
				
		@Test(priority=2)
		public void best_deal_tc004() 
		{
			System.out.print("TC004:");

			System.out.println("Invalid Name ");
			driver.navigate().refresh();  

			driver.findElement(By.xpath("//*[@id=\"bcard0\"]/div[1]/section/div[2]/ul/li/a")).click();
			System.out.println("Test name entered is 89**##");
			driver.findElement(By.id("bd_name")).sendKeys("89**##");
			System.out.println("Test mobile number entered is 8800469299");
			driver.findElement(By.id("bd_mobile")).sendKeys("8800469299");
			driver.findElement(By.xpath("//*[@id=\"best_deal_div\"]/section/section/section/form/aside/p[4]/button")).click();
			Alert alert = driver.switchTo().alert();		
	        String alertMessage= driver.switchTo().alert().getText();		
	        System.out.println(alertMessage);
			driver.navigate().refresh();  

		}
	
    // Invalid Mobile Number
		
		@Test(priority=3) 
		public void best_deal_tc005() 
		{

			System.out.print("TC005:");

			System.out.println("Invalid Mobile Number");
			driver.navigate().refresh();  

			driver.findElement(By.xpath("//*[@id=\"bcard0\"]/div[1]/section/div[2]/ul/li/a")).click();
			System.out.println("Test name entered is Parth Sethi");
			driver.findElement(By.id("bd_name")).sendKeys("Parth Sethi");
			System.out.println("Test mobile number entered is ##00469299");
			driver.findElement(By.id("bd_mobile")).sendKeys("##00469299");
			driver.findElement(By.xpath("//*[@id=\"best_deal_div\"]/section/section/section/form/aside/p[4]/button")).click();
			Alert alert = driver.switchTo().alert();		
	        String alertMessage= driver.switchTo().alert().getText();		
	        System.out.println(alertMessage);
			driver.navigate().refresh();  

		}
		
	// Invalid E-mail
		
		@Test(priority=4)  
		public void best_deal_tc0006() 
		{
			System.out.print("TC006:");

			System.out.println("Invalid E-mail");
			driver.navigate().refresh();  

			driver.findElement(By.xpath("//*[@id=\"bcard0\"]/div[1]/section/div[2]/ul/li/a")).click();
			System.out.println("Test name entered is Parth Sethi");
			driver.findElement(By.id("bd_name")).sendKeys("Parth Sethi");
			System.out.println("Test mobile number entered is 8800469299");
			driver.findElement(By.id("bd_mobile")).sendKeys("8800469299");
			System.out.println("Test e-mail id entered is Invalid");
			driver.findElement(By.id("bd_email")).sendKeys("Parth Sethi");
			driver.findElement(By.xpath("//*[@id=\"best_deal_div\"]/section/section/section/form/aside/p[4]/button")).click();
			Alert alert = driver.switchTo().alert();		
	        String alertMessage= driver.switchTo().alert().getText();		
	        System.out.println(alertMessage);
			driver.navigate().refresh();  

		}
		
		
		
	@AfterTest 
	public void Closebrowser() { 
	driver.close(); 
	}

}
