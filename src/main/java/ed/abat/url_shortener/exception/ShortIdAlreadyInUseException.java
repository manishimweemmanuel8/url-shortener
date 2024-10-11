package ed.abat.url_shortener.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ShortIdAlreadyInUseException extends RuntimeException {
    public ShortIdAlreadyInUseException(String shortId) {
        super("Short ID already in use: " + shortId);
    }
}
