package mx.com.hexlink.charkota.data.entities.dao;

import mx.com.hexlink.charkota.data.entities.Product;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.UUID;



public interface ProductDao extends CrudRepository<Product, UUID> {
	Product findByBarCode(String barCode);
	List<Product> findByNameContainingIgnoreCase(String name);
}
