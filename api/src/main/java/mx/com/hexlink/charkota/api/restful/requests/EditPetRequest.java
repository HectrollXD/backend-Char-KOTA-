package mx.com.hexlink.charkota.api.restful.requests;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditPetRequest {
	private String name;
	private LocalDate birthDate;
	private List<PetVaccineRequest> vaccines;
}
