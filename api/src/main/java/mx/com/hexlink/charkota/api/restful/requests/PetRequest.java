package mx.com.hexlink.charkota.api.restful.requests;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.AnimalBreed;
import mx.com.hexlink.charkota.data.entities.Owner;
import mx.com.hexlink.charkota.data.entities.Pet;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetRequest {
	private String name;
	private LocalDate birthDate;
	private UUID animalBreedId;
	private UUID ownerId;
	private List<PetVaccineRequest> vaccines;



	public Pet toPet(Owner owner, AnimalBreed animalBreed){
		Pet pet = new Pet();

		pet.setName(name.trim().toUpperCase());
		pet.setBirthDate(birthDate);
		pet.setAnimalBreed(animalBreed);
		pet.setOwner(owner);

		return pet;
	}
}
