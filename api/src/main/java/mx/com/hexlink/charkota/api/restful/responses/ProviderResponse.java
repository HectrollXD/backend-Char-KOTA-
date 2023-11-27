package mx.com.hexlink.charkota.api.restful.responses;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.api.restful.data.ProviderData;
import mx.com.hexlink.charkota.data.entities.Provider;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderResponse {
	private List<ProviderData> providers;



	public static ProviderResponse fromProviders(List<Provider> providers){
		return new ProviderResponse(
			providers.stream().map(ProviderData::fromProvider).toList()
		);
	}
}
