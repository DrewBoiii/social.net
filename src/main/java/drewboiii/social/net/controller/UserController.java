package drewboiii.social.net.controller;

import drewboiii.social.net.dto.user.UserRegistrationDto;
import drewboiii.social.net.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UserRegistrationDto dto) {
        userService.saveUser(dto);
        return ResponseEntity.ok().build();
    }

}
