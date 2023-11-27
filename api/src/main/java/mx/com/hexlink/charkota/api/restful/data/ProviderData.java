package mx.com.hexlink.charkota.api.restful.data;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.Provider;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderData {
	private UUID id;
	private String name;
	private String rfc;
	private String phone;
	private String email;



	public static ProviderData fromProvider(Provider provider){
		return new ProviderData(
			provider.getId(),
			provider.getName().trim().toUpperCase(),
			provider.getRfc().trim().toUpperCase(),
			provider.getPhone().trim(),
			provider.getEmail().trim()
		);
	}
}
