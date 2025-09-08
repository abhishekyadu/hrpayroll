package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ElementUtils;

import java.time.Duration;

public class EmployeePage {

    private WebDriver driver;

    // Constructor
    public EmployeePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators 
    private By empMenu = By.xpath("//span[text()='PIM']");
    private By empSearchMenu = By.xpath("//a[text()='Employee List']"); 

    private By resultRow = By.xpath("//div[@class='oxd-table-body']//div[contains(@class,'oxd-table-card')]");
    private By noRecordMessage = By.xpath("//span[contains(@class,'oxd-text--span') and text()='No Records Found']");

    
    // Navigate to Employee Search page
    public void navigateToEmployeeSearch() {
        ElementUtils.click(empMenu);         
        ElementUtils.click(empSearchMenu);   
    }

    
 // Employee Name field
    private By nameField = By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input");

    // Employee ID field
    private By empIdInput = By.xpath("//label[text()='Employee Id']/../following-sibling::div//input");

    // Search button (already correct)
    private By searchButton = By.xpath("//button[contains(@class,'oxd-button') and contains(@class,'orangehrm-left-space')]");



 // Search employee by name
    public void searchEmployeeByName(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        ElementUtils.type(nameField, name);
    }

    // Search employee by ID
    public void searchEmployeeById(String empId) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(empIdInput));
        ElementUtils.type(empIdInput, empId);
    }


    // Click search
    public void clickSearch() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        ElementUtils.click(searchButton);
    }

    // Check if record exists (with wait)
    public boolean isRecordFound() {
        // Check "No Records Found" first
        if (!driver.findElements(noRecordMessage).isEmpty()) {
            return false;
        }
        // Then check if actual records exist
        return !driver.findElements(resultRow).isEmpty();
    }

}
