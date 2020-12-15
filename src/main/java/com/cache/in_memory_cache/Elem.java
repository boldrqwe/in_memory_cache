package com.cache.in_memory_cache;



public class Elem<K, V>{
    private final K key;
    private final V value;
    private final long ttl;
    private final long creationTime;



    public Elem(K key, V value, long ttl) {
        this.key = key;
        this.value = value;
        this.ttl = ttl;
        this.creationTime = getCurrentTime();

    }

    protected long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public long getTtl() {
        return ttl;
    }

    public long getCreationTime() {
        return creationTime;
    }

    @Override
    public String toString() {
        return "Element{" +
                "key=" + key +
                ", value=" + value +
                ", ttl=" + ttl +
                ", creationTime=" + creationTime +
                '}';
    }
}
