package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static util.Utils.TIMEOUT;
import static util.Utils.findClickableElement;

public class MainPage {

    private final WebDriverWait wait;
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT);
    }

    private static final String ACTIVE_TAB_CLASS = "tab_tab_type_current__2BEPc";
    private static final By BUNS_TAB = By.xpath("//span[contains(.,'Булки')]");
    private static final By SAUCE_TAB = By.xpath("//span[contains(.,'Соусы')]");
    private static final By FILLING_TAB = By.xpath("//span[contains(.,'Начинки')]");

    public void clickBunsTab() {
        findClickableElement(BUNS_TAB, wait).click();
    }

    public void clickSauceTab() {
        findClickableElement(SAUCE_TAB, wait).click();
    }

    public void clickFillingTab() {
        findClickableElement(FILLING_TAB, wait).click();
    }

    public boolean isBunsActive() {
        return isTabActive(BUNS_TAB);
    }

    public boolean isSauceActive() {
        return isTabActive(SAUCE_TAB);
    }

    public boolean isFillingActive() {
        return isTabActive(FILLING_TAB);
    }

    private boolean isTabActive(By tab) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(tab));
        WebElement elementParent = (WebElement) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].parentNode;", element);
        String classAttribute = elementParent.getAttribute("class");
        return classAttribute.contains(ACTIVE_TAB_CLASS);
    }
}