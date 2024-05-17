package webapp.restapi.dsmtt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import webapp.restapi.dsmtt.models.User;
import webapp.restapi.dsmtt.repo.UserRepository;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ActivityService activityService;
	
	public User getUserById(String id) {
	    log.info("Fetching user by id: {}", id);
	    Optional<User> optionalUser = userRepo.findById(id);
	    if (optionalUser.isPresent()) {
	        return optionalUser.get();
	    } else {
	        return null;
	    }
	}

	public User getUserByEmail(String email) {
		
		log.info("Fetching user by email: {}", email);
		 
		return userRepo.findByEmail(email);
	}

	public User createUser(User newUser) {
		
		log.info("Creating new user: {}", newUser.getEmail());
		
		return userRepo.save(newUser);
	}

	public User updateUser(String id, User updatedUser) {
		
		log.info("Updating user with id: {}", id);
		
		User existingUser = getUserById(id);
		if (existingUser != null) {
			if (updatedUser.getEmail() != null) {
				existingUser.setEmail(updatedUser.getEmail());
			}
			if (updatedUser.getName() != null) {
				existingUser.setName(updatedUser.getName());
			}
			if (updatedUser.getPassword() != null) {
				existingUser.setPassword(updatedUser.getPassword());
			}
			if (updatedUser.getRole() != null) {
				existingUser.setRole(updatedUser.getRole());
			}
			if (updatedUser.getTeam() != null) {
				existingUser.setTeam(updatedUser.getTeam());
			}
			activityService.addActivity("Update Account Details", id );
			
			return userRepo.save(existingUser);
		} else {
			
			log.error("User not found with id: {}", id);
			
			return null;
		}
	}

	public boolean deleteUser(String userId) {
		log.info("Deleting user with id: {}", userId);
		if (userRepo.findById(userId) != null) {
			userRepo.deleteById(userId);
			return true;
		}
		log.error("User not found with id: {}", userId);
		return false;
	}
	
	public List<User> getUsersInSameTeam(String team){
		
		log.info("Fetching users in the same team: {}", team);
		
		return userRepo.findAllByTeam(team);
	}

}
