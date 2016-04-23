package com.mycompany.workers;

import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class MediumWorker extends Worker {

    private static final Logger LOG = Logger.getLogger(MediumWorker.class.getName());
    
    public MediumWorker() {
         //it is bean
    }

    @Override
    public void runWorker() {
        LOG.info("In MediumWorker in runWorker methode");
        myThread = new Thread() {
            @Override
            public void run() {
                 workOnJobs("Medium",1);
            }
        };
        myThread.start();
    }

}
