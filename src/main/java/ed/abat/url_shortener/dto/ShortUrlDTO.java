package ed.abat.url_shortener.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortUrlDTO {

    private String longUrl;
    private String shortId;
    private Long ttl;  // Time-to-live in seconds
    
}
