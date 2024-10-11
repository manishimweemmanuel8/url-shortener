package ed.abat.url_shortener.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import ed.abat.url_shortener.dto.ShortUrlDTO;
import ed.abat.url_shortener.entity.ShortUrl;
import ed.abat.url_shortener.repository.ShortUrlRepository;

import org.assertj.core.api.Assertions;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShortUrlServiceTest {

    @Mock
    private ShortUrlRepository shortUrlRepository;

    @InjectMocks
    private ShortUrlServiceImp shortUrlServiceImp;

    private ShortUrlDTO request;

    @BeforeEach
    public void setup() {

        request = ShortUrlDTO.builder()
                .longUrl("https://www.abat.de/")
                // .shortId("sVc3")
                .build();
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    public void createShortUrlTest() {

       shortUrlServiceImp.createShortUrl(request);

        Assertions.assertThat(request);
    }

    @Test
    @Order(2)
    public void getLongUrlTest() {
        ShortUrl shortUrl = ShortUrl.builder()
                .longUrl("https://www.abat.de/")
                .shortId("sVc3")
                .build();
        when(shortUrlRepository.findByShortId(shortUrl.getShortId())).thenReturn(Optional.of(shortUrl));
        String longUrlResponse = shortUrlServiceImp.getLongUrl(shortUrl.getShortId());
        assertEquals(shortUrl.getLongUrl(), longUrlResponse);
    }

}
