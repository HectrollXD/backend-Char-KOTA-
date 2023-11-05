package mx.com.hexlink.charkota.api.restful.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.api.restful.data.PersonData;
import mx.com.hexlink.charkota.data.entities.User;
import mx.com.hexlink.charkota.data.entities.enums.UserType;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserResponse {
	private String username;
	private String email;
	private UserType userType;
	private PersonData personData;
	private String token;
	private String refreshToken;


	
	public static AuthUserResponse fromUser(User user){
		return new AuthUserResponse(
			user.getUsername().trim(),
			user.getEmail().trim(),
			user.getUserType(),
			PersonData.fromPerson(user.getPerson()),
			// TODO: Realizar el JWT Token con los datos del usuario para la autenticación por JWT
			"", ""
		);
	}
}
