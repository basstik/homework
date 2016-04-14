
package com.mycompany.jpafeladat;

import com.mycompany.main.BankCardType;
import javax.persistence.*;

@MappedSuperclass
@AttributeOverrides({
    @AttributeOverride(name = "name", column = @Column(name = "name_of_person"))
})
public class Person {

    @Id
    @GeneratedValue
    protected long id;

    protected String name;

    @Enumerated(EnumType.STRING)
    protected BankCardType bankCardType;

    public Person() {
        //default constructor,because it is entity
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
