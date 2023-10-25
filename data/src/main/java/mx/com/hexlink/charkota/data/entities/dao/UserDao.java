package mx.com.hexlink.charkota.data.entities.dao;

import mx.com.hexlink.charkota.data.entities.User;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;



public interface UserDao extends CrudRepository<User, UUID> {
}
