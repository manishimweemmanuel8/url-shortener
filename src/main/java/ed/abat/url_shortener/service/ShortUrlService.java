package ed.abat.url_shortener.service;

import ed.abat.url_shortener.dto.ShortUrlDTO;
import ed.abat.url_shortener.entity.ShortUrl;

public interface ShortUrlService {

    ShortUrl createShortUrl(ShortUrlDTO request);

    String getLongUrl(String shortId);

    String generateShortId();

    void deleteShortUrl(String shortId);
}
