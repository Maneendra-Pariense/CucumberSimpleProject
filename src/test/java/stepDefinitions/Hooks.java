package stepDefinitions;

import Base.BasePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.Collection;
import java.util.List;


public class Hooks extends BasePage {


    @Before
    public void BeforeScenario(Scenario scenario) {
    	Setup();
    }

    @After()
    public void afterScenario(Scenario scenario){
        Collection<String> tags = scenario.getSourceTagNames();
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        TearDown();
    }




}
