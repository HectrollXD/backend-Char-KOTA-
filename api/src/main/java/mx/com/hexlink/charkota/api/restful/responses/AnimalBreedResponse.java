package mx.com.hexlink.charkota.api.restful.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.api.restful.data.AnimalBreedData;
import mx.com.hexlink.charkota.data.entities.AnimalBreed;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalBreedResponse {
	List<AnimalBreedData> animals = new ArrayList<>();


	
	public static AnimalBreedResponse fromAnimalBreedsList(List<AnimalBreed> animalBreeds){
		AnimalBreedResponse response = new AnimalBreedResponse();

		response.animals.addAll(animalBreeds.stream().map(
			AnimalBreedData::fromAnimalBreed
		).collect(Collectors.toList()));

		return response;
	}
}
