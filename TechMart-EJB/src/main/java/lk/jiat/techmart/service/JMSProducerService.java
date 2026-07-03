package lk.jiat.techmart.service;

import jakarta.ejb.Local;

@Local
public interface JMSProducerService {

    void sendOrderMessage(Long orderId);

}