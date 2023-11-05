package mx.com.hexlink.charkota.api.restful.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class IsAuthUserResponse extends AuthUserResponse {
	private Boolean authenticaded;
}
