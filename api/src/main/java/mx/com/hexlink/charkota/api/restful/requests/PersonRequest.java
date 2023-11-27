package mx.com.hexlink.charkota.api.restful.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.Person;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequest {
    private String name;
    private String lastname;
    private String phone;
    private String email;



	public Person toPerson(){
		Person person = new Person();

		person.setName(name.trim().toUpperCase());
		person.setLastname(lastname.trim().toUpperCase());
		person.setPhone(phone);
		person.setEmail(email.trim());

		return person;
	}
}
