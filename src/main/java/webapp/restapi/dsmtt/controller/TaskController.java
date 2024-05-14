package webapp.restapi.dsmtt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import webapp.restapi.dsmtt.models.Task;
import webapp.restapi.dsmtt.services.TaskService;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping
	public Task addTask(@RequestBody Task newTask)
	{		
		return taskService.addTask(newTask);
	}
	
	@GetMapping("/{userId}")
	public List<Task> getAllTaskById(@PathVariable String userId)
	{
		return taskService.getTasksByUserId(userId);
	}
	
	@GetMapping("/date/{userId}")
	public List<Task> getAllTaskByIdAndDate(@PathVariable String userId, @RequestParam String date)
	{
		return taskService.getCurrentDayTasks(userId, date);
	}
	
	@PutMapping("/{taskId}")
	public Task updateTask(@PathVariable String taskId, @RequestBody Task newTask)
	{
		return taskService.updateTask(taskId, newTask);
	}
	
	@DeleteMapping("/{taskId}")
	public boolean deleteTask(@PathVariable String taskId)
	{
		return taskService.deleteTask(taskId);
	}
	
}
