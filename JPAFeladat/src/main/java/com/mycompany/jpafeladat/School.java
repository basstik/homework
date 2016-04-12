package com.mycompany.jpafeladat;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "School.findByHUNCountry",
            query = "SELECT s FROM School s WHERE s.country = 'HUN'"),
    @NamedQuery(name = "School.findById",
            query = "SELECT s FROM School s WHERE s.id = :my_id")
})
public class School extends Institution implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private String country;
    private String zipCode;

    public School() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "School{" + "id=" + id + ", country=" + country + ", zipCode=" + zipCode + '}';
    }

}
