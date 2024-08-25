package functionalities;

import common.CommonTest;
import org.junit.jupiter.api.*;
import pages.PersonalAccountPage;
import pages.RegisterPage;
import data_providers.DataGenerator;

import static data_providers.DataGenerator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
