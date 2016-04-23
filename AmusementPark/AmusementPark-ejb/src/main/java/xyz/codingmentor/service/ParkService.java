package xyz.codingmentor.service;

import xyz.codingmentor.entities.Park;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import xyz.codingmentor.Exceptions.NotEnoughAreaException;
import xyz.codingmentor.Exceptions.NotEnoughMoneyException;
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

    public Integer addMachineToPark(Long idOfPark, Long idOfMachine) throws NotEnoughMoneyException, NotEnoughAreaException, NotFoundException {
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

        park.setArea(sizeOfPark - sizeOfMachine);
        park.setMoney(moneyOfPark - priceOfMachine);
        park.addMachine(machine);

        return 1;

    }

    public Integer deleteMachineFromPark(Long idOfPark, Long idOfMachine) throws NotFoundException {
        Park park = parkFacade.getPark(idOfPark);
        Machine machine = machineFacade.getMachine(idOfMachine);

        Integer moneyOfPark = park.getMoney();
        Integer sizeOfPark = park.getArea();

        Integer priceOfMachine = machine.getSellingPrice();
        Integer sizeOfMachine = machine.getSizeOfMachine();

        park.setArea(sizeOfPark + sizeOfMachine);
        park.setMoney(moneyOfPark + priceOfMachine);
        park.removeMachine(machine);

        return 1;

    }

    public Integer deletePark(Long idOfPark) throws NotFoundException {
        Park park = parkFacade.getPark(idOfPark);
        parkFacade.remove(park);
        return 1;
    }

    public Integer updatePark(Long idOfPark, Park park) throws NotFoundException {
        Park parkInDB = parkFacade.getPark(idOfPark);
        park.setId(idOfPark);
        parkFacade.merge(park);
        return 1;
    }

}
