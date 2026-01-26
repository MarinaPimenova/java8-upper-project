package com.hw.jmp.user.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache<K, V> {

    private final Map<K, V> storage = new ConcurrentHashMap<>();

    public void put(K key, V value) {
        storage.put(key, value);
    }

    public V get(K key) {
        return storage.get(key);
    }

    public boolean containsKey(K key) {
        return storage.containsKey(key);
    }

    public Map<K, V> getAll() {
        return storage;
    }
}

