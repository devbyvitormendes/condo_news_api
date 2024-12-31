package br.com.gravitech.condonews.exception.token;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class TokenCreationException extends RuntimeException {

    public TokenCreationException() {
        super("Erro ao gerar token.");
    }
}
