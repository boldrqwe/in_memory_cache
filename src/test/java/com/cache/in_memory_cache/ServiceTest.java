package com.cache.in_memory_cache;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

@RunWith(Parameterized.class)
public class ServiceTest {
    private static final Elem testElem1 = new Elem<>("key1", "value1", 2000);
    private static final Elem testElem2 = new Elem<>(2, new ArrayList<Long>().add(1L), 200);
    private static final Elem testElem3 = new Elem<>("key3", new HashMap<String, Long>().put("key", 1L), 4800);
    private static final Elem testElem4 = new Elem<>(new HashMap<String, Long>().put("key", 1L), "value4", 3000);

    @Parameterized.Parameters
    public static List<Elem> data() {
        return Arrays.asList(new Elem[]{testElem1, testElem2, testElem3, testElem4});
    }

    private Elem elem;

    public ServiceTest(Elem elem) {
        this.elem = elem;
    }


    CacheService cacheService;

    @Before
    public void init() {
        cacheService = new CacheServiceImpl<>(100);
    }


    @Test
    public void setTest() {
        Assert.assertEquals(elem, cacheService.set(elem));
    }


    @Test
    public void getTest() {
        cacheService.set(elem);
        Assert.assertEquals(elem, cacheService.get(elem.getKey()));
    }

    @Test
    public void delTest() {
        LinkedList<Elem> elemList = new LinkedList<>();
        elemList.add(elem);
        LinkedList<Object> keyList = new LinkedList<>();
        keyList.add(elem.getKey());
        cacheService.set(elem);
        Assert.assertEquals(elemList, cacheService.del(keyList));
    }

    @Test
    public void keyTest() {
        cacheService.set(elem);
        Set<Object> keySet = new HashSet<Object>();
        keySet.add(elem.getKey());
        Assert.assertEquals(keySet, cacheService.keys());
    }

    @Test(expected = NullPointerException.class)
    public void timeoutTest(){
        cacheService.set(elem);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cacheService.timeout();
        cacheService.get(elem.getKey());
    }


}
