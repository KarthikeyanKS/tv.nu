// --------------------Broken link validation --------------------------
// Could be tested in any webpage....used Google.com for immediate response....
package tv.nu;

import org.testng.annotations.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import jxl.*; 

public class Dp_v01 {

	WebDriver driver;

	@BeforeClass
	public void setUp() {
		driver = new FirefoxDriver();
		  
		  driver.get("http://www.google.com");
		  //driver.manage().window().maximize();
		  
		  List<WebElement> links = driver.findElements(By.tagName("a"));
		  System.out.println("Total links in the page ---- "+links.size());
		  
		
		  String linkURL[]= new String[links.size()];
		  int i = 0;
		  
//		  for(WebElement templinkelement: links){
//			  	linkURL[i] = templinkelement.getAttribute("href");
//			  	if(linkURL[i].equals("javascript:void(0)")||linkURL[i].equals("null")||linkURL[i].equals("javascript:;")){continue;}
//			  	Reporter.log(i+" link is ---------"+ linkURL[i],true);
//			  	i++;}
//		  i = 0;
		  try{
		  for(WebElement templinkelement: links){
			  	linkURL[i] = templinkelement.getAttribute("href");
			  	if(linkURL[i].equals("javascript:void(0)")||linkURL[i]==null||linkURL[i].equals("javascript:;")){continue;}
			  	//Reporter.log(i+" link is ---------"+ linkURL[i],true);
			  	
			  	FileInputStream file = new FileInputStream(new File("C:\\QA\\ws\\nu\\src\\test\\resources\\test.xls"));
			    HSSFWorkbook workbook = new HSSFWorkbook(file);
			    HSSFSheet sheet = workbook.getSheetAt(0);
			    
			    Row row0 = sheet.createRow(0);
			    row0.createCell(0).setCellValue("----LINKS----");
			    Row rowi = sheet.createRow(i+1);
			    rowi.createCell(0).setCellValue(linkURL[i]);
			    
			    
			     URL url = new URL(linkURL[i]);
		         HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
		         httpURLConnect.setConnectTimeout(3000); // 6 seconds delay
		         httpURLConnect.connect();
		         
		         	//save response code 
		         row0.createCell(1).setCellValue("----Response code----");   
		         rowi.createCell(1).setCellValue(httpURLConnect.getResponseCode());
				    
		         
		         if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND){
		             Reporter.log("WARNING --- Bad response received for the link -- "+linkURL[i]+" <<<-- BROKEN LINK .. NEED YOUR ACTION  -->>> Got the Response code:  "+httpURLConnect.getResponseCode()+"  -  "+httpURLConnect.getResponseMessage()
		                     + " - "+ HttpURLConnection.HTTP_NOT_FOUND,true);
		             
//		             org.testng.Assert.fail(linkURL[i]+" ---->>> Response code:  "+httpURLConnect.getResponseCode()+"  -  "+httpURLConnect.getResponseMessage()
//		                     + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
		             
		             
		             
		          } else {		
		        	
//		        	 org.testng.Assert.assertTrue(true,linkURL[i]+"   <----  This URL is good: Got the response code:  "+httpURLConnect.getResponseCode());
		        	 Reporter.log(httpURLConnect.getResponseCode()+"    Got a valid response code ***  "+linkURL[i]+"   <----  This URL is good",true);  
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
		    catch (MalformedURLException e) {
		     e.printStackTrace();
		 }
		    catch (HeadlessException e){
		     e.printStackTrace();
		 }	catch (IOException e){
			 e.printStackTrace();
		 }
		    
	}

	// Call class method tearDown, and close firefox driver instance
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	@DataProvider(name = "data-provider", parallel = false)
	public Object[][] data() throws Exception {
		Object[][] retObjArr=getTableArray("C:\\QA\\ws\\nu\\src\\test\\resources\\test.xls");
        return(retObjArr);
	}
	
	public String[][] getTableArray(String xlFilePath) throws Exception{
        String[][] tabArray=null;
        
            Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
            Sheet wrksheet = workbook.getSheet(0);
           
            String tabdata[][] = new String[wrksheet.getRows()][wrksheet.getColumns()];
			
			System.out.println("Total Rows: " + wrksheet.getRows());
			System.out.println("Total Columns: " + wrksheet.getColumns());
			for (int i = 1; i < wrksheet.getRows(); i++) {
				 
				for (int j = 0; j < wrksheet.getColumns(); j++) {
				tabdata[i][j] = wrksheet.getCell(j, i).getContents();
//				System.out.println("elemet ---- "+tabdata[i][j]);			
				}
			}
	        return(tabdata);
	}

	// Call the test method
	@Test(dataProvider="data-provider")
	public final void testClick(String link, String responsecode) throws InterruptedException {
		
//		System.out.println("Link ----- ; "+link);
//		System.out.println("Responsecode ----- ; "+responsecode);	
//---------------We can have our validation here with specific response code-----------------
		if(link != null){
		Assert.assertNotEquals(responsecode,"404");}
		
	}

}
