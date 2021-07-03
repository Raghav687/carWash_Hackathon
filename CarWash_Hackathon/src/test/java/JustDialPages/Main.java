package JustDialPages;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Drivers.GridDrivers;
import Drivers.StaticDrivers;
import Utils.PropertiesFiles;
import Utils.ScreenShot;

public class Main {
 
  WebDriver driver;
  @Test(priority = 0)
	public void startAndRemoveAd() throws InterruptedException, IOException {
	    
	    PropertiesFiles file = new PropertiesFiles();
	    String getBrowser = file.properties("getBrowser");
	    String getWebsiteURL = file.properties("getWebsiteURL");
		String getInputLocation = file.properties("getInputLocation");
		String getInputValue = file.properties("getInputValue");
		String getDriverSetup = file.properties("getDriverSetup");
		
		
		if(getDriverSetup.equalsIgnoreCase("NormalDriverSetup")) {
			StaticDrivers getWebDriver = new StaticDrivers();
			driver = getWebDriver.getDriver(getBrowser);
		}
		else if(getDriverSetup.equalsIgnoreCase("GridDriverSetup")) {
			GridDrivers getGridDriver = new GridDrivers();
			driver = getGridDriver.getGridDriver(getBrowser);
		}
		
	    driver.get(getWebsiteURL);
		driver.manage().window().maximize();
		Actions actions = new Actions(driver);
		
		driver.findElement(By.id("city")).sendKeys(getInputLocation);
		driver.findElement(By.id("srchbx")).sendKeys(getInputValue, Keys.ENTER);
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.pollingEvery(Duration.ofSeconds(5));
		WebElement cross = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"best_deal_div\"]/section/span")));
		actions.moveToElement(cross).click().perform();
		
		ScreenShot screenShotPage = new ScreenShot();
		screenShotPage.screenshot(driver);
	}
  	@Test(priority = 1)
  	public void CarServiceInfoSearching() throws IOException {
  		CarServiceInfoPage helper = new CarServiceInfoPage();
  		Object[][] output = new Object[10][3];
  		
  		output = helper.carServiceInfoPage(driver);
  		
  		int row = output.length;
  		
  		for(int i=0;i<row;i++) {
  					if(output[i][0] != null) {
  			    	System.out.print("Name: ");
  	  				System.out.print(output[i][0]);
  	  				System.out.println();
  	  				System.out.print("Address: ");
  	  				System.out.print(output[i][1]);
  	  				System.out.println();
  	  				System.out.print("PhoneNumber: ");
  	  				System.out.print(output[i][2]);
  	  				
  	  				System.out.println();
  	  				System.out.println();
  				}
  		}
  		
  	}
  	@Test(priority = 2)
	public void Listing() throws IOException{
		FreeListingPage freeListingJava = new FreeListingPage();
		String ErrMessage = freeListingJava.Message(driver);
		System.out.println("Error Message After entering wrong value in Input Field: " + ErrMessage);
		System.out.println();
		
		ScreenShot screenShotPage = new ScreenShot();
		screenShotPage.screenshot(driver);
		
		driver.navigate().back();
	}
  	@SuppressWarnings("static-access")
	@Test(priority = 3)
	public void Fitness() throws IOException {
		
		driver.navigate().back();
		
		FitnessPage fitnessJava = new FitnessPage();
		Object[] output = new Object[20];
		output  = fitnessJava.fitness(driver);
		
		for(int i=0;i<output.length;i++) {
			if(output[i] != null) {
				System.out.println(output[i]);
			}
		}
		
		ScreenShot screenShotPage = new ScreenShot();
		screenShotPage.screenshot(driver);
	}
  	@Test(priority = 4)
	public void closeBrowser() {
		driver.close();	
	}
}
