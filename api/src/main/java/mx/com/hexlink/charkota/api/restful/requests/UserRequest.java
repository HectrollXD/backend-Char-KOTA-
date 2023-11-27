package mx.com.hexlink.charkota.api.restful.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.User;
import mx.com.hexlink.charkota.data.entities.enums.UserType;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
	private String email;
	private String username;
	private String password;
	private PersonRequest personData;
	private UserType userType;



	public User toUser(){
		User user = new User();

		user.setEmail(email.trim());
		user.setUsername(username.trim());
		user.setPassword(password.trim());
		user.setPerson(personData.toPerson());
		user.setUserType(userType);

		return user;
	}
}
