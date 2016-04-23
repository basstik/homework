package com.mycompany.scheduler;

import com.mycompany.dto.StatisticsInfo;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class StatisticsSingleton {

    private final Map<Long, Date> jobsMap = new HashMap<>();
    private final Map<Long, String> finishedJobs = new HashMap<>();

    private static final Logger LOG = Logger.getLogger(StatisticsSingleton.class.getName());

    public StatisticsSingleton() {
        //It is bean
    }


    public void processJobInformations(StatisticsInfo info) {

        if (jobsMap.containsKey(info.getIdOfJob())) {
            Date date1 = jobsMap.get(info.getIdOfJob());
            Date date2 = info.getDate();
            long diffInMillies = Math.abs(date1.getTime() - date2.getTime());
            if(diffInMillies <= 5000L){
                finishedJobs.put(info.getIdOfJob(), "Success");
                LOG.info("The job (id: " + info.getIdOfJob() + ") FINISHED in "
                    + (diffInMillies / 1000) + " secondum. This is SUCCESS");
            }else{
                finishedJobs.put(info.getIdOfJob(), " Not success");
                LOG.info("The job (id: " + info.getIdOfJob() + ") FINISHED in "
                    + (diffInMillies / 1000) + " secondum. This isn't success");
            }
        } else {
            LOG.info("Add jobs (id: " + info.getIdOfJob() + ") to map");
            jobsMap.put(info.getIdOfJob(), info.getDate());

        }
    }
    
    public Map<Long, String> getStatistics(){
        return finishedJobs;
    }
}