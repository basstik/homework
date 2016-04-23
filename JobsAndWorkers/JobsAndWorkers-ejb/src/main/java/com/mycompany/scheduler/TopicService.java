package com.mycompany.scheduler;

import com.mycompany.dto.StatisticsInfo;
import javax.annotation.Resource;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;

@Singleton
public class TopicService {

    @Inject
    private JMSContext jmsContext;

    @Resource(lookup = "dzsobTopik")
    private Topic topic;

    @Lock(LockType.READ)
    public void sendMessageToStatisticsBean(StatisticsInfo statInfo) {
        jmsContext.createProducer().send(topic, statInfo);

    }
}
