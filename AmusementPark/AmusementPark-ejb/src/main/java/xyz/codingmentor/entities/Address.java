
package xyz.codingmentor.entities;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Access(AccessType.FIELD)
public class Address implements Serializable{

    @NotNull private String postCode;
    @NotNull private String country;
    @NotNull private String city;
    @NotNull private String street;
    @NotNull private String streetNumber;

    public Address() {
        //it is entity
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }
    
    
    
    
         
}
