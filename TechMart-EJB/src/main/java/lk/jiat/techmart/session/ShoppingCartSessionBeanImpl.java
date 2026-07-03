package lk.jiat.techmart.session;

import jakarta.ejb.Stateful;

import java.util.HashMap;
import java.util.Map;

@Stateful
public class ShoppingCartSessionBeanImpl
        implements ShoppingCartSessionBean {

    private final Map<Long, Integer> cartItems = new HashMap<>();

    @Override
    public void addItem(Long productId, Integer quantity) {

        cartItems.merge(productId, quantity, Integer::sum);

    }

    @Override
    public void removeItem(Long productId) {

        cartItems.remove(productId);

    }

    @Override
    public void clear() {

        cartItems.clear();

    }

    @Override
    public Map<Long, Integer> getItems() {

        return cartItems;

    }
}