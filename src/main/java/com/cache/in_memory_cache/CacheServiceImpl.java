package com.cache.in_memory_cache;

import org.springframework.scheduling.annotation.Scheduled;

import java.lang.ref.SoftReference;
import java.util.*;


public class CacheServiceImpl<K, V> implements CacheService<K,V> {

    private final int maxElements;

    private final Map<K, SoftReference<Elem<K, V>>> elements = new LinkedHashMap<>();


    public CacheServiceImpl(int maxElements) {
        this.maxElements = maxElements;
    }

    public Elem set(Elem<K, V> elem) {
        if (elements.size() == maxElements) {
            K firstKey = elements.keySet().iterator().next();
            elements.remove(firstKey);
        }
        K key = elem.getKey();
        elements.put(key, new SoftReference<>(elem));
        return elements.get(elem.getKey()).get();
    }


    public Elem<K, V> get(K key) {
        SoftReference<Elem<K, V>> element = elements.get(key);
        if (!Objects.isNull(element.get())) {
            return element.get();
        }else{
         throw new RuntimeException("element with this key is not exist");
        }
    }

    public LinkedList<Elem> del(List<Object> keyList){
       LinkedList<Elem> deletedValues = new LinkedList<>();
       for (Object key: keyList) {
           SoftReference<Elem<K, V>> element = elements.get(key);
            deletedValues.add(element.get());
            elements.remove(key);
        }
       return deletedValues;
    }

    @Override
    public Set<K> keys() {
        return elements.keySet();
    }


@Scheduled(fixedRate = 5000)
    public void timeout(){
        if(!Objects.isNull(elements)) {
            for (Map.Entry<K, SoftReference<Elem<K, V>>> element : elements.entrySet()) {
                if (element.getValue().get().getTtl() <=
                        System.currentTimeMillis() - element.getValue().get().getCreationTime()) {
                    elements.remove(element.getKey());
                }
            }
        }
    }

    @Override
    public int getMaxSize() {
        return maxElements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CacheServiceImpl)) return false;
        CacheServiceImpl<?, ?> that = (CacheServiceImpl<?, ?>) o;
        return maxElements == that.maxElements &&
                Objects.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxElements, elements);
    }
}
