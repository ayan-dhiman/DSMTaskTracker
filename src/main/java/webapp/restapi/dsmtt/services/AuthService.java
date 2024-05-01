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

import webapp.restapi.dsmtt.jwttokenfiles.JwtTokenUtil;
import webapp.restapi.dsmtt.jwttokenfiles.JwtUserDetailsService;
import webapp.restapi.dsmtt.models.LoginRequest;
import webapp.restapi.dsmtt.models.LoginResponse;
import webapp.restapi.dsmtt.models.User;
import webapp.restapi.dsmtt.repo.UserRepository;

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
            
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (BadCredentialsException e) {
            
        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        
        }
	}
	
	public User register(User newUser)
	{
		System.out.println(newUser);
		
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		return userRepo.save(newUser);
	}
	
}
