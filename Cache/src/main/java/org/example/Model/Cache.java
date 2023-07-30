package org.example.Model;

import lombok.Getter;

@Getter
public class Cache<K, V> {

    K key;
    V value;
    long creationTime;
    long lastAccessTime;

    public Cache(K key, V value) {
        this.key = key;
        this.value = value;
        this.creationTime = System.currentTimeMillis();
        this.lastAccessTime = System.currentTimeMillis();
    }
}

