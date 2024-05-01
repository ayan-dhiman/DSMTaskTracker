package webapp.restapi.dsmtt.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "usersCollection")
public class User {
	
	@Id
	private String id;
	
	@Indexed(unique = true)
	private String email;
	
	private String name;
	private String role;
	private String team;
	private String password;
	
}
