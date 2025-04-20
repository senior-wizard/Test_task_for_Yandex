import data_and_bodies.pet.DataAndBodyOfPetEndpoint;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.StatusCodeSteps;
import steps.StepsOfPetEndpoint;

public class PetWithoutBaseTest {

    DataAndBodyOfPetEndpoint dataAndBodyOfPetEndpoint = new DataAndBodyOfPetEndpoint();
    StepsOfPetEndpoint stepsOfPetEndpoint = new StepsOfPetEndpoint();
    StatusCodeSteps statusCodeSteps = new StatusCodeSteps();
    int nonExistentPetId = dataAndBodyOfPetEndpoint.getId() + 1234;

    @BeforeAll
    public static void setUpBeforeAll() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    @DisplayName("Невозможно удалить несуществующего питомца")
    @Description("Получение 404 ошибки при попытке удалить несуществующего питомца")
    void cantDeleteNonExistentPetTest() {
        statusCodeSteps.checkStatusCode404(stepsOfPetEndpoint.deletePet(nonExistentPetId));
    }

    @Test
    @DisplayName("Невозможность получить информацию о несуществующем питомце")
    @Description("Получение 404 ошибки при попытке получить информацию о несуществующем питомце")
    void cantCheckNonExistentPetTest() {
        statusCodeSteps.checkStatusCode404(stepsOfPetEndpoint.checkPet(nonExistentPetId));
    }

    @Test
    @DisplayName("Невозможность получить информацию о питомцах с несуществующим статусом")
    @Description("Получение 400 ошибки при попытке получить информацию о несуществующем питомце")
    void cantCheckByStatusNonExistentStatusTest() {
        Response response = stepsOfPetEndpoint.checkPetsByStatus(dataAndBodyOfPetEndpoint.getName());
        statusCodeSteps.checkStatusCode400(response);
    }
}
