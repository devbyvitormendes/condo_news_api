package br.com.gravitech.condonews.exception.condo;

import br.com.gravitech.condonews.domain.utils.StringConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CondoNotFoundException extends RuntimeException {

    public CondoNotFoundException() {
        super(StringConstants.Exception.CONDO_NOT_FOUND);
    }
}
