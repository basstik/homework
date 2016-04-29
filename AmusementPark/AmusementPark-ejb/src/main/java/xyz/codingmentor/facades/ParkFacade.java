package xyz.codingmentor.facades;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;
import xyz.codingmentor.entities.Machine;
import xyz.codingmentor.entities.Park;

@Singleton
public class ParkFacade extends EntityFacade<Park> {

    @Inject
    MachineFacade machineFacade;

    public ParkFacade() {
        //it is bean
    }

    public List<Park> findAllPark() {
        Query query = entityManager.createNamedQuery("Park.findAll");
        return query.getResultList();
    }

    public Park getPark(Long idOfPark) {
        Park park = entityManager.find(Park.class, idOfPark);
        if (null == park) {
            throw new NotFoundException("Park doesn't exit in DB.");
        }
        return park;
    }
    
    public List<Machine> findAllMachineInPark(Long idOfPark) {
        Query query = entityManager.createNativeQuery(
                "SELECT machine_fk FROM park_machine WHERE park_fk = ?");
        query.setParameter(1, idOfPark);
        List machineIds = query.getResultList();
        List<Machine> result = new ArrayList<>();
        for (int i = 0; i < machineIds.size(); i++) {
            result.add(machineFacade.getMachine((Long) machineIds.get(i)));
            
        }
        return result;
    }

}
