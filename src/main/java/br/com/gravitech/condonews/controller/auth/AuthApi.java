package br.com.gravitech.condonews.controller.auth;

import br.com.gravitech.condonews.domain.auth.AuthenticationRequest;
import br.com.gravitech.condonews.domain.auth.AuthenticationResponse;
import br.com.gravitech.condonews.dto.CondoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Tag(name = "Auth", description = "Authentication related operations")
public interface AuthApi {

    @Operation(summary = "Create Token", description = "Create a JWT Token.")
    AuthenticationResponse createToken(@RequestBody AuthenticationRequest request);

}
