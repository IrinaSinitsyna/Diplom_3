package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static util.Utils.*;

public class RegisterPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, TIMEOUT);
        this.driver = driver;
    }

    private static final By PERSONAL_ACCOUNT_BUTTON = By.xpath("//p[contains(.,'Личный Кабинет')]");
    private static final By HEADER_TEXT = By.xpath("//h2[contains(.,'Вход')]");
    private static final By NAME_INPUT = By.xpath("//input[@name='name']");
    private static final By EMAIL_INPUT_REGISTER_PAGE = By.xpath("//fieldset[2]/div/div/input");
    private static final By PASSWORD_INPUT_REGISTER_PAGE = By.xpath("//input[@name='Пароль']");
    private static final By REGISTER_LINK = By.xpath("//a[contains(text(),'Зарегистрироваться')]");
    private static final By REGISTER_BUTTON = By.xpath("//button[contains(.,'Зарегистрироваться')]");
    private static final By PASSWORD_INPUT_ERROR_TEXT = By.xpath("//p[text()='Некорректный пароль']");

    public void setName(String name) {
        findClickableElement(NAME_INPUT, wait).sendKeys(name);
    }

    public void setEmailRegisterPage(String email) {
        findClickableElement(EMAIL_INPUT_REGISTER_PAGE, wait).sendKeys(email);
    }

    public void setPasswordRegisterPage(String password) {
        findClickableElement(PASSWORD_INPUT_REGISTER_PAGE, wait).sendKeys(password);
    }

    public void clickRegisterLink() {
        findClickableElement(REGISTER_LINK, wait).click();
    }

    public void clickRegisterButton() {
        findClickableElement(REGISTER_BUTTON, wait).click();
    }

    public String getHeaderText() {
        WebElement headerElement = findVisibleElement(HEADER_TEXT, wait);
        return headerElement.getText();
    }

    public String getPasswordErrorMessage() {
        return findVisibleElement(PASSWORD_INPUT_ERROR_TEXT, wait).getText();
    }

    public void clickPersonalAccountButton() {
        findClickableElement(PERSONAL_ACCOUNT_BUTTON, wait).click();
    }
}

