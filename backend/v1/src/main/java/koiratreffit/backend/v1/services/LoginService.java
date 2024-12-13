package koiratreffit.backend.v1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import koiratreffit.backend.v1.objects.CustomUserDetails;
import koiratreffit.backend.v1.objects.Dog;
import koiratreffit.backend.v1.objects.User;
import koiratreffit.backend.v1.repositories.DogRepository;
import koiratreffit.backend.v1.repositories.UserRepository;

/*
 * Login service for the authentication manager to use.
 */

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DogRepository dogRepository;

    /*
     * 
     * fetch the user with email or username and the associated dog to the user
     * 
     * @param usernameOrEmail the username or email to fetch the user with
     * @return UserDetails with the user and users dog
     * 
     */

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(() -> new UsernameNotFoundException("User not found."));
        Dog dog = dogRepository.findById(user.getDogId()).orElseThrow(()->new RuntimeException("Dog not found."));

        return new CustomUserDetails(user, dog);
    }
    
}
