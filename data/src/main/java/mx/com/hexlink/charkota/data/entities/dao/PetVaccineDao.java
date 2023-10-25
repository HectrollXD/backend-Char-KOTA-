package mx.com.hexlink.charkota.data.entities.dao;

import mx.com.hexlink.charkota.data.entities.PetVaccine;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;



public interface PetVaccineDao extends CrudRepository<PetVaccine, UUID> {
}
