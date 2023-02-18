import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.concurrent.atomic.AtomicReference;

import com.vusatui.cache.simple.Cache;
import com.vusatui.cache.simple.CacheImpl;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
class CacheTest {

//    private static final int CACHE_LIMIT = 100_000;

    @Test
    void testLfuStrategy() {
        int cacheLimit = 2;

        Cache<String, String> cache = new CacheImpl<>(cacheLimit);

        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        cache.put("key4", "value4");

        assertNull(cache.get("key1"));
        assertNull(cache.get("key2"));

        assertEquals("value3", cache.get("key3"));
    }

    @Test
    void testRemoveListeners() {
        int cacheLimit = 2;
        AtomicReference<String> removedItemValue = new AtomicReference<>();

        Cache<String, String> cache = new CacheImpl<>(cacheLimit);
        cache.put("key1", "value1");
        cache.put("key2", "value2");

        cache.onRemove(removedItemValue::set);

        cache.put("key2", "value3");

        assertEquals("value2", removedItemValue.get());
    }

    @Test
    void testTtl() throws InterruptedException {
        int cacheLimit = 2;
        long ttl = 3 * 1000;
        Cache<String, String> cache = new CacheImpl<>(cacheLimit, ttl);

        cache.put("key", "value");
        assertEquals("value", cache.get("key"));

        Thread.sleep(ttl + 1000);

        assertNull(cache.get("key"));
    }
}
