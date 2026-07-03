package lk.jiat.techmart.session;

import jakarta.ejb.Local;

import java.util.Map;

@Local
public interface ShoppingCartSessionBean {

    void addItem(Long productId, Integer quantity);

    void removeItem(Long productId);

    void clear();

    Map<Long, Integer> getItems();

}