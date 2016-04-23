package com.mycompany.scheduler;

import com.mycompany.JMSservice.QueueService;
import com.mycompany.JMSservice.TopicService;
import com.mycompany.dto.Job;
import com.mycompany.dto.StatisticsInfo;
import com.mycompany.workers.MediumWorker;
import com.mycompany.workers.SlowWorker;
import com.mycompany.workers.SpeedWorker;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.jms.JMSException;

@Stateless
public class JobScheduler {

    private static final Logger LOG = Logger.getLogger(JobScheduler.class.getName());

    private static Long idCounter = 0L;

    @Inject
    QueueService queueService;

    @Inject
    TopicService topicService;

    @Resource
    TimerService timerService;

    @Inject
    SlowWorker slowWorker;

    @Inject
    SpeedWorker speedWorker;

    @Inject
    MediumWorker mediumWorker;

    public void runScheduler() {
        LOG.info("In runScheduler method");

        try {
            queueService.clearQueue();
        } catch (JMSException ex) {
            Logger.getLogger(JobScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }

        timerService.createCalendarTimer(new ScheduleExpression()
                .hour("*").minute("*").second("0"));
        
        slowWorker.runWorker();
        speedWorker.runWorker();
        mediumWorker.runWorker();
    }

    @Timeout
    public synchronized void createTenJobsPerMinutes() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Job job = new Job();
            Long jobId = idCounter++;
            int timeOfJob = 1+(int) (Math.random() * 5);
            LOG.info("New job with id: " + jobId + " and work time: " + timeOfJob+ " secodum");
            job.setId(jobId);
            job.setWorkTime(timeOfJob);

            StatisticsInfo statisticsInfo = new StatisticsInfo();
            statisticsInfo.setIdOfJob(jobId);
            statisticsInfo.setDate(new Date());
            topicService.sendMessageToStatisticsBean(statisticsInfo);

            queueService.sendMessageToWorker(job);

        }
    }

}
