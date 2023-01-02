package io.github.orchy.example.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class SmsAction implements Function<Map<String, Object>, Map<String, Object>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsAction.class);

    @Override
    public Map<String, Object> apply(Map<String, Object> input) {
        String to = input.get("to").toString();
        String message = input.get("message").toString();
        LOGGER.info("sending sms to {}, message {}", to, message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Map<String, Object> out = new HashMap<>();
        out.put("status", "success");
        return out;
    }
}
