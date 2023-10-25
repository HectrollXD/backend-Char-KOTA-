package mx.com.hexlink.charkota.data.services;

import mx.com.hexlink.charkota.data.entities.PetVaccine;
import mx.com.hexlink.charkota.data.entities.dao.PetVaccineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;



@Service
public class PetVaccineService implements BasicServiceAction<PetVaccine, UUID>{
	@Autowired
	PetVaccineDao repository;



	@Override
	public PetVaccine saveData(PetVaccine dataToSave){
		return repository.save(dataToSave);
	}

	@Override
	public List<PetVaccine> saveMultipleData(List<PetVaccine> listDataToSave){
		return (List<PetVaccine>) repository.saveAll(listDataToSave);
	}

	@Override
	public PetVaccine getById(UUID id){
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<PetVaccine> getAll(){
		return (List<PetVaccine>) repository.findAll();
	}

	@Override
	public List<PetVaccine> getAllByIds(List<UUID> ids){
		return (List<PetVaccine>) repository.findAllById(ids);
	}

	@Override
	public void delete(PetVaccine dataToDelete){
		repository.delete(dataToDelete);
	}

	@Override
	public void deleteById(UUID id){
		repository.deleteById(id);
	}

	@Override
	public void deleteAll(List<PetVaccine> listDataToDelete){
		repository.deleteAll(listDataToDelete);
	}

	@Override
	public void deleteAllById(List<UUID> listIdsToDelete){
		repository.deleteAllById(listIdsToDelete);
	}
}
