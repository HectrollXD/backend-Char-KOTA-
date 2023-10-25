package mx.com.hexlink.charkota.data.services;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;



public interface BasicServiceAction <T, ID> {



	@Transactional
	T saveData(T dataToSave);

	@Transactional
	List<T> saveMultipleData(List<T> listDataToSave);

	@Transactional(readOnly = true)
	T getById(ID id);

	@Transactional(readOnly = true)
	List<T> getAll();

	@Transactional(readOnly = true)
	List<T> getAllByIds(List<ID> ids);

	@Transactional
	void delete(T dataToDelete);

	@Transactional
	void deleteById(ID id);

	@Transactional
	void deleteAll(List<T> listDataToDelete);

	@Transactional
	void deleteAllById(List<ID> listIdsToDelete);
}
