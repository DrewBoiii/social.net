package drewboiii.social.net.controller;

import com.fasterxml.jackson.annotation.JsonView;
import drewboiii.social.net.persistence.model.Message;
import drewboiii.social.net.service.MessageService;
import drewboiii.social.net.util.MessageViews;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    @JsonView(MessageViews.Public.class)
    public List<Message> getMessages() {
        return messageService.getMessages();
    }

    @GetMapping("{id}")
    @JsonView(MessageViews.FullMessage.class)
    public Message getMessage(@PathVariable Long id) {
        return messageService.getMessage(id);
    }

    @PostMapping
    public Message saveMessage(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }

    @PutMapping("{id}")
    public Message putMessage(@PathVariable(value = "id") Message oldMessage, @RequestBody Message newMessage) {
        return messageService.editMessage(oldMessage, newMessage);
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
    }

}
