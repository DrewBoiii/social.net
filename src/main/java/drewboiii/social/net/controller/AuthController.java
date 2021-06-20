package drewboiii.social.net.controller;

import drewboiii.social.net.dto.auth.AuthenticationRequestDto;
import drewboiii.social.net.dto.auth.AuthenticationResponseDto;
import drewboiii.social.net.util.JwtUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.Optional;

@RestController
@RequestMapping("authentication")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    @Qualifier("customUserDetailsServiceImpl")
    private final UserDetailsService userDetailsService;

    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping
    public ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequestDto authRequest) throws AuthenticationException {
        // TODO: 6/18/2021 move to service
        Authentication authentication = Optional.ofNullable(
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authRequest.getUsername(),
                                authRequest.getPassword())))
                .orElseThrow(javax.naming.AuthenticationException::new);
        // TODO: 6/18/2021 extract from auth?
        String username = ((User) authentication.getPrincipal()).getUsername();
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String jwt = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponseDto(jwt));
    }

}
