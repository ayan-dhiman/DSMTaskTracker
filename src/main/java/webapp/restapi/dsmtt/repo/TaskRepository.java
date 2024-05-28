package webapp.restapi.dsmtt.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import webapp.restapi.dsmtt.models.Task;

@Repository
public interface TaskRepository extends MongoRepository<Task, String>{

	List<Task> findAllByUserId(String userId);
	
	List<Task> findAllByUserIdAndDate(String userId, String date);

	void deleteAllByUserId(String userId);

}
