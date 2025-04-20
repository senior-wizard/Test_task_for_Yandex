package steps;

import data_and_bodies.DataAbdBodyOfStoreEndpoint;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class StepsOfStoreEndpoint {

    String storeOrderEndpoint = "/store/order";
    String storeInventoryEndpoint = "/store/inventory";

    @Step("Получение списка статусов питомцев")
    public Response checkPetsStatusesCount() {
        return given()
                .when()
                .get(storeInventoryEndpoint);
    }

    @Step("Проверка, что в ответе присутствует статус 'sold'")
    public void checkStatusSoldOnResponse(Response response) {
        response.then().assertThat().body("sold", notNullValue());
    }

    @Step("Проверка, что в ответе присутствует статус 'pending'")
    public void checkStatusPendingOnResponse(Response response) {
        response.then().assertThat().body("pending", notNullValue());
    }

    @Step("Проверка, что в ответе присутствует статус 'available'")
    public void checkStatusAvailableOnResponse(Response response) {
        response.then().assertThat().body("available", notNullValue());
    }

    @Step("Создание заказа")
    public Response addOrder(DataAbdBodyOfStoreEndpoint dataAbdBodyOfStoreEndpoint) {
        return given()
                .contentType(ContentType.JSON)
                .body(dataAbdBodyOfStoreEndpoint)
                .when()
                .post(storeOrderEndpoint);
    }

    @Step("Удаление заказа")
    public Response deleteOrder(int orderId) {
        return given()
                .when()
                .delete(storeOrderEndpoint + "/" + orderId);
    }

    @Step("Получение информации о заказе")
    public Response checkOrder(int orderId) {
        return given()
                .when()
                .get(storeOrderEndpoint + "/" + orderId);
    }

    @Step("Проверка, что id заказа равен заданному при создании заказа id")
    public void checkIdInResponse(Response response, int id) {
        response.then().assertThat().body("id", equalTo(id));
    }

    @Step("Проверка, что id заказа равен заданному при создании заказа id")
    public void checkPetIdInResponse(Response response, int petId) {
        response.then().assertThat().body("petId", equalTo(petId));
    }

    @Step("Проверка, что id заказа равен заданному при создании заказа id")
    public void checkQuantityInResponse(Response response, int quantity) {
        response.then().assertThat().body("quantity", equalTo(quantity));
    }

    @Step("Проверка, что id заказа равен заданному при создании заказа id")
    public void checkShipDateInResponse(Response response, String shipDate) {
        response.then().assertThat().body("shipDate", equalTo(shipDate));
    }

    @Step("Проверка, что id заказа равен заданному при создании заказа id")
    public void checkStatusInResponse(Response response, String status) {
        response.then().assertThat().body("status", equalTo(status));
    }

    @Step("Проверка, что id заказа равен заданному при создании заказа id")
    public void checkCompleteInResponse(Response response, boolean complete) {
        response.then().assertThat().body("complete", equalTo(complete));
    }
}
