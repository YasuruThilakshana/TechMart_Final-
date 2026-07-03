package lk.jiat.techmart.service;

import jakarta.ejb.Local;

@Local
public interface NotificationService {

    void sendOrderNotification(Long orderId);

}