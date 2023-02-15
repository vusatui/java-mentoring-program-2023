package com.vusatui.cache.simple;

public class CacheItem <K, V> {

    private final K key;
    private V value;

    private CacheItem<K, V> prev;
    private CacheItem<K, V> next;

    private long hits = 0;

    public CacheItem(K key, V value) {
        this.key = key;
        this.value = value;
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
    }

    long getHits() {
        return hits;
    }
}
