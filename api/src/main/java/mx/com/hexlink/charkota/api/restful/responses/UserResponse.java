package mx.com.hexlink.charkota.api.restful.responses;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.api.restful.data.UserData;
import mx.com.hexlink.charkota.data.entities.User;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	private List<UserData> users;



	public static UserResponse fromUsers(List<User> users){
		return new UserResponse(
			users.stream().map(UserData::fromUser).toList()
		);
	}
}
