package mx.com.hexlink.charkota.data.entities.dao;

import mx.com.hexlink.charkota.data.entities.Person;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;



public interface PersonDao extends CrudRepository<Person, UUID> {
}
