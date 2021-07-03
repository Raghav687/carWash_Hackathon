package JustDialPages;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FitnessPage {
	public Object[] fitness(WebDriver driver) throws IOException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("ContextualHotkey_27")).click();
		driver.findElement(By.xpath("//*[@id=\'mnintrnlbnr\']/ul/li[3]/a/span[2]")).click();
		List<WebElement> options=driver.findElements(By.xpath("//*[@id='mnintrnlbnr']/ul/li/a/span[2]"));
		
		Object[] output = new Object[20];
		Object[][] obj = new Object[20][1];
		
		obj[0][0] = "Types of Fitness Gym";
		
		int count = 0;
		for (int i=0;i<options.size();i++)
		{
			output[count] = options.get(i).getAttribute("title");
			obj[count][0] = options.get(i).getAttribute("title");
			count++;
		}
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Fitness Data");
		
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
		
		String filePath = ".\\ExcelReport\\FitnessService.xlsx";
		FileOutputStream outstream = new FileOutputStream(filePath);
		workbook.write(outstream);
		outstream.close();
				
		return output;
	}
}
