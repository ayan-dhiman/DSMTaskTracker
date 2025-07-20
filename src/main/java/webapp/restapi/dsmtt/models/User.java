package webapp.restapi.dsmtt.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Document(collection = "usersCollection")
public class User {
	
	@Id
	private String id;
	
	@Indexed(unique = true)
	private String email;
	
	private String name;
	private String password;
	
	private String createdOn;
	private String lastLogin;
	
}
