package webapp.restapi.dsmtt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import webapp.restapi.dsmtt.jwttokenfiles.JwtTokenUtil;
import webapp.restapi.dsmtt.jwttokenfiles.JwtUserDetailsService;
import webapp.restapi.dsmtt.models.LoginRequest;
import webapp.restapi.dsmtt.models.LoginResponse;
import webapp.restapi.dsmtt.models.User;
import webapp.restapi.dsmtt.repo.UserRepository;

@Slf4j
@Service
public class AuthService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
    private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private UserRepository userRepo;
	
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public ResponseEntity<?> login(LoginRequest loginReq)	
	{
		
		try {
			
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginReq.getEmail());
            
            String token = jwtTokenUtil.generateToken(userDetails);
            
            log.info("User logged in successfully: {}", userDetails.getUsername());
            
            return ResponseEntity.ok(new LoginResponse(token));
        
		} catch (BadCredentialsException e) {
        	
        	log.error("Invalid email or password for login request: {}", loginReq.getEmail());
        	
        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        
        }
	}
	
	public User register(User newUser)
	{
		log.info("Registering new user: {}", newUser.getEmail());
		
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		
		return userRepo.save(newUser);
	}
	
}
