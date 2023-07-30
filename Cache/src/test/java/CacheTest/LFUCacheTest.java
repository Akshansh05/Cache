package CacheTest;

import org.example.Blogic.Evections.EvectionPolicy;
import org.example.Blogic.Evections.EvectionPolicyImpl.LFUEvection;
import org.example.Manager.CacheManager;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LFUCacheTest {

    //ordered test cases else randomly executing
    private static EvectionPolicy evectionPolicy;
    private static CacheManager<String, Integer> lfuCacheManager;
    private static boolean setUpIsDone = false;
    private int maxSizeCache;

    @Before
    public void setup() {
        if (setUpIsDone) {
            return;
        }
        maxSizeCache = 1;
        evectionPolicy = new LFUEvection(maxSizeCache);
        lfuCacheManager = new CacheManager<>(evectionPolicy, maxSizeCache);
        setUpIsDone = true;
    }

    @Test
    public void  Order1_getCacheStatusKey1() {
        lfuCacheManager.put("Akshansh", 23);
        int Akshansh = lfuCacheManager.get("Akshansh");
        assertEquals(Akshansh, 23);
    }

    @Test
    public void Order2_getCacheStatusKey2() {
        lfuCacheManager.put("Ashok", 25);
        int Ashok = lfuCacheManager.get("Ashok");
        assertEquals(Ashok, 25);
    }

    @Test
    public void Order3_getCacheStatusKey3() {
        lfuCacheManager.put("Abhay", 20);
        int Abhay = lfuCacheManager.get("Abhay");
        assertEquals(Abhay, 20);
    }

    @Test
    public void Order4_getCacheStatusKeyAshokAgain() {
        Integer Ashok_1 = lfuCacheManager.get("Ashok");
        assertNull(Ashok_1);
    }

    @Test
    public void Order5_getCacheStatusKeyAkshanshAgain() {
        Integer Akshansh_1 = lfuCacheManager.get("Akshansh");
        assertNull(Akshansh_1);
    }

    @Test
    public void Order6_getCacheStatusKeyAshokAgainTwice() {
        Integer Ashok_2 = lfuCacheManager.get("Ashok");
        assertNull(Ashok_2);
    }

    @Test
    public void Order7_getCacheStatusKeyAbhayAgain() {
        int Abhay_1 = lfuCacheManager.get("Abhay");
        assertEquals(Abhay_1, 20);
    }
}
