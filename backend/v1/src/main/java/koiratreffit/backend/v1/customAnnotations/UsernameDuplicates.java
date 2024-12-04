package koiratreffit.backend.v1.customAnnotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import koiratreffit.backend.v1.validators.UsernameValidator;

@Constraint(validatedBy=UsernameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD })
public @interface UsernameDuplicates {

    String message() default "Username is already taken";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}
