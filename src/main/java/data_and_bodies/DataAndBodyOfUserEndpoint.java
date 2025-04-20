package data_and_bodies;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Locale;
import java.util.Random;

@Data
@AllArgsConstructor
public class DataAndBodyOfUserEndpoint {

    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    public DataAndBodyOfUserEndpoint() {
    }

    public DataAndBodyOfUserEndpoint getRandomUser() {
        int id = returnIdOrUserStatus();
        String username = username();
        String firstName = firstName();
        String lastName = lastName();
        String email = email();
        String password = password();
        String phone = phone();
        int userStatus = returnIdOrUserStatus();
        return new DataAndBodyOfUserEndpoint(id, username, firstName, lastName, email, password, phone, userStatus);
    }

    public int returnIdOrUserStatus() {
        Random random = new Random();
        return random.nextInt(5) + 1;
    }

    public String username() {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
        return fakeValuesService.bothify("????????#######");
    }

    public String firstName() {
        Faker faker = new Faker(new Locale("en-US"));
        return faker.name().firstName();
    }

    public String lastName() {
        Faker faker = new Faker(new Locale("en-US"));
        return faker.name().lastName();
    }

    public String email() {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
        return fakeValuesService.bothify("?#?#?#?#?#@gmail.com");
    }

    public String password() {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
        return fakeValuesService.bothify("?#?#?#?#?##?#?#?##?");
    }

    public String phone() {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
        return fakeValuesService.numerify("8800########");
    }
}
