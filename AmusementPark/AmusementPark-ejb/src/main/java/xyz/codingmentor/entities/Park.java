package xyz.codingmentor.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries({
    @NamedQuery(name = "Park.findAll", query = "SELECT p FROM Park p"),
    @NamedQuery(name = "Park.findPoorParks", query = "SELECT p FROM Park p WHERE p.money<50000")
})
@XmlRootElement
@Entity
@Access(AccessType.FIELD)
public class Park implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer money;
    @NotNull
    private Integer area;

    @Embedded
    private Address address;
    
    private Integer priceOfTicket;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "park_machine",
            joinColumns = @JoinColumn(name = "park_fk"),
            inverseJoinColumns = @JoinColumn(name = "machine_fk"))
    private List<Machine> machines;

    public Park() {
        //it is entity
    }

    public Integer getPriceOfTicket() {
        return priceOfTicket;
    }

    public void setPriceOfTicket(Integer priceOfTicket) {
        this.priceOfTicket = priceOfTicket;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    public void addMachine(Machine machine) {
        this.machines.add(machine);
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void removeMachine(Machine machine) {
        this.machines.remove(machine);
    }

}
