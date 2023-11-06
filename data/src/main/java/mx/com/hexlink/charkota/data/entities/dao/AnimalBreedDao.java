package mx.com.hexlink.charkota.data.entities.dao;

import mx.com.hexlink.charkota.data.entities.AnimalBreed;
import mx.com.hexlink.charkota.data.entities.AnimalFamily;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.UUID;



public interface AnimalBreedDao extends CrudRepository<AnimalBreed, UUID> {
	List<AnimalBreed> findAllByAnimalFamily(AnimalFamily animalFamily);
}
