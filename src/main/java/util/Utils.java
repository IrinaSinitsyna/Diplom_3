package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utils {

    public static final Duration TIMEOUT = Duration.ofSeconds(20);
    public static final String MAIN_PAGE = "https://stellarburgers.nomoreparties.site";

    public static ChromeDriver setDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        return new ChromeDriver();
    }

    public static WebElement findClickableElement(By by, WebDriverWait wait) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static WebElement findVisibleElement(By by, WebDriverWait wait) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
