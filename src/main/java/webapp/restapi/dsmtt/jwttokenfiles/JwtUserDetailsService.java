package webapp.restapi.dsmtt.jwttokenfiles;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import webapp.restapi.dsmtt.models.User;
import webapp.restapi.dsmtt.repo.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		 User user = userRepository.findByEmail(email);
	        if (user == null) {
	            throw new UsernameNotFoundException("User not found with email: " + email);
	        }
	        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
	                new ArrayList<>());
	}
	
}
