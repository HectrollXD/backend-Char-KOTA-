package mx.com.hexlink.charkota.data.entities.dao;

import mx.com.hexlink.charkota.data.entities.Owner;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;



public interface OwnerDao extends CrudRepository<Owner, UUID> {
}
