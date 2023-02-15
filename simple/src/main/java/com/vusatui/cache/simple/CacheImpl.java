package com.vusatui.cache.simple;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class CacheImpl<K, V> implements Cache <K, V> {

    private final int maxSize;

    private final Map<K, CacheItem<K, V>> map = new HashMap<>();

    private final List<Consumer<V>> removeListeners = new ArrayList<>();

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
            item.hit();
            removeItem(map.get(key));
            item.setValue(value);
        } else {
            item = new CacheItem<>(key, value);
            if(map.size() == maxSize) {
                map.remove(first.getKey());
                removeItem(first);
            }
            map.put(key, item);
        }
        reorder(item);
    }

    private void removeItem(CacheItem<K, V> item) {
        removeListeners.forEach(listener -> listener.accept(item.getValue()));

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

    public void onRemove(Consumer<V> listener) {
        removeListeners.add(listener);
    }
}
