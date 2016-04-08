package xyz.codingmentor.ee.dto.constraints;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import xyz.codingmentor.ee.dto.UserDTO;

public class RegistrationAfterBirthdayValidator implements
        ConstraintValidator<RegistrationAfterBirthdayConstraint,UserDTO>{

    @Override
    public void initialize(RegistrationAfterBirthdayConstraint constraintAnnotation) {
        //it isn't used
    }

    @Override
    public boolean isValid(UserDTO user, ConstraintValidatorContext context) {
        if(user.getDateOfBirth() == null){
            return true;
        }
        return user.getRegistrationDate().after(user.getDateOfBirth());
    }

}
