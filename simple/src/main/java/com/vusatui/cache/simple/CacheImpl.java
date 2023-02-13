package com.vusatui.cache.simple;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.HashMap;
import java.util.Map;

public class CacheImpl<K, V> implements Cache <K, V> {

    private final int maxSize;

    private final Map<K, CacheItem<K, V>> map = new HashMap<>();

    private int size = 0;

    private CacheItem<K, V> first;

    private CacheItem<K, V> last;

    public CacheImpl(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }

        CacheItem<K, V> item = map.get(key);

        if (nonNull(item.getPrev())) {
            item.getPrev().setNext(item.getNext());
        }

        item.setNext(first);
        item.setPrev(null);
        first = item;
        return item.getValue();
    }

    @Override
    public void put(K key, V value) {
        CacheItem<K, V> item = new CacheItem<>(key, value);
        map.put(key, item);

        if (isNull(first)) {
            first = item;
        }
        if (isNull(last)) {
            last = item;
        }
        if(size == maxSize) {
            map.remove(last.getKey());
            last = last.getPrev();
        } else {
            size++;
        }

        item.setNext(first);
        first.setPrev(item);
        first = item;
    }
}
