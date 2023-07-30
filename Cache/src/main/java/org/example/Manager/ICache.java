package org.example.Manager;

public interface ICache<K, V> {

    void put(K key, V value);

    V get(K key);
}
