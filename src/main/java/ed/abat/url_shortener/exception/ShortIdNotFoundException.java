package ed.abat.url_shortener.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShortIdNotFoundException extends RuntimeException {
    public ShortIdNotFoundException(String shortId) {
        super("Short URL not found for ID: " + shortId);
    }
}
