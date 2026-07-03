package lk.jiat.techmart.service;

import jakarta.ejb.Local;
import lk.jiat.techmart.dto.OrderMessageDTO;

@Local
public interface JMSProducerService {

    void sendOrderMessage(OrderMessageDTO orderMessage);

}