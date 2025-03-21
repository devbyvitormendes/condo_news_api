package br.com.gravitech.condonews.controller.exception;

import br.com.gravitech.condonews.dto.ExceptionResponseDto;
import br.com.gravitech.condonews.exception.base.ForbiddenException;
import br.com.gravitech.condonews.exception.base.UnauthorizedException;
import br.com.gravitech.condonews.exception.condo.CondoNotFoundException;
import br.com.gravitech.condonews.exception.news.NewsNotFoundException;
import br.com.gravitech.condonews.exception.resident.ResidentNotFoundException;
import br.com.gravitech.condonews.exception.user.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UnauthorizedException.class, HttpClientErrorException.Unauthorized.class})
    public final ExceptionResponseDto handleUnauthorizedException(Exception ex) {
        logErrorMessage(ex);
        return new ExceptionResponseDto(401, ex.getMessage());
    }

    @ExceptionHandler({ForbiddenException.class, HttpClientErrorException.Forbidden.class})
    public final ExceptionResponseDto handleForbiddenException(Exception ex) {
        logErrorMessage(ex);
        return new ExceptionResponseDto(403, ex.getMessage());
    }

    @ExceptionHandler({CondoNotFoundException.class, NewsNotFoundException.class, ResidentNotFoundException.class, UserNotFoundException.class})
    public final ExceptionResponseDto handleNotFoundException(Exception ex) {
        logErrorMessage(ex);
        return new ExceptionResponseDto(404, ex.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public final ExceptionResponseDto defaultHandleException(Exception ex) {
        logErrorMessage(ex);
        return new ExceptionResponseDto(500,"Ocorreu um erro inesperado.");
    }

    private void logErrorMessage(Exception ex) {
        log.error("[ERROR] Error handling exception: {}", ex.getMessage());
    }
}
