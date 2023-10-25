package mx.com.hexlink.charkota.data.entities.dao;

import mx.com.hexlink.charkota.data.entities.AnimalFamily;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;



public interface AnimalFamilyDao extends CrudRepository<AnimalFamily, UUID> {
}
