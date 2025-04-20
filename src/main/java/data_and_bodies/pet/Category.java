package data_and_bodies.pet;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Locale;
import java.util.Random;

@Data
@AllArgsConstructor
public class Category {

    private int id;
    private String name;

    public Category() {
    }
}
