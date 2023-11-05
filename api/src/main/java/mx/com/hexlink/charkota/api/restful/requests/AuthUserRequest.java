package mx.com.hexlink.charkota.api.restful.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserRequest {
	private String username;
	private String email;
	private String password;
}
