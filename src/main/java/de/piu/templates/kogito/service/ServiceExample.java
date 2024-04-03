package de.piu.templates.kogito.service;


import java.util.Map;

import javax.inject.Singleton;

@Singleton
public class ServiceExample {

    public String doSomething(String variableA , String variableB, Map<String,Object> inputVariablesJavaObject) {

        String result = "Services with " + variableA + " " + variableB + " the World!";
        result = result + " with complex input variables : " + inputVariablesJavaObject.toString();

        return result;
    }   
}
