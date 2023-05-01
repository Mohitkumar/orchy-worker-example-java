package io.github.orchy.example.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class WhatsappAction implements Function<Map<String, Object>, Map<String, Object>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WhatsappAction.class);

    @Override
    public Map<String, Object> apply(Map<String, Object> input) {
        String to = input.get("phoneNumber").toString();
        String message = input.get("message").toString();
        LOGGER.info("sending whatsapp to {}, message {}", to, message);
        Map<String, Object> out = new HashMap<>();
        out.put("status", "success");
        return out;
    }
}
