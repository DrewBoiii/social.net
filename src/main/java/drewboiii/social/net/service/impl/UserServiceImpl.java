package drewboiii.social.net.service.impl;

import com.google.common.collect.Sets;
import drewboiii.social.net.dto.user.UserRegistrationDto;
import drewboiii.social.net.exception.NotFoundException;
import drewboiii.social.net.persistence.model.Role;
import drewboiii.social.net.persistence.model.Usr;
import drewboiii.social.net.persistence.repository.RoleRepository;
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
    private final RoleRepository roleRepository;

    @Override
    public Usr saveUser(UserRegistrationDto dto) {
        Role role = roleRepository.findByName(Role.RoleName.USER).orElseThrow(NotFoundException::new);
        return userRepository.save(Usr.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(Sets.newHashSet(role))
                .build());
    }

}
