package mx.com.hexlink.charkota.api.restful.requests;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.AnimalBreed;
import mx.com.hexlink.charkota.data.entities.AnimalFamily;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalBreedRequest {
	private String breedName;
	private UUID animalFamilyId;



	public AnimalBreed toAnimalBreed(AnimalFamily animalFamily){
		AnimalBreed animalBreed = new AnimalBreed();

		animalBreed.setBreedName(this.breedName);
		animalBreed.setAnimalFamily(animalFamily);

		return animalBreed;
	}
}
