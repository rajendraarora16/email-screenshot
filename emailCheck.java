import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class emailCheck {
	static WebDriver driver;
	
	public static void getscreenshot(String email) throws Exception 
    {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(email+".png"));
    }
	
	public static void main(String[] args) throws Exception{
		
		String[] emailLogin = new String[]{
				"your email address"
		};
		
		String[] emailPass = new String[]{
			"your pass"
		};
		
		ChromeOptions chromeOptions = new ChromeOptions();
		File chromeDriver = new File("C:\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
		chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("--disable-gpu");
		chromeOptions.addArguments("--window-size=1024, 768");
		
		driver = new ChromeDriver(chromeOptions);
		driver.get("https://www.gmail.com");
		Thread.sleep(5000);
		for(int i=0; i<emailLogin.length; i++){
			
			if(i != 0){
				//getscreenshot("F://emailCheck/fffff.png");
				if(i == 1){
					driver.findElement(By.xpath("//form/div/div/div/div[2]/div/content/span/span")).click();
					Thread.sleep(5000);
				}
				driver.findElement(By.xpath("//div[contains(@id, 'identifierLink')]")).click();
				Thread.sleep(5000);
			}
			
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			Thread.sleep(5000);
			driver.findElement(By.id("identifierId")).sendKeys(emailLogin[i]);
			System.out.println("Email filled");
			//getscreenshot("F://emailCheck/ffff1.png");
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			Thread.sleep(5000);
			driver.findElement(By.id("identifierNext")).click();
			System.out.println("Next clicked");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			Thread.sleep(5000);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
			driver.findElement(By.name("password")).sendKeys(emailPass[i]);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			System.out.println("Password filled");
			Thread.sleep(5000);
			driver.findElement(By.id("passwordNext")).click();
			System.out.println("Next clicked");
			Thread.sleep(5000);
			System.out.println("Login was successful");
			
			getscreenshot("F://emailCheck/"+emailLogin[i]+".png");
			driver.get("https://mail.google.com/mail/logout?hl=en");
			System.out.println("Email --> "+emailLogin[i]+" done!");	
		}
		driver.quit();
		//ssendEmail();
		
	}
}
