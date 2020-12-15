package com.cache.in_memory_cache;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

@RunWith(Parameterized.class)
public class ServiceSetTest {
    private static final Elem testElem1 = new Elem<>("key1", "value1", 2000);
    private static final Elem testElem2 = new Elem<>(2, new ArrayList<Long>().add(1L), 200);
    private static final Elem testElem3 = new Elem<>("key3", new HashMap<String, Long>().put("key", 1L), 4800);
    private static final Elem testElem4 = new Elem<>(new HashMap<String, Long>().put("key", 1L), "value4", 3000);

    @Parameterized.Parameters
    public static List<Elem> data() {
       Elem[] eLemArr = new Elem[110];
        for (int i = 0; i < 110; i++) {
            eLemArr[i] = testElem1;
        }
        return Arrays.asList(eLemArr);

    }

    private Elem elem;

    public ServiceSetTest(Elem elem) {
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





}
