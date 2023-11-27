package mx.com.hexlink.charkota.api.restful.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.PetVaccine;
import java.time.LocalDate;
import java.util.UUID;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetVaccineData {
	private UUID id;
	private String vaccineName;
	private LocalDate applicationDate;
	private Double dose;



	public static PetVaccineData fromPetVaccine(PetVaccine vaccine){
		return new PetVaccineData(
			vaccine.getId(),
			vaccine.getVaccineName().trim().toUpperCase(),
			vaccine.getApplicationDate(),
			vaccine.getDose()
		);
	}
}
