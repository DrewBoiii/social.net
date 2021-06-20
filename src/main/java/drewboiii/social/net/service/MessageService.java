package drewboiii.social.net.service;

import drewboiii.social.net.persistence.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {

    Message getMessage(Long id);

    Message getMessage(String uid);

    List<Message> getMessages();

    @Deprecated
    Message saveMessage(Message message);

    Message saveMessage(Message message, String username);

    Message editMessage(Message actual, Message edited);

    void deleteMessage(Message message);

    void deleteMessage(Long id);

}
