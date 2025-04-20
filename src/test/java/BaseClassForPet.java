import data_and_bodies.pet.DataAndBodyOfPetEndpoint;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import steps.StatusCodeSteps;
import steps.StepsOfPetEndpoint;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseClassForPet {

    DataAndBodyOfPetEndpoint dataAndBodyOfPetEndpoint = new DataAndBodyOfPetEndpoint();
    StepsOfPetEndpoint stepsOfPetEndpoint = new StepsOfPetEndpoint();
    StatusCodeSteps statusCodeSteps = new StatusCodeSteps();
    DataAndBodyOfPetEndpoint randomPet;

    @BeforeAll
    public void setUpBeforeAll() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @BeforeEach
    public void setUpBeforeEach() {
        randomPet = dataAndBodyOfPetEndpoint.getRandomPet();
        statusCodeSteps.checkStatusCode200(stepsOfPetEndpoint.addPet(randomPet));
    }

    @AfterEach
    public void tearDownAfterEach() {
        statusCodeSteps.checkStatusCode200(stepsOfPetEndpoint.deletePet(randomPet.getId()));
    }
}
