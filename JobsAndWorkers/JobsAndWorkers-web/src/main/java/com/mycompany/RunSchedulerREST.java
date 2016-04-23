
package com.mycompany;

import com.mycompany.scheduler.JobScheduler;
import com.mycompany.scheduler.StatisticsSingleton;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/")
public class RunSchedulerREST {
    
    @Inject
    JobScheduler jobScheduler;
    
    @Inject
    StatisticsSingleton statistics;
    
    //http://localhost:8080/JobsAndWorkers-web/jms/run
    
    @POST
    @Path("/run")
    public Integer runScheduler(){
        jobScheduler.runScheduler();
        return 1;
    }
    
    
    //http://localhost:8080/JobsAndWorkers-web/jms/statistics
    @GET
    @Path("/statistics")
    @Produces({MediaType.TEXT_PLAIN})
    public String getStatistics(){
        return statistics.getStatistics().toString();
    }
}
