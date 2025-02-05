package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import gherkin.formatter.model.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.io.FileHandler;
import tech.grasshopper.reporter.ExtentExcelCucumberReporter;
import tech.grasshopper.reporter.ExtentPDFReporter;

import java.time.Duration;

public class BasePage {
	public static ThreadLocal<WebDriver> _driver = new ThreadLocal<>();
	public static Properties prop;

	public BasePage() {
		try {
			String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\Base\\config.properties";
			FileInputStream fis = new FileInputStream(filePath);
			prop = new Properties();
			prop.load(fis);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void setDriver()
	{
		_driver.set(new ChromeDriver());
		System.out.println("Browser setup by Thread : " + Thread.currentThread().getId() + " and Driver reference is : "
				+ getDriver());
	}


	public WebDriver getDriver() {
		return _driver.get();
	}

	public void Setup() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		setDriver();
		getDriver();
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
	}
	
	public void TearDown() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getDriver().quit();
		
	}

	public String takeScreenshotAtEndOfTest() throws IOException {
		return ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BASE64);
	}
}
