package JustDialPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FreeListingPage {
	public String Message(WebDriver driver) {
	    driver.findElement(By.id("h_flist")).click();
		driver.findElement(By.id("fcom")).sendKeys("raghav car wash");
		driver.findElement(By.xpath("//*[@id=\"fmb0\"]")).sendKeys("12345");
		driver.findElement(By.xpath("//*[@id=\"add_div0\"]/div[3]/button")).click();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.pollingEvery(Duration.ofSeconds(5));
		String message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"intial_div\"]/span[2]"))).getText();
        return message;
  }
}
