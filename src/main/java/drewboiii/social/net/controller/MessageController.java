package drewboiii.social.net.controller;

import com.fasterxml.jackson.annotation.JsonView;
import drewboiii.social.net.exception.NotFoundException;
import drewboiii.social.net.persistence.model.Message;
import drewboiii.social.net.persistence.repo.MessageRepository;
import drewboiii.social.net.util.MessageViews;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageRepository messageRepository;

    @GetMapping
    @JsonView(MessageViews.Public.class)
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    @GetMapping("{id}")
    @JsonView(MessageViews.FullMessage.class)
    public Message getMessage(@PathVariable Long id) {
        return messageRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Message saveMessage(@RequestBody Message message) {
        message.setUid(UUID.randomUUID().toString());
        return messageRepository.save(message);
    }

    @PutMapping("{id}")
    public Message putMessage(@PathVariable(value = "id") Message oldMessage, @RequestBody Message newMessage) {
        BeanUtils.copyProperties(newMessage, oldMessage, "id");
        return messageRepository.save(oldMessage);
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageRepository.deleteById(id);
    }

}
