package drewboiii.social.net.controller;

import drewboiii.social.net.persistence.Message;
import drewboiii.social.net.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public List<Message> getMessages() {
        return messageService.getMessages();
    }

    @GetMapping("{id}")
    public Message getMessage(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    public Message saveMessage(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }

    @PutMapping("{id}")
    public Message putMessage(@PathVariable("id") Message message, @RequestBody Message editedMessage) {
        return messageService.editMessage(message, editedMessage);
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable("id") Message message) {
        messageService.deleteMessage(message);
    }

}
