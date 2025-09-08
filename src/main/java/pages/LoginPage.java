package pages;

import util.ElementUtils;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private By username = By.name("username");
    private By password = By.name("password");
    private By loginBtn = By.xpath("//button[@type='submit']");
    private By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
    private By loginErrorMsg = By.cssSelector("p.oxd-text.oxd-text--p.oxd-alert-content-text");// Locator for login error message

        private WebDriver driver;

        public LoginPage(WebDriver driver) {
            this.driver = driver;
        }

	public String getDashboardHeader() {
        return ElementUtils.getElement(dashboardHeader).getText();
    }
    
    public void login(String user, String pass) {
        ElementUtils.type(username, user);
        ElementUtils.type(password, pass);
        ElementUtils.click(loginBtn);
    }

    public String getErrorMessage() {
        // Assuming error appears under the first name field
        return driver.findElement(By.id("firstNameError")).getText();
    }
    
	public String getLoginErrorMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(loginErrorMsg));
            return error.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

	public String LoginErrorMessage() {
	    return getLoginErrorMessage();
	}

}

