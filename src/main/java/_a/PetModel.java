package _a;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.thomasmylonas.petstore_api_web_app.entities.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonPropertyOrder({"id", "category", "name", "photoUrls", "tags", "status"})
public class PetModel {

    private Long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;

    public PetModel(Pet pet) {
        this.id = pet.getId();
        this.name = pet.getName();
//        this.category = pet.getCategory();
//        this.tags = pet.getTags();
//        photoUrls = new ArrayList<>();
//        for (int i = 0; i < pet.getPhotoUrls().size(); i++) {
//            photoUrls.add(pet.getPhotoUrls().get(i).getName());
//        }
    }
}
