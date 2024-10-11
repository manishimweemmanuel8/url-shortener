package ed.abat.url_shortener.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LongUrlBadRequestException extends RuntimeException {
    public LongUrlBadRequestException(String message) {
        super(message);
    }
}
