
package com.mycompany.jpafeladat;


import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;


@Entity
public class Subject implements Serializable{
    @Id
    @GeneratedValue
    @Column(name="ID")
    private long id;
    
    @ManyToMany(mappedBy="subjects")
    private List<Student> students;

    public Subject() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }


    
    
    
}
