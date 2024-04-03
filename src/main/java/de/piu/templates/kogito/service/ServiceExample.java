package de.piu.templates.kogito.service;


import java.util.Map;

import javax.inject.Singleton;

@Singleton
public class ServiceExample {

    public Map<String,Object> doSomething(Map<String,Object> inputVariablesJavaObject) {

        var variableA = inputVariablesJavaObject.get("variableA");
        var variableB = inputVariablesJavaObject.get("variableB");

        String resultText = "Services with " + variableA + " " + variableB + " the World!";
        resultText = resultText + " with complex input variables : " + inputVariablesJavaObject.toString();

        Map<String,Object> result = Map.of("resultText", resultText);

        return result;
    }   
}
