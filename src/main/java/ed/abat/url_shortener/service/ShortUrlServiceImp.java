package ed.abat.url_shortener.service;

import java.time.LocalDateTime;
import java.net.*;


import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ed.abat.url_shortener.dto.ShortUrlDTO;
import ed.abat.url_shortener.entity.ShortUrl;
import ed.abat.url_shortener.exception.LongUrlBadRequestException;
import ed.abat.url_shortener.exception.ShortIdAlreadyInUseException;
import ed.abat.url_shortener.exception.ShortIdNotFoundException;
import ed.abat.url_shortener.exception.ShortUrlExpiredException;
import ed.abat.url_shortener.repository.ShortUrlRepository;


@Service
public class ShortUrlServiceImp implements ShortUrlService {
    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @Value("${base_url}")
    private String BASE_URL;

    private static final Logger logger = LoggerFactory.getLogger(ShortUrlServiceImp.class);

    @Override
    public ShortUrl createShortUrl(ShortUrlDTO request) {

        String longUrl = request.getLongUrl();
        String shortId = request.getShortId();
        Long ttl = request.getTtl();
        // Check if longUrl exist
        if (longUrl.isEmpty()) {
            throw new LongUrlBadRequestException("Long url is required");
        }

        // Check if long url is valid but this is not check reachable cause you can generate short url before reachable or late can't reacheble 
        if(!isValidURL(longUrl)){
            throw new LongUrlBadRequestException("Invalid Long url"); 
        }


        // check if shortId exist
        if (shortId != null && shortUrlRepository.findByShortId(shortId).isPresent()) {
            logger.error("Short ID {} already in use.", shortId);
            throw new ShortIdAlreadyInUseException(shortId);
        }


        // Create new shortul
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setLongUrl(longUrl);
        shortUrl.setShortId(shortId != null ? shortId : generateShortId());
        shortUrl.setCreatedAt(LocalDateTime.now());

        // Set expiration date
        if (ttl != null) {
            shortUrl.setExpiryTime(LocalDateTime.now().plusSeconds(ttl));
        }

        if(shortUrl.getShortId().length() >4){
            throw new LongUrlBadRequestException("Short ID url is to long please enter at list 4 character");
        }


        String shortUrlResponse = BASE_URL + shortUrl.getShortId();

        logger.info("Creating short URL: {} -> {}", shortUrlResponse, longUrl);
        return shortUrlRepository.save(shortUrl);
    }

    @Override
    public String getLongUrl(String shortId) {


        // check exist
        ShortUrl shortUrl = shortUrlRepository.findByShortId(shortId)
                .orElseThrow(() -> new ShortIdNotFoundException(shortId));

        // Check expiration
        if (shortUrl.getExpiryTime() != null && shortUrl.getExpiryTime().isBefore(LocalDateTime.now())) {
            shortUrlRepository.delete(shortUrl);
            throw new ShortUrlExpiredException(shortId);
        }

        return shortUrl.getLongUrl();
    }

    @Override
    public void deleteShortUrl(String shortId) {
        ShortUrl shortUrl = shortUrlRepository.findByShortId(shortId)
                .orElseThrow(() -> new ShortIdNotFoundException(shortId));
        shortUrlRepository.delete(shortUrl);
    }

    public String generateShortId() {
        // Generate a random alphanumeric ID
        return RandomStringUtils.randomAlphanumeric(4);
    }

    boolean isValidURL(String url)  {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException e) {
            return false;
        } catch (URISyntaxException e) {
            return false;
        }
    }

}
