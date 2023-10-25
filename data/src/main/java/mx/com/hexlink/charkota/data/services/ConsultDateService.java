package mx.com.hexlink.charkota.data.services;

import mx.com.hexlink.charkota.data.entities.ConsultDate;
import mx.com.hexlink.charkota.data.entities.dao.ConsultDateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;



@Service
public class ConsultDateService implements BasicServiceAction<ConsultDate, UUID>{
	@Autowired
	ConsultDateDao repository;



	@Override
	public ConsultDate saveData(ConsultDate dataToSave){
		return repository.save(dataToSave);
	}

	@Override
	public List<ConsultDate> saveMultipleData(List<ConsultDate> listDataToSave){
		return (List<ConsultDate>) repository.saveAll(listDataToSave);
	}

	@Override
	public ConsultDate getById(UUID id){
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<ConsultDate> getAll(){
		return (List<ConsultDate>) repository.findAll();
	}

	@Override
	public List<ConsultDate> getAllByIds(List<UUID> ids){
		return (List<ConsultDate>) repository.findAllById(ids);
	}

	@Override
	public void delete(ConsultDate dataToDelete){
		repository.delete(dataToDelete);
	}

	@Override
	public void deleteById(UUID id){
		repository.deleteById(id);
	}

	@Override
	public void deleteAll(List<ConsultDate> listDataToDelete){
		repository.deleteAll(listDataToDelete);
	}

	@Override
	public void deleteAllById(List<UUID> listIdsToDelete){
		repository.deleteAllById(listIdsToDelete);
	}
}
