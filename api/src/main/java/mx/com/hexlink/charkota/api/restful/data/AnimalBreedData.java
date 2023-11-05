package mx.com.hexlink.charkota.api.restful.data;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.AnimalBreed;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalBreedData {
	private UUID id;
	private String breedName;
	private AnimalFamilyData family;



	public static AnimalBreedData fromAnimalBreed(AnimalBreed animalBreed){
		return new AnimalBreedData(
			animalBreed.getId(),
			animalBreed.getBreedName(),
			AnimalFamilyData.fromAnimalFamily(animalBreed.getAnimalFamily())
		);
	}
}
