package lk.jiat.techmart.jms;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;
import jakarta.jms.TextMessage;

import java.util.logging.Logger;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(
                        propertyName = "destinationLookup",
                        propertyValue = "jms/OrderQueue"
                ),
                @ActivationConfigProperty(
                        propertyName = "destinationType",
                        propertyValue = "jakarta.jms.Queue"
                )
        }
)
public class OrderMessageListener implements MessageListener {

    private static final Logger LOGGER =
            Logger.getLogger(OrderMessageListener.class.getName());

    @Override
    public void onMessage(Message message) {

        try {

//            ObjectMessage objectMessage = (ObjectMessage) message;
//
//            Long orderId = (Long) objectMessage.getObject();


            TextMessage textMessage = (TextMessage) message;

            Long orderId = Long.parseLong(textMessage.getText());

            LOGGER.info("========== JMS MESSAGE RECEIVED ==========");
            LOGGER.info("Order ID : " + orderId);

            LOGGER.info("========== JMS MESSAGE RECEIVED ==========");
            LOGGER.info("Order ID : " + orderId);

        } catch (Exception e) {

            LOGGER.severe(e.getMessage());

        }

    }
}