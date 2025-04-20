package steps;

import data_and_bodies.pet.Category;
import data_and_bodies.pet.DataAndBodyOfPetEndpoint;
import data_and_bodies.pet.Tag;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class StepsOfPetEndpoint {

    String petEndpoint = "/pet";
    String petFindByStatusEndpoint = "/pet/findByStatus";

    @Step("Добавление питомца в магазин")
    public Response addPet(DataAndBodyOfPetEndpoint dataAndBodyOfPetEndpoint) {
        return given()
                .contentType(ContentType.JSON)
                .body(dataAndBodyOfPetEndpoint)
                .when()
                .post(petEndpoint);
    }

    @Step("Удаление питомца из магазина")
    public Response deletePet(int petId) {
        return given()
                .when()
                .delete(petEndpoint + "/" + petId);
    }

    @Step("Получение информации о питомце")
    public Response checkPet(int petId) {
        return given()
                .when()
                .get(petEndpoint + "/" + petId);
    }

    @Step("Поиск питомцев по статусу")
    public Response checkPetsByStatus (String status) {
        return given()
                .when()
                .get(petFindByStatusEndpoint + "?status=" + status);
    }

    @Step("Проверка, что id питомца равен заданному при создании питомца id")
    public void checkIdInResponse(Response response, int id) {
        response.then().assertThat().body("id", equalTo(id));
    }

    @Step("Проверка, что category питомца равен заданному при создании питомца category")
    public void checkCategoryInResponse(Response response, Category category) {
        response.then().assertThat().body("category", equalTo(category));
    }

    @Step("Проверка, что name питомца равен заданному при создании питомца name")
    public void checkNameInResponse(Response response, String name) {
        response.then().assertThat().body("name", equalTo(name));
    }

    @Step("Проверка, что photoUrls питомца равен заданному при создании питомца photoUrls")
    public void checkPhotoUrlsInResponse(Response response, String[] photoUrls) {
        response.then().assertThat().body("photoUrls", equalTo(photoUrls));
    }

    @Step("Проверка, что tags питомца равен заданному при создании питомца tags")
    public void checkTagsInResponse(Response response, Tag[] tags) {
        response.then().assertThat().body("tags", equalTo(tags));
    }

    @Step("Проверка, что status питомца равен заданному при создании питомца status")
    public void checkStatusInResponse(Response response, String status) {
        response.then().assertThat().body("status", equalTo(status));
    }
}
