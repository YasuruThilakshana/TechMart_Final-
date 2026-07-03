package lk.jiat.techmart.session;

import jakarta.ejb.Local;

import java.util.Map;

@Local
public interface InventoryCacheBean {

    void put(Long productId, Integer quantity);

    Integer get(Long productId);

    void remove(Long productId);

    void clear();

    Map<Long, Integer> getCache();

}