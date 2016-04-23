package xyz.codingmentor.service;

import xyz.codingmentor.entities.Machine;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import xyz.codingmentor.facades.MachineFacade;

@Stateless
public class MachineService {

    @Inject
    MachineFacade machineFacade;

    public List<Machine> getMachines() {
        return machineFacade.findAllMachine();
    }

    public Machine addMachine(Machine machine) {

        return machineFacade.create(machine);
    }

    public Integer deleteMachine(Long idOfMachine) throws NotFoundException {
        Machine machine = machineFacade.getMachine(idOfMachine);
        machineFacade.remove(machine);
        return 1;
    }

    public Integer updateMachine(Long idOfMachine, Machine machine) throws NotFoundException {
        Machine machineInDB = machineFacade.getMachine(idOfMachine);
        machine.setId(idOfMachine);
        machineFacade.merge(machine);
        return 1;
    }
}
