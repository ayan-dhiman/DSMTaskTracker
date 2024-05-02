package webapp.restapi.dsmtt.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import webapp.restapi.dsmtt.models.Team;

@Repository
public interface TeamRepository extends MongoRepository<Team, String> {

}
