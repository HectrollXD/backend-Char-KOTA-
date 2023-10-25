package mx.com.hexlink.charkota.data.entities.dao;

import mx.com.hexlink.charkota.data.entities.Pet;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;



public interface PetDao extends CrudRepository<Pet, UUID> {
}
