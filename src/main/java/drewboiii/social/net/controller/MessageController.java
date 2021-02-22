package drewboiii.social.net.controller;

import drewboiii.social.net.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("message")
public class MessageController {

    private static final List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", UUID.randomUUID().toString());
            put("content", "message content");
        }});
        add(new HashMap<String, String>() {{
            put("id", UUID.randomUUID().toString());
            put("content", "message content");
        }});
        add(new HashMap<String, String>() {{
            put("id", UUID.randomUUID().toString());
            put("content", "message content");
        }});
    }};

    @GetMapping
    public List<Map<String, String>> getMessages() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getMessage(@PathVariable Long id) {
        return messages.stream()
                .filter(message -> message.get("id").contentEquals(id.toString()))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> saveMessage(@RequestBody Map<String, String> message) {
        message.put("id", UUID.randomUUID().toString());
        messages.add(message);
        return message;
    }

}
