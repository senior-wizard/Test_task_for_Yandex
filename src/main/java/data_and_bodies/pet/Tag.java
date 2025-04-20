package data_and_bodies.pet;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Locale;
import java.util.Random;

@Data
@AllArgsConstructor
public class Tag {

    private int id;
    private String name;

    public Tag() {
    }
//
//    public Tag getRandomTag() {
//        int id = returnId();
//        String name = returnName();
//        return new Tag(id, name);
//    }
//
//    public int returnId() {
//        Random random = new Random();
//        return random.nextInt(5) + 1;
//    }
//
//    public String returnName() {
//        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
//        return fakeValuesService.bothify("????????#######");
//    }
}