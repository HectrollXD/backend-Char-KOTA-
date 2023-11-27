package mx.com.hexlink.charkota.api.restful.data;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.User;
import mx.com.hexlink.charkota.data.entities.enums.UserType;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
	private UUID id;
	private String email;
	private String username;
	private PersonData person;
	private UserType userType;



	public static UserData fromUser(User user){
		return new UserData(
			user.getId(),
			user.getEmail(),
			user.getUsername(),
			PersonData.fromPerson(user.getPerson()),
			user.getUserType()
		);
	}
}
