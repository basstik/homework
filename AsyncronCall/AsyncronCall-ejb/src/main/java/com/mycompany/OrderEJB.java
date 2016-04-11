
package com.mycompany;

import java.util.concurrent.Future;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.criteria.Order;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Asynchronous
public class OrderEJB {
 
    @Resource
    SessionContext ctx;
    
    public void sendOrderToWorkflow() {
        Integer status = 0;
        
        System.out.println("Befor sleep1.\t Thread: "+ Thread.currentThread().getName());
        try {
            Thread.sleep(10000);           //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println("After sleep1.\t Thread: "+ Thread.currentThread().getName());    
        status = 1;
         
        System.out.println("Befor sleep2.\t Thread: "+ Thread.currentThread().getName());
        try {
            Thread.sleep(10000);           //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println("After sleep2.\t Thread: "+ Thread.currentThread().getName());
        
        return;
    }
     
}

