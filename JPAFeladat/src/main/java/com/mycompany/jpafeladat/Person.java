/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpafeladat;

import com.mycompany.main.BankCardType;
import javax.persistence.*;


@MappedSuperclass
@AttributeOverrides({
    @AttributeOverride(name="name", column=@Column(name="name_of_person"))
})   
public class Person {
    @Id
    @GeneratedValue
    private long id;
   
    private String name;
    
    @Enumerated(EnumType.STRING)
    private BankCardType bankCardType;
    
    public Person() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BankCardType getBankCardType() {
        return bankCardType;
    }

    public void setBankCardType(BankCardType bankCardType) {
        this.bankCardType = bankCardType;
    }
    
    
    
    
}
