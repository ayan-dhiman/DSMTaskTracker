package webapp.restapi.dsmtt.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "teamsCollection")
public class Team {

	@Id
	private String teamId;
	
	@Indexed(unique = true)
	private String name;
	
	private String userId;
	
}