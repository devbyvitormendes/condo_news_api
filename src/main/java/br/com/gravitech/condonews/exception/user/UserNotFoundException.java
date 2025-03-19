package br.com.gravitech.condonews.exception.user;

import br.com.gravitech.condonews.domain.utils.StringConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super(StringConstants.Exception.USER_NOT_FOUND);
    }
}
