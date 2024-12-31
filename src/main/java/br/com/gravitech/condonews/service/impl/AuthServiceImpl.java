package br.com.gravitech.condonews.service.impl;

import br.com.gravitech.condonews.config.security.TokenService;
import br.com.gravitech.condonews.domain.User;
import br.com.gravitech.condonews.dto.auth.AuthRequestDto;
import br.com.gravitech.condonews.dto.auth.AuthResponseDto;
import br.com.gravitech.condonews.exception.base.BadRequestException;
import br.com.gravitech.condonews.service.AuthService;
import br.com.gravitech.condonews.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TokenService tokenService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponseDto authenticate(AuthRequestDto request) {
        User user = userService.findByUsername(request.username());
        if (passwordEncoder.matches(request.password(), user.getPassword())) {
            return tokenService.generateToken(user);
        } else {
            throw new BadRequestException();
        }
    }
}
