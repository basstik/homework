
package com.mycompany.dto;

import java.io.Serializable;
import java.util.Date;


public class StatisticsInfo implements Serializable{

    private Long idOfJob;
    private Date date;

    public StatisticsInfo() {
        //default construct
    }

    public Long getIdOfJob() {
        return idOfJob;
    }

    public void setIdOfJob(Long idOfJob) {
        this.idOfJob = idOfJob;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
