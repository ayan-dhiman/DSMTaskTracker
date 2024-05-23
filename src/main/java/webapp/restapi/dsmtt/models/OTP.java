package webapp.restapi.dsmtt.models;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "otpCollection")
public class OTP {

	private String OTP;
	
	@Indexed(unique = true)
	private String userId;
	
}
