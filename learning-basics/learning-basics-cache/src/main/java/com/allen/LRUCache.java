package com.allen;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * @author JUN
 * @Description 利用LinkedHashMap实现LRU策略的缓存
 * @createTime 11:47
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    
    private final int CACHE_SIZE;
    
    public LRUCache(int cacheSize) {
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        CACHE_SIZE = cacheSize;
    }
    
    @Override
    protected boolean removeEldestEntry(Entry<K, V> eldest) {
        return size() > CACHE_SIZE;
    }
}
