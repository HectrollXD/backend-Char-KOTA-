package mx.com.hexlink.charkota.data.services;

import mx.com.hexlink.charkota.data.entities.Product;
import mx.com.hexlink.charkota.data.entities.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;



@Service
public class ProductService implements BasicServiceAction<Product, UUID> {
	@Autowired
	ProductDao repository;



	
	@Transactional(readOnly = true)
	public Product getByBarCode(String barCode){
		Product product = repository.findByBarCode(barCode);

		if( Objects.nonNull(product) && product.getIsDeleted() ){
			return null;
		}

		return product;
	}

	@Transactional(readOnly = true)
	public List<Product> getLikeName(String name){
		return repository.findByNameContainingIgnoreCase(name).stream().filter(
			obj -> obj.getIsDeleted() == false
		).toList();
	}

	@Override
	public Product saveData(Product dataToSave){
		return repository.save(dataToSave);
	}

	@Override
	public List<Product> saveMultipleData(List<Product> listDataToSave){
		return (List<Product>) repository.saveAll(listDataToSave);
	}

	@Override
	public Product getById(UUID id){
		Product product = repository.findById(id).orElse(null);

		if( Objects.nonNull(product) && product.getIsDeleted() ){
			return null;
		}

		return product;
	}

	@Override
	public List<Product> getAll(){
		List<Product> products = (List<Product>) repository.findAll();

		return products.stream().filter(
			obj -> obj.getIsDeleted() == false
		).toList();
	}

	@Override
	public List<Product> getAllByIds(List<UUID> ids){
		List<Product> products = (List<Product>) repository.findAllById(ids);

		return products.stream().filter(
			obj -> obj.getIsDeleted() == false
		).toList();
	}

	@Override
	public void delete(Product dataToDelete){
		repository.delete(dataToDelete);
	}

	@Override
	public void deleteById(UUID id){
		repository.deleteById(id);
	}

	@Override
	public void deleteAll(List<Product> listDataToDelete){
		repository.deleteAll(listDataToDelete);
	}

	@Override
	public void deleteAllById(List<UUID> listIdsToDelete){
		repository.deleteAllById(listIdsToDelete);
	}
}
