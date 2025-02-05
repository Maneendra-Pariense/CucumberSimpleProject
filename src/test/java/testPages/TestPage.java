package testPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.BasePage;

public class TestPage  extends BasePage{
	private WebDriver driver;
	
	@FindBy(xpath = "//*[@id='sitsportalpagetitle']")
	public WebElement homePageTitle;
	
	@FindBy(name = "q")
	public WebElement search;

	@FindBy(name = "username")
	public WebElement username;

	@FindBy(name = "password")
	public WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement loginButton;
	
	
	public TestPage() {
		driver = getDriver();
		PageFactory.initElements(driver, this);
		
	}
	public void openBrowser() {
		// TODO Auto-generated method stub
		
	}
	public void GetTitle() {
		driver.getTitle();
	}
	
	public void EnterInSearch(String text) {
		search.sendKeys("Maneendra");
	}

	public void DoLogin(String un, String pw){
		username.sendKeys(un);
		password.sendKeys(pw);
		loginButton.click();
	}

}
