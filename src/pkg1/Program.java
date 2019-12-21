package pkg1;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Program {
  
  
	  static WebDriver driver;
		static String baseURL="http://www.moneycontrol.com";
		String parentWin;
		
		@BeforeTest
		public void startBrowser(){
		System.setProperty("webdriver.chrome.driver","D:\\SeleniumJars\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		
		@Test(priority=0)
		public void windowSwitching(){
			parentWin = driver.getWindowHandle();
			driver.findElement(By.xpath("(//a[contains(@title,'CNBC-TV18')])[1]")).click();
			Set<String> allWin= driver.getWindowHandles();
			for(String win: allWin){
				if(!win.equals(parentWin)){
					driver.switchTo().window(win);
					
				}
			}
			
			//String title=driver.findElement(By.xpath("//title")).getText();
			assert driver.getTitle().equalsIgnoreCase("Top Business Shows, Business News, Business Schedule, Videos, Anchors -CNBC TV18"):"Should be on child page";
		
			
		}
		
		@Test(priority=1)
		public void backToParent(){
			driver.switchTo().window(parentWin);
			
			assert driver.getTitle().equalsIgnoreCase("Stock/ShareMarket Investing - Live BSE/NSE, India Stock Market Recommendations and Tips, Live Stock Markets, Sensex/Nifty, Commodity Market, Investment Portfolio, Financial News, Mutual Funds"):"Should be on parent window";
		}
		
		@AfterTest
		public void closeBrowser(){
			driver.quit();
		}
  
}
