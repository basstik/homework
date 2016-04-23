package com.mycompany.workers;

import com.mycompany.dto.Job;
import com.mycompany.dto.StatisticsInfo;
import com.mycompany.JMSservice.QueueService;
import com.mycompany.JMSservice.TopicService;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public abstract class  Worker {

    @Inject
    private QueueService queueService;
    
    @Inject
    private TopicService topicService;

    private static final Logger LOG = Logger.getLogger(Worker.class.getName());

    protected Thread myThread;
    
    public Worker() {
        //is it bean
    }
    
    protected abstract void runWorker(); 
   
    protected void workOnJobs(String workerName, double  speed) {
        LOG.info("The " + workerName + "Worker method start");
   
        while(true){
            Job job = queueService.receiveMessageFromQueue();

            if (null == job) {
                LOG.info("Nullpointer in "+ workerName +"Worker");
                return;
            }
            LOG.info("The " + workerName + "Worker START on the job with " + job.getId() + " ID");
                long multiplier = (int) (1000*speed);
                
            try {
                LOG.info(((job.getWorkTime()) * multiplier) + "millisec work by" + workerName );
                Thread.sleep((job.getWorkTime()) * multiplier);
            } catch (InterruptedException ex) {
                LOG.info(SlowWorker.class.getName() + ex.toString());
            }
            
            StatisticsInfo statisticsInfo = new StatisticsInfo();
            statisticsInfo.setIdOfJob(job.getId());
            statisticsInfo.setDate(new Date());
            topicService.sendMessageToStatisticsBean(statisticsInfo);
            
            LOG.info("The " + workerName + "Worker END the job with " + job.getId() + " ID");

        }
    }

}
