package drewboiii.social.net.service;

import drewboiii.social.net.persistence.dto.user.UserRegistrationDto;
import drewboiii.social.net.persistence.model.Usr;

public interface UserService {

    Usr saveUser(UserRegistrationDto dto);

}
