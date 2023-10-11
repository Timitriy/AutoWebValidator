package authorization.invalidCases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

public class RegistrationFormWithEmptyEmail {

    private WebDriver driver;
    private final String URL = "file:///Users/dmitrijsorokin/Desktop/qa-test.html";

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/dmitrijsorokin/Desktop/chromedriver_mac_arm64/chromedriver");
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testAuthorization() throws InterruptedException {

        driver.findElement(By.id("loginEmail")).sendKeys("test@protei.ru");
        Thread.sleep(1000);
        driver.findElement(By.id("loginPassword")).sendKeys("test");
        Thread.sleep(1000);
        driver.findElement(By.id("authButton")).click();

        Thread.sleep(1000);
        driver.findElement(By.id("dataEmail")).sendKeys("");
        Thread.sleep(1000);
        driver.findElement(By.id("dataName")).sendKeys("Name");

        WebElement checkbox = driver.findElement(By.id("dataCheck11"));
        WebElement radioButton = driver.findElement(By.id("dataSelect21"));

        checkbox.click();
        Thread.sleep(1000);
        radioButton.click();

        WebElement genderDropdownElement = driver.findElement(By.id("dataGender"));
        Select genderDropdown = new Select(genderDropdownElement);

        genderDropdown.selectByVisibleText("Мужской");
        Thread.sleep(1000);

        WebElement addButton = driver.findElement(By.id("dataSend"));
        addButton.click();
        Thread.sleep(1000);
        try {
            WebElement emailFormatError = driver.findElement(By.id("emailFormatError"));
            Assert.assertTrue(emailFormatError.isDisplayed(), "Сообщение об ошибке не отображается.");
        } catch (NoSuchElementException e) {
            Assert.fail("Сообщение об ошибке 'Неверный формат E-Mail' не найдено.");
        }
    }
}


