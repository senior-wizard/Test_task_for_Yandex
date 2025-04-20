import data_and_bodies.DataAbdBodyOfStoreEndpoint;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import steps.StatusCodeSteps;
import steps.StepsOfStoreEndpoint;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseClassForStore {
    DataAbdBodyOfStoreEndpoint dataAbdBodyOfStoreEndpoint = new DataAbdBodyOfStoreEndpoint();
    StepsOfStoreEndpoint stepsOfStoreEndpoint = new StepsOfStoreEndpoint();
    StatusCodeSteps statusCodeSteps = new StatusCodeSteps();
    DataAbdBodyOfStoreEndpoint randomOrder;

    @BeforeAll
    public void setUpBeforeAll() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @BeforeEach
    public void setUpBeforeEach() {
        randomOrder = dataAbdBodyOfStoreEndpoint.getRandomOrder();
        statusCodeSteps.checkStatusCode200(stepsOfStoreEndpoint.addOrder(randomOrder));
    }

    @AfterEach
    public void tearDownAfterEach() {
        statusCodeSteps.checkStatusCode200(stepsOfStoreEndpoint.deleteOrder(randomOrder.getId()));
    }
}
