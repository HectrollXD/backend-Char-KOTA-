package mx.com.hexlink.charkota.api.restful.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.Provider;
import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMultipleProviderRequest {
	private List<ProviderRequest> providers;



	public List<Provider> toProviders(){
		return providers.stream().map(ProviderRequest::toProvider).toList();
	}
}
