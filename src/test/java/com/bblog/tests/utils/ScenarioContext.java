package com.bblog.tests.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ScenarioContext {

    private Map<String, Object> scenarioContext;

    public ScenarioContext() {
        scenarioContext = new HashMap<>();
    }

    public void setContext(DataKeys key, Object value) {
        scenarioContext.put(key.toString(), value);
    }

    public void removeKey(DataKeys key) {
        scenarioContext.remove(key);
    }

    public Object getContext(DataKeys key) {
        return scenarioContext.get(key.toString());
    }

    public Boolean isContains(DataKeys key) {
        return scenarioContext.containsKey(key.toString());
    }
}
