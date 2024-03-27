package de.piu.templates.kogito.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

@Singleton
public class ServiceExample {

    public Map<String,Object> doSomething() {

        Map<String,Object> result = new HashMap<>();
        result.put("key", "value");
        result.put("key2", "value2");
        return result;
       
    }   
    
}
