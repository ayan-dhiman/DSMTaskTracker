package webapp.restapi.dsmtt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import webapp.restapi.dsmtt.models.User;
import webapp.restapi.dsmtt.repo.OTPRepository;
import webapp.restapi.dsmtt.repo.TaskRepository;
import webapp.restapi.dsmtt.repo.TeamRepository;
import webapp.restapi.dsmtt.repo.UserRepository;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private TeamRepository teamRepo;

	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private EmailService emailService;
	
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
		
		//emailService.sendWelcomeMail(newUser.getEmail());

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
				existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
			}
			activityService.addActivity("Update Account Details", id);

			return userRepo.save(existingUser);
		} else {

			log.error("User not found with id: {}", id);

			return null;
		}
	}

	public User updateUserByEmaiil(String email, User updatedUser) {

		log.info("Updating user with email: {}", email);

		User existingUser = getUserByEmail(email);
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
			activityService.addActivity("Update Account Details", email);

			return userRepo.save(existingUser);
		} else {

			log.error("User not found with id: {}", email);

			return null;
		}
	}

	public boolean deleteUser(String userId) {
		log.info("Deleting user with id: {}", userId);
		if (userRepo.findById(userId) != null) {
			userRepo.deleteById(userId);
			taskRepo.deleteAllByUserId(userId);
			teamRepo.deleteAllByUserId(userId);
			return true;
		}
		log.error("User not found with id: {}", userId);
		return false;
	}

}
