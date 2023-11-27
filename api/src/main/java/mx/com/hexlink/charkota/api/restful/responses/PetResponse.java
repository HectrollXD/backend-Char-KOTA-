package mx.com.hexlink.charkota.api.restful.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.api.restful.data.PetData;
import mx.com.hexlink.charkota.data.entities.Pet;
import java.util.List;
import java.util.stream.Collectors;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetResponse {
	private List<PetData> pets;



	public static PetResponse fromPets(List<Pet> pets){
		return new PetResponse(pets.stream().map(PetData::fromPet).collect(Collectors.toList()));
	}
}
