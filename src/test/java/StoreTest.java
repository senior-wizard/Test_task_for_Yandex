import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreTest extends BaseClassForStore {

    @Test
    @DisplayName("Получение информации о заказе")
    @Description("Проверка, что при получении заказа код ответа 200 и все данные, используемые при создании заказа совпадают в созданном заказе")
    void checkOrder() {
        Response response = stepsOfStoreEndpoint.checkOrder(randomOrder.getId());
        String responseShipDate = randomOrder.getShipDate().substring(0, 23);
        statusCodeSteps.checkStatusCode200(response);
                assertAll(
                () -> stepsOfStoreEndpoint.checkIdInResponse(response, randomOrder.getId()),
                () -> stepsOfStoreEndpoint.checkPetIdInResponse(response, randomOrder.getPetId()),
                () -> stepsOfStoreEndpoint.checkQuantityInResponse(response, randomOrder.getQuantity()),
                () -> assertEquals(responseShipDate, randomOrder.getShipDate()),
                () -> stepsOfStoreEndpoint.checkStatusInResponse(response, randomOrder.getStatus()),
                () -> stepsOfStoreEndpoint.checkCompleteInResponse(response, randomOrder.getComplete())
                );
    }

    @Test
    @DisplayName("Получение списка статусов питомцев")
    @Description("Проверка, при получение списка статусов питомцев код ответа равен 200 и значения 'sold', 'pending' и 'available' не равны null")
    void checkPetsStatusesCount() {
        Response response = stepsOfStoreEndpoint.checkPetsStatusesCount();
        statusCodeSteps.checkStatusCode200(response);
                assertAll(
                () -> stepsOfStoreEndpoint.checkStatusAvailableOnResponse(response),
                () -> stepsOfStoreEndpoint.checkStatusSoldOnResponse(response),
                () -> stepsOfStoreEndpoint.checkStatusPendingOnResponse(response)
                );
    }
}
