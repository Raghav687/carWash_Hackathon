package ui;

import org.testng.annotations.Test;

import Drivers.GridDrivers;
import Drivers.StaticDrivers;
import Utils.PropertiesFiles;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
//import org.openqa.selenium.NoAlertPresentException;	
import org.openqa.selenium.Alert;


public class Valid_Details {
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
	@Test(priority=1) 
	public void select_ratings() 
	{
		
		driver.findElement(By.linkText("Ratings")).click();
	}
	
	@Test(priority=2)
	public void best_deal() 
	{
		driver.findElement(By.xpath("//*[@id=\"bcard0\"]/div[1]/section/div[2]/ul/li/a")).click();
		System.out.println("Test name entered is Parth Sethi");
		driver.findElement(By.id("bd_name")).sendKeys("Parth Sethi");
		System.out.println("Test mobile number entered is 8800469299");
		driver.findElement(By.id("bd_mobile")).sendKeys("8800469200");
		
		driver.findElement(By.xpath("//*[@id=\"best_deal_div\"]/section/section/section/form/aside/p[4]/button")).click();
		driver.navigate().refresh();  
		
	}
	@AfterTest 
	public void Closebrowser() { 
	driver.close(); 
	}
}


