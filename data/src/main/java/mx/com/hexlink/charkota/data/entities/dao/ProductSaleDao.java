package mx.com.hexlink.charkota.data.entities.dao;

import mx.com.hexlink.charkota.data.entities.ProductSale;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;



public interface ProductSaleDao extends CrudRepository<ProductSale, UUID> {
}
