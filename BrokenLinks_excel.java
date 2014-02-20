package tv.nu;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Listeners;


public class BrokenLinks_excel{
  
	private WebDriver driver;
	
@Test
public void savelinksinExcel(){
	
	driver = new FirefoxDriver();
	  
	  driver.get("http://www.tv.nu");
	  //driver.manage().window().maximize();
	  
	  List<WebElement> links = driver.findElements(By.tagName("a"));
	  //System.out.println("Total links in the page ---- "+links.size());
	  
	
	String linkURL[]= new String[links.size()];
	  int i = 0;
	  
	  try{
	  for(WebElement templinkelement: links){
		  	linkURL[i] = templinkelement.getAttribute("href");
		  	FileInputStream file = new FileInputStream(new File("C:\\QA\\ws\\nu\\src\\test\\resources\\test.xls"));
		    HSSFWorkbook workbook = new HSSFWorkbook(file);
		    HSSFSheet sheet = workbook.getSheetAt(0);
		    
		    Row row0 = sheet.createRow(0);
		    row0.createCell(0).setCellValue("----LINKS----");
		    Row rowi = sheet.createRow(i+1);
		    rowi.createCell(0).setCellValue(linkURL[i]);
		    
		    
		     URL url = new URL(linkURL[i]);
	         HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
	         httpURLConnect.setConnectTimeout(3000); // 3 seconds delay
	         httpURLConnect.connect();
	         
	         	//save response code 
	         row0.createCell(1).setCellValue("----Response code----");   
	         rowi.createCell(1).setCellValue(httpURLConnect.getResponseCode());
			    
	         
	         if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND){
	             Reporter.log(linkURL[i]+" <<<-- BROKEN LINK .. NEED YOUR ACTION  -->>> Got the Response code:  "+httpURLConnect.getResponseCode()+"  -  "+httpURLConnect.getResponseMessage()
	                     + " - "+ HttpURLConnection.HTTP_NOT_FOUND,true);
	             
//	             org.testng.Assert.fail(linkURL[i]+" ---->>> Response code:  "+httpURLConnect.getResponseCode()+"  -  "+httpURLConnect.getResponseMessage()
//	                     + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
	             
	             
	             
	          } else {		
	        	
//	        	 org.testng.Assert.assertTrue(true,linkURL[i]+"   <----  This URL is good: Got the response code:  "+httpURLConnect.getResponseCode());
	        	// Reporter.log(linkURL[i]+"   <----  This URL is good: Got the response code:  "+httpURLConnect.getResponseCode(),true);  
	          }
	       

			    file.close();
			    FileOutputStream outFile =new FileOutputStream(new File("C:\\QA\\ws\\nu\\src\\test\\resources\\test.xls"));
			    workbook.write(outFile);
			    //Reporter.log(linkURL[i]+"------ link no : "+i, true);
			   
	         i++;
	        
	  }
	  
	  }catch (FileNotFoundException e) {
		    e.printStackTrace();
	  } 
	    catch (IOException e) {
	     e.printStackTrace();
	 }
	    catch (HeadlessException e){
	     e.printStackTrace();
	 }	
	    
	}
@AfterMethod
public void closeConn(){
	driver.quit();
}
	
}
