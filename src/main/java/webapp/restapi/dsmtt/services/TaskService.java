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
		return taskRepo.save(newTask);
	}
	
	public Task updateTask(String taskId,Task updatedTask)
	{
		log.info("Updating task with id: {}", taskId);
		
		Task existingTask = taskRepo.findById(taskId).orElse(null);
		if (existingTask != null) {
			if (updatedTask.getTask() != null) {
				existingTask.setTask(updatedTask.getTask());
			}
			if (updatedTask.getStatus() != null) {
				existingTask.setStatus(updatedTask.getStatus());
			}

			return taskRepo.save(existingTask);
		} else {
			
			log.error("Task not found with id: {}", taskId);
			
			return null;
		}
	}
	
	public boolean deleteTask(String taskId)
	{
		log.info("Deleting task with id: {}", taskId);
		if (taskRepo.findById(taskId) != null) {
			taskRepo.deleteById(taskId);
			return true;
		}
		log.error("Task not found with id: {}", taskId);
		return false;
	}
	
}
