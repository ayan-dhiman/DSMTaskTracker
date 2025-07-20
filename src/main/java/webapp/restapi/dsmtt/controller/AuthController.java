package webapp.restapi.dsmtt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import webapp.restapi.dsmtt.models.LoginRequest;
import webapp.restapi.dsmtt.models.ResetPasswordBody;
import webapp.restapi.dsmtt.models.User;
import webapp.restapi.dsmtt.services.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

	@Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        authService.register(user);
    }
    
    @GetMapping("/verifyemail")
	public boolean verifyEmail(@RequestParam String email)
	{
		return authService.verifyEmail(email);
	}
    
    @PostMapping("/generateOTP")
	public void generateOTP(@RequestParam String email)
	{
		authService.generateOTP(email);
	}
    
    @PostMapping("/validateOTP")
	public boolean validateOTP(@RequestParam String email , @RequestParam String OTP )
	{
		return authService.validateOTP(OTP, email);
	}
    
    @PostMapping("/resetPassword")
	public void resetPassword(@RequestBody ResetPasswordBody body )
	{
		authService.resetPassword(body);
	}
    
}
