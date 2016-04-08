package codingmentor.dto.constraints;

import codingmentor.dto.UserDTO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegistrationAfterBirthdayValidator implements
        ConstraintValidator<RegistrationAfterBirthdayConstraint,UserDTO>{

    @Override
    public void initialize(RegistrationAfterBirthdayConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserDTO user, ConstraintValidatorContext context) {
        if(user.getDateOfBirth() == null){
            return true;
        }
        return user.getRegistrationDate().after(user.getDateOfBirth());
    }

}
