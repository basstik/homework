
package xyz.codingmentor.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
    @NamedQuery (name="GuestBook.findAll",query="SELECT g FROM GuestBook g")
})
@Entity
public class GuestBook implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "park_fk", nullable = false)
    private Park parkId;
    
    @OneToOne
    @JoinColumn(name = "visitor_fk", nullable = false)
    private Visitor visitorId;
    
    @Temporal(TemporalType.DATE)
    private Date entryDate;
    
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Park getParkId() {
        return parkId;
    }

    public void setParkId(Park parkId) {
        this.parkId = parkId;
    }

    public Visitor getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Visitor visitorId) {
        this.visitorId = visitorId;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
    
}
