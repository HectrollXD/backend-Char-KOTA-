package mx.com.hexlink.charkota.api.restful.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.Provider;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderRequest {
	private String name;
	private String rfc;
	private String phone;
	private String email;



	public Provider toProvider(){
		Provider provider = new Provider();

		provider.setName(name.trim().toUpperCase());
		provider.setRfc(rfc.trim().toUpperCase());
		provider.setPhone(phone.trim());
		provider.setEmail(email.trim());

		return provider;
	}
}
