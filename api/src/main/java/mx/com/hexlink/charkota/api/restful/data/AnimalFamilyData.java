package mx.com.hexlink.charkota.api.restful.data;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.AnimalFamily;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalFamilyData {
	private UUID id;
	private String name;
	private String description;



	public static AnimalFamilyData fromAnimalFamily(AnimalFamily animalFamily){
		return new AnimalFamilyData(
			animalFamily.getId(),
			animalFamily.getFamilyName(),
			animalFamily.getDescription()
		);
	}
}
