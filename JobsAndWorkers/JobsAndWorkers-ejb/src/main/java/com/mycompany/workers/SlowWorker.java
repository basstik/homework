package com.mycompany.workers;

import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class SlowWorker extends Worker {

    private static final Logger LOG = Logger.getLogger(SlowWorker.class.getName());

    public SlowWorker() {
           //it is bean
    }

    @Override
    public void runWorker() {
        LOG.info("In SlowWorker in runWorker methode");
        new Thread() {
            @Override
            public void run() {
                 workOnJobs("Slow", 2);
            }
        }.start();
    }

}