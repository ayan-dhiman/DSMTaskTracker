package webapp.restapi.dsmtt.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginRequest {

	private String email;
	private String password;
	
}
