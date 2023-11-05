package mx.com.hexlink.charkota.api.restful.requests;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMultipleAnimalBreedRequest {
	private List<AnimalBreedRequest> animalBreedsList = new ArrayList<>();
}
