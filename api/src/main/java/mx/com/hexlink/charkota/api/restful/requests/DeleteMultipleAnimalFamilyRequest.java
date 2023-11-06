package mx.com.hexlink.charkota.api.restful.requests;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteMultipleAnimalFamilyRequest {
	List<UUID> familiesId;
}
