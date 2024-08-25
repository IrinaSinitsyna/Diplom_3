package functionalities;

import common.CommonTest;
import data_providers.DataGenerator;
import api.ApiClient;
import org.junit.jupiter.api.*;
import pages.LoginPage;
import pages.PersonalAccountPage;
import pages.RegisterPage;

import static api.ApiClient.registerUser;
import static data_providers.DataGenerator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.Utils.MAIN_PAGE;

public class LogoutTest extends CommonTest {

    private RegisterPage registerPage;
    private LoginPage loginPage;
    private PersonalAccountPage personalAccountPage;

    private String email;
    private String password;
    private String name;

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

        registerUser(email, password, name);

        driver.get(MAIN_PAGE);

        registerPage.clickPersonalAccountButton();
        loginPage.enterCredentials(email, password);
        loginPage.clickLoginButton();
    }

    @Test
    @DisplayName("Check that logout works as expected")
    public void logout() {
        personalAccountPage.clickLogoutButton();
        assertTrue(loginPage.isLoginButtonVisible());
    }
}


