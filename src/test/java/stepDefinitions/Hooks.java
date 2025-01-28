package stepDefinitions;

import Base.BasePage;
import com.aventstack.extentreports.MediaEntityBuilder;
import gherkin.formatter.model.Result;
import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import com.aventstack.extentreports.Status;
import org.junit.Assume;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Hooks extends BasePage {

    @BeforeAll
    public static void before_all() {
        System.out.println("before all called ");
        testReportTimeStamp = new SimpleDateFormat("d-MMM-yyyy-HH-mm-ss").format(new Date());
//        test = ExtentManager.getExtentInstance();
    }

    @Before
    public void beforeScenario(Scenario scenario) throws ClassNotFoundException {
    	
        System.out.println("Before Scenario called");
        scenarioName = scenario.getName().replaceAll(" ", "_");
        newFeatureName = FilenameUtils.getBaseName(scenario.getUri().toString());
        System.out.println("Thread: " + Thread.currentThread().getId() + " and feature file name: "
				+ newFeatureName + " and ScenarioNamae: " + scenarioName);
        if (!newFeatureName.equalsIgnoreCase(oldFeatureName)) {
            if (test == null) {
                extent = ExtentManager.getExtentInstance();
            }
            test = extent.createTest(scenarioName);
            Setup();
        }else{
            String message = "Skipping scenarios as " + scenario.getName() + "is Failed";
            Assume.assumeTrue(message, false);
        }

    }

    @After()
    public void afterScenario(Scenario scenario) {
        System.out.println("After Scenario called");
        if (scenario.isFailed()) {
            oldFeatureName = newFeatureName;
            ExtentManager.flushExtentInstance();
            test = null;
        }
        if (oldFeatureName != null && !newFeatureName.equalsIgnoreCase(oldFeatureName)) {
            ExtentManager.flushExtentInstance();
        }

        TearDown();
    }

    @AfterStep
    public void addStepToReport(Scenario scenario) {
        io.cucumber.java.Status status = scenario.getStatus();
        if (status == io.cucumber.java.Status.FAILED) {
            test.fail("");
//            test.createNode(StepDetails.stepName).fail("");
//            test.createNode(StepDetails.stepName);
            test.log(Status.FAIL, StepDetails.stepName);
//            logError(scenario);
        } else{
//            test.createNode(StepDetails.stepName);
                test.log(Status.PASS, StepDetails.stepName);
        }

    }

    private static void logError(Scenario scenario) {
//        try {
//            Field field = FieldUtils.getField(scenario.getClass(), "stepResults", true);
//            field.setAccessible(true);
//            ArrayList<Result> results = (ArrayList<Result>) field.get(scenario);
//            for (Result result : results) {
//                if (result.getError() != null) {
//                    test.log(Status.FAIL, result.getError());
//                    String screenshotPath = takeScreenshotAtEndOfTest();
//                    test.fail("Test Case failed check screenshot below" + test.addScreenCaptureFromPath(screenshotPath));
//                }
//
//            }
//        } catch (Exception e) {
//            test.log(Status.FAIL, e);
//
//        }

        try {
            String screenshotPath = takeScreenshotAtEndOfTest();
            test.fail("Test Case failed check screenshot below " + test.addScreenCaptureFromPath(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
