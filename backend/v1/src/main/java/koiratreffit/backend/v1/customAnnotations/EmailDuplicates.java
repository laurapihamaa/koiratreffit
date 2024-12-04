package koiratreffit.backend.v1.customAnnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import koiratreffit.backend.v1.validators.EmailValidator;

@Constraint(validatedBy=EmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD })
public @interface EmailDuplicates {

    String message() default "Email aready exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}

