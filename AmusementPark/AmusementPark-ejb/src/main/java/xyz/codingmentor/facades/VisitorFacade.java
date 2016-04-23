package xyz.codingmentor.facades;

import java.util.List;
import javax.ws.rs.NotFoundException;
import javax.ejb.Singleton;
import javax.persistence.Query;
import xyz.codingmentor.entities.Visitor;

@Singleton
public class VisitorFacade extends EntityFacade {


    public VisitorFacade() {
        //it is bean
    }

    public List<Visitor> findAllVisitor() {
        Query query = entityManager.createNamedQuery("Visitor.findAll");
        return query.getResultList();
    }

    public Visitor addVisitor(Visitor visitor) {
        return create(visitor);
    }

    public Visitor getVisitor(Long idOfVisitor) {
        Visitor visitor = entityManager.find(Visitor.class, idOfVisitor);
        if (null == visitor) {
            throw new NotFoundException("Visitor doesn't exit in DB.");
        }
        return visitor;
    }
    
    public void update(Visitor visitor){
        merge(visitor);
    }
    
    public void remove(Visitor visitor){
        delete(visitor);
    }

}
