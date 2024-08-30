package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static util.Utils.*;


public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT);
    }

    private static final By EMAIL_INPUT = By.xpath("//input[@name='name']");
    private static final By PASSWORD_INPUT = By.xpath("//input[@name='Пароль']");
    private static final By LOGIN_TO_ACCOUNT_BUTTON = By.xpath("//button[contains(.,'Войти в аккаунт')]");
    private static final By LOGIN_BUTTON = By.xpath("//button[contains(.,'Войти')]");
    private static final By REGISTER_LINK = By.xpath("//a[text()='Зарегистрироваться']");
    private static final By FORGOT_PASSWORD_LINK = By.xpath("//a[contains(text(),'Восстановить пароль')]");
    private static final By ORDER_BUTTON = By.xpath("//button[contains(.,'Оформить заказ')]");
    private static final By LOGIN_ON_THE_USER_REGISTRATION_FORM_BUTTON = By.xpath("//a[contains(text(),'Войти')]");
    private static final By LOGIN_ON_THE_PASSWORD_RECOVERY_FORM_BUTTON = By.xpath("//a[contains(text(),'Войти')]");
    public static final By PERSONAL_ACCOUNT_UPPER_BUTTON = By.xpath("//a[@href='/account']");

    public void enterEmail(String email) {
        findClickableElement(EMAIL_INPUT, wait).sendKeys(email);
    }

    public void enterPassword(String password) {
        findClickableElement(PASSWORD_INPUT, wait).sendKeys(password);
    }

    public void clickLoginToAccountButton() {
        findClickableElement(LOGIN_TO_ACCOUNT_BUTTON, wait).click();
    }

    public void clickLoginOnThePasswordRecoveryFormButton() {
        findClickableElement(LOGIN_ON_THE_PASSWORD_RECOVERY_FORM_BUTTON, wait).click();
    }

    public void clickLoginButton() {
        findClickableElement(LOGIN_BUTTON, wait).click();
    }

    public boolean isLoginButtonVisible() {
        return findVisibleElement(LOGIN_BUTTON, wait).isDisplayed();
    }

    public void clickRegisterLink() {
        findClickableElement(REGISTER_LINK, wait).click();
    }

    public void clickForgotPasswordLink() {
        findClickableElement(FORGOT_PASSWORD_LINK, wait).click();
    }

    public void enterCredentials(String email, String password) {
        enterEmail(email);
        enterPassword(password);
    }

    public void clickLoginOnTheUserRegistrationFormButton() {
        findClickableElement(LOGIN_ON_THE_USER_REGISTRATION_FORM_BUTTON, wait).click();
    }


    public void clickPersonalAccountUpperButton() {
        findClickableElement(PERSONAL_ACCOUNT_UPPER_BUTTON, wait).click();
    }

    public boolean isOrderButtonDisplayed() {
        try {
            return findVisibleElement(ORDER_BUTTON, wait).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
