package mx.com.hexlink.charkota.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "catalog_animal_breeds")
@OnDelete(action = OnDeleteAction.CASCADE)
public class AnimalBreed extends CommonData{
	@Column(name = "breed_name", length = 64, unique = true)
	private String breedName;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = AnimalFamily.class)
	@JoinColumn(name = "animal_family_id", nullable = false)
	private AnimalFamily animalFamily;

	@OneToMany(mappedBy = "animalBreed", fetch = FetchType.LAZY, cascade = {
		CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE
	})
	private List<Pet> pets;
}
