package functionalities;

import common.CommonTest;
import model.CreateUserResponse;
import org.junit.jupiter.api.*;
import pages.LoginPage;
import pages.PersonalAccountPage;
import pages.RegisterPage;

import static api.ApiClient.deleteUser;
import static api.ApiClient.registerUser;
import static providers.DataGenerator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Utils.MAIN_PAGE;

public class TransitionsTest extends CommonTest {

    private RegisterPage registerPage;
    private LoginPage loginPage;
    private PersonalAccountPage personalAccountPage;

    private String email;
    private String password;
    private String name;
    private String accessToken;

    @BeforeAll
    public void setUpBeforeAll() {
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
    }

    @BeforeEach
    public void setUpBeforeEach() {
        email = generateEmail();
        password = generatePassword(8);
        name = generateName();

        CreateUserResponse createUserResponse = registerUser(email, password, name);
        accessToken = createUserResponse.getAccessToken();

        driver.get(MAIN_PAGE);

        registerPage.clickPersonalAccountButton();
        loginPage.enterCredentials(email, password);
        loginPage.clickLoginButton();
    }

    @AfterEach
    public void cleanUpAfterEach() {
        personalAccountPage.clickLogoutButton();
        deleteUser(accessToken);
    }

    @Test
    @DisplayName("Check that to the personal account works as expected")
    public void transitionToThePageUsingThePersonalAccountButton() {
        personalAccountPage.clickPersonalAccountUpperButton();

        String profileText = personalAccountPage.getProfileText();
        assertEquals("Профиль", profileText, "Текст 'Профиль' не найден на странице.");
    }

    @Test
    @DisplayName("Check that to the constructor works as expected")
    public void transitionToTheConstructorAfterThePersonalAccountButton() {
        personalAccountPage.clickPersonalAccountUpperButton();
        personalAccountPage.clickConstructorButton();

        String profileText = personalAccountPage.getConstructorText();
        assertEquals("Соберите бургер", profileText, "Текст 'Соберите бургер' не найден на странице.");
    }

    @Test
    @DisplayName("Check that to the main page works as expected")
    public void transitionToTheMainPageAfterThePersonalAccountButton() {
        personalAccountPage.clickPersonalAccountUpperButton();
        personalAccountPage.clickLogoButton();

        String currentUrl = driver.getCurrentUrl();
        assertEquals(MAIN_PAGE + "/", currentUrl, "URL не соответствует ожидаемому.");
    }
}

