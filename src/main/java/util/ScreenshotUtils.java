package util;

import base.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtils {

    public static String captureScreenshot(String fileName) {
        File src = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);

        // Read screenshot folder from properties
        String folderPath = PropertyReader.getProperty("screenshotPath");
        if (folderPath == null || folderPath.trim().isEmpty()) {
            folderPath = "./reports/screenshots/"; // default fallback
        }

        // Ensure folder exists
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Build final file path
        String path = folderPath + File.separator + fileName.replaceAll("[^a-zA-Z0-9-_\\.]", "_") + ".png";

        try {
            Files.copy(src.toPath(), new File(path).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Screenshot not captured: " + path + " -> " + e.getMessage(), e);
        }

        return path;
    }
}
