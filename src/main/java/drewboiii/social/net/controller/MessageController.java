package drewboiii.social.net.controller;

import com.fasterxml.jackson.annotation.JsonView;
import drewboiii.social.net.persistence.model.Message;
import drewboiii.social.net.service.MessageService;
import drewboiii.social.net.views.MessageViews;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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
    public ResponseEntity<?> saveMessage(@RequestBody Message message, @AuthenticationPrincipal User user) {
        // TODO: 6/20/2021 message dto
        Message saved = messageService.saveMessage(message, user.getUsername());
        return saved != null ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PutMapping("{id}")
    public Message putMessage(@PathVariable(value = "id") Message oldMessage, @RequestBody Message newMessage, @AuthenticationPrincipal User user) {
        // TODO: 6/20/2021 only author is able to delete message
        return messageService.editMessage(oldMessage, newMessage);
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable Long id, @AuthenticationPrincipal User user) {
        // TODO: 6/20/2021 only author and moder is able to delete message
        messageService.deleteMessage(id);
    }

}
