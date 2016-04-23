
package xyz.codingmentor.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import xyz.codingmentor.enumerations.MachineType;

@NamedQueries({
    @NamedQuery (name="Machine.findAll",query="SELECT m FROM Machine m")
})
@Entity
public class Machine implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    private String fantasiaName;
    @NotNull
    private Integer sizeOfMachine;
    @NotNull
    private Integer priceOfTicket;
    @NotNull
    private Integer maxCapacity;
    @NotNull
    private Integer freeSpace;
    
    @NotNull
    private Integer sellingPrice;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private MachineType type;
    
    @NotNull
    private Integer minimumAge;

    public Machine() {
        //it is entity
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFantasiaName() {
        return fantasiaName;
    }

    public void setFantasiaName(String fantasiaName) {
        this.fantasiaName = fantasiaName;
    }

    public Integer getSizeOfMachine() {
        return sizeOfMachine;
    }

    public void setSizeOfMachine(Integer sizeOfMachine) {
        this.sizeOfMachine = sizeOfMachine;
    }

    public Integer getPriceOfTicket() {
        return priceOfTicket;
    }

    public void setPriceOfTicket(Integer priceOfTicket) {
        this.priceOfTicket = priceOfTicket;
    }


    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        freeSpace=maxCapacity;
        this.maxCapacity = maxCapacity;
    }

    public Integer getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(Integer freeSpace) {
        this.freeSpace = freeSpace;
    }
    
    

    public MachineType getType() {
        return type;
    }

    public void setType(MachineType type) {
        this.type = type;
    }

    public Integer getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(Integer minimumAge) {
        this.minimumAge = minimumAge;
    }
    
    public void increaseFreeSpace(){
        this.freeSpace++;
    }
    
    public void reduceFreeSpace(){
        this.freeSpace--;
    }
    
    public boolean isFull(){
        return 0 == freeSpace;
    }


    

}
