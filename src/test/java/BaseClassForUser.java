import data_and_bodies.DataAndBodyOfUserEndpoint;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import steps.StatusCodeSteps;
import steps.StepsOfUserEndpoint;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseClassForUser {

    StepsOfUserEndpoint stepsOfUserEndpoint = new StepsOfUserEndpoint();
    DataAndBodyOfUserEndpoint dataAndBodyOfUserEndpoint = new DataAndBodyOfUserEndpoint();
    StatusCodeSteps statusCodeSteps = new StatusCodeSteps();
    DataAndBodyOfUserEndpoint randomUser;
    DataAndBodyOfUserEndpoint updatedUser;
    DataAndBodyOfUserEndpoint hardCodeUser;


    @BeforeAll
    public void setUpBeforeAll() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @BeforeEach
    public void setUpBeforeEach() {
        randomUser = dataAndBodyOfUserEndpoint.getRandomUser();
        statusCodeSteps.checkStatusCode200(stepsOfUserEndpoint.createNewUser(randomUser));
    }

    @AfterEach
    public void tearDownAfterEach() {
        if (stepsOfUserEndpoint.deleteUser(randomUser.getUsername()).statusCode() != 200) {
            if (stepsOfUserEndpoint.deleteUser(updatedUser.getUsername()).statusCode() != 200) {
                statusCodeSteps.checkStatusCode200(stepsOfUserEndpoint.deleteUser(hardCodeUser.getUsername()));
            }
        }
    }
}