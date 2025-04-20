package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class StatusCodeSteps {
    @Step("Проверка, что код ответа равен 200")
    public void checkStatusCode200(Response response) {
        response.then().statusCode(200);
    }

    @Step("Проверка, что код ответа равен 400")
    public void checkStatusCode400(Response response) {
        response.then().statusCode(400);
    }

    @Step("Проверка, что код ответа равен 404")
    public void checkStatusCode404(Response response) {
        response.then().statusCode(404);
    }

    @Step("Явное ожидания в 1 секунду")
    public void waitMethod(int milliseconds) {
        try {
            Thread.sleep(milliseconds); // 1000 миллисекунд = 1 секунда
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
