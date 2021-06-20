package drewboiii.social.net.service.impl;

import drewboiii.social.net.persistence.dto.user.UserRegistrationDto;
import drewboiii.social.net.persistence.model.Usr;
import drewboiii.social.net.persistence.repository.UserRepository;
import drewboiii.social.net.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Usr saveUser(UserRegistrationDto dto) {
        return userRepository.save(Usr.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build());
    }

}
