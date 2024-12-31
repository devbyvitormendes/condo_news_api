package br.com.gravitech.condonews.service;

import br.com.gravitech.condonews.dto.auth.AuthRequestDto;
import br.com.gravitech.condonews.dto.auth.AuthResponseDto;

public interface AuthService {
    AuthResponseDto authenticate(AuthRequestDto request);
}
