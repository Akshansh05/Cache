package CacheTest;

import org.example.Blogic.Evections.EvectionPolicy;
import org.example.Blogic.Evections.EvectionPolicyImpl.LRUEvection;
import org.example.Manager.CacheManager;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LRUCacheTest {

    //ordered test cases else randomly executing
    private static EvectionPolicy evectionPolicy;
    private static CacheManager<String, String> cacheManager;
    private static boolean setUpIsDone = false;
    private int maxSizeCache;

    @Before
    public void setup() {
        if (setUpIsDone) {
            return;
        }
        maxSizeCache = 2;
        evectionPolicy = new LRUEvection(maxSizeCache);
        cacheManager = new CacheManager<>(evectionPolicy, maxSizeCache);
        setUpIsDone = true;
    }

    @Test
    public void Order1_getCacheStatusKey1() {
        cacheManager.put("key1", "value1");
        String value1 = cacheManager.get("key1");
        assertEquals(value1, "value1");
    }

    @Test
    public void Order2_getCacheStatusKey2() {
        cacheManager.put("key2", "value2");
        String value2 = cacheManager.get("key2");
        assertEquals(value2, "value2");
    }

    @Test
    public void Order3_getCacheStatusKey3() {
        cacheManager.put("key3", "value3");
        String value3 = cacheManager.get("key3");
        assertEquals(value3, "value3");
    }


    @Test
    public void Order4_getCacheStatusKey1SecondTime() {
        String value1_1 = cacheManager.get("key1");
        assertNull(value1_1);
    }

    @Test
    public void Order5_getCacheStatusKey2SecondTime() {
        String value2_1 = cacheManager.get("key2");
        assertEquals(value2_1, "value2");
    }

    @Test
    public void Order6_getCacheStatusKey3SecondTime() {
        String value3_1 = cacheManager.get("key3");
        assertEquals(value3_1, "value3");
    }
}
