package lk.jiat.techmart.service.impl;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import lk.jiat.techmart.service.JMSProducerService;

import java.util.logging.Logger;

@Stateless
public class JMSProducerServiceImpl implements JMSProducerService {

    private static final Logger LOGGER =
            Logger.getLogger(JMSProducerServiceImpl.class.getName());

    @Inject
    private JMSContext jmsContext;

    @Resource(lookup = "jms/OrderQueue")
    private Queue orderQueue;

    @Override
    public void sendOrderMessage(Long orderId) {

        jmsContext.createProducer()
                .send(orderQueue, orderId.toString());

        LOGGER.info("JMS Message Sent : Order ID = " + orderId);
    }
}