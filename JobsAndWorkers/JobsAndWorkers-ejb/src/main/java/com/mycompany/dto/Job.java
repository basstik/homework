
package com.mycompany.dto;

import java.io.Serializable;


public class Job implements Serializable{
    private Long id;
    private Integer workTime;   //in secondum

    public Job() {
        //default constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Integer workTime) {
        this.workTime = workTime;
    }
    
    

}
