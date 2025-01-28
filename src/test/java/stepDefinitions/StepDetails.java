package stepDefinitions;

import Base.BasePage;
import com.aventstack.extentreports.Status;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Scenario;
import io.cucumber.core.backend.TestCaseState;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestStepStarted;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class StepDetails extends BasePage implements ConcurrentEventListener {
    public static String stepName;

    public EventHandler<TestStepStarted> stepHandler = event -> handleTestStepStarted(event);

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, stepHandler);
    }

    private void handleTestStepStarted(TestStepStarted event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep testStep = (PickleStepTestStep) event.getTestStep();
            stepName = testStep.getStep().getText();
        }
    }
    public void logError (Scenario scenario) {
        try {
            Field delegate = scenario.getClass().getDeclaredField("delegate");
            delegate.setAccessible(true);
            TestCaseState tcs = (TestCaseState) delegate.get(scenario);

            Field stepResults = tcs.getClass().getDeclaredField("stepResults");
            stepResults.setAccessible(true);

            ArrayList<Result> results = (ArrayList<Result>) stepResults.get(tcs);
            for (Result result : results) {
                if (result.getError() != null) {
                    test.log(Status.FAIL, result.getError());
                    String screenshotPath = takeScreenshotAtEndOfTest();
                    test.fail("Test Case failed check screenshot below" + test.addScreenCaptureFromPath(screenshotPath));
                }
            }

        } catch (Exception e) {
            test.log(Status.FAIL, e);
        }

    }
}