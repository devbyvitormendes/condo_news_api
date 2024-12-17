package br.com.gravitech.condonews.exception.news;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NewsNotFoundException extends RuntimeException {

    public NewsNotFoundException() {
        super("Notícia não encontrada.");
    }
}
