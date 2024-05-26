package webapp.restapi.dsmtt.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import webapp.restapi.dsmtt.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	User findByEmail(String email);
	
}
