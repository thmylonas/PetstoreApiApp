package _a.entities;

import com.thomasmylonas.petstore_api_web_app.entities.Pet;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity(name = "Category")
@Table(name = "Category") // Pet_Category
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "ID_SEQUENCE_CATEGORY", allocationSize = 1)
    @Column(name = "Id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "CATEGORY_NAME", nullable = false, length = 25)
    private String name;

    // Mappings - OneToMany
    @OneToMany(mappedBy = "category", cascade = CascadeType.REFRESH)
    private List<Pet> pets;

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
