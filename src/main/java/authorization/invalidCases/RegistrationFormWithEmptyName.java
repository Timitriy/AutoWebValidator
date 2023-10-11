package authorization.invalidCases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

public class RegistrationFormWithEmptyName {

    private WebDriver driver;
    private final String URL = "file:///Users/dmitrijsorokin/Desktop/qa-test.html";

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/dmitrijsorokin/Desktop/chromedriver_mac_arm64/chromedriver");
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @AfterClass
    public void tearDown() {driver.quit();}

    @Test
    public void testAuthorization() throws InterruptedException {
        // Имитируем задержку, чтобы страница успела полностью загрузиться
        Thread.sleep(1000);

        // Вводим данные в поле Email и пароль
        driver.findElement(By.id("loginEmail")).sendKeys("test@protei.ru");
        Thread.sleep(1000);
        driver.findElement(By.id("loginPassword")).sendKeys("test");
        Thread.sleep(1000);

        // Нажимаем на кнопку Вход
        driver.findElement(By.id("authButton")).click();

        // Вводим данные в поля email и name
        Thread.sleep(1000);
        driver.findElement(By.id("dataEmail")).sendKeys("test@protei.ru");
        Thread.sleep(1000);
        driver.findElement(By.id("dataName")).sendKeys("");

        WebElement checkbox = driver.findElement(By.id("dataCheck11"));
        WebElement radioButton = driver.findElement(By.id("dataSelect21"));

        checkbox.click();
        Thread.sleep(1000);
        radioButton.click();

        // Выбор Мужской пол
        WebElement genderDropdownElement = driver.findElement(By.id("dataGender"));
        Select genderDropdown = new Select(genderDropdownElement);

        genderDropdown.selectByVisibleText("Мужской");
        Thread.sleep(1000);

        WebElement addButton = driver.findElement(By.id("dataSend"));
        addButton.click();
        Thread.sleep(1000);
        try {
            WebElement emailFormatError = driver.findElement(By.id("blankNameError"));
            Assert.assertTrue(emailFormatError.isDisplayed(), "Сообщение об ошибке не отображается.");
        } catch (NoSuchElementException e) {
            Assert.fail("Сообщение об ошибке 'Неверный ' не найдено.");
        }
    }
}
