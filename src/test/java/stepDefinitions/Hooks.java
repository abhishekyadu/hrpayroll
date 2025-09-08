package stepDefinitions;

import base.DriverFactory;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Before;
import io.cucumber.java.AfterAll;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import reports.ExtentReportManager;
import util.PropertyReader;
import util.ScreenshotUtils;

public class Hooks {


    public static WebDriver driver;

    // Runs once before all scenarios
    @BeforeAll
    public static void globalSetup() {
        driver = DriverFactory.getDriver();
        driver.get(PropertyReader.getProperty("url"));
        // Initialize ExtentReports
        ExtentReportManager.getInstance();
    }

    // Runs before each scenario
    @Before
    public void beforeScenario(Scenario scenario) {
        // Create a new ExtentTest for the current scenario
        ExtentReportManager.createTest(scenario.getName());
    }

    // Runs after each scenario
    @After
    public void afterScenario(Scenario scenario) {
        ExtentTest test = ExtentReportManager.getTest();
        if (test != null) {
            // Unique screenshot name
            String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
            String fileName = scenario.getName().replaceAll(" ", "_") + "_" + timestamp;
            String screenshotPath = ScreenshotUtils.captureScreenshot(fileName);

            if (scenario.isFailed()) {
                test.log(Status.FAIL, "Scenario Failed");
                test.addScreenCaptureFromPath(screenshotPath, "Failed Screenshot");
            } else {
                test.log(Status.PASS, "Scenario Passed");
                test.addScreenCaptureFromPath(screenshotPath, "Passed Screenshot");
            }

            System.out.println("Screenshot saved at: " + screenshotPath);
        }
    }


    // Runs once after all scenarios
    @AfterAll
    public static void globalTearDown() {
        if (driver != null) {
            driver.quit();
        }
        ExtentReportManager.flushReports(); // flush report to disk
    }
}

//package stepDefinitions;
//
//import base.DriverFactory;
//import io.cucumber.java.BeforeAll;
//import io.cucumber.java.AfterAll;
//import io.cucumber.java.After;
//import io.cucumber.java.Scenario;
//import org.openqa.selenium.WebDriver;
//import reports.ExtentReportManager;
//
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import util.PropertyReader;
//import util.ScreenshotUtils;
//
//public class Hooks {
//
//    public static WebDriver driver;
//
//    @BeforeAll
//    public static void setup() {
//        driver = DriverFactory.getDriver();
//        driver.get(PropertyReader.getProperty("url"));
//        // Initialize ExtentReports
//        ExtentReportManager.getInstance();
//    }
//
//    @After
//    public void afterScenario(Scenario scenario) {
//        String fileName = scenario.getName().replaceAll(" ", "_");
//        String screenshotPath = ScreenshotUtils.captureScreenshot(fileName);
//
//        // Create Extent test and attach screenshot
//        ExtentTest test = ExtentReportManager.createTest(scenario.getName());
//        if (scenario.isFailed()) {
//            test.log(Status.FAIL, "Scenario Failed");
//        } else {
//            test.log(Status.PASS, "Scenario Passed");
//        }
//        test.addScreenCaptureFromPath(screenshotPath);
//
//        System.out.println("Screenshot saved at: " + screenshotPath);
//    }
//
//    @AfterAll
//    public static void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//        ExtentReportManager.flushReports(); // Flush report to disk
//    }
//}
//
