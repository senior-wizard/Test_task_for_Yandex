package data_and_bodies;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

@Getter
@Data
@AllArgsConstructor
public class DataAbdBodyOfStoreEndpoint {
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private Boolean complete;

    public DataAbdBodyOfStoreEndpoint() {
    }

    public DataAbdBodyOfStoreEndpoint getRandomOrder() {
        int id = returnIdOrPetIdOrQuantity();
        int petId = returnIdOrPetIdOrQuantity();
        int quantity = returnIdOrPetIdOrQuantity();
        String shipDate = returnShipDate();
        String status = returnStatus();
        boolean complete = returnComplete();
        return new DataAbdBodyOfStoreEndpoint(id, petId, quantity, shipDate, status, complete);
    }

    public int returnIdOrPetIdOrQuantity() {
        Random random = new Random();
        return random.nextInt(1000) + 1;
    }

    public String returnShipDate() {
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(returnIdOrPetIdOrQuantity());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return localDateTime.format(formatter).replace(" ", "T");
    }

    public String returnStatus() {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
        return fakeValuesService.bothify("????????#######");
    }

    public boolean returnComplete() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
