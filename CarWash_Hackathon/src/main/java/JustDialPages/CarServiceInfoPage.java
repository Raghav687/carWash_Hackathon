package JustDialPages;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;


public class CarServiceInfoPage {
	public Object[][] carServiceInfoPage(WebDriver driver,ExtentTest test,ExtentReports extent) throws IOException {
		
		int flag = 0;
		String b = "";
		int rowCount = 1;
		
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("CarService CenterData");
		
		Object[][] obj = new Object[20][3];
		obj[0][0] = "Name";
		obj[0][1] = "Address";
		obj[0][2] = "PhoneNumber";
		
		
		Object[][] output = new Object[10][3];
		int outputrow = 0; 
		
		try {
			List<WebElement> elements = driver.findElements(By.className("store-details"));
			String[] phone = null;
			String phoneString = null;
			String[] stringArray = new String[0];
			int j = 0;
			float rat[]=new float[elements.size()]; 
			for(int i=0;i<elements.size();i++) {
				String temp =elements.get(i).findElement(By.className("green-box")).getText();
				rat[i]= Float.parseFloat(temp);
				String Vote = elements.get(i).findElement(By.xpath("//*[@id=\"bcard"+i+"\"]/div[1]/section/div[1]/p[1]/a/span[3]")).getText();
				String numberOnly= Vote.replaceAll("[^0-9]", "");
				int VoteInteger = Integer.parseInt(numberOnly);
				List<WebElement> PhoneNumberString =  driver.findElements(By.className("mobilesv"));
				if(flag == 0) {
					for(int k = 0;k<PhoneNumberString.size();k++) {
						phoneString = PhoneNumberString.get(k).getAttribute("class").split("-")[1];
						MobileClass r = new MobileClass();
						String temp2 = r.mobileClass(phoneString);
						b += temp2;
					}
					flag = 1;
					while(j<b.length()) {
						if(b.charAt(j) == '0') {
							int count = 0;  
							String var = "";
							while(j<b.length() && count <= 10) {
								var += b.charAt(j); 
								j++;count++;
							}
							stringArray = Arrays.copyOf(stringArray,stringArray.length+1);
							stringArray[stringArray.length - 1] = var;
						}
						else if(b.charAt(j) == '+') {
							int count = 0;
							String var = "";
							while(j<b.length() && count <= 15) {
								var += b.charAt(j); 
								j++;count++;
							}
							stringArray = Arrays.copyOf(stringArray,stringArray.length+1);
							stringArray[stringArray.length - 1] = var;
						}
					}
				}
				if(rat[i]>=4.0 && VoteInteger > 20) {
					output[outputrow][0] = elements.get(i).findElement(By.className("lng_cont_name")).getText();
					obj[rowCount][0] = elements.get(i).findElement(By.className("lng_cont_name")).getText();
					
					output[outputrow][1] = elements.get(i).findElement(By.className("cont_sw_addr")).getText();
					obj[rowCount][1] = elements.get(i).findElement(By.className("cont_sw_addr")).getText();
					
					output[outputrow][2] = stringArray[i];
					obj[rowCount][2] = stringArray[i];
					rowCount++;
				}
				outputrow++;
			}
			
			test = extent.createTest("Car Service Info Searching");
	  		
	  		test.log(Status.INFO, "This step shows usage of log,info");
			test.info("This test shows searching car wash service and printing on Console");
			test.pass("Passed",MediaEntityBuilder.createScreenCaptureFromPath("screenshot3.png").build());
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.fail("Failed",MediaEntityBuilder.createScreenCaptureFromPath("screenshot3.png").build());
		}
		int rows = obj.length;
		int cols = obj[0].length;
		
		
		for(int k=0;k<rows;k++) {
			XSSFRow row = sheet.createRow(k);
			for(int p=0;p<cols;p++) {
				XSSFCell cell = row.createCell(p);
				Object value = obj[k][p];
				if(value instanceof String) {
					cell.setCellValue((String)value);
				}
				if(value instanceof Integer) {
					cell.setCellValue((Integer)value);
				}
			}
		}
		
		
		String filePath = ".\\ExcelReport\\JustDialService.xlsx";
		FileOutputStream outstream = new FileOutputStream(filePath);
		workbook.write(outstream);
		outstream.close();
		
		return output;
	}
}
