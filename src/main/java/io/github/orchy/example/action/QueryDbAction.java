package io.github.orchy.example.action;

import io.github.orchy.example.model.Address;
import io.github.orchy.example.model.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

@Component
public class QueryDbAction implements Function<Map<String, Object>, Map<String, Object>> {
    @Override
    public Map<String, Object> apply(Map<String, Object> input) {
        String userId = input.get("userId").toString();
        User user = mock(userId);
        return Map.of("user",user);
    }

    private User mock(String userId){
        Map<String, User> table = Map.of("1", new User("1", "user1", 20, "user1@gmail.com", "811111111", "MALE", new Address("NSW","AU"))
                , "2", new User("2", "user2", 40, "user2@gmail.com", "911111111", "FEMALE", new Address("NSW","AU")));
        return table.get(userId);
    }
}
