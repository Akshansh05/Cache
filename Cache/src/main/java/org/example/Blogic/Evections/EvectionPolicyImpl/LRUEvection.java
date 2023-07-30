package org.example.Blogic.Evections.EvectionPolicyImpl;

import org.example.Blogic.Evections.EvectionPolicy;

import java.util.LinkedHashMap;

public class LRUEvection<K> implements EvectionPolicy<K> {

    private final LinkedHashMap<K, Long> accessMap;
    private final int MAX_CAPACITY;

    public LRUEvection(int MAX_CAPACITY) {
        this.MAX_CAPACITY = MAX_CAPACITY;
        this.accessMap = new LinkedHashMap<>(MAX_CAPACITY, 0.75F, true); //linkedHM based on insertion order
    }

    @Override
    public void trackRequest(K key) {
        accessMap.put(key, System.currentTimeMillis());
    }

    @Override
    public K evictKey() {
        if (accessMap.size() >= MAX_CAPACITY) {
            K key = accessMap.keySet().iterator().next(); //get element at 0 index which is least used
            accessMap.remove(key);
            return key;
        }
        return null;
    }
}
