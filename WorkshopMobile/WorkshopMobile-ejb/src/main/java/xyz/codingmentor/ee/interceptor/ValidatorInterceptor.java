package xyz.codingmentor.ee.interceptor;


import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import xyz.codingmentor.ee.annotation.Validate;
import xyz.codingmentor.ee.exception.ValidationException;


@Interceptor
@BeanValidation
public class ValidatorInterceptor {

    @Inject
    private Validator validator;
    
    private static final Logger LOG = Logger.getLogger(ValidatorInterceptor.class.getName());

    
    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        validateParameters(ic.getParameters());
        return ic.proceed();
    }

    private void validateParameters(Object[] parameters) {
        
        Arrays.asList(parameters).stream().filter(p -> 
                p.getClass().isAnnotationPresent(Validate.class)).
                forEach(this::validateBean);
        
        for (Object parameter : parameters) {
           LOG.info(parameter.getClass().toString());
        }
    }

    private void validateBean(Object o) {
        Set<ConstraintViolation<Object>> violations = validator.validate(o);
        Optional<String> errorMessage = violations.stream().
                map(e -> "Validation error: " + e.getMessage()  
                        + " - property: " + e.getPropertyPath().toString() 
                        + " . ").reduce(String::concat);
        if (errorMessage.isPresent()) {
            throw new ValidationException(errorMessage.get());
        }
    }

}
