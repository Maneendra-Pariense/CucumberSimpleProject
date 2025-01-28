package stepDefinitions;

import Base.BasePage;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FilenameUtils;

import com.aventstack.extentreports.ExtentReports;

import io.cucumber.java.Scenario;
import tech.grasshopper.reporter.ExtentPDFReporter;

import java.io.File;
import java.io.IOException;

public class ExtentManager extends BasePage {
    public static ExtentTest test;
//    public static ExtentReports extent;

    private static String getReportName() {
        String dir = "target/extent-Reports/";
        String extension = ".pdf";
        return dir + newFeatureName + "_" + testReportTimeStamp + extension;
    }


    public static ExtentReports getExtentInstance() {
        String reportName = getReportName();
        ExtentPDFReporter pdfReporter = new ExtentPDFReporter(reportName);
        pdfReporter.config().setTitle("Montefiore Test Report for Feature: " + newFeatureName);
        pdfReporter.config().setTitleColor("Red");
        File jsonExtentConfigFile = new File(System.getProperty("user.dir") + "/src/test/resources/extent-config.json");
        try {
            pdfReporter.loadJSONConfig(jsonExtentConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        extent = new ExtentReports();
        extent.attachReporter(pdfReporter);
        return extent;
    }

    public static void flushExtentInstance() {
        extent.flush();
    }

}
