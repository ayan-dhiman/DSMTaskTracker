//package ControlllerTest;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import webapp.restapi.dsmtt.DSMTaskTrackerRESTAPI;
//import webapp.restapi.dsmtt.controller.TaskController;
//import webapp.restapi.dsmtt.models.Task;
//import webapp.restapi.dsmtt.services.TaskService;
//
//@SpringBootTest(classes = DSMTaskTrackerRESTAPI.class)
//@AutoConfigureMockMvc
//public class TaskControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private TaskService taskService;
//
//    @InjectMocks
//    private TaskController taskController;
//    
//    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhQGEuY29tIiwiaWF0IjoxNzE1MTU2MTMwLCJleHAiOjE3MTUxNzQxMzB9.ZsiW1nco7FFbuN28-XphSkwyUUkaRl3SXMhybeybv3ch8cC5T0A3adYQyv4GWDTkcp3jS9M2IItUhGwGgXnwhw";
//
//    @Test
//    public void testAddTask() throws Exception {
//        Task newTask = new Task("1", "New Task", "ToDo", "08-05-2024", "11");
//
//        when(taskService.addTask(newTask)).thenReturn(newTask);
//
//        mockMvc.perform(post("/api/tasks")
//                .header("Authorization", "Bearer " + token)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"taskId\":\"1\",\"task\":\"New Task\",\"status\":\"ToDo\",\"date\":\"08-05-2024\",\"userId\":\"11\"}"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testGetAllTaskById() throws Exception {
//        List<Task> tasks = Arrays.asList(new Task("1", "New Task", "ToDo", "08-05-2024", "11"), new Task("2", "New Task 2", "ToDo", "08-05-2024", "11"));
//
//        when(taskService.getTasksByUserId("11")).thenReturn(tasks);
//
//        mockMvc.perform(get("/api/tasks/11")
//        		.header("Authorization", "Bearer " + token))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testGetAllTaskByIdAndDate() throws Exception {
//    	List<Task> tasks = Arrays.asList(new Task("1", "New Task", "ToDo", "08-05-2024", "11"), new Task("2", "New Task 2", "ToDo", "08-05-2024", "11"));
//    	
//        when(taskService.getCurrentDayTasks("11", "08-05-2024")).thenReturn(tasks);
//
//        mockMvc.perform(get("/api/tasks/date/123?date=2024-05-08")
//        		.header("Authorization", "Bearer " + token))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testUpdateTask() throws Exception {
//        Task updatedTask = new Task("1", "Updated New Task", "ToDo", "08-05-2024", "11");
//
//        when(taskService.updateTask("1", updatedTask)).thenReturn(updatedTask);
//
//        mockMvc.perform(put("/api/tasks/1")
//                .header("Authorization", "Bearer " + token)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"taskId\":\"1\",\"task\":\"Updated New Task\",\"status\":\"ToDo\",\"date\":\"08-05-2024\",\"userId\":\"11\"}"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testDeleteTask() throws Exception {
//        when(taskService.deleteTask("1")).thenReturn(true);
//
//        mockMvc.perform(delete("/api/tasks/1")
//        		.header("Authorization", "Bearer " + token))
//                .andExpect(status().isOk());
//    }
//}
