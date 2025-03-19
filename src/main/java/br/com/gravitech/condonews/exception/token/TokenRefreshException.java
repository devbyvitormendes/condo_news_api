package br.com.gravitech.condonews.exception.token;

import br.com.gravitech.condonews.exception.base.BadRequestException;

public class TokenRefreshException extends BadRequestException {
    public TokenRefreshException(String message) {
        super(message);
    }
}
