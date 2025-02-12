package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import testPages.AdminPage;

import java.util.List;
import java.util.Map;

public class AdminPageSteps {

    AdminPage adminPage;

    public AdminPageSteps(){
        adminPage = new AdminPage();
    }

    @Given("user is on {string} Page")
    public void iAmOnPage(String pageTitle) {
        adminPage.NavigateTo(pageTitle);
        String title = adminPage.GetPageTitle();

        Assert.assertEquals(pageTitle, title);

    }

    @When("user enters Username as {string}")
    public void userEntersUsernameAs(String un) {
        adminPage.EnterUserName(un);
    }

    @And("user selects UserRole as {string}")
    public void userSelectsUserRoleAs(String userRole) {
        adminPage.clickOnUserRoleDropdown();
        adminPage.selectUserRoleDropdownOption(userRole);
    }

    @And("user selects Employee Name as {string}")
    public void userSelectsEmployeeNameAs(String en) {
        adminPage.SelectEmployeeName(en);
    }

    @And("user selects Status as {string}")
    public void userSelectsStatusAs(String st) {
        adminPage.SelectStatus(st);
    }

    @And("user clicks on Search button")
    public void userClicksOnSearchButton() {
        adminPage.ClickOnSearchButton();
    }

    @Then("user should see the record with following details")
    public void userShouldSeeTheRecordWithFollowingDetails(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for(Map<String, String> columns : rows){
            String un = columns.get("Username");
            String ur = columns.get("User Role");
            String en = columns.get("Employee Name");
            String st = columns.get("Status");
        }
        adminPage.GetRecords("Username", 1);


    }

    @Then("user should see the record with {string} {string}")
    public void userShouldSeeTheRecordWith(String columnName, String columnValue) {
        String actulaValue = adminPage.GetRecords(columnName, 1);
        Assert.assertEquals(actulaValue, columnValue);
    }
}
