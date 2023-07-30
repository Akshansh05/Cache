package org.example.Blogic.Evections;

public interface EvectionPolicy<K> {

    void trackRequest(K key);

    K evictKey();
}
