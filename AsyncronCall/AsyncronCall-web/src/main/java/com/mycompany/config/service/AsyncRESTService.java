/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.config.service;

import com.mycompany.OrderEJB;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/test")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
@SessionScoped
public class AsyncRESTService implements Serializable {

    //http://localhost:8080/AsyncronCall-web/asyncron/test/sleepOhneReturn
    @Inject
    OrderEJB orderEJB;

    @GET
    @Path("/sleepOhneReturn")
    public String testAsyncronFunctionsOhneReturn() {

        System.out.println("Before first asyncron call");
        orderEJB.sendOrderToWorkflowOhneReturn();
        System.out.println("After first asyncron call");

        System.out.println("Before second asyncron call");
        orderEJB.sendOrderToWorkflowOhneReturn();
  
        System.out.println("After second asyncron call");
        /*
            We can see in the GlassFish log, that the client call twice 
            the sendOrderToWorkflow and this function can simultan run 
            in diffrent thread.
         */
        return "The testAsyncronFunctionOhenResult been end.";

    }

    //http://localhost:8080/AsyncronCall-web/asyncron/test/sleepWithReturn
    @GET
    @Path("/sleepWithReturn")
    public String testAsyncronFunctionsWithReturn() throws InterruptedException, ExecutionException {
        

        System.out.println("Submitting Task ...");
        Future future = orderEJB.sendOrderToWorkflowWithReturn();
        System.out.println("Task is submitted");
        
        while (!future.isDone()) {
            System.out.println("Task is not completed yet....");
            Thread.sleep(1000); //sleep for 1 millisecond before checking again
        }
        
        System.out.println("Task is completed, let's check result");
        Integer result = (Integer) future.get();
        System.out.println("The returned value is" + result);
        
        return "The testAsyncronFunctionWithReturn been end.";

    }
}
