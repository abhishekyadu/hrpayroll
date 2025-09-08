package util;

import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementUtils {
    private static WebDriver driver = DriverFactory.getDriver();
    private static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public static WebElement getElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void type(By locator, String text) {
        WebElement element = getElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public static void click(By locator) {
        getElement(locator).click();
    }
    
    public static boolean isElementDisplayed(By locator) {
        try {
            return getElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
////////////////////////////////////////////////////////////////////////////////////
	public static boolean waitForElementVisible(By successToast, int i) {
		// TODO Auto-generated method stub
		return false;
	}

	public static String getText(By successToast) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void selectDropdownByVisibleText(By locator, String visibleText) {
	    WebElement element = getElement(locator);
	    new Select(element).selectByVisibleText(visibleText);
	}
	public static void waitForVisibility(By locator, int seconds) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}


	public static void selectCustomDropdown(WebDriver driver, By dropdownLocator, String visibleText) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    // Click the dropdown to open
	    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
	    dropdown.click();
	    // Build XPath for the option within the listbox
	    By optionLocator = By.xpath("//div[@role='listbox']//span[text()='" + visibleText + "']");
	    // Wait for the option to appear and click it
	    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
	    option.click();
	}




}
