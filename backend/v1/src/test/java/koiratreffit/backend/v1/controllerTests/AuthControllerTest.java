package koiratreffit.backend.v1.controllerTests;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import koiratreffit.backend.v1.configurations.SecurityConfig;
import koiratreffit.backend.v1.controllers.AuthController;
import koiratreffit.backend.v1.interfaces.UserServiceInterface;
import koiratreffit.backend.v1.objects.CustomUserDetails;
import koiratreffit.backend.v1.objects.LoginRequest;
import koiratreffit.backend.v1.objects.User;
import koiratreffit.backend.v1.repositories.UserRepository;
import koiratreffit.backend.v1.services.LoginService;

@WebMvcTest(AuthController.class)
@Import(SecurityConfig.class)
public class AuthControllerTest {

    @MockBean
    private UserServiceInterface userServiceInterface;

    @MockBean
    private LoginService loginService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    AuthenticationManager authenticationManager;

    @MockBean
    CustomUserDetails customUserDetails;

    @Autowired
    private MockMvc mockMvc;

    private User user;

    @MockBean
    LoginRequest loginRequest;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {

        objectMapper = new ObjectMapper();

        user = new User();
        user.setId(ObjectId.get().toString());
        user.setEmail("email@test.fi");
        user.setUsername("testUser");
        user.setPassword("TestPassword1234!");
        user.setImageData(null);
        user.setRoles("USER");

    }

     /*
     * 
     * test creating a user that has all of the required fields in correct format.
     * the test should return status ok
     * 
     */

    @Test
    public void testCreateUser_NoFieldErrors() throws Exception {

        when(userServiceInterface.createUser(user)).thenReturn(user);

        mockMvc.perform(post("/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
            .andDo(print())
            .andExpect(status().isOk());


    }

    /*
     * 
     * test creating a user that has name in incorrect format and missing.
     * the test should return client error
     * 
     */

     @Test
     public void testCreateUser_nameErros() throws Exception {

        //test a username too long

        user.setUsername("ausernamethatiswaytoolongfortheapp");
 
         when(userServiceInterface.createUser(user)).thenReturn(user);
 
         mockMvc.perform(post("/register")
             .contentType(MediaType.APPLICATION_JSON)
             .content(objectMapper.writeValueAsString(user)))
             .andDo(print())
             .andExpect(status().is4xxClientError())
             .andExpect(jsonPath("$.username").value("Maximum length for username is 10"));

        //test missing username

        user.setUsername(null);

        when(userServiceInterface.createUser(user)).thenReturn(user);
 
        mockMvc.perform(post("/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("$.username").value("Username is required"));
 
     }

     /*
     * 
     * test creating a user that has email in incorrect format and missing.
     * the test should return client error
     * 
     */

     @Test
     public void testCreateUser_emailErros() throws Exception {

        //test email with no @-sign

        user.setEmail("emailthatisnotincorrectformat");
 
         when(userServiceInterface.createUser(user)).thenReturn(user);
         mockMvc.perform(post("/register")
             .contentType(MediaType.APPLICATION_JSON)
             .content(objectMapper.writeValueAsString(user)))
             .andDo(print())
             .andExpect(status().is4xxClientError())
             .andExpect(jsonPath("$.email").value("Email is in invalid format"));

        //test email with numbers in the end
        
        user.setEmail("test.test@email.56");

        when(userServiceInterface.createUser(user)).thenReturn(user);
         mockMvc.perform(post("/register")
             .contentType(MediaType.APPLICATION_JSON)
             .content(objectMapper.writeValueAsString(user)))
             .andDo(print())
             .andExpect(status().is4xxClientError())
             .andExpect(jsonPath("$.email").value("Email is in invalid format"));

        //test missing email

        user.setEmail(null);

        when(userServiceInterface.createUser(user)).thenReturn(user);
         mockMvc.perform(post("/register")
             .contentType(MediaType.APPLICATION_JSON)
             .content(objectMapper.writeValueAsString(user)))
             .andDo(print())
             .andExpect(status().is4xxClientError());
 
     }

     /*
     * 
     * test creating a user that has email in incorrect format and missing.
     * the test should return client error
     * 
     */

     @Test
     public void testCreateUser_passwordErros() throws Exception {

        String errorMessage="Password should include at least one uppercase letter, one lowercase letter and one digit. Password should be over 8 characters long and no whitespaces are allowed.";

        //test with no uppercase letter
        
        user.setPassword("password1234!!");
 
        when(userServiceInterface.createUser(user)).thenReturn(user);
        mockMvc.perform(post("/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
            .andDo(print())
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("$.password").value(errorMessage));

        //test with no lower case letter

        user.setPassword("PASSWORD1234!!");

        when(userServiceInterface.createUser(user)).thenReturn(user);
            mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.password").value(errorMessage));
        
        //test with no digits
        
        user.setPassword("paswoRD!!&%");

        when(userServiceInterface.createUser(user)).thenReturn(user);
        mockMvc.perform(post("/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
            .andDo(print())
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("$.password").value(errorMessage));

        //test with no white spaces
        
        user.setPassword("paswo RD3! &%");

        when(userServiceInterface.createUser(user)).thenReturn(user);
        mockMvc.perform(post("/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
            .andDo(print())
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("$.password").value(errorMessage));

        //test too short password
        
        user.setPassword("Pa1!!");

        when(userServiceInterface.createUser(user)).thenReturn(user);
        mockMvc.perform(post("/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
            .andDo(print())
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("$.password").value(errorMessage));

        //test missing password

        user.setPassword(null);

        when(userServiceInterface.createUser(user)).thenReturn(user);
        mockMvc.perform(post("/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
            .andDo(print())
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("$.password").value("Password is required"));
 
     }

     @Test
     public void loginUser_loginOk () throws Exception{

        String usernameOrEmail="testlogin@test.fi";
        String password = "TestLogin1234!";

        loginRequest.setUsernameOrEmail(usernameOrEmail);
        loginRequest.setPassword(password);

        Authentication authentication=mock(UsernamePasswordAuthenticationToken.class);
        User loginUser=mock(User.class);

        when(authenticationManager.authenticate(any(Authentication.class)))
            .thenReturn(authentication);

        when(authentication.getPrincipal()).thenReturn(customUserDetails);

        when(customUserDetails.getUser()).thenReturn(loginUser);
        when(loginUser.getEmail()).thenReturn(usernameOrEmail);

        mockMvc.perform(get("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(loginRequest)))
            .andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$.email").value(usernameOrEmail));

     }

    
}
