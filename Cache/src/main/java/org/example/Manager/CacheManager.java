package org.example.Manager;

import org.example.Blogic.Evections.EvectionPolicy;
import org.example.Model.Cache;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class CacheManager<K, V> implements ICache<K, V> {

    private final EvectionPolicy<K> evectionPolicy;
    private final int MAX_CAPACITY;
    private final ConcurrentHashMap<K, Cache> cacheMap;

    public CacheManager(EvectionPolicy<K> evectionPolicy, int MAX_CAPACITY) {
        this.evectionPolicy = evectionPolicy;
        this.MAX_CAPACITY = MAX_CAPACITY;
        cacheMap = new ConcurrentHashMap<>(MAX_CAPACITY);
    }

    @Override
    public void put(K key, V value) {
        if (cacheMap.size() >= MAX_CAPACITY) {
            K evictedKey = evectionPolicy.evictKey();
            if (!Objects.isNull(evictedKey))
                cacheMap.remove(evictedKey);
        }
        cacheMap.put(key, new Cache<>(key, value));
    }

    @Override
    public V get(K key) {
        Cache cacheEntry = cacheMap.get(key);
        if (!Objects.isNull(cacheEntry)) {
            evectionPolicy.trackRequest(key);
            return (V) cacheEntry.getValue();
        }
        return null;
    }
}
