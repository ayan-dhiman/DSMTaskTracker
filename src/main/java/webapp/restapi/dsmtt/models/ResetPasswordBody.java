package webapp.restapi.dsmtt.models;

import lombok.Data;

@Data
public class ResetPasswordBody {

	private String otp;
	private String email;
	private String newPassword;
	
}
