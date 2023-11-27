package mx.com.hexlink.charkota.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "data_pets")
@EqualsAndHashCode(callSuper = true)
public class Pet extends CommonData{
	@Column(name = "name")
	private String name;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, targetEntity = AnimalBreed.class)
	@JoinColumn(name = "animal_breed_id", nullable = false)
	private AnimalBreed animalBreed;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, targetEntity = Owner.class)
	@JoinColumn(name = "owner_id", nullable = false)
	private Owner owner;

	@OneToMany(
		mappedBy = "pet",
		cascade = {CascadeType.MERGE},
		fetch = FetchType.EAGER
	)
	private List<PetVaccine> vaccines = new ArrayList<>();

	@OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ConsultDate> consultDates = new ArrayList<>();
}
