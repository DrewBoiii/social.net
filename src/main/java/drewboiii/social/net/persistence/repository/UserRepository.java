package drewboiii.social.net.persistence.repository;

import drewboiii.social.net.persistence.model.Usr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usr, Long> {

    Optional<Usr> findByUsername(String username);

}
