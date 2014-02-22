// This test case validates whether the future broadcasts are scheduled or not for the channel TV8.
// Due to time constraint I did only for TV8 by getting xpath. It is possible to validate for all the channels in a single go by getting entire li webelements in List and iterating through it...
// This warnings could be sent as email , sms to the concerned user or group of users...

package tv.nu;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertTrue;

public class TV8 {
  @Test
public void validateFutureBroadcast() {
  

	  WebDriver driver = new FirefoxDriver();
	  driver.get("http://tv.nu");
   try{  
	  List<WebElement> liElements = driver.findElements(By.xpath(".//*[@id='page_left']/div[4]/div[2]/div[5]/div[2]/div[2]/ul/li"));
	  
  	  int futurebroadcast = 0;
   	  for(WebElement li : liElements){
  		  String temp1 = li.getAttribute("class");
//  		  String temp = li.getText();
//  		  System.out.println("Text in li ---  "+temp);
//  		  System.out.println("class in li ---  "+temp1);
  		  if(temp1.equals("py")){
  			  futurebroadcast = futurebroadcast+1;
  		  }
  	  }
  	  assertTrue(futurebroadcast != 0);
  	  
	}catch (Throwable e){
		System.out.println("Warning ----- There are no future broadcasts scheduled for TV8 -- "+e);
	  }
	finally{
		driver.quit();
	}
 } 
}
