package ed.abat.url_shortener.exception;

import lombok.Data;

@Data
public class ErrorResponse {

    private int statusCode;
    private String message;
    private String details;

    public ErrorResponse(int statusCode, String message, String details) {
        this.statusCode = statusCode;
        this.message = message;
        this.details = details;
    }

}
