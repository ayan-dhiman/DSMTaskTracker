package webapp.restapi.dsmtt.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "tasksCollection")
public class Task {

	@Id
	private String taskId;
	private String task;
	private String status;
	private String date;
	private String Team;
	private String Comment;
	private String userId;
}
