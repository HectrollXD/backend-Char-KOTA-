package mx.com.hexlink.charkota.data.services;

import mx.com.hexlink.charkota.data.entities.Provider;
import mx.com.hexlink.charkota.data.entities.dao.ProviderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;



@Service
public class ProviderService implements BasicServiceAction<Provider, UUID> {
	@Autowired
	ProviderDao repository;



	@Override
	public Provider saveData(Provider dataToSave){
		return repository.save(dataToSave);
	}

	@Override
	public List<Provider> saveMultipleData(List<Provider> listDataToSave){
		return (List<Provider>) repository.saveAll(listDataToSave);
	}

	@Override
	public Provider getById(UUID id){
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Provider> getAll(){
		return (List<Provider>) repository.findAll();
	}

	@Override
	public List<Provider> getAllByIds(List<UUID> ids){
		return (List<Provider>) repository.findAllById(ids);
	}

	@Override
	public void delete(Provider dataToDelete){
		repository.delete(dataToDelete);
	}

	@Override
	public void deleteById(UUID id){
		repository.deleteById(id);
	}

	@Override
	public void deleteAll(List<Provider> listDataToDelete){
		repository.deleteAll(listDataToDelete);
	}

	@Override
	public void deleteAllById(List<UUID> listIdsToDelete){
		repository.deleteAllById(listIdsToDelete);
	}
}
