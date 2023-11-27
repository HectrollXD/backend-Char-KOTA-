package mx.com.hexlink.charkota.api.restful.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.Pet;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetData {
	private UUID id;
	private String name;
	private LocalDate birthDate;
	private AnimalBreedData animalBreed;
	private OwnerData owner;
	private List<PetVaccineData> vaccines;



	public static PetData fromPet(Pet pet){
		return new PetData(
			pet.getId(),
			pet.getName(),
			pet.getBirthDate(),
			AnimalBreedData.fromAnimalBreed(pet.getAnimalBreed()),
			OwnerData.fromOwner(pet.getOwner()),
			pet.getVaccines().stream().map(PetVaccineData::fromPetVaccine).toList()
		);
	}
}
