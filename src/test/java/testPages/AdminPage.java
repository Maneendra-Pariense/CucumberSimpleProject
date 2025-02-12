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

    public AdminPage() {
        driver = getDriver();
        PageFactory.initElements(driver, this);
    }

    public String GetPageTitle() {
        return pageTitle.getText();
    }

    public void clickOnUserRoleDropdown() {
        userRoleDropdown.click();
    }

    public void selectUserRoleDropdownOption(String option) {

        driver.findElement(By.xpath("//div[@role='option']/span[text()='" + option + "']/..")).click();
    }

    public void NavigateTo(String pageTitle) {
        driver.findElement(By.xpath("//ul[@class='oxd-main-menu']//a/span[text() ='" + pageTitle + "']/..")).click();
    }

    public void EnterUserName(String un) {
        usernameInput.sendKeys(un);
    }

    public void SelectEmployeeName(String en) {
        employeeNameInput.sendKeys(en);
    }

    public void SelectStatus(String st) {
        driver.findElement(By.xpath("//label[@class='oxd-label' and text() = 'Status']/../..//i")).click();
        driver.findElement(By.xpath("//div[@role='option']/span[text()='" + st + "']/..")).click();
    }

    public void ClickOnSearchButton() {
        searchButton.click();
    }

    public void SelectFromDropdown(String label, String option) {
        driver.findElement(By.xpath("//label[@class='oxd-label' and text() = '" + label + "']/../..//i")).click();
        driver.findElement(By.xpath("//div[@role='option']/span[text()='" + option + "']/..")).click();
    }

    public String GetRecords(String columnHeader, int rowNumber) {
//    // Logic if total pages are available
//        String pagecount = driver.findElement(By.className("oxd-table")).getText();
//
//
//        int totalpages = Integer.parseInt(pagecount);
//        for(int i= 0; i< totalpages; i++){
//            // do the logic
//            // click on next page button
//        }
//// logic if next page button is enabled
//        boolean pageEnabled = driver.findElement(By.className("oxd-table")).isEnabled();
//        while(pageEnabled){
//            //
//            //click
//        }


        WebElement table = driver.findElement(By.className("oxd-table"));
        WebElement header = table.findElement(By.className("oxd-table-header"));
        WebElement body = table.findElement(By.className("oxd-table-body"));
        List<WebElement> columnHeaders = header.findElements(By.xpath(".//div[@role = 'columnheader']"));
        List<WebElement> rows = body.findElements(By.xpath(".//div[@role='row']"));

        if(rows.size()<1)
            return "";

        int colIndex = 0;
        for(int i= 0; i< columnHeaders.size(); i++){
            if(columnHeaders.get(i).getText().equalsIgnoreCase(columnHeader)){
                colIndex = i;
                break;
            }
        }

        String requiredValue = rows.get(rowNumber-1).findElements(By.xpath("./div")).get(colIndex).getText();
        return requiredValue;
    }

    public String GetValueFromGrid(String tableName, String columnName, int rowNumber) {
        WebElement table = driver.findElement(By.className(tableName));
        WebElement header = table.findElement(By.className("oxd-table-header"));
        WebElement body = table.findElement(By.className("oxd-table-body"));
        List<WebElement> columnHeaders = header.findElements(By.xpath(".//div[@role = 'columnheader']"));
        List<WebElement> rows = body.findElements(By.xpath(".//div[@role='row']"));
        if (rows.size() < 1) return null;
        WebElement requiredRow = rows.get(rowNumber - 1);
        int columnIndex = 0;
        for (int i = 0; i < columnHeaders.size(); i++) {
            if (columnHeaders.get(i).getText().equalsIgnoreCase(columnName)) {
                columnIndex = i;
                break;
            }
        }
        String requiredValue = requiredRow.findElements(By.xpath("./div")).get(columnIndex).getText();
        System.out.println("required value: " + requiredValue);
        return requiredValue;

    }
}
