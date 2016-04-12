/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpafeladat;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;

/**
 *
 * @author basstik
 */
@Entity
@Table(name = "student_table")
@NamedQueries({
    @NamedQuery(name="Student.countOfFriends",
                query="SELECT COUNT(s.friends) FROM Student s"),
    @NamedQuery(name="Person.findByBankCardType",
                query="SELECT s FROM Student s WHERE s.bankCardType = :type")
}) 
public class Student extends Person implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="ID")
    private long id;

    @ManyToMany
    @JoinTable(
            name = "STUDENT_SUBJECT",
            joinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "ID"))
    private List<Subject> subjects;

    
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    
  @ElementCollection
  @CollectionTable(name ="friends_tags")
  private List<String> friends;
    
    public Student() {
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", subjects=" + subjects + ", dateOfBirth=" + dateOfBirth + ", friends=" + friends + '}';
    }

    

}
