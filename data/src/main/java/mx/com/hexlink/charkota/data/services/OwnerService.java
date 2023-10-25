package mx.com.hexlink.charkota.data.services;

import mx.com.hexlink.charkota.data.entities.Owner;
import mx.com.hexlink.charkota.data.entities.dao.OwnerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;



@Service
public class OwnerService implements BasicServiceAction<Owner, UUID>{
	@Autowired
	OwnerDao repository;



	@Override
	public Owner saveData(Owner dataToSave){
		return repository.save(dataToSave);
	}

	@Override
	public List<Owner> saveMultipleData(List<Owner> listDataToSave){
		return (List<Owner>) repository.saveAll(listDataToSave);
	}

	@Override
	public Owner getById(UUID id){
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Owner> getAll(){
		return (List<Owner>) repository.findAll();
	}

	@Override
	public List<Owner> getAllByIds(List<UUID> ids){
		return (List<Owner>) repository.findAllById(ids);
	}

	@Override
	public void delete(Owner dataToDelete){
		repository.delete(dataToDelete);
	}

	@Override
	public void deleteById(UUID id){
		repository.deleteById(id);
	}

	@Override
	public void deleteAll(List<Owner> listDataToDelete){
		repository.deleteAll(listDataToDelete);
	}

	@Override
	public void deleteAllById(List<UUID> listIdsToDelete){
		repository.deleteAllById(listIdsToDelete);
	}
}
