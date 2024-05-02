package webapp.restapi.dsmtt.models;


import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "tasksCollection")
public class Task {

	@Id
	private String taskId;
	private String task;
	private String status;
	private LocalDate date;
	private String userId;
	
}
