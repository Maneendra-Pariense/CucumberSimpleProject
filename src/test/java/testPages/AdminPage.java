package testPages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AdminPage extends BasePage {

    private WebDriver driver;
    // locators
    @FindBy(xpath = "//div[@class='oxd-topbar-header-title']//h6")
    WebElement pageTitle;
    @FindBy(xpath = "//label[@class='oxd-label' and text() = 'Username']/../..//input")
    public WebElement usernameInput;
    @FindBy(xpath = "//label[@class='oxd-label']/../..//i")
    public WebElement userRoleDropdown;
    @FindBy(xpath = "//label[@class='oxd-label' and text() = 'Employee Name']/../..//input")
    public WebElement employeeNameInput;
    @FindBy(xpath = "//button[text() = ' Search ']")
    WebElement searchButton;

    public AdminPage(){
        driver = getDriver();
        PageFactory.initElements(driver, this);
    }

    public String GetPageTitle() {
        return pageTitle.getText();
    }
    public void clickOnUserRoleDropdown(){
        userRoleDropdown.click();
    }

    public void selectUserRoleDropdownOption(String option){

        driver.findElement(By.xpath("//div[@role='option']/span[text()='"+ option +"']/..")).click();
    }

    public void NavigateTo(String pageTitle) {
        driver.findElement(By.xpath("//ul[@class='oxd-main-menu']//a/span[text() ='"+pageTitle+"']/..")).click();
    }

    public void EnterUserName(String un) {
        usernameInput.sendKeys(un);
    }

    public void SelectEmployeeName(String en) {
        employeeNameInput.sendKeys(en);
    }

    public void SelectStatus(String st) {
        driver.findElement(By.xpath("//label[@class='oxd-label' and text() = 'Status']/../..//i")).click();
        driver.findElement(By.xpath("//div[@role='option']/span[text()='"+ st +"']/..")).click();
    }

    public void ClickOnSearchButton() {
        searchButton.click();
    }

    public void SelectFromDropdown(String label, String option){
        driver.findElement(By.xpath("//label[@class='oxd-label' and text() = '"+label+"']/../..//i")).click();
        driver.findElement(By.xpath("//div[@role='option']/span[text()='"+ option +"']/..")).click();
    }

    public void GetRecords() {
        WebElement table = driver.findElement(By.className("oxd-table"));
        WebElement header = table.findElement(By.className("oxd-table-header"));
        WebElement body = table.findElement(By.className("oxd-table-body"));
        List<WebElement> columnHeaders = header.findElements(By.xpath(".//div[@role = 'columnheader']"));
        System.out.println("column headers count: " + columnHeaders.size());
//        for (WebElement columnheader :columnHeaders
//             ) {
//            System.out.println("column header " + header.getText());
//
//        }
        for(int i = 0; i< columnHeaders.size(); i++){
            System.out.println("column header " + columnHeaders.get(i).getText());
        }

    }
}
