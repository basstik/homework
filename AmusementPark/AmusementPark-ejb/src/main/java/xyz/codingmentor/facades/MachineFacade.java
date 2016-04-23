package xyz.codingmentor.facades;

import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.NotFoundException;
import javax.ejb.Singleton;
import javax.persistence.Query;
import xyz.codingmentor.entities.Machine;

@Singleton
public class MachineFacade extends EntityFacade {

    private static final Logger LOG = Logger.getLogger(MachineFacade.class.getName());

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

    public Machine getMachine(Long idOfMachine) throws NotFoundException {
        Machine machine = entityManager.find(Machine.class, idOfMachine);
        if (null == machine) {
             throw new NotFoundException("Machine doesn't exit in DB.");
        }
        return machine;
    }

    public void addMachine(Machine machine, Long idOfMachine) {
        create(machine);
    }

//    public boolean existMachineInDB(Long idOfMachine) {
//        try {
//            entityManager.getReference(Machine.class, idOfMachine);
//        } catch (EntityNotFoundException ex) {
//            LOG.info("Machine doesn't exit in DB.");
//            return false;
//        }
//        return true;
//    }

    public void update(Machine machine) {
        merge(machine);
    }

    public void remove(Machine machine) {
        delete(machine);
    }
}
