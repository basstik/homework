
package com.mycompany.jpafeladat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Teacher extends Person implements Serializable {
    
    @Temporal(TemporalType.DATE)
    private Date startOfJob;
    
    @ManyToMany
    @JoinTable(
            name = "TEACHER_SUBJECT",
            joinColumns = @JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "ID"))
    private List<Subject> subjects;

    public Teacher() {
         //default constructor,because it is entity
    }

    public Date getStartOfJob() {
        return startOfJob;
    }

    public void setStartOfJob(Date startOfJob) {
        this.startOfJob = startOfJob;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
    
    
}
