package lk.jiat.techmart.service.impl;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import lk.jiat.techmart.dto.OrderMessageDTO;
import lk.jiat.techmart.service.JMSProducerService;

import java.util.logging.Level;
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
    public void sendOrderMessage(OrderMessageDTO orderMessage) {

        try {

            LOGGER.warning("STEP 1 - Producer method entered");

            LOGGER.warning("STEP 2 - Queue = " + orderQueue);

            LOGGER.warning("STEP 3 - Order ID = " + orderMessage.getOrderId());

            jmsContext.createProducer()
                    .send(orderQueue, orderMessage);

            LOGGER.warning("STEP 4 - JMS Message Sent Successfully");

        } catch (Exception e) {

            LOGGER.log(Level.SEVERE,
                    "JMS SEND FAILED", e);

            throw new RuntimeException(e);

        }
    }
}