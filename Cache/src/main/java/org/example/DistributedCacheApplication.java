package org.example;

import org.example.Blogic.Evections.EvectionPolicy;
import org.example.Blogic.Evections.EvectionPolicyImpl.LFUEvection;
import org.example.Blogic.Evections.EvectionPolicyImpl.LRUEvection;
import org.example.Manager.CacheManager;

public class DistributedCacheApplication {
    public static void main(String[] args) {
        System.out.println("START");

        //LRU cache Implementation
        int maxSizeCache = 1;
        System.out.println("LRU " + "Size " + maxSizeCache);

        EvectionPolicy lruEvectionPolicy = new LRUEvection(maxSizeCache);

        CacheManager<String, String> cacheManager = new CacheManager<>(lruEvectionPolicy, maxSizeCache);
        /*Size =1
        key1:value1
        key2:value2
        key3:value3
        key1:null
        key2:null
        key3:value3
         */

        /*Size =2
        key1:value1
        key2:value2
        key3:value3
        key1:null
        key2:value2
        key3:value3
         */

        cacheManager.put("key1", "value1");
        String value1 = cacheManager.get("key1");
        System.out.println("key1" + ":" + value1);


        cacheManager.put("key2", "value2");
        String value2 = cacheManager.get("key2");
        System.out.println("key2" + ":" + value2);

        cacheManager.put("key3", "value3");
        String value3 = cacheManager.get("key3");
        System.out.println("key3" + ":" + value3);

        String value1_1 = cacheManager.get("key1");
        System.out.println("key1" + ":" + value1_1);


        String value2_1 = cacheManager.get("key2");
        System.out.println("key2" + ":" + value2_1);

        String value3_1 = cacheManager.get("key3");
        System.out.println("key3" + ":" + value3_1);


        //LFU cache Implementation

        System.out.println("###################");

        int maxLFUSizeCache = 2;
        System.out.println("LFU " + "Size " + maxLFUSizeCache);
        EvectionPolicy lfuEvectionPolicy = new LFUEvection(maxLFUSizeCache);

        CacheManager<String, Integer> lfuCacheManager = new CacheManager<>(lfuEvectionPolicy, maxLFUSizeCache);
         /*Size =1
       Akshansh:23
       Ashok:25
       Abhay:20
       Ashok_1:null
       Akshansh_1:null
       Ashok_2:null
       Abhay_1:20
         */

        /*Size =2
        Akshansh:23
        Ashok:25
        Abhay:20
        Ashok_1:25
        Akshansh_1:null
        Ashok_2:25
        Abhay_1:20
         */

        lfuCacheManager.put("Akshansh", 23);
        Integer Akshansh = lfuCacheManager.get("Akshansh");
        System.out.println("Akshansh" + ":" + Akshansh);


        lfuCacheManager.put("Ashok", 25);
        Integer Ashok = lfuCacheManager.get("Ashok");
        System.out.println("Ashok" + ":" + Ashok);

        lfuCacheManager.put("Abhay", 20);
        Integer Abhay = lfuCacheManager.get("Abhay");
        System.out.println("Abhay" + ":" + Abhay);


        Integer Ashok_1 = lfuCacheManager.get("Ashok");
        System.out.println("Ashok_1" + ":" + Ashok_1);


        Integer Akshansh_1 = lfuCacheManager.get("Akshansh");
        System.out.println("Akshansh_1" + ":" + Akshansh_1);

        Integer Ashok_2 = lfuCacheManager.get("Ashok");
        System.out.println("Ashok_2" + ":" + Ashok_2);

        Integer Abhay_1 = lfuCacheManager.get("Abhay");
        System.out.println("Abhay_1" + ":" + Abhay_1);

        System.out.println("STOP");
    }
}