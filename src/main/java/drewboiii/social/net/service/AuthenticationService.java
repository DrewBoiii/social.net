package drewboiii.social.net.service;

import drewboiii.social.net.dto.auth.AuthenticationRequestDto;
import drewboiii.social.net.dto.auth.AuthenticationResponseDto;

public interface AuthenticationService {

    AuthenticationResponseDto authenticate(AuthenticationRequestDto authRequest);

}
