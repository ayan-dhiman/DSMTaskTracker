//package ControlllerTest;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.test.web.servlet.MockMvc;
//
//import webapp.restapi.dsmtt.DSMTaskTrackerRESTAPI;
//import webapp.restapi.dsmtt.controller.UserController;
//import webapp.restapi.dsmtt.jwttokenfiles.JwtTokenUtil;
//import webapp.restapi.dsmtt.models.User;
//import webapp.restapi.dsmtt.services.UserService;
//
//@SpringBootTest(classes = DSMTaskTrackerRESTAPI.class)
//@AutoConfigureMockMvc
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private UserService userService;
//
//    @InjectMocks
//    private UserController userController;
//    
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//    
//    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhQGEuY29tIiwiaWF0IjoxNzE1MTU2MTMwLCJleHAiOjE3MTUxNzQxMzB9.ZsiW1nco7FFbuN28-XphSkwyUUkaRl3SXMhybeybv3ch8cC5T0A3adYQyv4GWDTkcp3jS9M2IItUhGwGgXnwhw";
//    
//    
//    @Test
//    public void testGetUserById() throws Exception {
//        
//        User user = new User("1777", "test@example.com", "Test User", "USER", "TeamA", "password");
//        
//        when(userService.getUserById("1777")).thenReturn(user);
//
//        mockMvc.perform(get("/api/users/1777")
//        		.header("Authorization", "Bearer " + token))
//                .andExpect(status().isOk());
//                //.andExpect(header().exists("Authorization"));;
//    }
//
//
//    @Test
//    public void testGetUserByEmail() throws Exception {
//        
//        User user = new User("1888", "test@example.com", "Test User", "USER", "TeamA", "password");
//        when(userService.getUserByEmail("test@example.com")).thenReturn(user);
//
//        mockMvc.perform(get("/api/users/email/test@example.com")
//        		.header("Authorization", "Bearer " + token))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testDeleteUser() throws Exception {
//        
//        when(userService.deleteUser("1999")).thenReturn(true);
//
//        mockMvc.perform(delete("/api/users/1999")
//        		.header("Authorization", "Bearer " + token))
//                .andExpect(status().isOk());
//    }
//    
//    @Test
//    public void testUpdateUser() throws Exception {
//        
//        User updatedUser = new User("1555", "test@example.com", "Updated User", "USER", "TeamA", "newpassword");
//        when(userService.updateUser("1555", updatedUser)).thenReturn(updatedUser);
//
//        mockMvc.perform(put("/api/users/1555")
//        		.header("Authorization", "Bearer " + token)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"id\":\"1555\",\"email\":\"test@example.com\",\"name\":\"Updated User\",\"role\":\"USER\",\"team\":\"TeamA\",\"password\":\"newpassword\"}"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testGetUsersInSameTeam() throws Exception {
//        
//        User user1 = new User("1444", "test1@example.com", "Test User 1", "USER", "TeamA", "password");
//        User user2 = new User("1333", "test2@example.com", "Test User 2", "USER", "TeamA", "password");
//        List<User> users = Arrays.asList(user1, user2);
//        when(userService.getUsersInSameTeam("TeamA")).thenReturn(users);
//
//        mockMvc.perform(get("/api/users/team?team=TeamA")
//        		.header("Authorization", "Bearer " + token))
//                .andExpect(status().isOk());
//    }
//    
//    private String generateJwtToken(String username) {
//        UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, "password", new ArrayList<>());
//        return jwtTokenUtil.generateToken(userDetails);
//    }
//
//}
