package ed.abat.url_shortener.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.GONE)
public class ShortUrlExpiredException extends RuntimeException {
    public ShortUrlExpiredException(String shortUrl) {
        super("Short URL has expired for ID: " + shortUrl);
    }
}
