package mx.com.hexlink.charkota.api.restful.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.Owner;
import java.util.UUID;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerData {
	private UUID id;
	private String name;
	private String lastname;
	private String phone;
	private String email;
	private String address;



	public static OwnerData fromOwner(Owner owner){
		return new OwnerData(
			owner.getId(),
			owner.getName(),
			owner.getLastname(),
			owner.getPhone(),
			owner.getEmail(),
			owner.getAddress()
		);
	}
}
