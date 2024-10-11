package ed.abat.url_shortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

        // Handle ShartIdNotFound exceptions
        @ExceptionHandler(ShortIdNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleShortUrlNotFoundException(ShortIdNotFoundException ex,
                        WebRequest request) {
                ErrorResponse errorResponse = new ErrorResponse(
                                HttpStatus.NOT_FOUND.value(),
                                ex.getMessage(),
                                request.getDescription(false));
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        // Handle ShartIdAlready exist exceptions
        @ExceptionHandler(ShortIdAlreadyInUseException.class)
        public ResponseEntity<ErrorResponse> handleShortUrlAlreadyExistException(ShortIdAlreadyInUseException ex,
                        WebRequest request) {
                ErrorResponse errorResponse = new ErrorResponse(
                                HttpStatus.CONFLICT.value(),
                                ex.getMessage(),
                                request.getDescription(false));
                return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }

        // handle LongUrl Bad Request Exception means its required
        @ExceptionHandler(LongUrlBadRequestException.class)
        public ResponseEntity<ErrorResponse> handleLongUrlBadRequestException(LongUrlBadRequestException ex,
                        WebRequest request) {
                ErrorResponse errorResponse = new ErrorResponse(
                                HttpStatus.BAD_REQUEST.value(),
                                ex.getMessage(),
                                request.getDescription(false));
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        // Handle ShortUrl Expired Exception
        @ExceptionHandler(ShortUrlExpiredException.class)
        public ResponseEntity<ErrorResponse> handleShortUrlExpiredException(ShortUrlExpiredException ex,
                        WebRequest request) {
                ErrorResponse errorResponse = new ErrorResponse(
                                HttpStatus.GONE.value(),
                                ex.getMessage(),
                                request.getDescription(false));
                return new ResponseEntity<>(errorResponse, HttpStatus.GONE);
        }

        // Handle global exceptions
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
                ErrorResponse errorResponse = new ErrorResponse(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                ex.getMessage(),
                                request.getDescription(false));
                return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
