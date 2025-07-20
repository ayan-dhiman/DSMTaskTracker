package webapp.restapi.dsmtt.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import webapp.restapi.dsmtt.models.OTP;

@Repository
public interface OTPRepository extends MongoRepository<OTP, String> {
	
	void deleteByUserId(String userId);

	OTP findByUserId(String id);
	
}
