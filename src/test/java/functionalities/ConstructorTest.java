package functionalities;

import common.CommonTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.Utils.MAIN_PAGE;

public class ConstructorTest extends CommonTest {

    @BeforeEach
    public void setUpBeforeEach() {
        driver.get(MAIN_PAGE);
    }

    @Test
    @DisplayName("Check that transition to sauces tab works as expected")
    public void transitionToSauceTab() {
        MainPage mainPage = new MainPage(driver);
        makeTransitionToSauceTab(mainPage);
    }

    private void makeTransitionToSauceTab(MainPage mainPage) {
        mainPage.clickSauceTab();
        assertTrue(mainPage.isSauceActive(), "Соус не выбран.");
    }

    @Test
    @DisplayName("Check that transition to buns tab works as expected")
    public void transitionToBunsTab() {
        MainPage mainPage = new MainPage(driver);
        makeTransitionToSauceTab(mainPage);
        mainPage.clickBunsTab();
        assertTrue(mainPage.isBunsActive(), "Булки не выбраны.");
    }

    @Test
    @DisplayName("Check that transition to fillings tab works as expected")
    public void transitionToFillingTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingTab();
        assertTrue(mainPage.isFillingActive(), "Начинки не выбраны.");
    }
}