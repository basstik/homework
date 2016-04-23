package xyz.codingmentor.facades;

import java.util.List;
import javax.ws.rs.NotFoundException;
import javax.ejb.Singleton;
import javax.persistence.Query;
import xyz.codingmentor.entities.Machine;

@Singleton
public class MachineFacade extends EntityFacade {

    public MachineFacade() {
        //it is bean
    }

    public void addMachine(Machine machine) {
        create(machine);
    }

    public List<Machine> findAllMachine() {
        Query query = entityManager.createNamedQuery("Machine.findAll");
        return query.getResultList();
    }

    public Machine getMachine(Long idOfMachine){
        Machine machine = entityManager.find(Machine.class, idOfMachine);
        if (null == machine) {
             throw new NotFoundException("Machine doesn't exit in DB.");
        }
        return machine;
    }

    public void update(Machine machine) {
        merge(machine);
    }

    public void remove(Machine machine) {
        delete(machine);
    }
}
