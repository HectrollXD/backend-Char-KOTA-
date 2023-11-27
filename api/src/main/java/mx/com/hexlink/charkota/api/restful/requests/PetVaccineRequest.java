package mx.com.hexlink.charkota.api.restful.requests;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.Pet;
import mx.com.hexlink.charkota.data.entities.PetVaccine;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetVaccineRequest {
	private String vaccineName;
	private LocalDate applicationDate;
	private Double dose;



	public static PetVaccine toPetVaccine(PetVaccineRequest request, Pet pet) {
		PetVaccine petVaccine = new PetVaccine();

		petVaccine.setVaccineName(request.getVaccineName().trim().toUpperCase());
		petVaccine.setApplicationDate(request.getApplicationDate());
		petVaccine.setDose(request.getDose());
		petVaccine.setPet(pet);

		return petVaccine;
	}
}
