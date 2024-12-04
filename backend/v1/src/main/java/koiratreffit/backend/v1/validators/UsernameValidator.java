package koiratreffit.backend.v1.validators;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import koiratreffit.backend.v1.customAnnotations.UsernameDuplicates;
import koiratreffit.backend.v1.repositories.UserRepository;

public class UsernameValidator implements ConstraintValidator<UsernameDuplicates, String>{

    @Autowired UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        
        return username==null ? true : !userRepository.existsByUserName(username);
    }
    
}
