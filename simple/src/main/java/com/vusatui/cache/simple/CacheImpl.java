package com.vusatui.cache.simple;

import static java.util.Objects.isNull;

import java.util.HashMap;
import java.util.Map;

public class CacheImpl<K, V> implements Cache <K, V> {

    private final int maxSize;

    private final Map<K, CacheItem<K, V>> map = new HashMap<>();

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
        item.hit();

        reorder(item);

        return item.getValue();
    }

    @Override
    public void put(K key, V value) {
        CacheItem<K, V> item;
        if (map.containsKey(key)) {
            item = map.get(key);
            item.setValue(value);
            item.hit();
            removeItem(map.get(key));
        } else {
            item = new CacheItem<>(key, value);
            if(map.size() == maxSize) {
                map.remove(first.getKey());
                first = first.getNext();
                first.setPrev(null);
            }
            map.put(key, item);
        }
        reorder(item);
    }

    private void removeItem( CacheItem<K, V> item) {
        if (!isNull(item.getPrev())) {
            item.getPrev().setNext(item.getNext());
        } else {
            first = item.getNext();
        }

        if (!isNull(item.getNext())) {
            item.getNext().setPrev(item.getPrev());
        } else {
            last = item.getPrev();
        }
    }

    private void reorder(CacheItem<K, V> item) {
        if (!isNull(first) && !isNull(last)) {
            CacheItem<K, V> tempItem = first;
            while (!isNull(tempItem)) {
                if (tempItem.getHits() > item.getHits()) {
                    if (tempItem == first) {
                        item.setNext(tempItem);
                        item.setPrev(null);
                        tempItem.setPrev(item);
                        first = item;
                        break;
                    } else {
                        item.setNext(tempItem);
                        item.setPrev(tempItem.getPrev());
                        tempItem.getPrev().setNext(item);
                        tempItem.setPrev(item);
                        break;
                    }
                } else {
                    tempItem = tempItem.getNext();
                    if (isNull(tempItem)) {
                        last.setNext(item);
                        item.setPrev(last);
                        item.setNext(null);
                        last = item;
                        break;
                    }
                }
            }
        } else {
            first = item;
            last = item;
        }
    }
}
