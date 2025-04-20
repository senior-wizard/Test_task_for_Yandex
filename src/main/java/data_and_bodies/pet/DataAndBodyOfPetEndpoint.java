package data_and_bodies.pet;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Locale;
import java.util.Random;

@Data
@AllArgsConstructor
public class DataAndBodyOfPetEndpoint {

    private int id;
    private Category category;
    private String name;
    private String[] photoUrls;
    private Tag[] tags;
    private String status;

    public DataAndBodyOfPetEndpoint() {
    }

    public DataAndBodyOfPetEndpoint getRandomPet() {
        int id = returnId();
        Category category = returnCategory();
        String nane = returnName();
        String[] photoUrls = returnPhotoUrls();
        Tag[] tag = returnTags();
        String status = returnStatus();
        return new DataAndBodyOfPetEndpoint(id, category, nane, photoUrls,tag, status);
    }

    public int returnId() {
        Random random = new Random();
        return random.nextInt(20001 - 10000) + 10000;
    }

    public Category returnCategory() {
        return new Category(returnId(), returnName());
    }

    public String returnName() {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
        return fakeValuesService.bothify("????????#######");
    }

    public String[] returnPhotoUrls() {
        return new String[] {
            "https://images.unsplash.com/photo-1543852786-1cf6624b9987",
            "https://images.unsplash.com/photo-1518791841217-8f162f1e1131",
            "https://images.unsplash.com/photo-1592194996308-7b43878e84a6"
        };
    }

    public Tag[] returnTags() {
        return new Tag[] {
            new Tag(returnId(), returnName()),
            new Tag(returnId(), returnName()),
            new Tag(returnId(), returnName())
        };
    }

    public String returnStatus() {
        String[] statuses = new String[] {
                "available",
                "pending",
                "sold"
        };
        Random random = new Random();
        int randomIndex = random.nextInt(statuses.length);
        return statuses[randomIndex];
    }

    public String returnGoodStringToAssert(String string) {
        return string.replace("Category", "").replace("Tag", "").replace("(", "[").replace(")", "]").replace("=", ":");
    }
}