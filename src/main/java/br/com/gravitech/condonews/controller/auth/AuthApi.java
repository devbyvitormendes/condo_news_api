package br.com.gravitech.condonews.controller.auth;

import br.com.gravitech.condonews.dto.auth.AuthRequestDto;
import br.com.gravitech.condonews.dto.auth.AuthResponseDto;
import br.com.gravitech.condonews.dto.auth.RefreshRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Auth", description = "Authentication related operations")
public interface AuthApi {

    @Operation(summary = "Create Token", description = "Create a JWT Token.")
    AuthResponseDto createToken(@RequestBody AuthRequestDto request);

    @Operation(summary = "Refresh Token", description = "Refresh JWT Token.")
    AuthResponseDto refreshToken(@RequestBody RefreshRequestDto request);

}
