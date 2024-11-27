package koiratreffit.backend.v1.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

        Dog dog = dogServiceInterface.getDogDataById(id);

        if(dog==null){
            return ResponseEntity.badRequest().body("No dog is found");
        }

        return ResponseEntity.ok(dog);
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

        Dog dog = dogServiceInterface.getRandomDog();

        if(dog==null){
            return ResponseEntity.badRequest().body("No dog is found");
        }

        return ResponseEntity.ok(dog);
    }

     /**
     * 
     * Post a new dog to the database. Use the binding result to check that all the required attributes are provided.
     * 
     * @param dog The dog to be posted
     * @return ResponseEntity containing the created dog if the dog has all of the required attributes
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
    	
    	return ResponseEntity.ok(dogServiceInterface.createDog(dog));
    	

    }
    
}
