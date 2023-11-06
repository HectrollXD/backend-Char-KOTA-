package mx.com.hexlink.charkota.api.restful.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.AnimalFamily;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalFamilyRequest {
	private String familyName;
	private String description;



	public AnimalFamily toAnimalFamily(){
		AnimalFamily animalFamily = new AnimalFamily();

		animalFamily.setFamilyName(this.familyName.trim().toUpperCase());
		animalFamily.setDescription(this.description.trim().toUpperCase());

		return animalFamily;
	}
}
