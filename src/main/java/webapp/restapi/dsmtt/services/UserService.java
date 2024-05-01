package webapp.restapi.dsmtt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webapp.restapi.dsmtt.models.User;
import webapp.restapi.dsmtt.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public User getUserById(String id) {
		return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found with id : " + id));
	}

	public User getUserByEmail(String email) {
		System.out.println(userRepo.findByEmail(email));
		return userRepo.findByEmail(email);
	}

	public User createUser(User newUser) {
		return userRepo.save(newUser);
	}

	public User updateUser(String id, User updatedUser) {
		User existingUser = userRepo.findById(id).orElse(null);
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

			return userRepo.save(existingUser);
		} else {

			return null;
		}
	}

	public boolean deleteUser(String userId) {

		if (userRepo.findById(userId) != null) {
			userRepo.deleteById(userId);
			return true;
		}
		
		return false;
	}
	
	public List<User> getUsersInSameTeam(String team){
		return userRepo.findAllByTeam(team);
	}

}
