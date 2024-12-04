package koiratreffit.backend.v1.validators;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import koiratreffit.backend.v1.customAnnotations.EmailDuplicates;
import koiratreffit.backend.v1.repositories.UserRepository;

public class EmailValidator implements ConstraintValidator<EmailDuplicates, String>{

    @Autowired UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        
        return email==null ? true : !userRepository.existsByEmail(email);
    }
    
}
