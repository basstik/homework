package codingmentor.dto.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = RegistrationAfterBirthdayValidator.class)
@Target(ElementType.TYPE)
@Retention(RUNTIME)
public @interface RegistrationAfterBirthdayConstraint {

    String message() default "{AfterBirthday.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
