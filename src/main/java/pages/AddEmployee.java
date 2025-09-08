package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ElementUtils;
import java.time.Duration;

public class AddEmployee {

    private WebDriver driver;

    public AddEmployee(WebDriver driver) {
        this.driver = driver;
    }

    private By pimMenu = By.xpath("//span[text()='PIM']");
    private By addEmployeeMenu = By.xpath("//a[text()='Add Employee']");
    private By firstName = By.name("firstName");
    private By middleName = By.name("middleName");
    private By lastName = By.name("lastName");
    private By empid = By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input");
    private By saveBtn = By.xpath("//button[@type='submit']");
    private By successToast = By.xpath("//div[contains(@class,'oxd-toast-content') and contains(.,'Successfully Saved')]");
    private By errorMessage = By.xpath("//span[contains(@class,'oxd-input-field-error-message')]");
    
    public void navigateToAddEmployee() {
        ElementUtils.click(pimMenu);
        ElementUtils.click(addEmployeeMenu);
    }

    public void addEmployee(String fName, String mName, String lName, String empId) {
        ElementUtils.type(firstName, fName);
        ElementUtils.type(middleName, mName);
        ElementUtils.type(lastName, lName);
        ElementUtils.type(empid, empId);
        ElementUtils.click(saveBtn);
    }
    
    public String getSuccessMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
            return toast.getText().trim();
        } 
        catch (Exception e) {
            return "";
        }
    }

    public boolean isEmployeeSaved() {
        String msg = getSuccessMessage();
        System.out.println("DEBUG: Toast message captured = [" + msg + "]");
        return msg.contains("Successfully Saved");
    }

    public String getErrorMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return error.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }



}
