package mx.com.hexlink.charkota.data.services;

import mx.com.hexlink.charkota.data.entities.Pet;
import mx.com.hexlink.charkota.data.entities.dao.PetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;



@Service
public class PetService implements BasicServiceAction<Pet, UUID>{
	@Autowired
	PetDao repository;



	@Transactional(readOnly = true)
	public List<Pet> getLikeName(String name){
		return repository.findByNameContaining(name);
	}

	@Override
	public Pet saveData(Pet dataToSave){
		return repository.save(dataToSave);
	}

	@Override
	public List<Pet> saveMultipleData(List<Pet> listDataToSave){
		return (List<Pet>) repository.saveAll(listDataToSave);
	}

	@Override
	public Pet getById(UUID id){
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Pet> getAll(){
		return (List<Pet>) repository.findAll();
	}

	@Override
	public List<Pet> getAllByIds(List<UUID> ids){
		return (List<Pet>) repository.findAllById(ids);
	}

	@Override
	public void delete(Pet dataToDelete){
		repository.delete(dataToDelete);
	}

	@Override
	public void deleteById(UUID id){
		repository.deleteById(id);
	}

	@Override
	public void deleteAll(List<Pet> listDataToDelete){
		repository.deleteAll(listDataToDelete);
	}

	@Override
	public void deleteAllById(List<UUID> listIdsToDelete){
		repository.deleteAllById(listIdsToDelete);
	}
}
