package mx.com.hexlink.charkota.api.restful.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenRequest {
	private String refreshToken;
}
