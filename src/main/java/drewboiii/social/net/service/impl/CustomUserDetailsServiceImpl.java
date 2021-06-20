package drewboiii.social.net.service.impl;

import drewboiii.social.net.persistence.model.Role;
import drewboiii.social.net.persistence.model.Usr;
import drewboiii.social.net.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usr user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("%s was not found", username)));
        return buildUserDetails(user);
    }

    private UserDetails buildUserDetails(Usr user) {
        Set<Role> roles = user.getRoles();
        return new User(user.getUsername(), user.getPassword(), getMappedRolesToAuthorities(roles));
    }

    private Collection<? extends GrantedAuthority> getMappedRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

}
