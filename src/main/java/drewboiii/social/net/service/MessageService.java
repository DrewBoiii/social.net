package drewboiii.social.net.service;

import drewboiii.social.net.persistence.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {

    List<Message> getMessages();

}
