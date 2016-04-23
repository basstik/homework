package com.mycompany.JMSservice;

import com.mycompany.dto.Job;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;

@Singleton
public class QueueService {

    private static final Logger LOG = Logger.getLogger(QueueService.class.getName());

    @Inject
    private JMSContext jmsContext;

    @Resource(lookup = "jobKju")
    private Queue queue;

    public void clearQueue() throws JMSException {
        Message message;
        JMSConsumer createConsumer = jmsContext.createConsumer(queue);
        do {
            LOG.info("Delete message from queue");
            message = createConsumer.receive(2500);
            if (message != null) {
                message.acknowledge();
            }
        } while (message != null);
    }

    @Lock(LockType.READ)
    public void sendMessageToWorker(Job job) {
        jmsContext.createProducer().send(queue, job);
    }

    @Lock(LockType.READ)
    public Job receiveMessageFromQueue() {
        return  jmsContext.createConsumer(queue).receiveBody(Job.class);
    }

}
