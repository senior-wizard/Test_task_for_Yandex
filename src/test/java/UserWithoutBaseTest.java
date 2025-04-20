import data_and_bodies.DataAndBodyOfUserEndpoint;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.StatusCodeSteps;
import steps.StepsOfUserEndpoint;

public class UserWithoutBaseTest {

    StepsOfUserEndpoint stepsOfUserEndpoint = new StepsOfUserEndpoint();
    DataAndBodyOfUserEndpoint dataAndBodyOfUserEndpoint = new DataAndBodyOfUserEndpoint();
    StatusCodeSteps statusCodeSteps = new StatusCodeSteps();
    String nonExistentUser = dataAndBodyOfUserEndpoint.getUsername() + "Test123";

    @BeforeAll
    public static void setUpBeforeAll() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    @DisplayName("Невозможность удалить несуществующего пользователя")
    @Description("Получение ошибки при попытке удалить несуществующего пользователя")
    void cantDeleteNonExistentUserTest() {
        statusCodeSteps.checkStatusCode404(stepsOfUserEndpoint.deleteUser(nonExistentUser));
    }

    @Test
    @DisplayName("Невозможность получить информацию о несуществующем пользователе")
    @Description("Получение ошибки при попытке получить информацию о несуществующем пользователе")
    void cantCheckNonExistentUserTest() {
        statusCodeSteps.checkStatusCode404(stepsOfUserEndpoint.checkUser(nonExistentUser));
    }

    @Test
    @DisplayName("Невозможность обновить информацию несуществующего пользователя")
    @Description("Получение ошибки при попытке получить информацию о несуществующем пользователе")
    void cantUpdateNonExistentUserTest() {
        DataAndBodyOfUserEndpoint dataAndBodyOfUserEndpoint = new DataAndBodyOfUserEndpoint();
        DataAndBodyOfUserEndpoint randomUser = dataAndBodyOfUserEndpoint.getRandomUser();
        statusCodeSteps.checkStatusCode404(stepsOfUserEndpoint.updateUser(randomUser, nonExistentUser));
    }

    @Test
    @DisplayName("Невозможно выйти из аккаунта будучи неавторизованным")
    @Description("Проверка, что при попытке выйти из аккаунта будучи неавторизованным код ответа равен 400")
    void cantLogoutWithoutAuthorization() {
        statusCodeSteps.checkStatusCode400(stepsOfUserEndpoint.logoutUser());
    }
}
