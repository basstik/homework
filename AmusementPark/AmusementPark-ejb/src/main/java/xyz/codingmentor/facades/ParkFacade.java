package xyz.codingmentor.facades;

import java.util.List;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;
import xyz.codingmentor.entities.Park;

@Singleton
public class ParkFacade extends EntityFacade {

    @Inject
    MachineFacade machineFacade;

    public ParkFacade() {
        //it is bean
    }

    public List<Park> findAllPark() {
        Query query = entityManager.createNamedQuery("Park.findAll");
        return query.getResultList();
    }

    public void addPark(Park park) {
        create(park);
    }

    public Park getPark(Long idOfPark) {
        Park park = entityManager.find(Park.class, idOfPark);
        if (null == park) {
            throw new NotFoundException("Park doesn't exit in DB.");
        }
        return park;
    }
    
    public void update(Park park){
        merge(park);
    }
    
    public void remove(Park park){
        delete(park);
    }
}
