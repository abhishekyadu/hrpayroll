package reports;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import util.PropertyReader;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = PropertyReader.getProperty("extentReportPath");

            // Add timestamp to the report filename
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            if (reportPath.endsWith(".html")) {
                reportPath = reportPath.replace(".html", "_" + timestamp + ".html");
            } else {
                reportPath += "/ExtentReport_" + timestamp + ".html";
            }

            // Ensure folder exists
            File reportFile = new File(reportPath);
            reportFile.getParentFile().mkdirs();

            // Initialize reporter
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }

    public static ExtentTest createTest(String name) {
        test = getInstance().createTest(name);
        return test;
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
