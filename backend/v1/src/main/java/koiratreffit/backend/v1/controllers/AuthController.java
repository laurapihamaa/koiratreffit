package koiratreffit.backend.v1.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import koiratreffit.backend.v1.interfaces.UserServiceInterface;
import koiratreffit.backend.v1.objects.User;

@RestController
@CrossOrigin(origins = "http://localhost:8081/")
public class AuthController {

    @Autowired
    private UserServiceInterface userServiceInterface;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

     /**
     * 
     * Post a new user to the database. Use the binding result to check that all the required attributes are provided.
     * 
     * @param user The user to be posted
     * @return ResponseEntity containing the created user if the user has all of the required attributes
     * 
     */
    @PostMapping("/register")
    public ResponseEntity<?>  createUser (@Valid @RequestBody User user, BindingResult bindingResult) {
    	
        //check for any validation errors. Create a key-value map for frontend if yes

        if(bindingResult.hasErrors()){
    	Map<String, String> errorMessages = new HashMap<>();
    
        // Iterate over field errors and add them to the map
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessages.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
             return ResponseEntity.badRequest().body(errorMessages);
        }
    	
    	return ResponseEntity.ok(userServiceInterface.createUser(user));
    	

    }

    /**
     * 
     * Post a new user to the database. Use the binding result to check that all the required attributes are provided.
     * 
     * @param user The user to be posted
     * @return ResponseEntity containing the created user if the user has all of the required attributes
     * 
     */
    @GetMapping("/login")
    public ResponseEntity<?>  loginUser (@RequestBody LoginRequest loginRequest) {


    	
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user, user)
        );
    	
    	return ResponseEntity.ok(userServiceInterface.loginUser(user));
    	

    }
    
}
