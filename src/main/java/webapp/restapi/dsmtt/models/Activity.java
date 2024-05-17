package webapp.restapi.dsmtt.models;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "activitiesCollection")
public class Activity {
	
	private String date;
	private String activity;
	private String userId;
	
}