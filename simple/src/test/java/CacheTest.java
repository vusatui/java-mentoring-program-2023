import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

}
