package functionalities;

import common.CommonTest;
import model.CreateUserResponse;
import org.junit.jupiter.api.*;
import pages.LoginPage;
import pages.PersonalAccountPage;
import pages.RegisterPage;

import static api.ApiClient.deleteUser;
import static api.ApiClient.registerUser;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static providers.DataGenerator.*;
import static util.Utils.MAIN_PAGE;

public class LogoutTest extends CommonTest {

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
        deleteUser(accessToken);
    }

    @Test
    @DisplayName("Check that logout works as expected")
    public void logout() {
        personalAccountPage.clickLogoutButton();
        assertTrue(loginPage.isLoginButtonVisible());
    }
}


