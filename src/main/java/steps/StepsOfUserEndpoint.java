package steps;

import data_and_bodies.DataAndBodyOfUserEndpoint;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StepsOfUserEndpoint {

    String userEndpoint = "/user";
    String userLoginEndpoint = "/user/login";
    String userLogoutEndpoint = "/user/logout";

    @Step("Создание пользователя")
    public Response createNewUser(DataAndBodyOfUserEndpoint dataAndBodyOfUserEndpoint) {
        return given()
                .contentType(ContentType.JSON)
                .body(dataAndBodyOfUserEndpoint)
                .when()
                .post(userEndpoint);
    }

    @Step("Удаление пользователя")
    public Response deleteUser(String username) {
        return given()
                .when()
                .delete(userEndpoint + "/" + username);
    }

    @Step("Получение информации о пользователе")
    public Response checkUser(String username) {
        return given()
                .when()
                .get(userEndpoint + "/" + username);
    }

    @Step("Обновление информации о пользователе")
    public Response updateUser(DataAndBodyOfUserEndpoint dataAndBodyOfUserEndpoint, String username) {
        return given()
                .contentType(ContentType.JSON)
                .body(dataAndBodyOfUserEndpoint)
                .when()
                .put(userEndpoint + "/" + username);
    }

    @Step("Авторизация пользователя")
    public Response loginUser(String username, String password) {
        return given()
                .when()
                .get(userLoginEndpoint + "?username=" + username + "&password=" + password);
    }

    @Step("Выход из аккаунта пользователя")
    public Response logoutUser() {
        return given()
                .when()
                .get(userLogoutEndpoint);
    }

    @Step("Проверка, что id пользователя равен заданному при создании пользователя id")
    public void checkIdInResponse(Response response, int id) {
        response.then().assertThat().body("id", equalTo(id));
    }

    @Step("Проверка, что username пользователя равен заданному при создании пользователя username")
    public void checkUsernameInResponse(Response response, String username) {
        response.then().assertThat().body("username", equalTo(username));
    }

    @Step("Проверка, что firstName пользователя равен заданному при создании пользователя firstName")
    public void checkFirstNameInResponse(Response response, String firstName) {
        response.then().assertThat().body("firstName", equalTo(firstName));
    }

    @Step("Проверка, что lastName пользователя равен заданному при создании пользователя lastName")
    public void checkLastNameInResponse(Response response, String lastName) {
        response.then().assertThat().body("lastName", equalTo(lastName));
    }

    @Step("Проверка, что email пользователя равен заданному при создании пользователя email")
    public void checkEmailInResponse(Response response, String email) {
        response.then().assertThat().body("email", equalTo(email));
    }

    @Step("Проверка, что password пользователя равен заданному при создании пользователя password")
    public void checkPasswordInResponse(Response response, String password) {
        response.then().assertThat().body("password", equalTo(password));
    }

    @Step("Проверка, что phone пользователя равен заданному при создании пользователя phone")
    public void checkPhoneInResponse(Response response, String phone) {
        response.then().assertThat().body("phone", equalTo(phone));
    }

    @Step("Проверка, что userStatus пользователя равен заданному при создании пользователя userStatus")
    public void checkUserStatusInResponse(Response response, int userStatus) {
        response.then().assertThat().body("userStatus", equalTo(userStatus));
    }
}
