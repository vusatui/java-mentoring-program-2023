package com.vusatui.cache.guava;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class Main {

    private static final long FIVE_SECONDS = 5 * 1000;

    private static final long TWO_SECONDS = 2;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(100_000)
                .removalListener( n -> {
                    if (n.wasEvicted()) {
                        log(String.format("Evicted cause: %s",  n.getCause().name()));
                    }
                })
                .expireAfterWrite(Duration.ofSeconds(TWO_SECONDS))
                .recordStats()
                .build(new CacheLoader<>() {
                    @Override
                    public String load(String s) {
                        log(String.format("Load: %s", s));
                        return s;
                    }
                });

        cache.put("key", "value");

        log(String.format("Value: %s", cache.get("key")));
        log(String.format("Unchecked value: %s", cache.getUnchecked("hello")));

        Thread.sleep(FIVE_SECONDS);

        log(String.format("Average load penalty: %s", cache.stats().totalLoadTime()));
        log(String.format("Evictions count: %s", cache.stats().evictionCount()));
    }

    private static void log(String message) {
        System.out.println(message);
    }
}