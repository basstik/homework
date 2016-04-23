package xyz.codingmentor.entities;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import xyz.codingmentor.enumerations.VisitorState;

@NamedQueries({
    @NamedQuery(name = "Visitor.findAll", query = "SELECT v FROM Visitor v")
})
@Entity
public class Visitor implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    private VisitorState state;

    @NotNull
    private Integer money;

    @Temporal(TemporalType.DATE)
    private Calendar entryDate;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Calendar dateOfBirth;

    @Transient
    private Integer age;
    private boolean isActive;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine_fk", nullable = true)
    private Machine machine;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "park_fk", nullable = true)
    private Park park;

    public Visitor() {
    }

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }



    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
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

    public VisitorState getState() {
        return state;
    }

    public void setState(VisitorState state) {
        this.state = state;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }



    public Integer getAge() {

        Calendar birth = dateOfBirth;
        Calendar today = Calendar.getInstance();
        int calculateAge = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) <= birth.get(Calendar.DAY_OF_YEAR)) {
            calculateAge--;
        }
        return calculateAge;
    }

//    public void setAge(Integer age) {
//        this.age = age;
//    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Calendar getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Calendar entryDate) {
        this.entryDate = entryDate;
    }
    
    public void setEntryDate() {
        this.entryDate = Calendar.getInstance();
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

//    public void setDateOfBirth(String date) throws ParseException {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        this.dateOfBirth.setTime(format.parse(date));
//    }

    public void setDateOfBirth(Calendar dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
    
        this.dateOfBirth = dateOfBirth;
    }
    
    

    
    
    

}
