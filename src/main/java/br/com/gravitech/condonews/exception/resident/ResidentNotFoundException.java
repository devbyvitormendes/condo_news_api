package br.com.gravitech.condonews.exception.resident;

import br.com.gravitech.condonews.domain.utils.StringConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResidentNotFoundException extends RuntimeException {

    public ResidentNotFoundException() {
        super(StringConstants.Exception.RESIDENT_NOT_FOUND);
    }
}
