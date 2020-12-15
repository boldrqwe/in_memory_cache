package com.cache.in_memory_cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CacheController cacheController;

    @Autowired
    private CacheService cacheService;


    @Test
    public void contextLoads() throws Exception {
        assertThat(cacheController).isNotNull();
    }

    private Map<String, String> testMap = new LinkedHashMap<>();


    private Set<Object> testSet = new HashSet<>();

    @Before
    public void init() {
        testMap.put("key1", "value1");
        testMap.put("key2", "value2");
        testMap.put("key3", "value3");
        testSet.add("key");


    }

    @Test
    public void getCacheController() throws Exception {
        this.cacheController.set("key1", 2000, "value1");
        this.mockMvc.perform(get("/get")
                .param("key", "key1"))
                .andExpect(status().isOk());
    }

    @Test
    public void setCacheController() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/set")
                .param("key", "key1")
                .param("ttl", "20000")
                .contentType("application/json")
                .content(this.objectMapper.writeValueAsString(this.testMap)))
                .andExpect(status().isOk()).andReturn();
        Elem testElem = this.cacheService.get("key1");
        Assert.assertEquals("{\"key\":\"key1\",\"value\":{},\"ttl\":20000,\"creationTime\":" +
                testElem.getCreationTime() + "}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void delCacheController() throws Exception {
        List testList = new LinkedList();
        testList.add("key");
        Elem testElem = new Elem("key", "value", 2000);
        this.cacheService.set(testElem);
        MvcResult mvcResult = this.mockMvc.perform(post("/del")
                .contentType("application/json")
                .content(this.objectMapper.writeValueAsString(testList)))
                .andExpect(status().isOk()).andReturn();
        Assert.assertEquals("[{\"key\":\"key\",\"value\":\"value\",\"ttl\":2000,\"creationTime\":" +
                        testElem.getCreationTime() + "}]"
                , mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void keysCacheController() throws Exception {
        Elem testElem = new Elem("key", "value", 2000);
        this.cacheService.set(testElem);
        MvcResult mvcResult = this.mockMvc.perform(get("/keys"))
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("[\"key\"]", mvcResult.getResponse().getContentAsString());
    }


}
