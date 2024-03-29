package mx.com.hexlink.charkota.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.List;



@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(
	name = "catalog_animal_families",
	indexes = @Index(name = "family_name_index", unique = true, columnList = "family_name")
)
@OnDelete(action = OnDeleteAction.CASCADE)
public class AnimalFamily extends CommonData {
	@Column(name = "family_name", length = 32, unique = true, nullable = false)
	private String familyName;

	@Column(name = "description", length = 128)
	private String description;

	@OneToMany(mappedBy = "animalFamily", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	private List<AnimalBreed> animalBreeds;
}
