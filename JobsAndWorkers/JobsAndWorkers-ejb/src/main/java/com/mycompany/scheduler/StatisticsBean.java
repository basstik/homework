package com.mycompany.scheduler;

import com.mycompany.dto.StatisticsInfo;
import java.util.logging.Logger;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "dzsobTopik")
public class StatisticsBean implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(StatisticsBean.class.getName());

    @Inject
    StatisticsSingleton statSingleton;

    public StatisticsBean() {
        //it is bean
    }

    @Override
    public void onMessage(Message message) {

        StatisticsInfo info;
        try {
            info = message.getBody(StatisticsInfo.class);
            statSingleton.processJobInformations(info);
        } catch (JMSException ex) {
            LOGGER.info(StatisticsBean.class.getName() + ex.toString());
        }

    }

}
