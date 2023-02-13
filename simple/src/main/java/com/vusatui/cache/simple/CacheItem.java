package com.vusatui.cache.simple;

public class CacheItem <K, V> {

    private final K key;
    private final V value;

    private CacheItem<K, V> prev;
    private CacheItem<K, V> next;

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
}
