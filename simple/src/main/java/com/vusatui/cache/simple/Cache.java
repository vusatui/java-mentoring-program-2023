package com.vusatui.cache.simple;

import java.util.function.Consumer;

public interface Cache <K, V> {

    V get(K key);

    void put(K key, V value);

    void onRemove(Consumer<V> listener);
}
