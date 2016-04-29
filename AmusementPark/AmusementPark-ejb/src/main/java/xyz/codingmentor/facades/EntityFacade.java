package xyz.codingmentor.facades;

import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public abstract class EntityFacade<T> {

    private static final Logger LOG = Logger.getLogger(EntityFacade.class.getName());

    @PersistenceContext(unitName = "JpaPUPark")
    protected EntityManager entityManager;

    public EntityFacade() {
        //it is bean
    }

    public T create(T entity) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        if (!constraintViolations.isEmpty()) {
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<T> cv = iterator.next();
                LOG.info(cv.getRootBeanClass().getName() + "." + cv.getPropertyPath() + " " + cv.getMessage());
            }
        } else {
            entityManager.persist(entity);
        }
        return entity;

    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public void remove(T entity) {
        entityManager.remove(entity);
    }

    public T find(Class<T> clazz, Object primaryKey) {
        return entityManager.find(clazz, primaryKey);
    }

}
