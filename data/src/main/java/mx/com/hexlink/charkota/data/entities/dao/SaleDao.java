package mx.com.hexlink.charkota.data.entities.dao;

import mx.com.hexlink.charkota.data.entities.Sale;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;



public interface SaleDao extends CrudRepository<Sale, UUID> {
}
