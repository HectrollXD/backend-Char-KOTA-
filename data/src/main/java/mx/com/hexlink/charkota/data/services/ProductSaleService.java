package mx.com.hexlink.charkota.data.services;

import mx.com.hexlink.charkota.data.entities.ProductSale;
import mx.com.hexlink.charkota.data.entities.dao.ProductSaleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;



@Service
public class ProductSaleService implements BasicServiceAction<ProductSale, UUID> {
	@Autowired
	ProductSaleDao repository;



	@Override
	public ProductSale saveData(ProductSale dataToSave){
		return repository.save(dataToSave);
	}

	@Override
	public List<ProductSale> saveMultipleData(List<ProductSale> listDataToSave){
		return (List<ProductSale>) repository.saveAll(listDataToSave);
	}

	@Override
	public ProductSale getById(UUID id){
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<ProductSale> getAll(){
		return (List<ProductSale>) repository.findAll();
	}

	@Override
	public List<ProductSale> getAllByIds(List<UUID> ids){
		return (List<ProductSale>) repository.findAllById(ids);
	}

	@Override
	public void delete(ProductSale dataToDelete){
		repository.delete(dataToDelete);
	}

	@Override
	public void deleteById(UUID id){
		repository.deleteById(id);
	}

	@Override
	public void deleteAll(List<ProductSale> listDataToDelete){
		repository.deleteAll(listDataToDelete);
	}

	@Override
	public void deleteAllById(List<UUID> listIdsToDelete){
		repository.deleteAllById(listIdsToDelete);
	}
}
