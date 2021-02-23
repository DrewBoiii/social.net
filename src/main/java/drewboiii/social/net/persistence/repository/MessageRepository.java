package drewboiii.social.net.persistence.repository;

import drewboiii.social.net.persistence.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {


}
