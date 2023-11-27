package mx.com.hexlink.charkota.data.services;

import mx.com.hexlink.charkota.data.entities.User;
import mx.com.hexlink.charkota.data.entities.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;



@Service
public class UserService implements BasicServiceAction<User, UUID> {
	@Autowired
	UserDao repository;



	@Transactional(readOnly = true)
	public List<User> getLikeUsername(String username){
		return repository.findByUsernameContainingIgnoreCase(username);
	}

	@Override
	public User saveData(User dataToSave){
		return repository.save(dataToSave);
	}

	@Override
	public List<User> saveMultipleData(List<User> listDataToSave){
		return (List<User>) repository.saveAll(listDataToSave);
	}

	@Override
	public User getById(UUID id){
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<User> getAll(){
		return (List<User>) repository.findAll();
	}

	@Override
	public List<User> getAllByIds(List<UUID> ids){
		return (List<User>) repository.findAllById(ids);
	}

	@Override
	public void delete(User dataToDelete){
		repository.delete(dataToDelete);
	}

	@Override
	public void deleteById(UUID id){
		repository.deleteById(id);
	}

	@Override
	public void deleteAll(List<User> listDataToDelete){
		repository.deleteAll(listDataToDelete);
	}

	@Override
	public void deleteAllById(List<UUID> listIdsToDelete){
		repository.deleteAllById(listIdsToDelete);
	}
}
