import data_and_bodies.DataAndBodyOfUserEndpoint;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertAll;

public class UserTest extends BaseClassForUser {

    @Test
    @DisplayName("Получение информации о пользователе")
    @Description("Проверка, что при получении пользователя код ответа 200 и все данные, используемые при создании пользователя совпадают в созданном пользователе")
    void checkUserTest() {
        statusCodeSteps.waitMethod(1000);
        Response response = stepsOfUserEndpoint.checkUser(randomUser.getUsername());
        statusCodeSteps.checkStatusCode200(response);
                assertAll(
                () -> stepsOfUserEndpoint.checkIdInResponse(response, randomUser.getId()),
                () -> stepsOfUserEndpoint.checkUsernameInResponse(response, randomUser.getUsername()),
                () -> stepsOfUserEndpoint.checkFirstNameInResponse(response, randomUser.getFirstName()),
                () -> stepsOfUserEndpoint.checkLastNameInResponse(response, randomUser.getLastName()),
                () -> stepsOfUserEndpoint.checkEmailInResponse(response, randomUser.getEmail()),
                () -> stepsOfUserEndpoint.checkPasswordInResponse(response, randomUser.getPassword()),
                () -> stepsOfUserEndpoint.checkPhoneInResponse(response, randomUser.getPhone()),
                () -> stepsOfUserEndpoint.checkUserStatusInResponse(response, randomUser.getUserStatus())
        );
    }

    @Test
    @DisplayName("Изменение параметров пользователя")
    @Description("Проверка, что при получении пользователя код ответа 200 и все данные, используемые при изменении пользователя совпадают в созданном пользователе")
    void updateUserTest() {
        updatedUser = dataAndBodyOfUserEndpoint.getRandomUser();
        statusCodeSteps.checkStatusCode200(stepsOfUserEndpoint.updateUser(updatedUser, randomUser.getUsername()));
    }

    @Test
    @DisplayName("Авторизация пользователя")
    @Description("Проверка, что при логине пользователя код ответа равен 200")
    void loginUserTest() {
        statusCodeSteps.checkStatusCode200(stepsOfUserEndpoint.loginUser(randomUser.getUsername(), randomUser.getPassword()));
    }

    @Test
    @DisplayName("Выход из аккаунта пользователя после авторизации")
    @Description("Проверка, что при попытке выйти из аккаунта пользователя после авторизации код ответа равен 200")
    void logoutUserTest() {
        statusCodeSteps.checkStatusCode200(stepsOfUserEndpoint.loginUser(randomUser.getUsername(), randomUser.getPassword()));
        statusCodeSteps.checkStatusCode200(stepsOfUserEndpoint.logoutUser());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/bad_data_login_test.csv", numLinesToSkip = 1)
    @DisplayName("Невозможно пройти авторизацию с неправильным логином или паролем")
    @Description("Проверка, что при попытке авторизоваться с неправильным логином или паролем код ответа равен 400")
    void cantAuthorizationWithBadLoginOrPassword(String username, String password) {
        hardCodeUser = new DataAndBodyOfUserEndpoint(1, "hardCodeUser!~@#`123731684", "hardCodeUser!~@#`123731684", "hardCodeUser!~@#`123731684", "hardCodeUser!~@#`123731684", "hardCodeUser!~@#`123731684", "hardCodeUser!~@#`123731684", 1);
        statusCodeSteps.checkStatusCode400(stepsOfUserEndpoint.loginUser(username, password));
    }
}
