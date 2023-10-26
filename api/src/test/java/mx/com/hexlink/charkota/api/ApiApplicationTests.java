package mx.com.hexlink.charkota.api;

import mx.com.hexlink.charkota.data.entities.*;
import mx.com.hexlink.charkota.data.entities.enums.UserType;
import mx.com.hexlink.charkota.data.services.AnimalBreedService;
import mx.com.hexlink.charkota.data.services.AnimalFamilyService;
import mx.com.hexlink.charkota.data.services.PetService;
import mx.com.hexlink.charkota.data.services.UserService;
import mx.com.hexlink.charkota.data.utils.PasswordUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.*;



@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApiApplicationTests {
	@Autowired
	UserService userService;
	@Autowired
	AnimalFamilyService animalFamilyService;
	@Autowired
	AnimalBreedService animalBreedService;
	@Autowired
	PetService petService;
	static User user;
	private static AnimalFamily animalFamily;
	private static AnimalBreed animalBreed;
	// Person
	static final String name = "HECTOR GEOVANNY";
	static final String lastname = "RODRIGUEZ MARTINEZ";
	static final String phone = "3333333333";
	// User
	static final String username = "HectrollXD";
	static final String email = "test@mail.com";
	static final String password = "thebestpaswordever1234";




	// Create persons and users
	@Test
	@Order(0)
	public void createUserAndPerson() throws NoSuchAlgorithmException {
		Person personToSave = new Person();
		User userToSave = new User();

		personToSave.setName(name);
		personToSave.setLastname(lastname);
		personToSave.setPhone(phone);
		personToSave.setEmail(email);
		userToSave.setUsername(username);
		userToSave.setEmail(email);
		userToSave.setPassword(PasswordUtil.encrypt(password));
		userToSave.setUserType(UserType.ADMINISTRATOR);
		userToSave.setPerson(personToSave);

		user = userService.saveData(userToSave);

		assertThat(user).isNotNull();
		assertThat(user.getUsername()).isEqualTo(username);
		assertThat(user.getEmail()).isEqualTo(email);
		assertThat(user.getUserType()).isEqualTo(UserType.ADMINISTRATOR);
		assertThat(user.getPerson()).isNotNull();
		assertThat(user.getPerson().getName()).isEqualTo(name);
		assertThat(user.getPerson().getLastname()).isEqualTo(lastname);
		assertThat(user.getPerson().getPhone()).isEqualTo(lastname);
		assertThat(user.getPerson().getEmail()).isEqualTo(email);
	}

	// Create animal family
	@Test
	@Order(1)
	void createNewAnimalFamily() {
		AnimalFamily animalFamilyToSave = new AnimalFamily(
				"CANINO",
				"familia de animal canino perteneciente a perros, lobos, coyotes, etc.",
				null
		);

		animalFamily = animalFamilyService.saveData(animalFamilyToSave);

		assertThat(animalFamily).isNotNull();
		assertThat(animalFamily.getId()).isNotNull();
		assertThat(animalFamily.getFamilyName()).isEqualTo("CANINO");
	}

	// Create animal breed
	@Test
	@Order(2)
	void createNewAnimalBreed() {
		assertThat(animalFamily).isNotNull();

		AnimalBreed animalFamilyToSave = new AnimalBreed(
				"LABRADOR",
				animalFamily,
				null
		);

		animalBreed = animalBreedService.saveData(animalFamilyToSave);

		assertThat(animalBreed).isNotNull();
		assertThat(animalBreed.getId()).isNotNull();
		assertThat(animalBreed.getBreedName()).isEqualTo("LABRADOR");
	}

	// Create pet and owner
	@Test
	@Order(3)
	public void createPetAndOwn(){
		Pet petToAdd = new Pet();
		Owner ownerToAdd = new Owner();
		List<PetVaccine> vaccines = List.of(
				new PetVaccine("PARBOVIRUS", LocalDate.now(), 5d, null),
				new PetVaccine("ANTIRRABICA", LocalDate.now(), 3d, null)
		);

		ownerToAdd.setName("MARIA JOVITA");
		ownerToAdd.setLastname("MORALES ARRIAGA");
		ownerToAdd.setPhone("3312345678");
		ownerToAdd.setEmail(email);
		petToAdd.setName("BONI");
		petToAdd.setAnimalBreed(animalBreed);
		petToAdd.setVaccines(vaccines);
		petToAdd.setBirthDate(LocalDate.now());
		petToAdd.setOwner(ownerToAdd);

		Pet pet = petService.saveData(petToAdd);

		assertThat(pet).isNotNull();
		assertThat(pet.getOwner()).isNotNull();
		assertThat(pet.getVaccines()).isNotNull();
	}
}
