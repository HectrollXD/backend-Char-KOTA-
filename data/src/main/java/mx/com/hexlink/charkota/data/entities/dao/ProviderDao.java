package mx.com.hexlink.charkota.data.entities.dao;

import mx.com.hexlink.charkota.data.entities.Provider;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;
import java.util.List;



public interface ProviderDao extends CrudRepository<Provider, UUID> {
	List<Provider> findByNameContaining(String name);
}
