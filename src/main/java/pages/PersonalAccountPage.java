package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Utils;

import java.time.Duration;

import static pages.LoginPage.PERSONAL_ACCOUNT_UPPER_BUTTON;
import static util.Utils.*;

public class PersonalAccountPage {

    private final WebDriverWait wait;
    private final WebDriver driver;

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT);
    }

    private static final By LOGOUT_BUTTON = By.xpath("//button[contains(.,'Выход')]");
    private static final By LOGO_BUTTON = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']");
    private static final By CONSTRUCTOR_BUTTON = By.xpath("//p[text()='Конструктор']");
    private static final By PROFILE_TEXT = By.xpath("//a[contains(text(),'Профиль')]");
    private static final By MAKE_A_BURGER_TEXT = By.xpath("//h1[contains(text(),'Соберите бургер')]");

    public void clickConstructorButton() {
        findClickableElement(CONSTRUCTOR_BUTTON, wait).click();
    }

    public String getProfileText() {
        WebElement profileElement = findVisibleElement(PROFILE_TEXT, wait);
        return profileElement.getText();
    }

    public String getConstructorText() {
        WebElement constructorElement = findVisibleElement(MAKE_A_BURGER_TEXT, wait);
        return constructorElement.getText();
    }

    public void clickPersonalAccountUpperButton() {
        findClickableElement(PERSONAL_ACCOUNT_UPPER_BUTTON, wait).click();
    }

    public void clickLogoutButton() {
        clickPersonalAccountUpperButton();
        findClickableElement(LOGOUT_BUTTON, wait).click();
    }

    public void clickLogoButton() {
        findClickableElement(LOGO_BUTTON, wait).click();
    }
}