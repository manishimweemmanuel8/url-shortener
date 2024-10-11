package ed.abat.url_shortener.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import ed.abat.url_shortener.dto.ShortUrlDTO;
import ed.abat.url_shortener.entity.ShortUrl;
import ed.abat.url_shortener.service.ShortUrlService;
import jakarta.servlet.http.HttpServletResponse;
// import io.swagger.annotations.Api;
// import io.swagger.annotations.ApiOperation;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/url")
public class ShortUrlController {

    @Autowired
    private ShortUrlService shortUrlService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    // @ApiOperation(value = "Execute POST method")
    public ResponseEntity<ShortUrl> shortenUrl(@RequestBody ShortUrlDTO request) {
        ShortUrl shortUrl = shortUrlService.createShortUrl(request);
        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping("/{shortId}")
    // @Operation(summary = "Get a long url by shortId")
    public ResponseEntity<Void> redirect(@PathVariable String shortId, HttpServletResponse response)
            throws IOException {
        String longUrl = shortUrlService.getLongUrl(shortId);
        response.sendRedirect(longUrl);
     
        return ResponseEntity.status(HttpStatus.FOUND).build();
    }

    @DeleteMapping("/{shortId}")
    // @Operation(summary = "Delete a shortUrl")
    public ResponseEntity<Void> deleteShortUrl(@PathVariable String shortId) {
        shortUrlService.deleteShortUrl(shortId);
        return ResponseEntity.noContent().build();
    }
}
