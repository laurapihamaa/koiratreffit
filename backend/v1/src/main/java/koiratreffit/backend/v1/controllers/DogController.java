package koiratreffit.backend.v1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    
}
