package br.com.gravitech.condonews.controller.auth;

import br.com.gravitech.condonews.dto.auth.AuthRequestDto;
import br.com.gravitech.condonews.dto.auth.AuthResponseDto;
import br.com.gravitech.condonews.dto.auth.RefreshRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Auth", description = "Authentication related operations")
public interface AuthApi {

    @Operation(summary = "Create Token", description = "Create a JWT Token.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully authenticated"),
        @ApiResponse(responseCode = "400", description = "Invalid credentials")
    })
    AuthResponseDto createToken(@RequestBody AuthRequestDto request);

    @Operation(summary = "Refresh Token", description = "Refresh JWT Token using refresh token.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Token successfully refreshed"),
        @ApiResponse(responseCode = "400", description = "Invalid or expired refresh token")
    })
    AuthResponseDto refreshToken(@RequestBody RefreshRequestDto request);

    @Operation(summary = "Logout", description = "Invalidate the current session and revoke refresh token.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully logged out"),
        @ApiResponse(responseCode = "400", description = "Invalid refresh token")
    })
    void logout();
}
