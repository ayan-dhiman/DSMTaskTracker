package webapp.restapi.dsmtt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import webapp.restapi.dsmtt.models.User;
import webapp.restapi.dsmtt.services.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	@PostMapping
//	public User addUser(@RequestBody User newUser)
//	{
//		return userService.createUser(newUser);
//	}
	
	@GetMapping("/{userId}")
	public User getUserById(@PathVariable String userId)
	{
		return userService.getUserById(userId);
	}
	
	@GetMapping("/email/{email}")
	public User getUserByEmail(@PathVariable String email)
	{
		return userService.getUserByEmail(email);
	}
	
	@DeleteMapping("/{userId}")
	public boolean deleteUser(@PathVariable String userId)
	{    
	    return userService.deleteUser(userId);
	}
	
	@PutMapping("/{userId}")
	public User updateUser(@PathVariable String userId, @RequestBody User updatedUser)
	{
	    return userService.updateUser(userId, updatedUser);
	}
	
	@PutMapping("/email/{email}")
	public User updateUserByEmail(@PathVariable String email, @RequestBody User updatedUser)
	{
	    return userService.updateUserByEmaiil(email, updatedUser);
	}
	
	@GetMapping("/team")
	public List<User> getUsersInSameTeam(@RequestParam String team)
	{
		return userService.getUsersInSameTeam(team);
	}
	
}
