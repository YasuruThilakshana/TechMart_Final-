package lk.jiat.techmart.service;

import jakarta.ejb.Local;
import lk.jiat.techmart.dto.OrderMessageDTO;

@Local
public interface NotificationService {

    void sendOrderNotification(OrderMessageDTO order);

    void sendCustomerEmail(OrderMessageDTO order);

    void generateInvoice(OrderMessageDTO order);

    void updateAnalytics(OrderMessageDTO order);

    void notifyAdmin(OrderMessageDTO order);

}