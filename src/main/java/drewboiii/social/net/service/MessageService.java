package drewboiii.social.net.service;

import drewboiii.social.net.persistence.Message;

import java.util.List;

public interface MessageService {

    Message getMessage(Long id);

    Message getMessage(String uid);

    List<Message> getMessages();

    Message saveMessage(Message message);

    Message editMessage(Message actual, Message edited);

    void deleteMessage(Message message);


}
