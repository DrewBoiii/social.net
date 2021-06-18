package drewboiii.social.net.service.impl;

import drewboiii.social.net.exception.NotFoundException;
import drewboiii.social.net.persistence.model.Message;
import drewboiii.social.net.persistence.repository.MessageRepository;
import drewboiii.social.net.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public Message getMessage(Long id) {
        return messageRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Message getMessage(String uid) {
        return null;
    }

    @Override
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message saveMessage(Message message) {
        message.setUid(UUID.randomUUID().toString());
        return messageRepository.save(message);
    }

    @Override
    public Message editMessage(Message actual, Message edited) {
        BeanUtils.copyProperties(edited, actual, "id", "uid", "createdAt");
        return messageRepository.save(actual);
    }

    @Override
    public void deleteMessage(Message message) {
        messageRepository.delete(message);
    }

    @Override
    public void deleteMessage(Long id) {
        Message message = messageRepository.findById(id).orElseThrow(NotFoundException::new);
        messageRepository.delete(message);
    }
}
