package koiratreffit.backend.v1.controllerTests;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import koiratreffit.backend.v1.controllers.DogController;
import koiratreffit.backend.v1.interfaces.DogServiceInterface;
import koiratreffit.backend.v1.objects.Dog;

@SpringBootTest
@AutoConfigureMockMvc
public class DogControllerTest {

    @InjectMocks
    private DogController dogController;

    @Mock
    private DogServiceInterface dogServiceInterface;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(dogController).build();
    }

    @Test
    public void testGetDogById_DogFound() throws Exception {
        Dog dog = new Dog();
        dog.setId(ObjectId.get()); 
        dog.setName("musti");

        when(dogServiceInterface.getDogDataById(dog.getId().toString())).thenReturn(dog);

        mockMvc.perform(get("/dogs/getDog/{id}", dog.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(dog.getName()));
    }

    @Test
    public void testGetDogById_DogNotFound() throws Exception {

        mockMvc.perform(get("/dogs/getDog/{id}", "999L")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
    
}
