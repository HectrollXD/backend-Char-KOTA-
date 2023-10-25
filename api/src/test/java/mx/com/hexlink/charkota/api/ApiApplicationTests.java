package mx.com.hexlink.charkota.api;

import mx.com.hexlink.charkota.data.entities.AnimalBreed;
import mx.com.hexlink.charkota.data.entities.AnimalFamily;
import mx.com.hexlink.charkota.data.services.AnimalBreedService;
import mx.com.hexlink.charkota.data.services.AnimalFamilyService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.assertj.core.api.Assertions.*;




@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApiApplicationTests {
	@Autowired
	AnimalFamilyService animalFamilyService;
	@Autowired
	AnimalBreedService animalBreedService;
	private static AnimalFamily animalFamily;



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

	@Test
	@Order(2)
	void createNewAnimalBreed() {
		assertThat(animalFamily).isNotNull();

		AnimalBreed animalFamilyToSave = new AnimalBreed(
				"PASTOR ALEMAN",
				animalFamily,
				null
		);

		AnimalBreed animalBreedSaved = animalBreedService.saveData(animalFamilyToSave);

		assertThat(animalBreedSaved).isNotNull();
		assertThat(animalBreedSaved.getId()).isNotNull();
		assertThat(animalBreedSaved.getBreedName()).isEqualTo("PASTOR ALEMAN");
	}
}
