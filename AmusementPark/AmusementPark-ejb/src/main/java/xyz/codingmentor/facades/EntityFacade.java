package xyz.codingmentor.facades;

import java.util.Iterator;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public abstract class EntityFacade {

    @PersistenceContext(unitName = "JpaPUPark")
    protected EntityManager entityManager;

    public EntityFacade() {
        //it is bean
    }

    public <T> T create(T entity) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        if (constraintViolations.size() > 0) {
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<T> cv = iterator.next();
                System.err.println(cv.getRootBeanClass().getName() + "." + cv.getPropertyPath() + " " + cv.getMessage());
            }
        } else {
            entityManager.persist(entity);
        }
        return entity;

    }

    public <T> T merge(T entity) {
        return entityManager.merge(entity);
    }

    public <T> void delete(T entity) {
        entityManager.remove(entity);
    }

    public <T> T find(Class<T> clazz, Object primaryKey) {
        return entityManager.find(clazz, primaryKey);
    }

}
