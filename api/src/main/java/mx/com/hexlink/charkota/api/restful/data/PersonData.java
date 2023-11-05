package mx.com.hexlink.charkota.api.restful.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.Person;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonData {
    private String name;
    private String lastname;
    private String phone;
    private String email;



	public static PersonData fromPerson(Person person){
		return new PersonData(
			person.getName().trim().toUpperCase(),
			person.getLastname().trim().toUpperCase(),
			person.getPhone().trim(),
			person.getEmail().trim()
		);
	}
}
