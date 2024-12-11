package koiratreffit.backend.v1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import koiratreffit.backend.v1.interfaces.UserServiceInterface;
import koiratreffit.backend.v1.objects.User;
import koiratreffit.backend.v1.repositories.UserRepository;

@Service
public class UserService implements UserServiceInterface, UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {

        String hashedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException{
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return org.springframework.security.core.userdetails.User
            .builder()
            .username(user.getUserName())
            .password(user.getPassword())
            .roles("USER")
            .build();


    }

    public boolean checkPassword(String givenPassword, String storedPassword){
        return passwordEncoder.matches(givenPassword, storedPassword);
    }

}
