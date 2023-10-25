package mx.com.hexlink.charkota.data.services;

import mx.com.hexlink.charkota.data.entities.Product;
import mx.com.hexlink.charkota.data.entities.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;



@Service
public class ProductService implements BasicServiceAction<Product, UUID> {
	@Autowired
	ProductDao repository;



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
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Product> getAll(){
		return (List<Product>) repository.findAll();
	}

	@Override
	public List<Product> getAllByIds(List<UUID> ids){
		return (List<Product>) repository.findAllById(ids);
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
