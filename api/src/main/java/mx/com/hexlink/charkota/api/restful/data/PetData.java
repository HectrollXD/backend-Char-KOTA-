package mx.com.hexlink.charkota.api.restful.data;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.AnimalBreed;
import mx.com.hexlink.charkota.data.entities.Owner;
import mx.com.hexlink.charkota.data.entities.PetVaccine;
import java.time.LocalDate;
import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetData {
	private String name;
	private LocalDate birthDate;
	private AnimalBreed animalBreed;
	private Owner owner;
	private List<PetVaccine> vaccines;
}
