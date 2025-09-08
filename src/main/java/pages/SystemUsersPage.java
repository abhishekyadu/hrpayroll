package pages;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.ElementUtils;
import util.WaitUtils;

public class SystemUsersPage {

    private WebDriver driver;

    public SystemUsersPage(WebDriver driver) {
        this.driver = driver;
    }

    private By adminMenu = By.xpath("//span[text()='Admin']");
    private By userManagementMenu = By.xpath("//span[text()='User Management ']");
    private By usersMenu = By.xpath("//a[text()='Users']");
    private By addButton = By.xpath("//button[text()=' Add ']");
    private By userRoleDropdown = By.xpath("//label[text()='User Role']/../following-sibling::div//div[contains(@class,'oxd-select-text')]");
    private By employeeName = By.xpath("//input[@placeholder='Type for hints...']");
    private By username = By.xpath("//label[text()='Username']/../following-sibling::div/input");
    private By statusDropdown = By.xpath("//label[text()='Status']/../following-sibling::div//div[contains(@class,'oxd-select-text')]");
    private By password = By.xpath("//label[text()='Password']/../following-sibling::div/input");
    private By confirmPassword = By.xpath("//label[text()='Confirm Password']/../following-sibling::div/input");
    private By saveBtn = By.xpath("//button[contains(@class,'oxd-button--secondary') and contains(@class,'orangehrm-left-space')]");

    private By successToast = By.xpath("//div[contains(@class,'oxd-toast-content') and contains(.,'Successfully Saved')]");
    private By errorMessage = By.xpath("//span[contains(@class,'oxd-input-field-error-message')]");

    // Navigation
    public void clickAdminMenu() { ElementUtils.click(adminMenu); }
    public void clickUserManagementMenu() { ElementUtils.click(userManagementMenu); }
    public void clickUsersMenu() { ElementUtils.click(usersMenu); }
    public void clickAddUser() { ElementUtils.click(addButton); }
    
    // Add user
    public void addUser(String role, String empName, String uname, String status, String pwd, String confPwd) {
        // Select User Role
        ElementUtils.selectCustomDropdown(driver, userRoleDropdown, role);

        // Type Employee Name and select first suggestion
     // Type Employee Name and select first suggestion
        selectEmployeeNameRandom();
        // call the method in this class


        // Type Username
        ElementUtils.type(username, uname);

        // Select Status
        ElementUtils.selectCustomDropdown(driver, statusDropdown, status);

        // Type Password
        ElementUtils.type(password, pwd);

        // Type Confirm Password
        ElementUtils.type(confirmPassword, confPwd);

        // Click Save
        ElementUtils.click(saveBtn);
    }
  
    	public void selectEmployeeNameRandom() {
    	    // Type any character to trigger suggestions
    	    ElementUtils.type(employeeName, "a");

    	    // Wait for all suggestions
    	    List<WebElement> suggestions = WaitUtils.waitForElements(
    	        By.xpath("//div[@role='listbox']//div[contains(@class,'oxd-autocomplete-option')]"), 
    	        5
    	    );

    	    if (suggestions != null && !suggestions.isEmpty()) {
    	        // Pick a random suggestion
    	        Random rand = new Random();
    	        suggestions.get(rand.nextInt(suggestions.size())).click();
    	    } else {
    	        throw new RuntimeException("No employee suggestions found!");
    	    }
    	}


    public void clickSave() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
        button.click();
    }



    public String getSuccessMessage() {
        return ElementUtils.getText(successToast);
    }

    public String getErrorMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return error.getText().trim();
        } catch (Exception e) {
            return null;
        }
    }
}
