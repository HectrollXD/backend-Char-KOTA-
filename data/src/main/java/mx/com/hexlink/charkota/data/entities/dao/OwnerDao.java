package mx.com.hexlink.charkota.data.entities.dao;

import mx.com.hexlink.charkota.data.entities.Owner;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.UUID;



public interface OwnerDao extends CrudRepository<Owner, UUID> {
	List<Owner> findByNameContaining(String name);
}
