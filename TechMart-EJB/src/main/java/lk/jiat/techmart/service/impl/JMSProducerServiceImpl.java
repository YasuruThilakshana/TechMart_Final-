package lk.jiat.techmart.service.impl;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;

import lk.jiat.techmart.service.JMSProducerService;

@Stateless
public class JMSProducerServiceImpl implements JMSProducerService {

    @Inject
    private JMSContext jmsContext;

    @Resource(lookup = "jms/OrderQueue")
    private Queue orderQueue;

    @Override
    public void sendOrderMessage(Long orderId) {

        jmsContext.createProducer()
                .send(orderQueue, orderId);

        System.out.println("JMS Message Sent : Order ID = " + orderId);
    }
}