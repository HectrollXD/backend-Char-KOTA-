package mx.com.hexlink.charkota.api.restful.requests;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMultipleAnimalFamilyRequest {
	List<AnimalFamilyRequest> families;
}
