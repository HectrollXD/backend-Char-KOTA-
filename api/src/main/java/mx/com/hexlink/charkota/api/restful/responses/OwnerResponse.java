package mx.com.hexlink.charkota.api.restful.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.api.restful.data.OwnerData;
import mx.com.hexlink.charkota.data.entities.Owner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerResponse {
	private List<OwnerData> owners;



	public static OwnerResponse fromOwners(List<Owner> owrs){
		return new OwnerResponse(
			owrs.stream().map(OwnerData::fromOwner).collect(Collectors.toList())
		);
	}
}
