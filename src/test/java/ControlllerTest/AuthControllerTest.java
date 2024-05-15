package ControlllerTest;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import webapp.restapi.dsmtt.DSMTaskTrackerRESTAPI;
import webapp.restapi.dsmtt.controller.AuthController;
import webapp.restapi.dsmtt.models.User;
import webapp.restapi.dsmtt.services.AuthService;

@SpringBootTest(classes = DSMTaskTrackerRESTAPI.class)
@AutoConfigureMockMvc
public class AuthControllerTest {
	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@Mock
//	private AuthService authService;
//	
//	@InjectMocks
//	private AuthController authController;
//
//	@Test
//	public void testRegister() throws Exception{
//		
//		 User newUser = new User("1777", "test@example.com", "Test User", "USER", "TeamA", "password");
//
//	        when(authService.register(newUser)).thenReturn(newUser);
//
//	        mockMvc.perform(post("/auth/register")
//	                .contentType(MediaType.APPLICATION_JSON)
//	                .content("{\"id\":\"1777\",\"email\":\"test@example.com\",\"name\":\"Test User\",\"role\":\"USER\",\"team\":\"TeamA\",\"password\":\"password\"}"))
//	                .andExpect(status().isOk());
//		
//	}
	
}
