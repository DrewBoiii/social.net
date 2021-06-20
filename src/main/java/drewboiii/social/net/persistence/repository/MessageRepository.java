package drewboiii.social.net.persistence.repository;

import drewboiii.social.net.persistence.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<Message> getMessageByUid(String uid);

}
