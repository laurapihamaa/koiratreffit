package koiratreffit.backend.v1.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import koiratreffit.backend.v1.interfaces.DogServiceInterface;
import koiratreffit.backend.v1.objects.Dog;

@RestController
@CrossOrigin(origins = "http://localhost:8081/")
public class DogController {

    @Autowired
    private DogServiceInterface dogServiceInterface;

    /*
     * 
     * Retrieve a dog with the given id
     * 
     * @param id the id of the dog
     * @return ResponseEntity containing the dog if the dog is found
     * 
     */

    @GetMapping("/dogs/getDog/{id}")
    public ResponseEntity<?> getDogById(@PathVariable String id){

        try {
            Dog dog = dogServiceInterface.getDogDataById(id);
            return ResponseEntity.ok(dog);
            
        }catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No dog found with ID: " + id);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured. Please try again later");
        }    
    }

    /*
     * 
     * Retrieve a random dog
     * 
     * @return ResponseEntity containing a dog if any dog is found
     * 
     */

    @GetMapping("/dogs/getRandomDog")
    public ResponseEntity<?> getRandomDog(){

        try {
        Dog dog = dogServiceInterface.getRandomDog();
        return ResponseEntity.ok(dog);

        }catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No dogs found");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured. Please try again later");
        } 
    }

     /**
     * 
     * Post a new dog to the database. Use the binding result to check that all the required attributes are provided.
     * 
     * @param dog The dog to be posted
     * @return ResponseEntity of ok if the dog has all of the required attributes
     * 
     */
    @PostMapping("/dogs/createNewDog")
    public ResponseEntity<?>  createDog (@Valid @RequestBody Dog dog, BindingResult bindingResult) {
    	
        //check for any validation errors. Create a key-value map for frontend if yes

        if(bindingResult.hasErrors()){
    	Map<String, String> errorMessages = new HashMap<>();
    
        // Iterate over field errors and add them to the map
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessages.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
             return ResponseEntity.badRequest().body(errorMessages);
        }
    	
    	try {
            dogServiceInterface.createDog(dog);
            return ResponseEntity.ok("dog created succesfully");
        }catch(Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured. Please try again later");
            } 
    	

    }
    
}
