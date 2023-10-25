package mx.com.hexlink.charkota.data.services;

import mx.com.hexlink.charkota.data.entities.Sale;
import mx.com.hexlink.charkota.data.entities.dao.SaleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;



@Service
public class SaleService implements BasicServiceAction<Sale, UUID> {
	@Autowired
	SaleDao repository;



	@Override
	public Sale saveData(Sale dataToSave){
		return repository.save(dataToSave);
	}

	@Override
	public List<Sale> saveMultipleData(List<Sale> listDataToSave){
		return (List<Sale>) repository.saveAll(listDataToSave);
	}

	@Override
	public Sale getById(UUID id){
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Sale> getAll(){
		return (List<Sale>) repository.findAll();
	}

	@Override
	public List<Sale> getAllByIds(List<UUID> ids){
		return (List<Sale>) repository.findAllById(ids);
	}

	@Override
	public void delete(Sale dataToDelete){
		repository.delete(dataToDelete);
	}

	@Override
	public void deleteById(UUID id){
		repository.deleteById(id);
	}

	@Override
	public void deleteAll(List<Sale> listDataToDelete){
		repository.deleteAll(listDataToDelete);
	}

	@Override
	public void deleteAllById(List<UUID> listIdsToDelete){
		repository.deleteAllById(listIdsToDelete);
	}
}
