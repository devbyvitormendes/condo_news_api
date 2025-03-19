package br.com.gravitech.condonews.service;

import br.com.gravitech.condonews.dto.auth.AuthRequestDto;
import br.com.gravitech.condonews.dto.auth.AuthResponseDto;
import br.com.gravitech.condonews.dto.auth.RefreshRequestDto;

public interface AuthService {
    AuthResponseDto authenticate(AuthRequestDto request);
    AuthResponseDto refreshToken(RefreshRequestDto request);
    void logout(String refreshToken);
}
