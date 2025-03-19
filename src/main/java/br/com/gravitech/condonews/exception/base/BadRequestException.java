package br.com.gravitech.condonews.exception.base;

import br.com.gravitech.condonews.domain.utils.StringConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super(StringConstants.Exception.BAD_REQUEST);
    }

    public BadRequestException(String message) {
        super(message);
    }
}
