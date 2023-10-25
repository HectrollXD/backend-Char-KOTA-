package mx.com.hexlink.charkota.data.services;

import mx.com.hexlink.charkota.data.entities.AnimalBreed;
import mx.com.hexlink.charkota.data.entities.dao.AnimalBreedDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;



@Service
public class AnimalBreedService implements BasicServiceAction<AnimalBreed, UUID> {
	@Autowired
	AnimalBreedDao repository;



	@Override
	public AnimalBreed saveData(AnimalBreed dataToSave){
		return repository.save(dataToSave);
	}

	@Override
	public List<AnimalBreed> saveMultipleData(List<AnimalBreed> listDataToSave){
		return (List<AnimalBreed>) repository.saveAll(listDataToSave);
	}

	@Override
	public AnimalBreed getById(UUID id){
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<AnimalBreed> getAll(){
		return (List<AnimalBreed>) repository.findAll();
	}

	@Override
	public List<AnimalBreed> getAllByIds(List<UUID> ids){
		return (List<AnimalBreed>) repository.findAllById(ids);
	}

	@Override
	public void delete(AnimalBreed dataToDelete){
		repository.delete(dataToDelete);
	}

	@Override
	public void deleteById(UUID id){
		repository.deleteById(id);
	}

	@Override
	public void deleteAll(List<AnimalBreed> listDataToDelete){
		repository.deleteAll(listDataToDelete);
	}

	@Override
	public void deleteAllById(List<UUID> listIdsToDelete){
		repository.deleteAllById(listIdsToDelete);
	}
}
