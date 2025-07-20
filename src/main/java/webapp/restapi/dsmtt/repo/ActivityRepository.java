package webapp.restapi.dsmtt.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import webapp.restapi.dsmtt.models.Activity;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String>{

	List<Activity> findAllByUserId(String userId);

	void deleteAllByUserId(String userId);
	
}
