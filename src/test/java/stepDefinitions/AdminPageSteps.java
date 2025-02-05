package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import testPages.AdminPage;

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
    public void userShouldSeeTheRecordWithFollowingDetails() {
    }
}
