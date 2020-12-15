package com.cache.in_memory_cache;

import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@RestController
public class CacheController {

    private final CacheService<String, Object> cacheService;

    public CacheController(CacheService<String, Object> cacheService) {
        this.cacheService = cacheService;
    }

    @PostMapping("/set")
    public Elem set(@RequestParam String key, @RequestParam long ttl, @RequestBody Object value){
        Elem newElem = new Elem<>(key, value, ttl);
        return  cacheService.set(newElem);
    }

    @GetMapping("/get")
    public Elem<String, Object> getValue(@RequestParam String key) {
        return cacheService.get(key);
    }


    @PostMapping("/del")
    public LinkedList<Elem> delete(@RequestBody List<Object> keyList) {
      return cacheService.del(keyList);
    }

    @GetMapping("/keys")
    public Set<?> keys(){
    return cacheService.keys();
    }

}
