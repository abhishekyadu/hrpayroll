package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import util.ElementUtils;

public class DashboardPage {

    private WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator for top-right user dropdown/menu
    private By userMenu = By.xpath("//span[@class='oxd-userdropdown-tab']");
    // Locator for logout button in the dropdown
    private By logoutBtn = By.xpath("//a[text()='Logout']");

    public boolean isLoggedIn() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(userMenu));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogout() {
        ElementUtils.click(userMenu);
        ElementUtils.click(logoutBtn);
        // wait for login page to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
    }
}
