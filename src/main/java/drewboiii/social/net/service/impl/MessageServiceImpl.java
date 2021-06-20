package drewboiii.social.net.service.impl;

import drewboiii.social.net.exception.NotFoundException;
import drewboiii.social.net.persistence.model.BasedEntity;
import drewboiii.social.net.persistence.model.Message;
import drewboiii.social.net.persistence.model.Usr;
import drewboiii.social.net.persistence.repository.MessageRepository;
import drewboiii.social.net.persistence.repository.UserRepository;
import drewboiii.social.net.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Override
    public Message getMessage(Long id) {
        return messageRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Message getMessage(String uid) {
        return messageRepository.getMessageByUid(uid).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Message> getMessages() {
        // TODO: 6/20/2021 db filter
        return messageRepository.findAll().stream()
                .filter(message -> BasedEntity.Status.ACTIVE.equals(message.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message saveMessage(Message message, String username) {
        Usr user = userRepository.findByUsername(username).orElseThrow(NotFoundException::new);
        message.setAuthor(user);
        return messageRepository.save(message);
    }

    @Override
    public Message editMessage(Message actual, Message edited) {
        BeanUtils.copyProperties(edited, actual, Message.ID_ATTR, Message.UID_ATTR, Message.STATUS_ATTR, Message.CREATED_AT_ATTR);
        return messageRepository.save(actual);
    }

    @Override
    public void deleteMessage(Message message) {
        messageRepository.delete(message);
    }

    @Override
    public void deleteMessage(Long id) {
        Message message = messageRepository.findById(id).orElseThrow(NotFoundException::new);
        message.setStatus(BasedEntity.Status.DELETED);
        messageRepository.save(message);
    }
}
