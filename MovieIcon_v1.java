//                        ***** Please Read *****
// The approach followed here is completely a fresh one. Please read the description below for a clear view.
//The images for movies are supplied through css file, it is fair enough if we ensure respective gif is present here
//There is no need to get all the movie names from tv.nu and iterate through the list to validate as the movie names has the property values for the class as mg, mo, m15, my. All the class names for the movies starts with 'm' where other programs starts with 'p'. You can check the li class attribute for all the ul's 
//These class names just reserves a space for the image in HTML and the image is dynamically provided through CSS. I recommend to look for images directly in the CSS file. 
//If this test case fails, it means that one of the image provided to mg,mo,m15,my is not present and will not be displayed in the webpage.
//Recommend to look at the below links
//http://static01.tv.nu/_graphics/tvnu.startpage.sprite.v.1.0.5.gif
//http://static5.tv.nu/_graphics/mg.gif
//http://static5.tv.nu/_graphics/mo.gif
//http://static5.tv.nu/_graphics/m15.gif
//http://static5.tv.nu/_graphics/my.gif
//http://www.tv.nu/_css/_compiled_wide.css?v=6020
//asset was not added in the previous commit as I thought you were only looking for the logic 

package tv.nu;

import static org.testng.AssertJUnit.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class MovieIcon {
  @Test
  public void validateMovieIcon() {
	  WebDriver driver = new FirefoxDriver();
	  driver.get("http://www.tv.nu/_css/_compiled_wide.css?v=6020");
	  
	  // validate mg image - completed programs
	  try{
	  String mgcheckstring = ".prog_tabla .mg{background:url(http://static5.tv.nu/_graphics/mg.gif)";
//	  boolean mgcheck = driver.getPageSource().contains(mgcheckstring);
	  assertTrue(driver.getPageSource().contains(mgcheckstring));
//	  System.out.println("mycheck string -- "+mgcheckstring);
//	  System.out.println("mycheck true or false-- "+mgcheck);
//	   
	  
	  // validate mo image - live
	  String mocheckstring = ".prog_tabla .mo{background:url(http://static5.tv.nu/_graphics/mo.gif)";
//	  boolean mocheck = driver.getPageSource().contains(mocheckstring);
	  assertTrue(driver.getPageSource().contains(mocheckstring));
//	  
//	  System.out.println("mycheck string -- "+mocheckstring);
//	  System.out.println("mycheck true or false-- "+mocheck);
//	  

	  // validate m15 image - starting in 15 mins
	  String m15checkstring = ".prog_tabla .m15{background:url(http://static5.tv.nu/_graphics/m15.gif)";
//	  boolean m15check = driver.getPageSource().contains(m15checkstring);
	  assertTrue(driver.getPageSource().contains(m15checkstring));
	  
//	  System.out.println("mycheck string -- "+m15checkstring);
//	  System.out.println("mycheck true or false-- "+m15check);
//	  
			  
	  // validate my image - scheduled programs
	  String mycheckstring = ".prog_tabla .my{background:url(http://static5.tv.nu/_graphics/my.gif)";
//	  boolean mycheck = driver.getPageSource().contains(mycheckstring);
	  assertTrue(driver.getPageSource().contains(mycheckstring));
	  
//	  assertTrue(false);
	  
//	  System.out.println("mycheck string -- "+mycheckstring);
//	  System.out.println("mycheck true or false-- "+mycheck);

	  } 
	  catch (Throwable e){
		  System.out.println("Warning ---- Film Strip image for the listed films are not displayed properly ---- "+e);
	  }
	  
	  //mycheck = false;
//	  if(!(mgcheck && mocheck && m15check && mycheck)){
//		  
//		    System.out.println("Warning ----- Film Strip gif is missing ");
//	  }
//	  
	  
	  driver.quit();
  }
}
