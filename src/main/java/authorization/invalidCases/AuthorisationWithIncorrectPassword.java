package authorization.invalidCases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthorisationWithIncorrectPassword {

    private WebDriver driver;
    private final String URL = "file:///Users/dmitrijsorokin/Desktop/qa-test.html";
    private WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/dmitrijsorokin/Desktop/chromedriver_mac_arm64/chromedriver");
        driver = new ChromeDriver();
        driver.get(URL);
        wait = new WebDriverWait(driver, 5);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testInvalidLogin() throws InterruptedException {
        System.out.println("Starting testInvalidLogin test");
        Thread.sleep(2000);
        driver.findElement(By.id("loginEmail")).sendKeys("test@protei.ru");

        Thread.sleep(2000);
        driver.findElement(By.id("loginPassword")).sendKeys("no_password");

        Thread.sleep(2000);
        driver.findElement(By.id("authButton")).click();

        try {
            Thread.sleep(2000);
            WebElement authButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("authButton")));
            boolean isDisabled = authButton.getAttribute("disabled") != null;
            System.out.println("Finished testInvalidLogin test");
        } catch (TimeoutException e) {
            System.out.println("Произошла авторизация аккаунта");
        }
    }
}
