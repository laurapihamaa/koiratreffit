package koiratreffit.backend.v1.controllerTests;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import koiratreffit.backend.v1.configurations.SecurityConfig;
import koiratreffit.backend.v1.controllers.DogController;
import koiratreffit.backend.v1.interfaces.DogServiceInterface;
import koiratreffit.backend.v1.objects.Dog;
import koiratreffit.backend.v1.services.LoginService;

@WebMvcTest(DogController.class)
@Import(SecurityConfig.class)
public class DogControllerTest {

    @MockBean
    private DogServiceInterface dogServiceInterface;

    @MockBean
    LoginService loginService;

    @Autowired
    private MockMvc mockMvc;

    private Dog dog;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {

        objectMapper = new ObjectMapper();

        dog = new Dog();
        dog.setId(ObjectId.get()); 
        dog.setBreed("golden retriever");
        dog.setName("musti");
        dog.setAge(5.5);
        dog.setDescription("some description of the dog");
        dog.setImageData("");
        dog.setGender("male");
        dog.setLocation("test location");

    }

    /*
     * 
     * test fecthing the dog using its id. The test should return status ok.
     * 
     */

    @Test
    public void testGetDogById_DogFound() throws Exception {

        when(dogServiceInterface.getDogDataById(dog.getId().toString())).thenReturn(dog);

        mockMvc.perform(get("/dogs/getDog/{id}", dog.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(dog.getName()));
    }

    /*
     * 
     * test fetching the dog with it not being present in the database
     * the test should return client error.
     * 
     */

    @Test
    public void testGetDogById_DogNotFound() throws Exception {

        mockMvc.perform(get("/dogs/getDog/{id}", "999L")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    /*
     * 
     * test fetching any dog from the database
     * the test should return one dog and status ok.
     * 
     */

    @Test
    public void testGetRandomDog_DogFound() throws Exception {

        when(dogServiceInterface.getRandomDog()).thenReturn(dog);

        mockMvc.perform(get("/dogs/getRandomDog")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    /*
     * 
     * test fetching any dog with no dogs found from the database
     * the test should return client error
     * 
     */

    @Test
    public void testGetRandomDog_DogNotFound() throws Exception {

        mockMvc.perform(get("/dogs/getRandomDog")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is4xxClientError());
    }

    /*
     * 
     * test creating a dog that has all of the required fields.
     * the test should return status ok
     * 
     */

    @Test
    public void testCreateDog_NoFieldErrors() throws Exception {

        when(dogServiceInterface.createDog(dog)).thenReturn(dog);

        mockMvc.perform(post("/dogs/createNewDog")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dog)))
            .andDo(print())
            .andExpect(status().isOk());


    }

    /*
     * 
     * test creating a dog with no required fields given.
     * test should return client error and the error messages in the body
     * 
     */

    @Test
    public void testCreateDog_FieldErrors() throws Exception {

        Dog testDog = new Dog();

        when(dogServiceInterface.createDog(testDog)).thenReturn(testDog);

        mockMvc.perform(post("/dogs/createNewDog")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(testDog)))
            .andDo(print())
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("$.gender").value("Gender is required"))
            .andExpect(jsonPath("$.name").value("Name is required"))
            .andExpect(jsonPath("$.breed").value("Breed is required"))
            .andExpect(jsonPath("$.age").value("Age is required"))
            .andExpect(jsonPath("$.location").value("Location is required"));


    }
    
}
