package functionalities;

import common.CommonTest;
import model.CreateUserResponse;
import org.junit.jupiter.api.*;
import pages.LoginPage;
import pages.PersonalAccountPage;

import static api.ApiClient.deleteUser;
import static api.ApiClient.registerUser;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static providers.DataGenerator.*;
import static util.Utils.MAIN_PAGE;

public class LoginTest extends CommonTest {

    private LoginPage loginPage;
    private PersonalAccountPage personalAccountPage;
    private String email;
    private String password;
    private String accessToken;

    @BeforeAll
    public void setUpBeforeAll() {
        loginPage = new LoginPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
    }

    @BeforeEach
    public void setUpBeforeEach() {
        email = generateEmail();
        password = generatePassword(8);
        String name = generateName();

        CreateUserResponse createUserResponse = registerUser(email, password, name);
        accessToken = createUserResponse.getAccessToken();

        driver.get(MAIN_PAGE);
    }

    @AfterEach
    public void cleanUpAfterEach() {
        personalAccountPage.clickLogoutButton();
        deleteUser(accessToken);
    }

    @Test
    @DisplayName("Check that login works as expected using account button")
    public void loginUsingTheLoginToAccountButton() {
        loginPage.clickLoginToAccountButton();

        loginPage.enterCredentials(email, password);
        loginPage.clickLoginButton();

        assertTrue(loginPage.isOrderButtonDisplayed(), "Кнопка 'Оформить заказ' не отображается.");
    }

    @Test
    @DisplayName("Check that login works as expected using personal account button")
    public void loginUsingThePersonalAccountButton() {
        loginPage.clickPersonalAccountUpperButton();

        loginPage.enterCredentials(email, password);
        loginPage.clickLoginButton();

        assertTrue(loginPage.isOrderButtonDisplayed(), "Кнопка 'Оформить заказ' не отображается.");
    }

    @Test
    @DisplayName("Check that login works as expected using registration form button")
    public void loginUsingTheUserRegistrationFormButton() {
        loginPage.clickPersonalAccountUpperButton();

        loginPage.clickRegisterLink();

        loginPage.clickLoginOnTheUserRegistrationFormButton();

        loginPage.enterCredentials(email, password);
        loginPage.clickLoginButton();

        assertTrue(loginPage.isOrderButtonDisplayed(), "Кнопка 'Оформить заказ' не отображается.");
    }

    @Test
    @DisplayName("Check that login works as expected using password recovery button")
    public void loginUsingTheLoginButtonOnThePasswordRecoveryForm() {
        loginPage.clickPersonalAccountUpperButton();

        loginPage.clickForgotPasswordLink();

        loginPage.clickLoginOnThePasswordRecoveryFormButton();

        loginPage.enterCredentials(email, password);
        loginPage.clickLoginButton();

        assertTrue(loginPage.isOrderButtonDisplayed(), "Кнопка 'Оформить заказ' не отображается.");
    }
}
