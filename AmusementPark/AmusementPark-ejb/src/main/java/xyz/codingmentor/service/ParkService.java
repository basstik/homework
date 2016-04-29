package xyz.codingmentor.service;

import xyz.codingmentor.entities.Park;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import xyz.codingmentor.exceptions.NotEnoughAreaException;
import xyz.codingmentor.exceptions.NotEnoughMoneyException;
import xyz.codingmentor.entities.Machine;
import xyz.codingmentor.facades.MachineFacade;
import xyz.codingmentor.facades.ParkFacade;

@Stateless
public class ParkService {

    //JNDI Name: jdbc/Park
    //Persistence unit name: JpaPUPark
    //database name: vidampark
    //user: admin1
    //password admin1
    @Inject
    ParkFacade parkFacade;

    @Inject
    MachineFacade machineFacade;

    public List<Park> getParks() {
        return parkFacade.findAllPark();
    }

    public Park addPark(Park park) {
        return parkFacade.create(park);
    }

    public void addMachineToPark(Long idOfPark, Long idOfMachine) {
        Park park = parkFacade.getPark(idOfPark);
        Machine machine = machineFacade.getMachine(idOfMachine);

        Integer moneyOfPark = park.getMoney();
        Integer sizeOfPark = park.getArea();

        Integer priceOfMachine = machine.getSellingPrice();
        Integer sizeOfMachine = machine.getSizeOfMachine();

        if (moneyOfPark < priceOfMachine) {
            throw new NotEnoughMoneyException("ERROR: The " + moneyOfPark + " have" + park.getMoney()
                    + " money, but the machine cost " + priceOfMachine + " money.");
        }
        if (sizeOfPark < sizeOfMachine) {
            throw new NotEnoughAreaException("ERROR: The " + sizeOfPark + " have" + park.getArea()
                    + " area, but the machine muss have " + sizeOfMachine + " area.");
        }

        machine.setIdOfLenderPark(park.getId());
        park.setArea(sizeOfPark - sizeOfMachine);
        park.setMoney(moneyOfPark - priceOfMachine);
        park.addMachine(machine);

    }

    public void deleteMachineFromPark(Long idOfPark, Long idOfMachine) {
        Park park = parkFacade.getPark(idOfPark);
        Machine machine = machineFacade.getMachine(idOfMachine);

        Integer moneyOfPark = park.getMoney();
        Integer sizeOfPark = park.getArea();

        Integer priceOfMachine = machine.getSellingPrice();
        Integer sizeOfMachine = machine.getSizeOfMachine();

        park.setArea(sizeOfPark + sizeOfMachine);
        park.setMoney(moneyOfPark + priceOfMachine);
        park.removeMachine(machine);
    }

    public void deletePark(Long idOfPark) {
        Park park = parkFacade.getPark(idOfPark);
        parkFacade.remove(park);
    }

    public Park updatePark(Long idOfPark, Park park) {
        parkFacade.getPark(idOfPark);
        park.setId(idOfPark);
        parkFacade.update(park);
        return park;
    }

    public Park getParkById(Long idOfPark) {
        return parkFacade.getPark(idOfPark);
    }

    public List<Machine> getMachineListInPark(Long idOfPark) {
        return parkFacade.findAllMachineInPark(idOfPark);
    }

}
