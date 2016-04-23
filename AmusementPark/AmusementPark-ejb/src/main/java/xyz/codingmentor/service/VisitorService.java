package xyz.codingmentor.service;

import java.util.Date;
import xyz.codingmentor.entities.Visitor;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import xyz.codingmentor.exceptions.NotEnoughAreaException;
import xyz.codingmentor.exceptions.NotEnoughMoneyException;
import xyz.codingmentor.entities.GuestBook;
import xyz.codingmentor.entities.Machine;
import xyz.codingmentor.entities.Park;
import xyz.codingmentor.enumerations.VisitorState;
import xyz.codingmentor.facades.GuestBookFacade;
import xyz.codingmentor.facades.MachineFacade;
import xyz.codingmentor.facades.ParkFacade;
import xyz.codingmentor.facades.VisitorFacade;

@Stateless
public class VisitorService {

    @Inject
    VisitorFacade visitorFacade;

    @Inject
    MachineFacade machineFacade;

    @Inject
    ParkFacade parkFacade;

    @Inject
    GuestBookFacade guestBookFacade;

    public List<Visitor> getVisitors() {
        return visitorFacade.findAllVisitor();
    }

    public Visitor addVisitor(Visitor visitor) {

        return visitorFacade.addVisitor(visitor);
    }

    public void loginToPark(Long ifOfVisitor, Long idOfPark){
        Visitor visitor = visitorFacade.getVisitor(ifOfVisitor);
        Park park = parkFacade.getPark(idOfPark);
        if (visitor.isIsActive()) {
            throw new IllegalArgumentException("The visitor is already in park.");
        }

        Integer moneyOfVisitor = visitor.getMoney();
        Integer prieceOfParkTicket = park.getPriceOfTicket();

        if (moneyOfVisitor < prieceOfParkTicket) {
            throw new NotEnoughMoneyException("ERROR: The " + visitor.getName() + " have" + moneyOfVisitor
                    + " money, but the price of park ticket cost " + prieceOfParkTicket + ".");
        }

        visitor.setEntryDate();
        visitor.setMoney(moneyOfVisitor - prieceOfParkTicket);
        visitor.setIsActive(true);
        visitor.setPark(park);
    }

    public void exitFromPark(Long ifOfVisitor, Long idOfPark){
        Visitor visitor = visitorFacade.getVisitor(ifOfVisitor);
        parkFacade.getPark(idOfPark);
        if (!visitor.isIsActive()) {
            throw new IllegalArgumentException("The visitor is already in park.");
        }
        if (visitor.getState() == VisitorState.ON_MACHINE) {
            throw new IllegalArgumentException("The visitor is already on machine.");

        }

        visitor.setEntryDate(null);
        visitor.setPark(null);
        visitor.setIsActive(false);

    }

    public void boardToMachine(Long idOfVisitor, Long idOfMachine){
        Visitor visitor = visitorFacade.getVisitor(idOfVisitor);
        Machine machine = machineFacade.getMachine(idOfMachine);
        if (!visitor.isIsActive()) {
            throw new IllegalArgumentException("The visitor isn't in park.");
        }

        Integer moneyOfVisitor = visitor.getMoney();
        Integer priceOfMachineTicket = machine.getPriceOfTicket();
        if (moneyOfVisitor < priceOfMachineTicket) {
            throw new NotEnoughMoneyException("The " + visitor.getName() + " have" + moneyOfVisitor
                    + " money, but the ticket cost " + priceOfMachineTicket + ".");
        }

        Integer altOfVisitor = visitor.getAge();
        Integer allowedAge = machine.getMinimumAge();
        if (altOfVisitor < allowedAge) {
            throw new IllegalArgumentException("The visitor(" + altOfVisitor + " old) isn't old enough");
        }

        if (machine.isFull()) {
            throw new NotEnoughAreaException("The machine haven't enough place");

        }

        machine.reduceFreeSpace();
        visitor.setState(VisitorState.ON_MACHINE);
        visitor.setMachine(machine);
    }

    public void unBoardFromMachine(Long idOfVisitor, Long idOfMachine) {
        Visitor visitor = visitorFacade.getVisitor(idOfVisitor);
        Machine machine = machineFacade.getMachine(idOfMachine);
        if (!visitor.isIsActive()) {
            throw new IllegalArgumentException("The visitor isn't in park.");
        }

        machine.increaseFreeSpace();
        visitor.setState(VisitorState.REST);
        visitor.setMachine(null);
    }

    public String writeToGuestBook(Long idOfVisitor, Long idOfPark, String message) {
        Visitor visitor = visitorFacade.getVisitor(idOfVisitor);
        Park park = parkFacade.getPark(idOfPark);

        GuestBook guestBook = new GuestBook();
        guestBook.setComment(message);
        guestBook.setEntryDate(new Date());
        guestBook.setParkId(park);
        guestBook.setVisitorId(visitor);

        return "Thank the feedback";
    }

    public Integer deleteVisitor(Long idOfVisitor) {
        Visitor visitor = visitorFacade.getVisitor(idOfVisitor);
        visitorFacade.remove(visitor);
        return 1;
    }

    public Integer updateVisitor(Long idOfVisitor, Visitor visitor) {
        visitorFacade.getVisitor(idOfVisitor);
        visitor.setId(idOfVisitor);
        visitorFacade.merge(visitor);
        return 1;
    }

}
