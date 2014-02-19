package tv.nu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class MovieIcon {
  @Test
  public void validateMovieIcon() {
	  WebDriver driver = new FirefoxDriver();
	  driver.get("http://www.tv.nu/_css/_compiled_wide.css?v=6020");
	  
	  // The images for movies are supplied through css file, it is fair enough if we ensure respective gif is present here
	  // validate mg image - completed programs
	  String mgcheckstring = ".prog_tabla .mg{background:url(http://static5.tv.nu/_graphics/mg.gif)";
	  boolean mgcheck = driver.getPageSource().contains(mgcheckstring);
	  System.out.println("mycheck string -- "+mgcheckstring);
	  System.out.println("mycheck true or false-- "+mgcheck);
	  
	  // validate mo image - live
	  String mocheckstring = ".prog_tabla .mo{background:url(http://static5.tv.nu/_graphics/mo.gif)";
	  boolean mocheck = driver.getPageSource().contains(mocheckstring);
	  System.out.println("mycheck string -- "+mocheckstring);
	  System.out.println("mycheck true or false-- "+mocheck);
	  

	  // validate m15 image - starting in 15 mins
	  String m15checkstring = ".prog_tabla .m15{background:url(http://static5.tv.nu/_graphics/m15.gif)";
	  boolean m15check = driver.getPageSource().contains(m15checkstring);
	  System.out.println("mycheck string -- "+m15checkstring);
	  System.out.println("mycheck true or false-- "+m15check);
	  
			  
	  // validate my image - scheduled programs
	  String mycheckstring = ".prog_tabla .my{background:url(http://static5.tv.nu/_graphics/my.gif)";
	  boolean mycheck = driver.getPageSource().contains(mycheckstring);
	  System.out.println("mycheck string -- "+mycheckstring);
	  System.out.println("mycheck true or false-- "+mycheck);

	  //mycheck = false;
	  if(!(mgcheck && mocheck && m15check && mycheck)){
		  
		    System.out.println("Warning ----- Film Strip gif is missing ");
	  }
	  
	  driver.quit();
  }
}
