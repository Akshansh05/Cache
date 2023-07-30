package org.example.Blogic.Evections.EvectionPolicyImpl;

import org.example.Blogic.Evections.EvectionPolicy;

import java.util.HashMap;
import java.util.Map;

public class LFUEvection<K> implements EvectionPolicy<K> {

    private final HashMap<K, Integer> accessMap;
    private final int MAX_CAPACITY;

    public LFUEvection(int MAX_CAPACITY) {
        this.MAX_CAPACITY = MAX_CAPACITY;
        this.accessMap = new HashMap<>(MAX_CAPACITY);
    }

    @Override
    public void trackRequest(K key) {
        accessMap.put(key, accessMap.getOrDefault(key, 0) + 1);
    }

    @Override
    public K evictKey() {
        K key = null;
        if (accessMap.size() >= MAX_CAPACITY) {
            Integer currMin = Integer.MAX_VALUE;
            for (Map.Entry<K, Integer> entry : accessMap.entrySet()) {
                if (entry.getValue() < currMin) {
                    currMin = entry.getValue();
                    key = entry.getKey();
                }
            }
            accessMap.remove(key);
        }
        return key;
    }
}
