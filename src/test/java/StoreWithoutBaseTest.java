import data_and_bodies.DataAbdBodyOfStoreEndpoint;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.StatusCodeSteps;
import steps.StepsOfStoreEndpoint;

public class StoreWithoutBaseTest {

    DataAbdBodyOfStoreEndpoint dataAbdBodyOfStoreEndpoint = new DataAbdBodyOfStoreEndpoint();
    StepsOfStoreEndpoint stepsOfStoreEndpoint = new StepsOfStoreEndpoint();
    StatusCodeSteps statusCodeSteps = new StatusCodeSteps();
    int nonExistentOrder = dataAbdBodyOfStoreEndpoint.getId() + 16243;

    @BeforeAll
    public static void setUpBeforeAll() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    @DisplayName("Невозможность удалить несуществующий заказ")
    @Description("Получение ошибки при попытке удалить несуществующеий заказа")
    void cantDeleteNonExistentOrderTest() {
        statusCodeSteps.checkStatusCode404(stepsOfStoreEndpoint.deleteOrder(nonExistentOrder));
    }

    @Test
    @DisplayName("Невозможно получить несуществующий заказ")
    @Description("Получение ошибки при попытке получить информацию несуществующем заказе")
    void cantCheckNonExistentOrderTest() {
        statusCodeSteps.checkStatusCode404(stepsOfStoreEndpoint.checkOrder(nonExistentOrder));
    }
}
