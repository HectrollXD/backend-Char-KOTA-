package mx.com.hexlink.charkota.data.services;

import mx.com.hexlink.charkota.data.entities.Person;
import mx.com.hexlink.charkota.data.entities.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;



@Service
public class PersonService implements BasicServiceAction<Person, UUID> {
    @Autowired
    PersonDao repository;



    @Override
    public Person saveData(Person dataToSave){
        return repository.save(dataToSave);
    }

    @Override
    public List<Person> saveMultipleData(List<Person> listDataToSave){
        return (List<Person>) repository.saveAll(listDataToSave);
    }

    @Override
    public Person getById(UUID id){
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Person> getAll(){
        return (List<Person>) repository.findAll();
    }

    @Override
    public List<Person> getAllByIds(List<UUID> ids){
        return (List<Person>) repository.findAllById(ids);
    }

    @Override
    public void delete(Person dataToDelete){
        repository.delete(dataToDelete);
    }

    @Override
    public void deleteById(UUID id){
        repository.deleteById(id);
    }

    @Override
    public void deleteAll(List<Person> listDataToDelete){
        repository.deleteAll(listDataToDelete);
    }

    @Override
    public void deleteAllById(List<UUID> listIdsToDelete){
        repository.deleteAllById(listIdsToDelete);
    }
}
