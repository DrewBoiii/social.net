package drewboiii.social.net.controller;

import drewboiii.social.net.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.*;

@RestController
@RequestMapping("message")
public class MessageController {

    private static final List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("uid", UUID.randomUUID().toString());
            put("id", "1");
            put("content", "message content");
        }});
        add(new HashMap<String, String>() {{
            put("uid", UUID.randomUUID().toString());
            put("id", "2");
            put("content", "message content");
        }});
        add(new HashMap<String, String>() {{
            put("uid", UUID.randomUUID().toString());
            put("id", "3");
            put("content", "message content");
        }});
    }};

    @GetMapping
    public List<Map<String, String>> getMessages() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getMessage(@PathVariable Long id) {
        return getMessageById(id);
    }

    @PostMapping
    public Map<String, String> saveMessage(@RequestBody Map<String, String> message) {
        message.put("uid", UUID.randomUUID().toString());
        message.put("id", String.valueOf(new SecureRandom().longs().filter(value -> value > 0).findFirst().orElse(-1)));
        messages.add(message);
        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> putMessage(@PathVariable Long id, @RequestBody Map<String, String> editedMessage) {
        Map<String, String> message = getMessageById(id);
        message.putAll(editedMessage);
        return message;
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable Long id) {
        Map<String, String> messageToDelete = getMessage(id);
        messages.remove(messageToDelete);
    }

    private Map<String, String> getMessageById(Long id) {
        return messages.stream()
                .filter(message -> message.get("id").contentEquals(id.toString()))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

}
