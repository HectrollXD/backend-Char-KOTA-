package mx.com.hexlink.charkota.api.restful.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.Owner;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerRequest {
	private String name;
	private String lastname;
	private String phone;
	private String email;
	private String address;



	public Owner toOwner(){
		Owner owner = new Owner();

		owner.setName(name.trim().toUpperCase());
		owner.setLastname(lastname.trim().toUpperCase());
		owner.setPhone(phone);
		owner.setEmail(email);
		owner.setAddress(address.trim().toUpperCase());

		return owner;
	}
}
