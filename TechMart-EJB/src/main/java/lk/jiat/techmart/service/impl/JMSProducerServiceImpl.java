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

            LOGGER.info("Sending Order DTO : " + orderMessage.getOrderId());

            jmsContext.createProducer()
                    .send(orderQueue, orderMessage);

            LOGGER.info("JMS Message Sent Successfully.");

        } catch (Exception e) {

            LOGGER.log(Level.SEVERE,
                    "Failed to send JMS Message", e);

            throw new RuntimeException(
                    "Failed to send JMS Message", e);

        }

    }
}