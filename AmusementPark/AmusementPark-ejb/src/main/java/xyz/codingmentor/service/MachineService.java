package xyz.codingmentor.service;

import xyz.codingmentor.entities.Machine;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
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

    public void deleteMachine(Long idOfMachine) {
        Machine machine = machineFacade.getMachine(idOfMachine);
        machineFacade.remove(machine);
    }

    public Machine updateMachine(Long idOfMachine, Machine machine) {
        machineFacade.getMachine(idOfMachine);
        machine.setId(idOfMachine);
        machineFacade.update(machine);
        return machine;
    }
    
    public Machine getMachineById(Long idOfMachine) {
        return machineFacade.getMachine(idOfMachine);
    }
}
