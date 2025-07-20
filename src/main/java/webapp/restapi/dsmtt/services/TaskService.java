package webapp.restapi.dsmtt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import webapp.restapi.dsmtt.models.Task;
import webapp.restapi.dsmtt.repo.TaskRepository;

@Slf4j
@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private ActivityService activityService;
	
	public List<Task> getTasksByUserId(String userId)
	{
		log.info("Finding all tasks for user: {}", userId);
		return taskRepo.findAllByUserId(userId);
	}
	
	public List<Task> getCurrentDayTasks(String userId, String currentDate)
	{
		log.info("Finding all tasks for user '{}' with date: {}", userId, currentDate);
		return taskRepo.findAllByUserIdAndDate(userId, currentDate);
	}
	
	public Task addTask(Task newTask)
	{	
		log.info("Adding new task: {}", newTask);
		
		activityService.addActivity(("Added Task - "+newTask.getTask()), newTask.getUserId());
		
		return taskRepo.save(newTask);
	}
	
	public Task updateTask(String taskId,Task updatedTask)
	{
		log.info("Updating task with id: {}", taskId);
		
		Task existingTask = taskRepo.findById(taskId).orElse(null);
		String existTask = existingTask.getTask();
		if (existingTask != null) {
			if (updatedTask.getTask() != null) {
				existingTask.setTask(updatedTask.getTask());
			}
			if (updatedTask.getStatus() != null) {
				existingTask.setStatus(updatedTask.getStatus());
			}
			if (updatedTask.getTeam() != null) {
				existingTask.setTeam(updatedTask.getTeam());
			}
			if (updatedTask.getComment() != null) {
				existingTask.setComment(updatedTask.getComment());
			}
			if (updatedTask.getPriority() != null) {
				existingTask.setPriority(updatedTask.getPriority());
			}
			if (updatedTask.getLink() != null) {
				existingTask.setLink(updatedTask.getLink());
			}
			
			activityService.addActivity(("Updated Task - "+existTask+" to - "+existingTask.getTask()), existingTask.getUserId());

			return taskRepo.save(existingTask);
		} else {
			
			log.error("Task not found with id: {}", taskId);
			
			return null;
		}
	}
	
	public boolean deleteTask(String taskId)
	{
		log.info("Deleting task with id: {}", taskId);
		Task task = taskRepo.findById(taskId).get();
		if (task != null) {
			activityService.addActivity(("Deleted Task - "+task.getTask()), task.getUserId());
			taskRepo.deleteById(taskId);
			return true;
		}
		log.error("Task not found with id: {}", taskId);
		return false;
	}
	
}
