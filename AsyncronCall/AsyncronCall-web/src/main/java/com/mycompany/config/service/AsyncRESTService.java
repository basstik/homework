/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.config.service;

import com.mycompany.OrderEJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.criteria.Order;
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

    //http://localhost:8080/AsyncronCall-web/asyncron/test/sleep
    
    @Inject
    OrderEJB orderEJB;

    @GET
    @Path("/sleep")
    public String testAsyncronFunctions(@Context HttpServletRequest request) {

        System.out.println("Before first asyncron call");
        orderEJB.sendOrderToWorkflow();
        System.out.println("After first asyncron call");

        System.out.println("Before second asyncron call");
        orderEJB.sendOrderToWorkflow();
        System.out.println("After second asyncron call");
        /*
            We can see in the GlassFish log, that the client call twice 
            the sendOrderToWorkflow and this function can simultan run 
            in diffrent thread.
        */
        return "The testAsyncronFunction been end.";

    }
}
