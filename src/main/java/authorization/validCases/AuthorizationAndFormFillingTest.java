package authorization.validCases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

public class AuthorizationAndFormFillingTest {
    private WebDriver driver;
    private final String URL = "file:///Users/dmitrijsorokin/Desktop/qa-test.html";

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/dmitrijsorokin/Desktop/chromedriver_mac_arm64/chromedriver");
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    private void sleep() throws InterruptedException {
        Thread.sleep(1000);
    }

    private void selectGender(String gender) {
        if (gender != null && !gender.isEmpty()) {
            WebElement genderDropdownElement = driver.findElement(By.id("dataGender"));
            new Select(genderDropdownElement).selectByVisibleText(gender);
        }
    }

    private void clickAndSleep(WebElement element) throws InterruptedException {
        element.click();
        sleep();
    }

    private void fillAndSubmitForm(String checkboxId, boolean check, String radioButtonId, String gender) throws InterruptedException {
        boolean isActionNeeded = false;

        if (checkboxId != null && !checkboxId.isEmpty()) {
            WebElement checkbox = driver.findElement(By.id(checkboxId));
            if (checkbox.isSelected() != check) {
                clickAndSleep(checkbox);
            }
        }

        if (radioButtonId != null && !radioButtonId.isEmpty()) {
            WebElement radioButton = driver.findElement(By.id(radioButtonId));
            clickAndSleep(radioButton);
            isActionNeeded = true;
        }

        selectGender(gender);

        if (isActionNeeded || gender != null) {
            WebElement addButton = driver.findElement(By.id("dataSend"));
            clickAndSleep(addButton);

            WebElement addSave = driver.findElement(By.cssSelector("button.uk-button.uk-button-primary.uk-modal-close"));
            clickAndSleep(addSave);
        }
    }

    @Test
    public void testAuthorization() throws InterruptedException {

        driver.findElement(By.id("loginEmail")).sendKeys("test@protei.ru");
        sleep();
        driver.findElement(By.id("loginPassword")).sendKeys("test");
        sleep();

        WebElement loginButton = driver.findElement(By.id("authButton"));
        clickAndSleep(loginButton);

        driver.findElement(By.id("dataEmail")).sendKeys("test@protei.ru");
        sleep();
        driver.findElement(By.id("dataName")).sendKeys("Name");

        fillAndSubmitForm("dataCheck11", true, "dataSelect21", "Мужской");
        fillAndSubmitForm(null, false, "dataSelect21", "Женский");
        fillAndSubmitForm(null, false, "dataSelect22", "Мужской");
        fillAndSubmitForm(null, false, "dataSelect22", "Женский");
        fillAndSubmitForm(null, false, "dataSelect23", "Мужской");
        fillAndSubmitForm(null, false, "dataSelect23", "Женский");
        fillAndSubmitForm("dataCheck11", false, null, null);
        fillAndSubmitForm("dataCheck12", true, "dataSelect21", "Мужской");
        fillAndSubmitForm(null, false, "dataSelect21", "Женский");
        fillAndSubmitForm(null, false, "dataSelect22", "Мужской");
        fillAndSubmitForm(null, false, "dataSelect22", "Женский");
        fillAndSubmitForm(null, false, "dataSelect23", "Мужской");
        fillAndSubmitForm(null, false, "dataSelect23", "Женский");
    }
}
