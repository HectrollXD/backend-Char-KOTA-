package mx.com.hexlink.charkota.data.services;

import mx.com.hexlink.charkota.data.entities.AnimalFamily;
import mx.com.hexlink.charkota.data.entities.dao.AnimalFamilyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;



@Service
public class AnimalFamilyService implements BasicServiceAction<AnimalFamily, UUID> {
	@Autowired
	AnimalFamilyDao repository;



	@Override
	public AnimalFamily saveData(AnimalFamily dataToSave){
		return repository.save(dataToSave);
	}

	@Override
	public List<AnimalFamily> saveMultipleData(List<AnimalFamily> listDataToSave){
		return (List<AnimalFamily>) repository.saveAll(listDataToSave);
	}

	@Override
	public AnimalFamily getById(UUID id){
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<AnimalFamily> getAll(){
		return (List<AnimalFamily>) repository.findAll();
	}

	@Override
	public List<AnimalFamily> getAllByIds(List<UUID> ids){
		return (List<AnimalFamily>) repository.findAllById(ids);
	}

	@Override
	public void delete(AnimalFamily dataToDelete){
		repository.delete(dataToDelete);
	}

	@Override
	public void deleteById(UUID id){
		repository.deleteById(id);
	}

	@Override
	public void deleteAll(List<AnimalFamily> listDataToDelete){
		repository.deleteAll(listDataToDelete);
	}

	@Override
	public void deleteAllById(List<UUID> listIdsToDelete){
		repository.deleteAllById(listIdsToDelete);
	}
}
