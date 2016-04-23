package com.mycompany.workers;


import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class SpeedWorker extends Worker {

    private static final Logger LOG = Logger.getLogger(SpeedWorker.class.getName());

    public SpeedWorker() {
        //it is bean
    }

    @Override
    public void runWorker() {
        LOG.info("In SpeedWorker in runWorker methode");
        new Thread() {
            @Override
            public void run() {
                 workOnJobs("Speed", 0.5);
            }
        }.start();
    }

}