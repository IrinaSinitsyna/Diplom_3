package functionalities;

import common.CommonTest;
import model.CreateUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegisterPage;

import static api.ApiClient.deleteUser;
import static api.ApiClient.login;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static providers.DataGenerator.*;
import static util.Utils.MAIN_PAGE;

public class RegistrationTest extends CommonTest {

    @BeforeEach
    public void setUpBeforeEach() {
        driver.get(MAIN_PAGE);
    }

    @Test
    @DisplayName("Check that registration works as expected when correct data is passed")
    public void successfulRegistration() {
        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.clickPersonalAccountButton();
        registerPage.clickRegisterLink();

        String name = generateName();
        String email = generateEmail();
        String password = generatePassword(8);

        registerPage.setName(name);
        registerPage.setEmailRegisterPage(email);
        registerPage.setPasswordRegisterPage(password);

        registerPage.clickRegisterButton();

        String headerText = registerPage.getHeaderText();
        assertEquals("Вход", headerText);

        CreateUserResponse loginResponse = login(email, password);
        deleteUser(loginResponse.getAccessToken());
    }

    @Test
    @DisplayName("Check that registration fails as expected when short password is passed")
    public void registrationWithShortPassword() {
        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.clickPersonalAccountButton();
        registerPage.clickRegisterLink();

        String name = generateName();
        String email = generateEmail();
        String password = generatePassword(3);

        registerPage.setName(name);
        registerPage.setEmailRegisterPage(email);
        registerPage.setPasswordRegisterPage(password);

        registerPage.clickRegisterButton();

        String errorMessage = registerPage.getPasswordErrorMessage();
        assertEquals("Некорректный пароль", errorMessage);
    }
}
