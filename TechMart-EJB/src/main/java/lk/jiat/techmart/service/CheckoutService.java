package lk.jiat.techmart.service;

import jakarta.ejb.Local;
import lk.jiat.techmart.entity.Order;

@Local
public interface CheckoutService {

    Order placeOrder(Long userId);

}