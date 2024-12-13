package koiratreffit.backend.v1.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import koiratreffit.backend.v1.interfaces.UserServiceInterface;
import koiratreffit.backend.v1.objects.User;
import koiratreffit.backend.v1.repositories.UserRepository;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /*
     * 
     * create a new user to the repository
     * 
     * @param user the user to be created
     * @return the user created
     * 
     */

    @Override
    public User createUser(User user) {

        String hashedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }

}
