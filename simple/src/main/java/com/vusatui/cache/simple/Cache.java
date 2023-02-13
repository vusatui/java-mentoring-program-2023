package com.vusatui.cache.simple;

public interface Cache <K, V> {

    V get(K key);

    void put(K key, V value);
}
