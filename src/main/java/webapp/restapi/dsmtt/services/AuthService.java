package webapp.restapi.dsmtt.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

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
import webapp.restapi.dsmtt.models.Activity;
import webapp.restapi.dsmtt.models.LoginRequest;
import webapp.restapi.dsmtt.models.LoginResponse;
import webapp.restapi.dsmtt.models.User;
import webapp.restapi.dsmtt.repo.OTPRepository;
import webapp.restapi.dsmtt.repo.UserRepository;
import webapp.restapi.dsmtt.models.OTP;
import webapp.restapi.dsmtt.models.ResetPasswordBody;

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

	@Autowired
	private ActivityService activityService;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private OTPRepository OTPRepo;

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public ResponseEntity<?> login(LoginRequest loginReq) {

		try {

			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));

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

	public void register(User newUser) {
		log.info("Registering new user: {}", newUser.getEmail());

		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

		emailService.sendWelcomeMail(newUser.getEmail(), newUser.getName());

		userRepo.save(newUser);
	}

	public boolean verifyEmail(String email) {

		log.info("Verifying email: {}", email);

		if (userRepo.findByEmail(email) != null) {
			log.info("Email already registered : {}", email);
			return true;
		}

		log.info("Email is not registered: {}", email);
		return false;
	}

	public void generateOTP(String email) {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		
		String OTP = passwordEncoder.encode(String.valueOf(otp));

		if (verifyEmail(email)) {
			User user = userRepo.findByEmail(email);
			OTPRepo.deleteByUserId(user.getId());
			OTPRepo.save(new OTP(OTP, user.getId()));
			emailService.sendOTPMail(email, String.valueOf(otp) );
			log.info("OTP generated");
		} else {
			log.error("Email not registered to generate OTP");
		}

	}

	public boolean validateOTP(String enteredOTP, String email) {
	    User user = userRepo.findByEmail(email);
	    
	    if (user == null) {
	        log.error("User not found with email: {}", email);
	        return false;
	    }
	    
	    OTP OTPStoredData = OTPRepo.findByUserId(user.getId());
	    
	    if (OTPStoredData == null) {
	        log.error("OTP not found for user: {}", email);
	        return false;
	    }
	    
	    String OTPStored = OTPStoredData.getOTP();
	    
	    if (passwordEncoder.matches(enteredOTP, OTPStored)) {
	        log.info("OTP validated successfully for user: {}", email);
	        return true;
	    } else {
	        log.error("Invalid OTP entered for user: {}", email);
	        return false;
	    }
	}


	public void resetPassword(ResetPasswordBody body) {

		User existingUser = userRepo.findByEmail(body.getEmail());
		
		if(validateOTP(body.getOtp(), body.getEmail())) {
		
			OTPRepo.deleteById(existingUser.getId());
			
			existingUser.setPassword(passwordEncoder.encode(body.getNewPassword()));
		
			userRepo.save(existingUser);
			
		}
		
	}

}
