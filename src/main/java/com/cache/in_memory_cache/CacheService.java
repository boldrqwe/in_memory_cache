package com.cache.in_memory_cache;


import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public interface CacheService<K, V> {
    Elem set(Elem<K, V> elem);

    Elem<K, V> get(K key);

    LinkedList<Elem> del(List<Object> keyList);

    Set<K> keys();

    int getMaxSize();

    void timeout();
}
