package stepDefinitions;

import Base.BasePage;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import testPages.TestPage;

public class testSteps extends BasePage{
	TestPage tp = new TestPage();
	
	@When("user opens browser")
	public void user_opens_browser() {
		tp.openBrowser();
		System.out.println(Thread.currentThread().getId());
	    
	}
	@Then("he should see url loaded")
	public void he_should_see_url_loaded() {
		tp.GetTitle();
	}

    @Then("fail this scenario")
    public void failThisScenario() {
		Assert.fail();
    }
    
    @Then("user enters in search {string}")
    public void failThisScenario(String text) {
		tp.EnterInSearch(text);
    }

	@Given("user logged in to the application with username {string} and password {string}")
	public void userLoggedInToTheApplicationWithUsernameAndPassword(String username, String pwd) {
		tp.DoLogin(username, pwd);

	}


}
