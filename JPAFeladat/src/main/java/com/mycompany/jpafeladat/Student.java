
package com.mycompany.jpafeladat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "student_table")
@NamedQueries({
    @NamedQuery(name = "Student.countOfFriends",
            query = "SELECT COUNT(s.friends) FROM Student s"),
    @NamedQuery(name = "Student.findByBankCardType",
            query = "SELECT s FROM Student s WHERE s.bankCardType = :type")
})
public class Student extends Person implements Serializable {


    @ManyToMany
    @JoinTable(
            name = "STUDENT_SUBJECT",
            joinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "ID"))
    private List<Subject> subjects;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @ElementCollection
    @CollectionTable(name = "friends_tags")
    private List<String> friends;

    public Student() {
        //default constructor,because it is entity
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
