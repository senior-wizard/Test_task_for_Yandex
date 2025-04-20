import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PetTest extends BaseClassForPet {

    @Test
    @DisplayName("Получение информации о питомце")
    @Description("Проверка, что при получении питомца код ответа 200 и все данные, используемые при создании питомца совпадают в созданном питомце")
    void checkPetTest() {
        statusCodeSteps.waitMethod(3000);
        Response response = stepsOfPetEndpoint.checkPet(randomPet.getId());
        String responseCategory = response.jsonPath().getString("category");
        String responsePhotoUrls = response.jsonPath().getString("photoUrls");
        String responseTags = response.jsonPath().getString("tags");
        statusCodeSteps.checkStatusCode200(response);
        assertAll(
                () -> stepsOfPetEndpoint.checkIdInResponse(response, randomPet.getId()),
                () -> assertEquals(dataAndBodyOfPetEndpoint.returnGoodStringToAssert(randomPet.getCategory().toString()), responseCategory),
                () -> stepsOfPetEndpoint.checkNameInResponse(response, randomPet.getName()),
                () -> assertEquals(Arrays.toString(randomPet.getPhotoUrls()), responsePhotoUrls),
                () -> assertEquals(dataAndBodyOfPetEndpoint.returnGoodStringToAssert(Arrays.toString(randomPet.getTags())), responseTags),
                () -> stepsOfPetEndpoint.checkStatusInResponse(response, randomPet.getStatus())
        );
    }

    @Test
    @DisplayName("Получение списка питомцев по статусу")
    @Description("Проверка, что при получении списка питомцев по статусу код ответа 200 и созданный питомец попал в список")
    void checkPetsByStatusTest() {
        Response response = stepsOfPetEndpoint.checkPetsByStatus(randomPet.getStatus());
        statusCodeSteps.checkStatusCode200(response);
        assertTrue(response.jsonPath().getString("id").contains(String.valueOf(randomPet.getId())));
    }
}
