package koiratreffit.backend.v1.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import koiratreffit.backend.v1.interfaces.UserServiceInterface;
import koiratreffit.backend.v1.objects.CustomUserDetails;
import koiratreffit.backend.v1.objects.Dog;
import koiratreffit.backend.v1.objects.LoginRequest;
import koiratreffit.backend.v1.objects.User;
import koiratreffit.backend.v1.objects.UserLoginResponse;

@RestController
@CrossOrigin(origins = "http://localhost:8081/")
public class AuthController {

    @Autowired
    private UserServiceInterface userServiceInterface;

    @Autowired
    private AuthenticationManager authenticationManager;

     /**
     * 
     * Post a new user to the database. Use the binding result to check that all the required attributes are provided.
     * 
     * @param user The user to be posted
     * @return ResponseEntity of ok if the user had all required attributes and creation was succesfull.
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
    	
        try {
            userServiceInterface.createUser(user);
            return ResponseEntity.ok("User created succesfully");           
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An exeption occured. Please try again later.");
        }	

    }

    /**
     * 
     * Login a user with username or email and password
     * 
     * @param loginRequest The login request sent
     * @return ResponseEntity containing the user details if the login was succesfull, otherwise return error
     * 
     */
    @GetMapping("/login")
    public ResponseEntity<?>  loginUser (@RequestBody LoginRequest loginRequest) {

        try{

            //create token for the authentication request 
            Authentication authentication = new UsernamePasswordAuthenticationToken(
            loginRequest.getUsernameOrEmail(), loginRequest.getPassword());

            //test authentication with the given credentials
            authentication = authenticationManager.authenticate(authentication);

            //retrieve the object attached to the authentication
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

            User loggedUser = customUserDetails.getUser();
            Dog usersDog = customUserDetails.getDog();

            //create login response containing username, email, imagedata and users dog
            UserLoginResponse loginResponse = new UserLoginResponse(
                loggedUser.getUsername(),
                loggedUser.getEmail(),
                loggedUser.getImageData(),
                usersDog
            );

            return ResponseEntity.ok(loginResponse);

        } catch (BadCredentialsException ex){
            return ResponseEntity.badRequest().body("Invalid credentials.");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body("Username/email not found.");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
    }
        
    	

    }
    
}
