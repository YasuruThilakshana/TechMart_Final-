package lk.jiat.techmart.session;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
@Startup
public class InventoryCacheBeanImpl implements InventoryCacheBean {

    private Map<Long, Integer> inventoryCache;

    @PostConstruct
    public void init() {

        inventoryCache = new ConcurrentHashMap<>();

        System.out.println("InventoryCacheBean initialized...");

    }

    @Override
    public void put(Long productId, Integer quantity) {

        inventoryCache.put(productId, quantity);

    }

    @Override
    public Integer get(Long productId) {

        return inventoryCache.get(productId);

    }

    @Override
    public void remove(Long productId) {

        inventoryCache.remove(productId);

    }

    @Override
    public void clear() {

        inventoryCache.clear();

    }

    @Override
    public Map<Long, Integer> getCache() {

        return inventoryCache;

    }

}