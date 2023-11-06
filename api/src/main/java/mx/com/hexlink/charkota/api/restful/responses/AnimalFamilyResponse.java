package mx.com.hexlink.charkota.api.restful.responses;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.api.restful.data.AnimalFamilyData;
import mx.com.hexlink.charkota.data.entities.AnimalFamily;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalFamilyResponse {
	List<AnimalFamilyData> families = new ArrayList<>();



	public static AnimalFamilyResponse fromAnimalFamily(List<AnimalFamily> animalFamilies){
		AnimalFamilyResponse response = new AnimalFamilyResponse();

		response.families.addAll(animalFamilies.stream().map(
			AnimalFamilyData::fromAnimalFamily
		).collect(Collectors.toList()));

		return response;
	}
}
