package io.github.orchy.example.action;

import io.github.orchy.example.model.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

@Component
public class EnhanceDataAction implements Function<Map<String, Object>, Map<String, Object>> {
    @Override
    public Map<String, Object> apply(Map<String, Object> input) {
        input.put("newKey", "newData");
        input.put("message", "sms message " + input.get("param3"));
        return input;
    }
}
