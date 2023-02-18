package com.vusatui.cache.simple;

import java.sql.Timestamp;

public class CacheItem <K, V> {

    private final K key;

    private V value;

    private Timestamp lastHitTimestamp;

    private CacheItem<K, V> prev;
    private CacheItem<K, V> next;

    private long hits = 0;

    public CacheItem(K key, V value) {
        this.key = key;
        this.value = value;
        hit();
    }

    public CacheItem<K, V> getPrev() {
        return prev;
    }

    public void setPrev(CacheItem<K, V> prev) {
        this.prev = prev;
    }

    public CacheItem<K, V> getNext() {
        return next;
    }

    public void setNext(CacheItem<K, V> next) {
        this.next = next;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    void hit() {
        hits++;
        lastHitTimestamp =  new Timestamp(System.currentTimeMillis());
    }

    long getHits() {
        return hits;
    }

    public Timestamp getLastHitTimestamp() {
        return lastHitTimestamp;
    }
}
