package webapp.restapi.dsmtt.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import webapp.restapi.dsmtt.models.User;

public interface UserRepository extends MongoRepository<User, String> {

	User findByEmail(String email);

	List<User> findAllByTeam(String team);
	
}
